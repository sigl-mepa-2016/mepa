package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.front.pojo.ClassicObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NameController {

    private static final Logger LOG = LoggerFactory.getLogger(NameController.class);

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


    @RequestMapping
    public List<ClassicObject> list()
    {
        List<ClassicObject> retunList = new ArrayList<>();

        return retunList;
    }

}
