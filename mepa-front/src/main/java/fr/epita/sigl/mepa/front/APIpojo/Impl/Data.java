package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import java.util.List;
import java.util.Map;

public class Data implements Pojo {

    private String _id;
    private Map<String, List<String>> data;

    public Data() {
    }

    public Data(String _id, Map<String, List<String>> data) {
        this._id = _id;
        this.data = data;
    }

    public String get_id() {
        return _id;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "_id='" + _id + '\'' +
                ", data=" + data +
                '}';
    }
}
