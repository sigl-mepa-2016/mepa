package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.APIpojo.ListSimpleObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/dataSet")
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
    public Object schemaDataSet(@PathVariable Long dataSetID) {
       return dataSetService.getDataSetById(dataSetID);
    }

    /**
     * @return
     */
    @RequestMapping("/dataSet/add")
    public boolean addDataSet() {
        return true;
    }

    /**
     * @param dataSetID
     * @return
     */
    @RequestMapping("/dataSet/delete/{dataSetID}")
    public boolean deleteDataSet(@PathVariable Long dataSetID) {
        return true;
    }

    /**
     * Data of a specific DataSet
     *
     * @param dataSetID
     * @return
     */
    @RequestMapping("/dataSet/{dataSetID}/data")
<<<<<<< HEAD
    public Object detailsDataSet(@PathVariable Long dataSetID, @RequestParam Map<String,String> allRequestParams) {
        if (allRequestParams.isEmpty())
        {

        }
        else
        {

        }
=======
    public Object detailsDataSet(@PathVariable Long dataSetID) {
>>>>>>> add new controller
        return "datasetID = " + dataSetID;
    }
}
