package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.APIpojo.ListSimpleObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    private static final Logger LOG = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private DataSetService dataSetService;

    /**
     * List of DataSet in database
     *
     * @return
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.GET)
    public ListSimpleObject listDataSet() {
        ListSimpleObject items = new ListSimpleObject();

        for (DataSet data : dataSetService.getAllDataSets())
            items.addSimpleObject(data.get_id(), data.getName());

        return items;
    }

    /**
     * schema of specific DataSet
     *
     * @param dataSetID
     * @return
     */
    @RequestMapping("/dataSet/{dataSetID}")
    public DataSet schemaDataSet(@PathVariable String dataSetID) {
        LOG.debug("get dataSet details id: {}", dataSetID);
        DataSet toto =  dataSetService.getDataSetById(dataSetID);
        return toto;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.PUT)
    public boolean addDataSet() {
        return true;
    }

    /**
     * @param dataSetID
     * @return
     */
    @RequestMapping(value = "/dataSet/{dataSetID}", method = RequestMethod.DELETE)
    public boolean deleteDataSet(@PathVariable String dataSetID) {
        return true;
    }

    @RequestMapping(value = "/dataSet/{dataSetID}/data", method = RequestMethod.GET)
    public Object detailsDataSet(@PathVariable String dataSetID) {

        return "datasetID = " + dataSetID;
    }


    @RequestMapping(value = "/dataSet/{dataSetID}/specificData", method = RequestMethod.GET)
    public Object detailsSpecificDataSet(@PathVariable String dataSetID, @RequestParam Map<String, String> allRequestParams) {

        if (allRequestParams.isEmpty()) {

        } else {

        }
        return "datasetID = " + dataSetID;
    }


}
