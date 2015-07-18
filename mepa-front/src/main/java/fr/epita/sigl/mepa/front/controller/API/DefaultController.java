package fr.epita.sigl.mepa.front.controller.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Replace RestController by controler when web page welcome and help are done
//TODO: faire 2 pages web pour welcome et help
@Controller
@RequestMapping("/api")
public class DefaultController {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/")
    public String defaultPage()
    {
        return "/api/welcome";
    }
    
}
