package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Data;

public interface DataService {

    void createDataSet(Data data);

    void updateDataSet(Data data);

    void deleteDataSet(Data data);

    Data getById(String id);
}
