package fr.epita.sigl.mepa.front.controller.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {

    private static final Logger LOG = LoggerFactory.getLogger(DetailsController.class);

    @RequestMapping
    public Object detailsObject()
    {
        return new Object();
    }

}