package fr.epita.sigl.mepa.front.controller.dataSet;

import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.domain.DataSetType;
import fr.epita.sigl.mepa.core.service.DataService;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import fr.epita.sigl.mepa.front.controller.API.UserController;
import fr.epita.sigl.mepa.front.dataSet.AddCustomColumnFormBean;
import fr.epita.sigl.mepa.front.dataSet.AddCustomDataFormBean;
import fr.epita.sigl.mepa.front.dataSet.AddCustomDataSetFormBean;
import fr.epita.sigl.mepa.front.model.search.SearchForm;
import fr.epita.sigl.mepa.front.model.search.SortForm;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.*;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/dataSet")
@SessionAttributes({DataSetController.DATASETS_MODEL_ATTRIBUTE})
public class DataSetController {

    private static final Logger LOG = LoggerFactory.getLogger(DataSetController.class);

    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    private static final String SEARCH = "searchFormAction";
    private static final String ADD_CUSTOM_DATASET_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomDataSetFormBean";
    private static final String ADD_CUSTOM_COLUMN_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomColumnFormBean";
    private static final String ADD_CUSTOM_DATA_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomDataFormBean";
    private static final String SORT = "sortFormAction";

    @Autowired
    private DataSetService dataSetService;

    @Autowired
    private DataService dataService;


    @RequestMapping(value = {"/form"})
    public String showForm(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        //@CookieValue(value = "token", defaultValue = "") String token,
        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/home/";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/home/";
        }

        // Get models data from database
        List<DataSet> datasets = this.dataSetService.getAllDataSets();
        if (LOG.isDebugEnabled()) {
            for (DataSet dataset : datasets)
                LOG.debug("There is {} in database", dataset);
        }

