package fr.epita.sigl.mepa.front.controller.cartography;

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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cartography/core")
public class CartographyController {

    private static final Logger LOG = LoggerFactory.getLogger(CartographyController.class);

    @Autowired
    private DataSetService dataSetService;

    @Autowired
    private DataService dataService;

    @RequestMapping(value = { "/", "/map" })
    public String showMap(HttpServletRequest request, ModelMap modelMap)
    {
        String datasetId = request.getParameter("datasetId");
        modelMap.addAttribute("datasetId", datasetId);

        System.out.println("datasetId : " + datasetId);

        return "/cartography/core/map";
    }
}
