package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Data implements Pojo {

    private Map<String, List<String>> data;

    public Data() {
        this.data = new LinkedHashMap<>();
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
