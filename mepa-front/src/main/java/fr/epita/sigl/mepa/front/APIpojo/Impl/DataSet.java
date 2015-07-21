package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class DataSet implements Pojo {
    private String _id;
    private String name;
    private String owner;
    private String theme;
    private Date lastModified;
    private Boolean isCarto = false;
    private Boolean isGraphic = false;
    private Map<String, String> fieldMap;

    public DataSet() {
        this.fieldMap = new HashMap<>();
    }

    public DataSet(String _id, String name, String owner, String theme, Date lastModified, Boolean isCarto, Boolean isGraphic, Map<String, String> fieldMap) {
        this._id = _id;
        this.name = name;
        this.owner = owner;
        this.theme = theme;
        this.lastModified = lastModified;
        this.isCarto = isCarto;
        this.isGraphic = isGraphic;
        this.fieldMap = fieldMap;
    }

    @XmlElement
    public String get_id() {
        return _id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getOwner() {
        return owner;
    }

    @XmlElement
    public String getTheme() {
        return theme;
    }

    @XmlElement
    public Date getLastModified() {
        return lastModified;
    }

    @XmlElement
    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    @XmlElement
    public Boolean getIsCarto() {
        return isCarto;
    }

    @XmlElement
    public Boolean getIsGraphic() {
        return isGraphic;
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

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public void setIsCarto(Boolean isCarto) {
        this.isCarto = isCarto;
    }

    public void setIsGraphic(Boolean isGraphic) {
        this.isGraphic = isGraphic;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", theme='" + theme + '\'' +
                ", lastModified=" + lastModified +
                ", isCarto=" + isCarto +
                ", isGraphic=" + isGraphic +
                ", fieldMap=" + fieldMap +
                '}';
    }
}
