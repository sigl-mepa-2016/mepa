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
    public ListSimpleObject list()
    {


        ListSimpleObject items = new ListSimpleObject();

        for(DataSet data: dataSetService.getAllDataSets())
            items.addSimpleObject(data.getId().toString(), data.getName());
        return items;
    }

    @RequestMapping("/listexemple")
    public ListSimpleObject listexemple()
    {
        ListSimpleObject items = new ListSimpleObject();
        items.addSimpleObject("1", "toto");
        items.addSimpleObject("2", "toto");
        items.addSimpleObject("3", "toto");
        items.addSimpleObject("4", "toto");
        items.addSimpleObject("5", "toto");
        items.addSimpleObject("6", "toto");
        items.addSimpleObject("7", "toto");

        return items;
    }

    @RequestMapping
    public Object detailsObject()
    {
        return new Object();
    }
}
