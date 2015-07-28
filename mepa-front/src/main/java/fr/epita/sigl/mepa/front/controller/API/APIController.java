package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.domain.DataSetType;
import fr.epita.sigl.mepa.core.service.DataService;
import fr.epita.sigl.mepa.core.service.DataSetService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.DataSetWithData;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ListSimpleDataSet;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            dataSet = dataSetService.getDataSetById(new ObjectId(dataSetID));
        } catch (IllegalArgumentException e) {
            return new ErrorMessage("Invalid ID");
        }
        if (dataSet == null)
            return new ErrorMessage("No DataSet found");
        else
            return new fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet(dataSet.getName(), dataSet.getOwner(), dataSet.getTheme(), dataSet.getLastModified(), dataSet.getIsCarto(), dataSet.getIsGraphic(), dataSet.getFieldMap());
    }

    @RequestMapping("/dataSetWithData/{dataSetID}")
    public Pojo dataSetWithData(@PathVariable String dataSetID) {
        Pojo dataSet = schemaDataSet(dataSetID);
        if (dataSet instanceof ErrorMessage)
            return new ErrorMessage("Invalid Id");

        Pojo data = dataOfDataSet(dataSetID);
        if (data instanceof ErrorMessage)
            return new DataSetWithData((fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet) dataSet, null);
        else
            return new DataSetWithData((fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet) dataSet, (fr.epita.sigl.mepa.front.APIpojo.Impl.Data) data);
    }

    /**
     * Specific application/json in Content-type
     *
     * @param dataSet
     * @return Message Pojo
     */
    @RequestMapping(value = "/dataSet", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pojo addDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet dataSet, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {
        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;

        DataSet newdataSet = new DataSet(dataSet.getName(), dataSet.getOwner(), dataSet.getTheme(), dataSet.getIsCarto(), dataSet.getIsGraphic(), new Date());
        for (Map.Entry<String, String> entry : dataSet.getFieldMap().entrySet())
            if (!newdataSet.addField(entry.getKey(), entry.getValue()))
                return new ErrorMessage("Invalid Type in DataSet, only Text and Int are accepted");

        return (this.dataSetService.createDataSet(newdataSet)) ? new SuccessMessage("id: " + newdataSet.get_id().toString()) : new ErrorMessage("Missing Field");
    }

    /**
     * Update DataSet, with specific id give in arg
     *
     * @param dataSetID
     * @param InputDataSet
     * @param authorization
     * @return
     */
    @RequestMapping(value = "/dataSet/{dataSetId}", method = RequestMethod.POST)
    public Pojo updateDataSet(@PathVariable String dataSetID, @RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet InputDataSet, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {
        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;

        Pojo oldDataSet = schemaDataSet(dataSetID);
        if (oldDataSet instanceof ErrorMessage)
            return new ErrorMessage("Invalid Id");

        DataSet updateDataSet = new DataSet(dataSetID, InputDataSet.getName(), InputDataSet.getOwner(), InputDataSet.getTheme(), InputDataSet.getIsCarto(), InputDataSet.getIsGraphic(), new Date());

        for (Map.Entry<String, String> entry : InputDataSet.getFieldMap().entrySet())
            if (!updateDataSet.addField(entry.getKey(), entry.getValue()))
                return new ErrorMessage("Invalid Type in DataSet, only Text and NUMBER are accepted");


        Data data = this.dataService.getById(new ObjectId(dataSetID));

        for (Map.Entry<String, List<String>> entry : ((fr.epita.sigl.mepa.front.APIpojo.Impl.Data)oldDataSet).getDataInList().entrySet())
            if (!updateDataSet.getFieldMap().containsKey(entry.getKey()))
                data.getData().remove(entry.getKey());

        for (Map.Entry<String, String> entry : updateDataSet.getFieldMap().entrySet())
        {
            if (!updateDataSet.getFieldMap().containsKey(entry.getKey())) {
                List<String> generateData = new ArrayList<>();
                if (entry.getValue().equals(DataSetType.NUMBER.toString()))
                    generateData.add("0");
                else
                    generateData.add("");
                data.getData().put(entry.getKey(), generateData);
            }
        }

        this.dataService.updateData(data);

        return (this.dataSetService.updateDataSet(updateDataSet)) ? new SuccessMessage("id: " + updateDataSet.get_id().toString()) : new ErrorMessage("Missing Field");
    }


    /**
     * Remove DataSet and Data in database
     *
     * @param dataSetID = Id Of DataSet
     * @return Message Pojo
     */
    @RequestMapping(value = "/dataSet/{dataSetID}", method = RequestMethod.DELETE)
    public Pojo deleteDataSet(@PathVariable String dataSetID, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {
        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;

        try {
            ObjectId dataSetObjectId = new ObjectId(dataSetID);
            this.dataSetService.deleteDataSet(dataSetObjectId);
            this.dataService.deleteData(dataSetObjectId);
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
            data = dataService.getById(new ObjectId(dataSetID));
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
    public Pojo addDataOfDataSet(@RequestBody fr.epita.sigl.mepa.front.APIpojo.Impl.Data dataInput, @PathVariable String dataSetID, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {

        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;

        Pojo dataSet = schemaDataSet(dataSetID);
        if (dataSet instanceof ErrorMessage)
            return new ErrorMessage("invalid id");

        if (!dataInput.validInput((fr.epita.sigl.mepa.front.APIpojo.Impl.DataSet) dataSet))
            return new ErrorMessage("invalid number of Input");

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

}
