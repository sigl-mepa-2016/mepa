package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.DataSet;
import org.bson.types.ObjectId;

import java.util.List;

public interface DataSetService {

    Boolean createDataSet(DataSet dataSet);

    Boolean updateDataSet(DataSet dataSet);

    void deleteDataSet(ObjectId id);

    DataSet getDataSetById(ObjectId id);

    List<DataSet> getAllDataSets();

}
