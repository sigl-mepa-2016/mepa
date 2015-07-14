package fr.epita.sigl.mepa.front.controller.search;

import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.epita.sigl.mepa.front.model.search.SearchForm;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeline on 12/07/2015.
 */
@Controller
@RequestMapping("/search/core")
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    protected static final String MODELS_SEARCH_MODEL_ATTRIBUTE = "models";
    private static final String SEARCH = "searchFormAction";

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = { "/", "/search" })
    public String showSearch(HttpServletRequest request, ModelMap modelMap) {
        /*for (int i = 0; i < 15; i++) {
            createSearchModel();
        }
        // Get models data from database
        List<Model> models = this.modelService.getAllModels();
        LOG.info("There are {} models in database", models.size());
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, models);
        */
        return "/search/core/search";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = { "/searchAction" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, SearchForm parSearchForm) {
        String searchString = parSearchForm.getSearch();
        LOG.info(searchString);
        // Get models data from database
        //List<Model> models = this.modelService.getAllModels();
        //search(models, searchString);
        return "/search/core/result";
    }

    private List<Model> search(List<Model> models, String searchString) {
        /*Integer i = 0;
        for (Model model : models) {
            LOG.info(i.toString());
            i++;
        }*/
        LOG.info("search");
        return null;
    }

    /**
     * Create a ramdom search model and add it in database.
     */
    private void createSearchModel() {
        /*SearchModel searchModel = new SearchModel();
        RandomStringUtils stringUtils = new RandomStringUtils();
        String title = stringUtils.randomAlphabetic(3) + " " + stringUtils.randomAlphabetic(15);
        searchModel.setTitle(title);
        List<String> content = new ArrayList<>(5);
        for (int j = 0; j < 5; j++) {
            content.set(j, stringUtils.randomAlphabetic(15));
        }
        searchModel.setContent(content);
        this.modelService.createModel(searchModel);*/
        LOG.info("createSearchModel");
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

/**
 * Created by emeline on 13/07/2015.
 */
/*
@Entity
public class SearchModel extends Model{

    @NotNull
    private List<String> content;

    @NotNull
    private String title;

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}*/
