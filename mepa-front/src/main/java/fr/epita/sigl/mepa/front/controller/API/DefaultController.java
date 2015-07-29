package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/api")
    public String defaultPage() {
        return "/api/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@RequestParam(value = "inputEmail", defaultValue = "") String name, @RequestParam(value = "inputPassword", defaultValue = "") String password, HttpServletResponse response) {
        LOG.debug("{}, {}", name, password);
        Pojo pojo = getToken(name, password);
        if (pojo instanceof SuccessMessage) {
            response.addCookie(new Cookie("token", ((SuccessMessage) pojo).getMessage()));
            return "redirect:/";
        } else {
            return "login/erreur";
        }
    }

    private Pojo checkToken( String token) {
        if (token.isEmpty())
            return new ErrorMessage("Missing Authentification");

        try {
            return (token.equals(UserController.ADMIN_TOKEN) || (userService.getById(new ObjectId(token)) != null)) ? new SuccessMessage("valid Token") : new ErrorMessage("Invalid Token");
        } catch (Exception e) {
            return new ErrorMessage("Invalid Token");
        }
    }

    private Pojo getToken(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {

        User user = userService.getByNameAndPassword(new User(name, password));
        return (user != null) ? new SuccessMessage(user.get_id().toString()) : new ErrorMessage("Invalid password or user");
    }

}
