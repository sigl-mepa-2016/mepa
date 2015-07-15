package fr.epita.sigl.mepa.front.controller.dataSet;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.dataSet.AddCustomDataSetFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dataSet")
@SessionAttributes({ DataSetController.DATASETS_MODEL_ATTRIBUTE })
public class DataSetController {

    private static final Logger LOG = LoggerFactory.getLogger(DataSetController.class);

    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    private static final String ADD_CUSTOM_DATASET_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomDataSetFormBean";

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
     * @param request
     * @param modelMap
     * @param addCustomDataSetFormBean
     * @param result
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddCustomDataSetFormBean addCustomDataSetFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/dataSet/form";
        }
        DataSet newDataSet = new DataSet();
        newDataSet.setName(addCustomDataSetFormBean.getName());
        newDataSet.setOwner(addCustomDataSetFormBean.getOwner());
        newDataSet.setTheme(addCustomDataSetFormBean.getTheme());
        this.dataSetService.createDataSet(newDataSet);

        modelMap.addAttribute("dataset", newDataSet);

        return "/dataSet/result";
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

    /**
     * Initialize "addCustomDataSetFormBean" model attribute
     *
     * @return a new AddCustomDataSetFormBean.
     */
    @ModelAttribute(ADD_CUSTOM_DATASET_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddCustomDataSetFormBean initAddCustomDataSetFormBean() {
        return new AddCustomDataSetFormBean();
    }
}
