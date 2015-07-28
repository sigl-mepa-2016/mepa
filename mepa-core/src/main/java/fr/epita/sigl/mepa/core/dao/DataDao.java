package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Data;
import org.bson.types.ObjectId;

public interface DataDao {

    void create(Data data);

    void update(Data data);

    void delete(ObjectId dataId);

    Data getById(ObjectId id);

}
