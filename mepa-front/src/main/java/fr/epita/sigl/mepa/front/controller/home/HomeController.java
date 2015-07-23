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
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {
    protected static final String DATASETS_MODEL_ATTRIBUTE = "datasets";
    protected static final String THEME_FILTER_ATTRIBUTE = "themeFilter";
    private static final String SEARCH = "searchFormAction";
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private DataSetService dataSetService;

    @RequestMapping(value = {"/", "/home"})
    public String home(ModelMap modelMap) {

        // Get models data from database
        List<DataSet> datasets = this.dataSetService.getAllDataSets();
        // Update model attribute "datasets", to use it in JSP
        modelMap.addAttribute(DATASETS_MODEL_ATTRIBUTE, datasets);

        initFilter(modelMap, datasets);
        return "/home/home";
    }

    private void initFilter(ModelMap modelMap, List<DataSet> dataSets) {
        List<DataSet> allCartoDatasets = new ArrayList<>();
        List<DataSet> allGraphicDatasets = new ArrayList<>();
        getCartoAndGraphicDataset(allCartoDatasets, allGraphicDatasets, dataSets);
        modelMap.addAttribute("resFilterGraph", allGraphicDatasets.size());
        modelMap.addAttribute("resFilterCarto", allCartoDatasets.size());

        HashMap<String, Integer> themeMap = new HashMap<>();
        for (DataSet dataSet : dataSets) {
            String theme = dataSet.getTheme();
            if (themeMap.containsKey(theme)) {
                Integer numberOfOccurence = themeMap.get(theme);
                themeMap.replace(theme, numberOfOccurence, numberOfOccurence +1);
            } else {
                themeMap.put(theme, 1);
            }
        }
        modelMap.addAttribute(THEME_FILTER_ATTRIBUTE, themeMap);
    }

    private void getCartoAndGraphicDataset(List<DataSet> allCartoDatasets, List<DataSet> allGraphicDatasets, List<DataSet> dataSets) {
        for (DataSet dataSet : dataSets) {
            if (null != dataSet.getIsCarto() && dataSet.getIsCarto()){
                allCartoDatasets.add(dataSet);
            }
            if (null != dataSet.getIsGraphic() && dataSet.getIsGraphic()){
                allGraphicDatasets.add(dataSet);
            }
        }
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
