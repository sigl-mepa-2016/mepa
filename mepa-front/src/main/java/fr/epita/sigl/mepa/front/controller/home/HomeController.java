package fr.epita.sigl.mepa.front.controller.home;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.model.search.Filter;
import fr.epita.sigl.mepa.front.model.search.SearchForm;
import fr.epita.sigl.mepa.front.model.search.SortForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {
    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    private static final String SEARCH = "searchFormAction";
    private static final String SORT = "sortFormAction";
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private DataSetService dataSetService;

    @RequestMapping(value = {"/", "/home"})
    public String home(HttpServletRequest request, ModelMap modelMap) {

        String errorMessage = request.getParameter("errorMessage");
        modelMap.addAttribute("errorMessage", errorMessage);

        // Get models data from database
        List<DataSet> datasets = this.dataSetService.getAllDataSets();
        // Update model attribute "datasets", to use it in JSP
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, datasets);
        Filter.initFilter(modelMap, datasets, "");
        return "/home/home";
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

    @ModelAttribute(SORT)
    public SortForm initSortForm() {
        return new SortForm();
    }
}
