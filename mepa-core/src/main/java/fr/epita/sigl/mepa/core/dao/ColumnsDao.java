package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Columns;

import javax.persistence.Column;
import java.util.List;

public interface ColumnsDao {

    void create(Columns columns);

    void update(Columns columns);

    void delete(Columns columns);

    Columns getById(Long id);

    List<Columns> getAll();

}
