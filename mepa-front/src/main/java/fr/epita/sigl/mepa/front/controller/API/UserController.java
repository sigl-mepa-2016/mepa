package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static String ADMIN_TOKEN = "507f191e810c19729de860ea";

    @Autowired
    private UserService userService;

    /**
     * get token with name and password
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET, params = {"name", "password"})
    public Pojo getToken(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        User user = userService.getByNameAndPassword(new User(name, password));
        return (user != null) ? new SuccessMessage("token: " + user.get_id().toString()) : new ErrorMessage("Invalid password or user");
    }

    /**
     * check if token is valid
     * @param token
     * @return
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.GET, params = "token")
    public Pojo checkToken(@RequestParam String token) {
        if (token.isEmpty())
            return new ErrorMessage("Missing Authentification");

        try {
            return (userService.getById(new ObjectId(token)) != null) ? new SuccessMessage("valid Token") : new ErrorMessage("Invalid Token");
        } catch (Exception e) {
            return new ErrorMessage("Invalid Token");
        }
    }

    /**
     *
     * @param inputUser
     * @param authorization
     * @return
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Pojo addUser(fr.epita.sigl.mepa.front.APIpojo.Impl.User inputUser, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {
        if (authorization.equals(ADMIN_TOKEN))
            return new ErrorMessage("Invalid Admin Token");
        this.userService.create(new User(inputUser.getName(), inputUser.getPassword()));
        return new SuccessMessage("Success Add");
    }
}
