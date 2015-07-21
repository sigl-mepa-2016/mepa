package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Data;

public interface DataDao {

    void create(Data data);

    void update(Data data);

    void delete(Data data);

    Data getById(String id);

}
