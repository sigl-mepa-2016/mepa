package fr.epita.sigl.mepa.front.controller.dataVisualisationTab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Raphael on 15/07/2015.
 */
@Controller
@RequestMapping("/dataVisualisationTab")
public class dataVisualisationTab {
    @RequestMapping(value = {"/", "/dataVisualisationTab"})
    public String dataVisualisationTab() {
        return "/dataVisualisationTab/dataVisualisationTab";
    }
}
