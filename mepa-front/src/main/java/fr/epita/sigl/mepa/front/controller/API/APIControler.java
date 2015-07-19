package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.APIpojo.ListSimpleObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIControler {

    private static final Logger LOG = LoggerFactory.getLogger(APIControler.class);

    @Autowired
    private DataSetService dataSetService;

    @RequestMapping("/list")
    public ListSimpleObject list() {
        ListSimpleObject items = new ListSimpleObject();

        for (DataSet data : dataSetService.getAllDataSets())
            items.addSimpleObject(data.getId().intValue(), data.getName());

        return items;
    }

    @RequestMapping
    public Object detailsObject() {
        return new Object();
    }
}
