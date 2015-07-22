package fr.epita.sigl.mepa.front.controller.dataSet;

import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataService;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.dataSet.AddCustomColumnFormBean;
import fr.epita.sigl.mepa.front.dataSet.AddCustomDataFormBean;
import fr.epita.sigl.mepa.front.dataSet.AddCustomDataSetFormBean;
import fr.epita.sigl.mepa.front.model.search.SearchForm;
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
import java.util.*;

@Controller
@RequestMapping("/dataSet")
@SessionAttributes({DataSetController.DATASETS_MODEL_ATTRIBUTE})
public class DataSetController {

    private static final Logger LOG = LoggerFactory.getLogger(DataSetController.class);

    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    protected static final String COLUMNS_MODEL_ATTRIBUTE = "columns";
    private static final String SEARCH = "searchFormAction";
    private static final String ADD_CUSTOM_DATASET_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomDataSetFormBean";
    private static final String ADD_CUSTOM_COLUMN_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomColumnFormBean";
    private static final String ADD_CUSTOM_DATA_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomDataFormBean";

    @Autowired
    private DataSetService dataSetService;

    @Autowired
    private DataService dataService;


    @RequestMapping(value = {"/form"})
    public String showForm(HttpServletRequest request, ModelMap modelMap) {

        // Get models data from database
        List<DataSet> datasets = this.dataSetService.getAllDataSets();
        if (LOG.isDebugEnabled()) {
            for (int i = 0; i < datasets.size(); ++i)
                LOG.debug("There is {} in database", datasets.get(i));
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
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddCustomDataSetFormBean addCustomDataSetFormBean,
                              BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/dataSet/form";
        }
        DataSet newDataSet = new DataSet();
        newDataSet.setName(addCustomDataSetFormBean.getName());
        newDataSet.setOwner(addCustomDataSetFormBean.getOwner());
        newDataSet.setTheme(addCustomDataSetFormBean.getTheme());
        this.dataSetService.createDataSet(newDataSet);

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute("datasets", allDataSets);

        modelMap.addAttribute("dataset", newDataSet);
        return "/home/home";
    }

    /**
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/details"})
    public String showDetails(HttpServletRequest request, ModelMap modelMap) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        modelMap.addAttribute("dataset", dataSet);
        modelMap.addAttribute("fieldKeys", dataSet.getFieldMap().keySet());

        return "/dataSet/details";
    }

    @RequestMapping(value = {"/delete"})
    public String showDelete(HttpServletRequest request, ModelMap modelMap) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        modelMap.addAttribute("dataset", dataSet);

        this.dataSetService.deleteDataSet(datasetId);
        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "/home/home";
    }

    @RequestMapping(value = {"/updateDatasetForm"})
    public String showUpdateDatasetForm(HttpServletRequest request, ModelMap modelMap) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        modelMap.addAttribute("dataset", dataSet);

        return "/dataSet/updateDatasetForm";
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public String processUpdateDatasetForm(HttpServletRequest request, ModelMap modelMap,
                                    @Valid AddCustomDataSetFormBean addCustomDataSetFormBean,
                                    BindingResult result) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        Map<String, String[]> paramMap = request.getParameterMap();

        dataSet.setName(addCustomDataSetFormBean.getName());
        dataSet.setTheme(addCustomDataSetFormBean.getTheme());
        dataSet.setOwner(addCustomDataSetFormBean.getOwner());
        this.dataSetService.updateDataSet(dataSet);

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "/home/home";
    }

    @RequestMapping(value = {"/columnForm"})
    public String showColumnForm(HttpServletRequest request, ModelMap modelMap) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        modelMap.addAttribute("dataset", dataSet);

        return "/dataSet/columnForm";
    }

    @RequestMapping(value = {"/addColumn"}, method = {RequestMethod.POST})
    public String processColumnForm(HttpServletRequest request, ModelMap modelMap,
                                    @Valid AddCustomColumnFormBean addCustomColumnFormBean,
                                    BindingResult result) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        Map<String, String[]> paramMap = request.getParameterMap();

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        String[] nameValues = paramMap.get("name");
        String[] typeValues = paramMap.get("type");

        if (nameValues.length != typeValues.length)
            return "/home/home";

        for (int i = 0; i < nameValues.length; ++i) {
            if (!nameValues[i].isEmpty() && !typeValues[i].isEmpty())
                dataSet.addField(nameValues[i], typeValues[i]);
        }
        this.dataSetService.updateDataSet(dataSet);
        modelMap.addAttribute("dataset", dataSet);

        allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "/home/home";
    }

    @RequestMapping(value = {"/dataForm"})
    public String showDataForm(HttpServletRequest request, ModelMap modelMap) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        modelMap.addAttribute("dataset", dataSet);
        modelMap.addAttribute("fieldKeys", dataSet.getFieldMap().keySet());

        return "/dataSet/dataForm";
    }

    @RequestMapping(value = {"/addData"}, method = {RequestMethod.POST})
    public String processDataForm(HttpServletRequest request, ModelMap modelMap,
                                    @Valid AddCustomDataFormBean addCustomDataFormBean,
                                    BindingResult result) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
        Map<String, String[]> paramMap = request.getParameterMap();

        String[] fieldsValues = paramMap.get("fields");
        Object[] fields = dataSet.getFieldMap().keySet().toArray();

        Data data = this.dataService.getById(datasetId);
        if (null != data)
        {
            for (int i = 0; i < fields.length; ++i) {
                String column = fields[i].toString();
                List<String> dataList = data.getData().get(column);
                dataList.add(fieldsValues[i]);
                data.getData().put(column, dataList);
            }
            this.dataService.updateData(data);
        } else {
            Map<String, List<String>> dataMap = new HashMap<>();
            for (int i = 0; i < fields.length; ++i) {
                List<String> value = new ArrayList<>();
                value.add(fieldsValues[i]);
                dataMap.put(fields[i].toString(), value);
            }
            Data toCreate = new Data(datasetId, dataMap);
            this.dataService.createData(toCreate);
        }

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "/home/home";
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

    @ModelAttribute(ADD_CUSTOM_COLUMN_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddCustomColumnFormBean initAddCustomColumnFormBean() {
        return new AddCustomColumnFormBean();
    }

    @ModelAttribute(ADD_CUSTOM_DATA_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddCustomDataFormBean initAddCustomDataFormBean() {
        return new AddCustomDataFormBean();
    }

    @ModelAttribute(SEARCH)
    public SearchForm initSearchForm() {
        return new SearchForm();
    }
}
