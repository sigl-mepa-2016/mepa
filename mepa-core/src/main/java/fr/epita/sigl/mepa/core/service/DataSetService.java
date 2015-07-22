package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.DataSet;

import java.util.List;

public interface DataSetService {

    Boolean createDataSet(DataSet dataSet);

    Boolean updateDataSet(DataSet dataSet);

    void deleteDataSet(String id);

    DataSet getDataSetById(String id);

    List<DataSet> getAllDataSets();

}
