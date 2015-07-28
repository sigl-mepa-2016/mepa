package fr.epita.sigl.mepa.front.controller.dataVisualisationTab;

import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataService;
import fr.epita.sigl.mepa.core.service.DataSetService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Created by Raphael on 15/07/2015.
 */
@Controller
@RequestMapping("/dataVisualisationTab")
public class DataVisualisationTabController {
    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private DataService dataService;


    @RequestMapping(value = {"/", "/dataVisualisationTab"})
    public String dataVisualisationTab() {
        return "/dataVisualisationTab/dataVisualisationTab";
    }

    @RequestMapping(value = {"/customVisualisationTab"})
    public String customVisualisationTab(HttpServletRequest request, ModelMap modelMap) {
        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        Data data = this.dataService.getById(new ObjectId(datasetId));

        modelMap.addAttribute("dataset", dataSet);
        modelMap.addAttribute("fieldKeys", dataSet.getFieldMap().keySet());
        modelMap.addAttribute("data", data);

        return "/dataVisualisationTab/customVisualisationTab";
    }
    @RequestMapping(value = {"/dataVisualisation"})
    public String dataVisualisation() {
        return "/dataVisualisationTab/dataVisualisation";
    }
}
