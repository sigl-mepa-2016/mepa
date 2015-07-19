package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Columns;

import java.util.List;

public interface ColumnsService {

    void createColumns(Columns columns);

    void updateColumns(Columns columns);

    void deleteColumns(Columns columns);

    Columns getColumnsById(Long id);

    List<Columns> getAllColumns();
}
