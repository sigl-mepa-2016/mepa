package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Map;

@XmlRootElement
public class DataSet implements Pojo {
    private String _id;
    private String name;
    private String owner;
    private String theme;
    private Date lastModified;
    private Map<String, String> fieldMap;

    public DataSet() {
    }

    public DataSet(String _id, String name, String owner, String theme, Date lastModified) {
        this._id = _id;
        this.name = name;
        this.owner = owner;
        this.theme = theme;
        this.lastModified = lastModified;
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
}
