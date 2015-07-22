package fr.epita.sigl.mepa.core.domain;

import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataSet {

    @MongoObjectId
    private String _id;
    private String name;
    private String owner;
    private String theme;
    private Boolean isCarto;
    private Boolean isGraphic;
    private Date lastModified;
    private Map<String, String> fieldMap;

    public String get_id() {
        return _id;
    }

    public DataSet() {
        this.fieldMap = new HashMap<>();
    }

    public DataSet(String _id, String name, String owner, String theme, Boolean isCarto, Boolean isGraphic, Date lastModified) {
        this._id = _id;
        this.name = name;
        this.owner = owner;
        this.theme = theme;
        this.isCarto = isCarto;
        this.isGraphic = isGraphic;
        this.lastModified = lastModified;
        this.fieldMap = new HashMap<>();
    }

    public boolean addField(String name, String type) {
        try {
            DataSetType.valueOf(type);
        } catch (Exception e) {
            return false;
        }
        fieldMap.put(name, type);
        return true;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getTheme() {
        return theme;
    }

    public Boolean getIsCarto() {
        return isCarto;
    }

    public Boolean getIsGraphic() {
        return isGraphic;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setIsCarto(Boolean isCarto) {
        this.isCarto = isCarto;
    }

    public void setIsGraphic(Boolean isGraphic) {
        this.isGraphic = isGraphic;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", theme='" + theme + '\'' +
                ", isCarto=" + isCarto +
                ", isGraphic=" + isGraphic +
                ", lastModified=" + lastModified +
                ", fieldMap=" + fieldMap +
                '}';
    }
}

enum DataSetType {
    TEXT,INT,DATE;

}
