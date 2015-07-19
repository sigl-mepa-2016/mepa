package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.DataSet;

import java.util.List;

public interface DataSetDao {

    void create(DataSet dataSet);

    void update(DataSet dataSet);

    void delete(DataSet dataSet);

    DataSet getById(Long id);

    List<DataSet> getAll();

}
