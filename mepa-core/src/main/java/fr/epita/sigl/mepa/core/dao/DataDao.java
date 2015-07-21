package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Data;

/**
 * Created by Calu on 21/07/2015.
 */
public interface DataDao {

    void create(Data data);

    void update(Data data);

    void delete(Data data);

    Data getById(String id);

}
