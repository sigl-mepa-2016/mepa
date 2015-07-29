package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/api")
    public String defaultPage() {
        return "/api/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "password", defaultValue = "") String password, HttpServletResponse response) {
        Pojo pojo = new UserController().getToken(name, password);
        if (pojo instanceof SuccessMessage) {
            response.addCookie(new Cookie("token", ((SuccessMessage) pojo).getMessage()));
            return "redirect:/";
        } else {
            return "login/erreur";
        }


    }

}
