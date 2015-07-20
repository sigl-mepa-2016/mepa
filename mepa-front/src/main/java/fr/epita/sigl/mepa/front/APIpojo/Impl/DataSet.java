package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import java.util.Date;
import java.util.Map;

public class DataSet implements Pojo {
    private String _id;
    private String name;
    private String owner;
    private String theme;
    private Date lastModified;
    private Map<String, String> fieldMap;

    public DataSet(String _id, String name, String owner, String theme, Date lastModified) {
        this._id = _id;
        this.name = name;
        this.owner = owner;
        this.theme = theme;
        this.lastModified = lastModified;
    }

    public String get_id() {
        return _id;
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
}
