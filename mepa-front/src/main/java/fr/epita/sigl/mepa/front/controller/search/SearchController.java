package fr.epita.sigl.mepa.front.controller.search;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.model.search.Filter;
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
        List<DataSet> dataSets;
        // Get models data from database
        if (Filter.listDataset.isEmpty()){
            dataSets = this.modelService.getAllDataSets();
        } else {
            dataSets = Filter.listDataset;
        }

        //lancement de l'algo de recherche
        List<DataSet> modelsResult = new ArrayList<>();

        modelsResult = searchMultiWord(dataSets, searchString, modelsResult);
        Filter.listFilter.add("Search : " + searchString);
        Filter.initFilter(modelMap, modelsResult);
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, modelsResult);
        return "/home/home";
    }

    @RequestMapping(value = { "/FilterCarto" })
    public String getCarto(HttpServletRequest request, ModelMap modelMap) {
        List<DataSet> dataSets;
        // Get models data from database
        if (Filter.listDataset.isEmpty()){
            dataSets = this.modelService.getAllDataSets();
        } else {
            dataSets = Filter.listDataset;
        }

        List<DataSet> allCartoDatasets = new ArrayList<>();
        allCartoDatasets = Filter.CartoFilter(dataSets, allCartoDatasets);
        Filter.listFilter.add("View : Cartography");
        Filter.initFilter(modelMap, allCartoDatasets);
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allCartoDatasets);
        return "/home/home";
    }


    @RequestMapping(value = { "/FilterGraphic" })
    public String getGraphic(HttpServletRequest request, ModelMap modelMap) {
        List<DataSet> dataSets;
        // Get models data from database
        if (Filter.listDataset.isEmpty()){
            dataSets = this.modelService.getAllDataSets();
        } else {
            dataSets = Filter.listDataset;
        }

        List<DataSet> allGraphicDatasets = new ArrayList<>();
        allGraphicDatasets = Filter.GraphicFilter(dataSets, allGraphicDatasets);
        Filter.listFilter.add("View : Graphic");
        Filter.initFilter(modelMap, allGraphicDatasets);
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allGraphicDatasets);
        return "/home/home";
    }


    @RequestMapping(value = { "/themeFilter" })
    public String getThemeFilter(HttpServletRequest request, ModelMap modelMap) {
        String theme = request.getParameter("theme");

        List<DataSet> dataSets;
        // Get models data from database
        if (Filter.listDataset.isEmpty()){
            dataSets = this.modelService.getAllDataSets();
        } else {
            dataSets = Filter.listDataset;
        }

        List<DataSet> allThemeDatasets = new ArrayList<>();
        allThemeDatasets = Filter.ThemeFilter(dataSets, allThemeDatasets, theme);
        Filter.listFilter.add("Theme : " + theme);
        Filter.initFilter(modelMap, allThemeDatasets);
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allThemeDatasets);
        return "/home/home";
    }

    @RequestMapping(value = { "/dateFilter" })
    public String getDateFilter(HttpServletRequest request, ModelMap modelMap) throws ParseException {
        String yearChoose = request.getParameter("date");

        List<DataSet> dataSets;
        // Get models data from database
        if (Filter.listDataset.isEmpty()){
            dataSets = this.modelService.getAllDataSets();
        } else {
            dataSets = Filter.listDataset;
        }

        List<DataSet> allDateDatasets = new ArrayList<>();
        allDateDatasets = Filter.DateFilter(dataSets, allDateDatasets, yearChoose);
        Filter.listFilter.add("Date : " + yearChoose);
        Filter.initFilter(modelMap, allDateDatasets);
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, allDateDatasets);
        return "/home/home";
    }


    @RequestMapping(value = { "/Cancel" })
    public String getCancelFilter(HttpServletRequest request, ModelMap modelMap) {
        String filterToCancel = request.getParameter("CancelFilter");
        Filter.listFilter.remove(filterToCancel);
        List<DataSet> datasets = this.modelService.getAllDataSets();
        List<DataSet> newDataset = new ArrayList<>();

        for (String s : Filter.listFilter){
            LOG.debug("begin");
            newDataset.clear();
            for (DataSet d : datasets) {
                LOG.debug(d.getName());
            }
            if (s.contains("Search : ")){
                String searchString = s.substring(8);
                LOG.debug(searchString);
                newDataset.addAll(datasets);
                datasets = searchMultiWord(datasets, searchString, newDataset);

            }else if (s.contains("Date : ")){
                String date = s.substring(7);
                LOG.debug(date);
                datasets = Filter.DateFilter(datasets, newDataset, date);

            }
            else if (s.contains("Theme : ")){
                String theme = s.substring(8);
                LOG.debug(theme);
                datasets = Filter.ThemeFilter(datasets, newDataset, theme);

            }
            else if (s.contains("View : Graphic")){
                LOG.debug("Graphic");
                datasets = Filter.GraphicFilter(datasets, newDataset);

            }
            else if (s.contains("View : Cartography")){
                LOG.debug("Carto");
                datasets = Filter.CartoFilter(datasets, newDataset);
            }
            LOG.debug("after");
            for (DataSet d : datasets) {
                LOG.debug(d.getName());
            }
            LOG.debug("end");
        }

        Filter.initFilter(modelMap, datasets);
        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, datasets);
        return "/home/home";
    }

    @RequestMapping(value = { "/CancelAll" })
    public String getCancelAll(HttpServletRequest request, ModelMap modelMap) {
        List<DataSet> dataSets = this.modelService.getAllDataSets();
        Filter.listFilter.clear();
        Filter.initFilter(modelMap, dataSets);

        //mise a jour de la liste de models résultats
        modelMap.addAttribute(MODELS_SEARCH_MODEL_ATTRIBUTE, dataSets);
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

