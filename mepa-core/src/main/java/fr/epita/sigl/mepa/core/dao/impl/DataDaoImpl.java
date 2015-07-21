package fr.epita.sigl.mepa.core.dao.impl;

import java.util.List;
import java.util.Map;

public class DataDaoImpl extends Dao {

    private Map<String, List<?>> data;

    public DataDaoImpl() {
    }

    public DataDaoImpl(Map<String, List<?>> data) {
        this.data = data;
    }

    public Map<String, List<?>> getData() {
        return data;
    }

    public void setData(Map<String, List<?>> data) {
        this.data = data;
    }
}
