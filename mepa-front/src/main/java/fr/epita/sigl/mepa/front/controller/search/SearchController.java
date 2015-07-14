package fr.epita.sigl.mepa.front.controller.search;

import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.service.ModelService;
import org.apache.commons.lang3.RandomStringUtils;
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
    protected static final String TABLE_NAME = "tableName";
    protected static final String SEARCHBAR_CONTENT = "searchBar";
    private static final String SEARCH = "searchFormAction";

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = { "/", "/search" })
    public String showSearch(HttpServletRequest request, ModelMap modelMap) {
        for (int i = 0; i < 15; i++) {
            createSearchModel();
        }
        // Get models data from database
        List<Model> models = this.modelService.getAllModels();
        LOG.info("There are {} models in database", models.size());
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, models);
        modelMap.addAttribute(TABLE_NAME, "List of all the models in database :");
        modelMap.addAttribute(SEARCHBAR_CONTENT, "search bar");
        return "/search/core/search";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = { "/searchAction" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, SearchForm parSearchForm, ModelMap modelMap) {
        String searchString = parSearchForm.getSearch();
        LOG.info(searchString);
        // Get models data from database
        List<Model> models = this.modelService.getAllModels();
        List<Model> modelsResult = new ArrayList<>();
        modelsResult = search(models, searchString, modelsResult);
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, modelsResult);

        modelMap.addAttribute(TABLE_NAME, "List of all the result of your search :");
        modelMap.addAttribute(SEARCHBAR_CONTENT, searchString);
        return "/search/core/search";
    }

    private List<Model> search(List<Model> models, String searchString, List<Model> modelResult) {

        for (Model model : models) {
            String data = model.getData();
            for (String s : data.split(" ")) {
                if (s.equalsIgnoreCase(searchString)) {
                    modelResult.add(model);
                    break;
                }
            }
        }
        LOG.info("search");
        return modelResult;
    }

    /**
     * Create a ramdom search model and add it in database.
     */
    private void createSearchModel() {
        Model newModel = new Model();
        newModel.setData(RandomStringUtils.randomAlphabetic(10) + " " + RandomStringUtils.randomAlphabetic(10));
        this.modelService.createModel(newModel);
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

