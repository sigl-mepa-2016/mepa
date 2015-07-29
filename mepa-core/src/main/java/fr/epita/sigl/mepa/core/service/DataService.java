package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Data;
import org.bson.types.ObjectId;

public interface DataService {

    void createData(Data data);

    void updateData(Data data);

    void deleteData(ObjectId data);

    Data getById(ObjectId id);
}
