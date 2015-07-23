package fr.epita.sigl.mepa.front.controller.search;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by emeline on 12/07/2015.
 * Controleur de la searchBar
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    //variables permettant d'êtres récupérées dans la page
    protected static final String MODELS_SEARCH_MODEL_ATTRIBUTE = "datasets";
    private static final String SEARCH = "searchFormAction";

    @Autowired
    private DataSetService modelService;


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

        // Get models data from database
        List<DataSet> dataSets = this.modelService.getAllDataSets();

        //lancement de l'algo de recherche
        List<DataSet> modelsResult = new ArrayList<>();

        modelsResult = searchMultiWord(dataSets, searchString, modelsResult);


        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, modelsResult);
        return "/home/home";
    }

    @RequestMapping(value = { "/FilterCarto" })
    public String getCarto(HttpServletRequest request, ModelMap modelMap) {
        List<DataSet> dataSets = this.modelService.getAllDataSets();
        List<DataSet> allCartoDatasets = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            if (dataSet.getIsCarto()){
                allCartoDatasets.add(dataSet);
            }
        }
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allCartoDatasets);
        return "/home/home";
    }


    @RequestMapping(value = { "/FilterGraphic" })
    public String getGraphic(HttpServletRequest request, ModelMap modelMap) {

        List<DataSet> dataSets = this.modelService.getAllDataSets();
        List<DataSet> allGraphicDatasets = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            if (dataSet.getIsGraphic()){
                allGraphicDatasets.add(dataSet);
            }
        }
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allGraphicDatasets);
        return "/home/home";
    }


    @RequestMapping(value = { "/themeFilter" })
    public String getThemeFilter(HttpServletRequest request, ModelMap modelMap) {
        String theme = request.getParameter("theme");
        List<DataSet> dataSets = this.modelService.getAllDataSets();
        List<DataSet> allThemeDatasets = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            if (dataSet.getTheme().equals(theme)){
                allThemeDatasets.add(dataSet);
            }
        }
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allThemeDatasets);
        return "/home/home";
    }

    @RequestMapping(value = { "/dateFilter" })
    public String getDateFilter(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        String yearChoose = request.getParameter("date");
        List<DataSet> dataSets = this.modelService.getAllDataSets();
        List<DataSet> allDateDatasets = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            Date lastModified = dataSet.getLastModified();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(lastModified);
            if (yearChoose.equals(date)){
                allDateDatasets.add(dataSet);
            }
        }
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allDateDatasets);
        return "/home/home";
    }
    /**
     * Fonction de recherche d'une string dans une autre string
     * @param models
     * @param searchString
     * @param modelResult
     * @return
     */
    private List<DataSet> searchMultiWord(List<DataSet> models, String searchString, List<DataSet> modelResult) {
        String[] searchStringList = searchString.split(" ");
        //Parcours de la liste de modèles
        for (DataSet model : models) {
            boolean isFind = false;
            //ici on récupère la data, lorsque on aura les bon modèles il faudra chercher dans le titre
            String data = model.getName();
            String owner = model.getOwner();
            String theme = model.getTheme();
            isFind = searchInOneField(data, searchStringList);
            if (isFind) {
                modelResult.add(model);
            }else {
                isFind = searchInOneField(owner, searchStringList);
                if (isFind) {
                    modelResult.add(model);
                }else {
                    isFind = searchInOneField(theme, searchStringList);
                    if (isFind) {
                        modelResult.add(model);
                    }
                }
            }
        }
        return modelResult;
    }

    private Boolean searchInOneField(String data, String[] searchStringList) {
        //on regarde si chaque mot existe dans le nom
        for (String word : searchStringList) {
            if (searchWord(data, word)){
                return true;
            }
        }
        return false;
    }

    private boolean searchWord(String data, String searchWord) {
        //séparation en token séparés par des espaces
        for (String s : data.split(" ")) {
            //recherche sans faire attention à la case
            if (s.toLowerCase().contains(searchWord.toLowerCase())) {
                return true;
            }
        }
        return false;
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

