package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.DataSet;
import org.bson.types.ObjectId;

import java.util.List;

public interface DataSetDao {

    void create(DataSet dataSet);

    void update(DataSet dataSet);

    void delete(ObjectId id);

    DataSet getById(ObjectId id);

    List<DataSet> getAll();

}
