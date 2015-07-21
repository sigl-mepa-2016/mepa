package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataService;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ListSimpleDataSet;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    private static final Logger LOG = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private DataSetService dataSetService;

    @Autowired
    private DataService dataService;

    /**
     * List of DataSet in database
     *
     * @return
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.GET)
    public Pojo listDataSet() {
        ListSimpleDataSet items = new ListSimpleDataSet();

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
    public Pojo schemaDataSet(@PathVariable String dataSetID) {
        DataSet dataSet;
        try {
            dataSet = dataSetService.getDataSetById(dataSetID);
        } catch (IllegalArgumentException e) {
            return new ErrorMessage("Invalid ID");
        }
        if (dataSet == null)
            return new ErrorMessage("No DataSet found");
        else
            return new fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet(dataSet.get_id(), dataSet.getName(), dataSet.getOwner(), dataSet.getTheme(), dataSet.getLastModified(), dataSet.getIsCarto(), dataSet.getIsGraphic(), dataSet.getFieldMap());
    }

    /**
     * Specific application/json in Content-type
     *
     * @param dataSet
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.POST)
    public Pojo addDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet dataSet) {

        DataSet newdataSet = new DataSet();
        newdataSet.setName(dataSet.getName());
        newdataSet.setOwner(dataSet.getOwner());
        newdataSet.setTheme(dataSet.getTheme());
        newdataSet.setLastModified(new Date());
        for (Map.Entry<String, String> entri : dataSet.getFieldMap().entrySet())
            newdataSet.addField(entri.getKey(), entri.getValue());
        this.dataSetService.createDataSet(newdataSet);
        return new SuccessMessage("Success add DataSet");
    }

    /**
     * @param dataSetID
     * @return
     */
    @RequestMapping(value = "/dataSet/{dataSetID}", method = RequestMethod.DELETE)
    public Pojo deleteDataSet(@PathVariable String dataSetID) {
        try {
            this.dataSetService.deleteDataSet(dataSetID);
        } catch (IllegalArgumentException e) {
            return new ErrorMessage("Invalid ID");
        }
        return new SuccessMessage("Success Remove");
    }

    @RequestMapping(value = "/dataSet/{dataSetID}/data", method = RequestMethod.GET)
    public Pojo dataOfDataSet(@PathVariable String dataSetID) {
        Data data;
        try {
            data = dataService.getById(dataSetID);
        } catch (IllegalArgumentException e) {
            return new ErrorMessage("Invalid ID");
        }
        if (data == null)
            return new ErrorMessage("No DataSet found");
        else
            return new fr.epita.sigl.mepa.front.APIpojo.Impl.Data(data.getData());
    }

    @RequestMapping(value = "/dataSet/{dataSetID}/data", method = RequestMethod.POST)
    public Pojo addDataOfDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.Data data, @PathVariable String dataSetID) {
        Pojo dataSet = schemaDataSet(dataSetID);
        if (dataSet instanceof ErrorMessage)
            return new ErrorMessage("invalid id");

        if (!data.checkDataType((fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet) dataSet))
            return new ErrorMessage("invalid type");

        this.dataService.createData(new Data(dataSetID, data.getData()));
        return new SuccessMessage("Success add Data in DataSet");
    }

    @RequestMapping(value = "/dataSet/{dataSetID}/specificData", method = RequestMethod.GET)
    public Object dataOfSpecificDataSet(@PathVariable String dataSetID, @RequestParam Map<String, String> allRequestParams) {

        if (allRequestParams.isEmpty()) {

        } else {

        }
        return "datasetID = " + dataSetID;
    }

}
