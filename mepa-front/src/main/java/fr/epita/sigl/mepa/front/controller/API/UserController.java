package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/token", method = RequestMethod.GET, params = {"name", "password"})
    public Pojo getToken(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        User user = userService.getByNameAndPassword(new User(name, password));
        return (user != null) ? new SuccessMessage("token: " + user.get_id().toString()) : new ErrorMessage("Invalid password or user");
    }

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
}
