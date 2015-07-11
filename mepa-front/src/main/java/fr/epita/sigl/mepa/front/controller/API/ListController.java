package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.front.APIpojo.ListSimpleObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    private static final Logger LOG = LoggerFactory.getLogger(ListController.class);

    @RequestMapping("/api/list")
    public ListSimpleObject list()
    {
        ListSimpleObject items = new ListSimpleObject();

        items.addSimpleObject("1", "toto");
        items.addSimpleObject("2", "toto");
        items.addSimpleObject("3", "toto");
        items.addSimpleObject("4", "toto");
        items.addSimpleObject("5", "toto");
        items.addSimpleObject("6", "toto");
        items.addSimpleObject("7", "toto");

        return items;
    }
}
