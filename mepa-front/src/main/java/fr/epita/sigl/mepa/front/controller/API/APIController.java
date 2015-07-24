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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
     * @return Pojo of List of DataSet
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.GET)
    public Pojo listDataSet() {
        ListSimpleDataSet listSimpleDataSet = new ListSimpleDataSet();

        for (DataSet data : dataSetService.getAllDataSets())
            listSimpleDataSet.addSimpleObject(data.get_id().toString(), data.getName());

        return listSimpleDataSet;
    }

    /**
     * schema of specific DataSet
     *
     * @param dataSetID = ID of DataSet
     * @return Pojo of DataSet
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
            return new fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet(dataSet.getName(), dataSet.getOwner(), dataSet.getTheme(), dataSet.getLastModified(), dataSet.getIsCarto(), dataSet.getIsGraphic(), dataSet.getFieldMap());
    }

    /**
     * Specific application/json in Content-type
     *
     * @param dataSet
     * @return Message Pojo
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pojo addDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet dataSet) {

        DataSet newdataSet = new DataSet(dataSet.getName(), dataSet.getOwner(), dataSet.getTheme(), dataSet.getIsCarto(), dataSet.getIsGraphic(), new Date());
        for (Map.Entry<String, String> entry : dataSet.getFieldMap().entrySet())
            if (!newdataSet.addField(entry.getKey(), entry.getValue()))
                return new ErrorMessage("Invalid Type in DataSet, only Text and Int are accepted");

        return (this.dataSetService.createDataSet(newdataSet)) ? new SuccessMessage("id: " + newdataSet.get_id().toString()) : new ErrorMessage("Missing Field");
    }

    @RequestMapping(value = "/dataSet/{dataSetId}", method = RequestMethod.PUT)
    public Pojo updateDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet dataSet, @PathVariable String dataSetID) {
        //TODO
        return new SuccessMessage("Success update");
    }

    /**
     * Remove DataSet and Data in database
     *
     * @param dataSetID = Id Of DataSet
     * @return Message Pojo
     */
    @RequestMapping(value = "/dataSet/{dataSetID}", method = RequestMethod.DELETE)
    public Pojo deleteDataSet(@PathVariable String dataSetID) {
        try {
            this.dataSetService.deleteDataSet(dataSetID);
            this.dataService.deleteData(dataSetID);
        } catch (IllegalArgumentException e) {
            return new ErrorMessage("Invalid ID");
        }
        return new SuccessMessage("Success Remove");
    }

    /**
     * Get Data In dataSet
     *
     * @param dataSetID = Id Of DataSet
     * @return Data Pojo
     */
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

    /**
     * add Data in DataSet
     *
     * @param dataInput = Data to add in database
     * @param dataSetID = Id of DataSet
     * @return Pojo Message
     */
    @RequestMapping(value = "/dataSet/{dataSetID}/data", method = RequestMethod.POST)
    public Pojo addDataOfDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.Data dataInput, @PathVariable String dataSetID) {
        if (!dataInput.validInput())
            return new ErrorMessage("invalid number of Input");

        Pojo dataSet = schemaDataSet(dataSetID);
        if (dataSet instanceof ErrorMessage)
            return new ErrorMessage("invalid id");

        if (!dataInput.checkDataType((fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet) dataSet))
            return new ErrorMessage("invalid type in Input");

        Pojo data = dataOfDataSet(dataSetID);
        if (data instanceof ErrorMessage)
            this.dataService.createData(new Data(dataSetID, dataInput.getDataInList()));
        else {
            ((fr.epita.sigl.mepa.front.APIpojo.Impl.Data) data).mergeData(dataInput);
            this.dataService.updateData(new Data(dataSetID, ((fr.epita.sigl.mepa.front.APIpojo.Impl.Data) data).getDataInList()));
        }
        return new SuccessMessage("Success add Data in DataSet");
    }

    /**
     * Not Ready
     *
     * @param dataSetID
     * @param allRequestParams
     * @return
     */
    @RequestMapping(value = "/dataSet/{dataSetID}/specificData", method = RequestMethod.GET)
    public Object dataOfSpecificDataSet(@PathVariable String dataSetID, @RequestParam Map<String, String> allRequestParams) {

        if (allRequestParams.isEmpty()) {

        } else {

        }
        return "datasetID = " + dataSetID;
    }

}
