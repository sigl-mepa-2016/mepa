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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeline on 12/07/2015.
 * Controleur de la searchBar
 */
@Controller
@RequestMapping("/search/core")
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    //variables permettant d'êtres récupérées dans la page
    protected static final String MODELS_SEARCH_MODEL_ATTRIBUTE = "models";
    protected static final String TABLE_NAME = "tableName";
    protected static final String SEARCHBAR_CONTENT = "searchBar";
    private static final String SEARCH = "searchFormAction";

    @Autowired
    private ModelService modelService;

    /**
     * Fonction appelée lors du chargement de la page d'accueil de la recherche
     * Génère 15 modèles avec un contenu random
     * @param request
     * @param modelMap variable permettant de transmettre des info dans la vue
     * @return le lien vers la page d'accueil de la recherche
     */
    @RequestMapping(value = { "/", "/search" })
    public String showSearch(HttpServletRequest request, ModelMap modelMap) {
       //creation de 15 model avec un contenu random
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
     * Fonction appelée lors du chargement du résultat de la reherche
     * @param request
     * @param parSearchForm mot recherché
     * @param modelMap
     * @return
     */
    @RequestMapping(value = { "/searchAction" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, SearchForm parSearchForm, ModelMap modelMap) {
        String searchString = parSearchForm.getSearch();
        LOG.info(searchString);
        // Get models data from database
        List<Model> models = this.modelService.getAllModels();

        //lancement de l'algo de recherche
        List<Model> modelsResult = new ArrayList<>();
        modelsResult = search(models, searchString, modelsResult);

        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, modelsResult);

        modelMap.addAttribute(TABLE_NAME, "List of all the result of your search :");
        modelMap.addAttribute(SEARCHBAR_CONTENT, searchString);

        return "/search/core/search";
    }

    /**
     * Fonction de recherche d'une string dans une autre string
     * @param models
     * @param searchString
     * @param modelResult
     * @return
     */
    private List<Model> search(List<Model> models, String searchString, List<Model> modelResult) {
        //Parcours de la liste de modèles
        for (Model model : models) {
            //ici on récupère la data, lorsque on aura les bon modèles il faudra chercher dans le titre
            String data = model.getData();

            //séparation en toker séparés par des espaces
            for (String s : data.split(" ")) {
                //recherche sans faire attention à la case
                if (s.equalsIgnoreCase(searchString)) {
                    modelResult.add(model);
                    break;
                }
            }
        }
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

