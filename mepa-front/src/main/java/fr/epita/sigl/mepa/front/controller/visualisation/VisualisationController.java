package fr.epita.sigl.mepa.front.controller.visualisation;

import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataService;
import fr.epita.sigl.mepa.core.service.DataSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/visualisation")

public class VisualisationController {

    private static final Logger LOG = LoggerFactory.getLogger(VisualisationController.class);

    @Autowired
    private DataSetService dataSetService;

    @Autowired
    private DataService dataService;

    @RequestMapping(value = {"/", "/home"})
    public String getDataSetId(HttpServletRequest request, ModelMap modelMap)
    {
        try {

            String datasetId = request.getParameter("datasetId");
            String dataType = request.getParameter("dataType");
            int data_type = Integer.parseInt(dataType);
            DataSet dataSet = this.dataSetService.getDataSetById(datasetId);
            Data data = this.dataService.getById(datasetId);
            List<List<String>> dataList = new ArrayList<>();
            for (String column : data.getData().keySet()) {
                dataList.add(data.getData().get(column));
            }

            // par defaut, toutes les 10 premières
            int count = 0;
            List<String> active_col = new ArrayList<>();
            for (String column : dataSet.getFieldMap().keySet()){
                active_col.add(column);
            }

            while (active_col.size() > 10) {
                active_col.remove(active_col.size() - 1);
                System.out.println("column removed");
            }

            /*System.out.println(active_col);*/
            modelMap.addAttribute("dataset", dataSet);
            modelMap.addAttribute("fieldKeys", active_col);
            modelMap.addAttribute("data", data);
            modelMap.addAttribute("fieldValues", data.getData());
            modelMap.addAttribute("size", dataList.get(0).size() - 1);
            modelMap.addAttribute("viewType", data_type);
            System.out.println("data type = " + dataType);
            // add attrivute list<"nom_colonne", bool:actif/inactif>

        } catch(Exception E) {

        }
        return "/visualisation/home";
    }

}
