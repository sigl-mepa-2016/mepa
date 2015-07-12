package fr.epita.sigl.mepa.front.controller.dataVisualisation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Robin on 09/07/2015.
 */


@Controller
@RequestMapping("/dataVisualisation")
public class DataVisualisationController {

    @RequestMapping(value = {"/", "/dataVisualisation"})
    public String dataVisualisation() {
        return "/dataVisualisation/dataVisualisation";
    }

}
