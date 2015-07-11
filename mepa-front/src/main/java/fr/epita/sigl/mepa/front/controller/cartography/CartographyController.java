package fr.epita.sigl.mepa.front.controller.cartography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cartography/core")
public class CartographyController {

    private static final Logger LOG = LoggerFactory.getLogger(CartographyController.class);


    @RequestMapping(value = { "/", "/map" })
    public String showMap(HttpServletRequest request)
    {
        return "/cartography/core/map";
    }
}
