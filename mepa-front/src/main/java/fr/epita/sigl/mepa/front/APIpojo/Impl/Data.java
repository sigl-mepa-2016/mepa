package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Data implements Pojo {

    private Map<String, List<String>> data;

    public Data() {
        this.data = new HashMap<>();
    }

    public Data(Map<String, List<String>> data) {
        this.data = data;
    }

    @XmlElement
    public Map<String, List<String>> getData() {
        return data;
    }


    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    public boolean checkDataType(DataSet dataSet)
    {
        return true;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}