        // Update model attribute "datasets", to use it in JSP
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, datasets);

        return "/dataSet/form";
    }

    /**
     * @param modelMap
     * @param addCustomDataSetFormBean
     * @param result
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddCustomDataSetFormBean addCustomDataSetFormBean,
                              BindingResult result, RedirectAttributes redirectAttributes) {

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/home/";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/home/";
        }

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/dataSet/form";
        }
        DataSet newDataSet = new DataSet();
        newDataSet.setName(addCustomDataSetFormBean.getName());
        newDataSet.setOwner(addCustomDataSetFormBean.getOwner());
        newDataSet.setTheme(addCustomDataSetFormBean.getTheme());
        newDataSet.setIsCarto(addCustomDataSetFormBean.isCarto());
        newDataSet.setIsGraphic(addCustomDataSetFormBean.isGraphic());
        newDataSet.setLastModified(new Date());
        this.dataSetService.createDataSet(newDataSet);

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute("datasets", allDataSets);

        modelMap.addAttribute("dataset", newDataSet);

        return "redirect:/home/";
    }

    /**
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/details"})
    public String showDetails(HttpServletRequest request, ModelMap modelMap) {

        String datasetId = request.getParameter("datasetId");
        String errorMessage = request.getParameter("errorMessage");
        modelMap.addAttribute("errorMessage", errorMessage);
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        modelMap.addAttribute("dataset", dataSet);
        modelMap.addAttribute("fieldKeys", dataSet.getFieldMap().keySet());

        modelMap.addAttribute("size", 0);

        Data data = this.dataService.getById(new ObjectId(datasetId));
        if (null != data) {
            List<List<String>> dataList = new ArrayList<>();
            for (String column : data.getData().keySet()) {
                dataList.add(data.getData().get(column));
            }

            modelMap.addAttribute("dataList", dataList);
            modelMap.addAttribute("data", data);
            modelMap.addAttribute("size", dataList.get(0).size());
        }


        return "/dataSet/details";
    }

    @RequestMapping(value = {"/delete"})
    public String showDelete(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/home/";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/home/";
        }

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        modelMap.addAttribute("dataset", dataSet);

        this.dataSetService.deleteDataSet(new ObjectId(datasetId));
        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "redirect:/home/";
    }

    @RequestMapping(value = {"/deleteData"})
    public String showDeleteData(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));

        redirectAttributes.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        Map<String, String[]> paramMap = request.getParameterMap();
        modelMap.addAttribute("dataset", dataSet);

        Data data = this.dataService.getById(new ObjectId(datasetId));
        for (String column : data.getData().keySet()) {
            data.getData().get(column).remove(Integer.parseInt(paramMap.get("index")[0]));
        }
        this.dataService.updateData(data);

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "redirect:/dataSet/details";
    }

    @RequestMapping(value = {"/updateDatasetForm"})
    public String showUpdateDatasetForm(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/home/";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/home/";
        }

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        modelMap.addAttribute("dataset", dataSet);

        return "/dataSet/updateDatasetForm";
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public String processUpdateDatasetForm(HttpServletRequest request, ModelMap modelMap,
                                           @Valid AddCustomDataSetFormBean addCustomDataSetFormBean, RedirectAttributes redirectAttributes) {

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/home/";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/home/";
        }

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        Map<String, String[]> paramMap = request.getParameterMap();

        dataSet.setName(addCustomDataSetFormBean.getName());
        dataSet.setTheme(addCustomDataSetFormBean.getTheme());
        dataSet.setOwner(addCustomDataSetFormBean.getOwner());
        dataSet.setIsCarto(addCustomDataSetFormBean.isCarto());
        dataSet.setIsGraphic(addCustomDataSetFormBean.isGraphic());
        this.dataSetService.updateDataSet(dataSet);

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "redirect:/home/";
    }

    @RequestMapping(value = {"/updateDataForm"})
    public String showUpdateDataForm(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));

        redirectAttributes.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        Map<String, String[]> paramMap = request.getParameterMap();
        modelMap.addAttribute("dataset", dataSet);

        Data data = this.dataService.getById(new ObjectId(datasetId));
        Map<String, String> columnToValueMap = new LinkedHashMap<>();
        for (String column : data.getData().keySet()) {
            columnToValueMap.put(column, data.getData().get(column).get(Integer.parseInt(paramMap.get("index")[0])));
        }
        modelMap.addAttribute("columnToValueMap", columnToValueMap);
        modelMap.addAttribute("index", paramMap.get("index")[0]);

        return "/dataSet/updateDataForm";
    }

    @RequestMapping(value = {"/updateData"}, method = {RequestMethod.POST})
    public String processUpdateDataForm(HttpServletRequest request, ModelMap modelMap,
                                        @Valid AddCustomDataFormBean addCustomDataFormBean,
                                        RedirectAttributes redirectAttributes) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        Map<String, String[]> paramMap = request.getParameterMap();

        redirectAttributes.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        Data data = this.dataService.getById(new ObjectId(datasetId));
        String[] fieldsValues = paramMap.get("fields");
        Object[] fields = dataSet.getFieldMap().keySet().toArray();

        for (int i = 0; i < fields.length; ++i) {
            DataSetType type = DataSetType.valueOf(dataSet.getFieldMap().get(fields[i]));
            if (false == DataSetType.checkType(fieldsValues[i], type))
                return "redirect:/dataSet/details";
            String column = fields[i].toString();
            data.getData().get(column).remove(Integer.parseInt(paramMap.get("index")[0]));
            data.getData().get(column).add(Integer.parseInt(paramMap.get("index")[0]), fieldsValues[i]);
        }

        this.dataService.updateData(data);

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "redirect:/dataSet/details";
    }

    @RequestMapping(value = {"/columnForm"})
    public String showColumnForm(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirectAttributes) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        modelMap.addAttribute("dataset", dataSet);

        redirectAttributes.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        Map<String, String> typeValueList = new LinkedHashMap<>();
        for (DataSetType dataSetType : DataSetType.values())
            typeValueList.put(dataSetType.name(), dataSetType.name());
        modelMap.addAttribute("typeValueList", typeValueList);

        return "/dataSet/columnForm";
    }

    @RequestMapping(value = {"/addColumn"}, method = {RequestMethod.POST})
    public String processColumnForm(HttpServletRequest request, ModelMap modelMap,
                                    @Valid AddCustomColumnFormBean addCustomColumnFormBean,
                                    RedirectAttributes redirAttr) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        Map<String, String[]> paramMap = request.getParameterMap();

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        String[] nameValues = paramMap.get("name");
        String[] typeValues = paramMap.get("type");

        redirAttr.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirAttr.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        if (nameValues.length != typeValues.length)
            return "redirect:/dataSet/details";

        for (int i = 0; i < nameValues.length; ++i) {
            if (!nameValues[i].isEmpty() && !typeValues[i].isEmpty())
                dataSet.addField(nameValues[i], typeValues[i]);
        }
        this.dataSetService.updateDataSet(dataSet);
        modelMap.addAttribute("dataset", dataSet);

        allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "redirect:/dataSet/details";
    }

    @RequestMapping(value = {"/dataForm"})
    public String showDataForm(HttpServletRequest request, ModelMap modelMap, RedirectAttributes redirAttr) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));

        redirAttr.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirAttr.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        modelMap.addAttribute("dataset", dataSet);
        modelMap.addAttribute("fieldKeys", dataSet.getFieldMap().keySet());

        return "/dataSet/dataForm";
    }

    @RequestMapping(value = {"/addData"}, method = {RequestMethod.POST})
    public String processDataForm(HttpServletRequest request, ModelMap modelMap,
                                  @Valid AddCustomDataFormBean addCustomDataFormBean,
                                  RedirectAttributes redirAttr) {

        String datasetId = request.getParameter("datasetId");
        DataSet dataSet = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        Map<String, String[]> paramMap = request.getParameterMap();

        redirAttr.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirAttr.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        String[] fieldsValues = paramMap.get("fields");
        Object[] fields = dataSet.getFieldMap().keySet().toArray();

        Data data = this.dataService.getById(new ObjectId(datasetId));
        if (null != data) {
            for (int i = 0; i < fields.length; ++i) {
                DataSetType type = DataSetType.valueOf(dataSet.getFieldMap().get(fields[i]));
                if (false == DataSetType.checkType(fieldsValues[i], type))
                    return "redirect:/dataSet/details";
                String column = fields[i].toString();
                List<String> dataList = data.getData().get(column);
                dataList.add(fieldsValues[i]);
                data.getData().put(column, dataList);
            }
            this.dataService.updateData(data);
        } else {
            Map<String, List<String>> dataMap = new LinkedHashMap<>();
            for (int i = 0; i < fields.length; ++i) {
                DataSetType type = DataSetType.valueOf(dataSet.getFieldMap().get(fields[i]));
                if (false == DataSetType.checkType(fieldsValues[i], type))
                    return "redirect:/dataSet/details";
                List<String> value = new ArrayList<>();
                value.add(fieldsValues[i]);
                dataMap.put(fields[i].toString(), value);
            }
            Data toCreate = new Data(datasetId, dataMap);
            this.dataService.createData(toCreate);
        }

        List<DataSet> allDataSets = this.dataSetService.getAllDataSets();
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, allDataSets);

        return "redirect:/dataSet/details";
    }

    @RequestMapping(value = {"/uploadCSV"}, method = {RequestMethod.POST})
    public String uploadCSV(HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException, ServletException {

        Map<String, String[]> paramMap = request.getParameterMap();
        String datasetId = paramMap.get("datasetId")[0];

        redirectAttributes.addAttribute("datasetId", datasetId);

        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        for (Cookie c : cookies)
            if (c.getName().equals("token"))
                token = c;

        if (token != null) {
            /*Pojo resultAuthorization = new UserController().checkToken(token.getValue());
            if (resultAuthorization instanceof ErrorMessage)
                return "redirect:/dataSet/details";*/
        } else {
            redirectAttributes.addAttribute("errorMessage", "You must be registered!");
            return "redirect:/dataSet/details";
        }

        DataSet dataset = this.dataSetService.getDataSetById(new ObjectId(datasetId));
        Data data = this.dataService.getById(new ObjectId(datasetId));

        Part part = request.getPart("file");
        InputStream fileContent = part.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        String fieldlist = reader.readLine();
        String[] fields;

        if (null != fieldlist)
            fields = fieldlist.split(";");
        else
            return "redirect:/dataSet/details";

        Object[] datasetFields = dataset.getFieldMap().keySet().toArray();

        if (datasetFields.length == 0) {
            for (String field : fields) {
                if (!field.contains("#"))
                    return "redirect:/dataSet/details";
                else
                    if (field.split("#")[1].equals("TEXT") || field.split("#")[1].equals("NUMBER"))
                        dataset.addField(field.split("#")[0], field.split("#")[1]);
                    else
                        return "redirect:/dataSet/details";
            }
            this.dataSetService.updateDataSet(dataset);
            dataset = this.dataSetService.getDataSetById(new ObjectId(datasetId));
            datasetFields = dataset.getFieldMap().keySet().toArray();
        }

        for (int i = 0; i < fields.length; ++i) {
            String fieldCSV = fields[i].split("#")[0];
            String typeCSV = fields[i].split("#")[1];
            String fieldDataset = (String) datasetFields[i];
            String typeDataset = dataset.getFieldMap().get(fieldDataset);
            if (!fieldCSV.equals(fieldDataset) && !typeCSV.equals(typeDataset))
                return "redirect:/dataSet/details";
        }

        String line;
        while (null != (line = reader.readLine())) {
            boolean isCorrect = true;
            // Read CSV + update/create data
            if (null != data) {
                for (int i = 0; i < fields.length; ++i) {
                    String field = fields[i].split("#")[0];
                    List<String> dataList = data.getData().get(field);
                    if (null == dataList)
                        dataList = new ArrayList<>();
                    String[] dataSplit = line.split(";");

                    DataSetType type = DataSetType.valueOf(dataset.getFieldMap().get(field));
                    if (false == DataSetType.checkType(dataSplit[i], type))
                        isCorrect = false;

                    dataList.add(dataSplit[i]);
                    data.getData().put(field, dataList);
                }
                if (isCorrect)
                    this.dataService.updateData(data);
                else
                    data = this.dataService.getById(new ObjectId(datasetId));
            } else {
                Map<String, List<String>> dataMap = new LinkedHashMap<>();
                for (int i = 0; i < fields.length; ++i) {
                    List<String> value = new ArrayList<>();
                    String[] dataSplit = line.split(";");

                    DataSetType type = DataSetType.valueOf(dataset.getFieldMap().get(fields[i].split("#")[0]));
                    if (false == DataSetType.checkType(dataSplit[i], type))
                        isCorrect = false;

                    value.add(dataSplit[i]);
                    dataMap.put(fields[i].split("#")[0], value);
                }
                if (isCorrect) {
                    Data toCreate = new Data(datasetId, dataMap);
                    this.dataService.createData(toCreate);
                    data = toCreate;
                }
            }
        }
        reader.close();
        fileContent.close();

        return "redirect:/dataSet/details";
    }

    @ModelAttribute(DATASETS_MODEL_ATTRIBUTE)
    public List<DataSet> initDatasets() {
        return new ArrayList<DataSet>();
    }

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

    @ModelAttribute(SORT)
    public SortForm initSortForm() {
        return new SortForm();
    }


}
