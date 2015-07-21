package fr.epita.sigl.mepa.core.domain;

import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Date;
import java.util.Map;

public class DataSet {

    @MongoObjectId
    private String _id;
    private String name;
    private String owner;
    private String theme;
    private Date lastModified;
    private Map<String, DataSetType> fieldMap;

    public String get_id() {
        return _id;
    }

    public DataSet() {
    }

    public DataSet(String name, String owner, String theme, Date lastModified) {
        this.name = name;
        this.owner = owner;
        this.theme = theme;
        this.lastModified = lastModified;
    }

    public boolean addField(String name, String type) {
        try {
            fieldMap.put(name, DataSetType.valueOf(type));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Map<String, DataSetType> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, DataSetType> fieldMap) {
        this.fieldMap = fieldMap;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", theme='" + theme + '\'' +
                ", lastModified=" + lastModified +
                ", fieldMap=" + fieldMap +
                '}';
    }
}

enum DataSetType {
    TEXT,INT,DATE;

}
