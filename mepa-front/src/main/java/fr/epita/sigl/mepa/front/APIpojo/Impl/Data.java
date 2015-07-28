package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.core.domain.DataSetType;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Data implements Pojo {

    private Map<String, DataList> data;

    public Data() {
        this.data = new HashMap<>();
    }

    public Data(Map<String, List<String>> data) {
        this.data = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet())
            this.data.put(entry.getKey(), new DataList(entry.getValue()));
    }


    public Map<String, DataList> getData() {
        return data;
    }

    public Map<String, List<String>> getDataInList() {
        Map<String, List<String>> returnData = new HashMap<>();

        for (Map.Entry<String, DataList> entry : this.data.entrySet())
            returnData.put(entry.getKey(), entry.getValue().getValue());

        return returnData;
    }


    public void setData(Map<String, DataList> data) {
        this.data = data;
    }

    /**
     * Check if the input type of data is valid
     *
     * @param dataSet
     * @return
     */
    public boolean checkDataType(DataSet dataSet) {
        for (Map.Entry<String, String> entrie : dataSet.getFieldMap().entrySet()) {
            String fieldName = entrie.getKey();
            String fieldType = entrie.getValue();

            for (String d : this.getDataInList().get(fieldName))
                if (!DataSetType.checkType(d, DataSetType.valueOf(fieldType)))
                    return false;
        }
        return true;
    }

    /**
     * Merge to Set of Data, add Data at the end of the actuals lists
     *
     * @param dataInput
     */
    public void mergeData(Data dataInput) {
        for (Map.Entry<String, List<String>> entry : dataInput.getDataInList().entrySet()) {
            List<String> actualData = this.getDataInList().get(entry.getKey());
            List<String> newData = entry.getValue();
            actualData.addAll(newData);

            this.data.put(entry.getKey(), new DataList(actualData));
        }
    }

    /**
     * Check if each list have the same number of input
     *
     * @return
     */
    public boolean validInput(DataSet dataSet) {
        if (dataSet.getFieldMap().size() != this.getData().size())
            return false;

        int size = -1;
        for (Map.Entry<String, List<String>> entry : this.getDataInList().entrySet()) {
            if (size == -1)
                size = entry.getValue().size();
            else if (size != entry.getValue().size())
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}

@XmlRootElement
class DataList {

    private List<String> value;

    public DataList(List<String> values) {
        this.value = values;
    }

    public DataList() {
    }

    public List<String> getValue() {
        return value;
    }
}
