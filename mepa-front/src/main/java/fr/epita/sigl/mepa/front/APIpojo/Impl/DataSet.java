package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class DataSet implements Pojo {
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

    public DataSet(String name, String owner, String theme, Date lastModified, Boolean isCarto, Boolean isGraphic, Map<String, String> fieldMap) {
        this.name = name;
        this.owner = owner;
        this.theme = theme;
        this.lastModified = lastModified;
        this.isCarto = isCarto;
        this.isGraphic = isGraphic;
        this.fieldMap = fieldMap;
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

    public Date getLastModified() {
        return lastModified;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public Boolean getIsCarto() {
        return isCarto;
    }

    public Boolean getIsGraphic() {
        return isGraphic;
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
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", theme='" + theme + '\'' +
                ", lastModified=" + lastModified +
                ", isCarto=" + isCarto +
                ", isGraphic=" + isGraphic +
                ", fieldMap=" + fieldMap +
                '}';
    }
}
