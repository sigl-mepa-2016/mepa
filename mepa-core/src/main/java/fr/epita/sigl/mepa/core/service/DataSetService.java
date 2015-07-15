package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.DataSet;

import java.util.List;

public interface DataSetService {

    void createDataSet(DataSet dataSet);

    void updateDataSet(DataSet dataSet);

    void deleteDataSet(DataSet dataSet);

    DataSet getDataSetById(Long id);

    List<DataSet> getAllDataSets();

}
