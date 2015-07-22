package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Data;

public interface DataDao {

    void create(Data data);

    void update(Data data);

    void delete(String dataId);

    Data getById(String id);

}
