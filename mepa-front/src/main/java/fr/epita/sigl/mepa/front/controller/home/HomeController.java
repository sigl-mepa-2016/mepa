package fr.epita.sigl.mepa.front.controller.home;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.model.search.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    private static final String SEARCH = "searchFormAction";
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private DataSetService dataSetService;

    @RequestMapping(value = {"/", "/home"})
    public String home(ModelMap modelMap) {
        initDatasets(modelMap);
        return "/home/home";
    }

    private void initDatasets(ModelMap modelMap) {
        DataSet newDataSet = new DataSet();
        newDataSet.setName("Etudiants SIGL 2015");
        newDataSet.setOwner("SIGL");
        newDataSet.setTheme("Etudiants");
        this.dataSetService.createDataSet(newDataSet);


        DataSet newDataSet2 = new DataSet();
        newDataSet2.setName("Etudiants TCOM 2015");
        newDataSet2.setOwner("TCOM");
        newDataSet2.setTheme("Etudiants");
        this.dataSetService.createDataSet(newDataSet2);


        DataSet newDataSet3 = new DataSet();
        newDataSet3.setName("Cours SIGL 2015");
        newDataSet3.setOwner("SIGL");
        newDataSet3.setTheme("Cours");
        this.dataSetService.createDataSet(newDataSet3);
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, this.dataSetService.getAllDataSets());
    }



    /**
     * Initialize "SearchForm" model attribute
     *
     * @return a new SearchForm.
     */
    @ModelAttribute(SEARCH)
    public SearchForm initSearchForm() {
        return new SearchForm();
    }
}
