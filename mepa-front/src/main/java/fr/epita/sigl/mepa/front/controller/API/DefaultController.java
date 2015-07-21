package fr.epita.sigl.mepa.front.controller.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/api")
    public String defaultPage()
    {
        return "/api/welcome";
    }
    
}
