package fr.epita.sigl.mepa.front.controller.dataVisualisationTab;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Raphael on 15/07/2015.
 */
@Controller
@RequestMapping("/dataVisualisationTab")
public class DataVisualisationTabController {
    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    @Autowired
    private DataSetService dataSetService;


    @RequestMapping(value = {"/", "/dataVisualisationTab"})
    public String dataVisualisationTab() {
        return "/dataVisualisationTab/dataVisualisationTab";
    }
}
