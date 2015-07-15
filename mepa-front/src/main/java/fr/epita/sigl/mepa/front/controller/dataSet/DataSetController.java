package fr.epita.sigl.mepa.front.controller.dataSet;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dataSet")
@SessionAttributes({ DataSetController.DATASETS_MODEL_ATTRIBUTE })
public class DataSetController {

    private static final Logger LOG = LoggerFactory.getLogger(DataSetController.class);

    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";

    @Autowired
    private DataSetService dataSetService;

    @RequestMapping(value = {"/", "/form"})
    public String showForm(HttpServletRequest request, ModelMap modelMap) {

        // Get models data from database
        List<DataSet> datasets = this.dataSetService.getAllDataSets();
        if (LOG.isDebugEnabled()) {
            LOG.debug("There are {} datasets in database", datasets.size());
        }

        // Update model attribute "datasets", to use it in JSP
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, datasets);

        return "/dataSet/form";
    }

    /**
     * Initialize "datasets" model attribute
     *
     * @return an empty List of Datasets.
     */
    @ModelAttribute(DATASETS_MODEL_ATTRIBUTE)
    public List<DataSet> initDatasets() {
        return new ArrayList<DataSet>();
    }

}
