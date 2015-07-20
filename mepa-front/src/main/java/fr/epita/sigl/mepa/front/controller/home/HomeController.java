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

        initFilter(modelMap);
        return "/home/home";
    }

    private void initFilter(ModelMap modelMap) {

        modelMap.addAttribute("resFilterGraph", "1");
        modelMap.addAttribute("resFilterCarto", "2");
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
