package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Data;

public interface DataService {

    void createData(Data data);

    void updateData(Data data);

    void deleteData(Data data);

    Data getById(String id);
}
