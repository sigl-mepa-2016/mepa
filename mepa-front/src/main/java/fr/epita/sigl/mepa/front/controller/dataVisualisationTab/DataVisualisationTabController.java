package fr.epita.sigl.mepa.front.controller.dataVisualisationTab;

import fr.epita.sigl.mepa.core.domain.DataSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Raphael on 15/07/2015.
 */
@Controller
@RequestMapping("/dataVisualisationTab")
public class DataVisualisationTabController {
    @RequestMapping(value = {"/", "/dataVisualisationTab"})
    public String dataVisualisationTab() {
        return "/dataVisualisationTab/dataVisualisationTab";
    }
}
