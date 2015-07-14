package fr.epita.sigl.mepa.front.controller.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Replace RestController by controler when web page welcome and help are done
@RestController
public class DefaultController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/api")
    public String defaultPage()
    {
        return "/api/welcome";
    }

    @RequestMapping("/api/help")
    public String HelpPage()
    {
        return "/api/help";
    }

}
