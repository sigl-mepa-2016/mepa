package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.core.domain.DataSetType;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data implements Pojo {

    private Map<String, List<String>> data;

    public Data() {
        this.data = new HashMap<>();
    }

    public Data(Map<String, List<String>> data) {
        this.data = data;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    public boolean checkDataType(DataSet dataSet) {
        for (Map.Entry<String, String> entrie : dataSet.getFieldMap().entrySet()) {
            String fieldName = entrie.getKey();
            String fieldType = entrie.getValue();

            for(String d : this.data.get(fieldName))
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
        for (Map.Entry<String, List<String>> entry : dataInput.getData().entrySet()) {
            List<String> actualData = this.data.get(entry.getKey());
            List<String> newData = entry.getValue();
            actualData.addAll(newData);

            this.data.put(entry.getKey(), actualData);
        }
    }

    /**
     * Check if each list have the same number of inpu
     *
     * @return
     */
    public boolean validInput() {
        int size = -1;
        for (Map.Entry<String, List<String>> entry : this.data.entrySet()) {
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
