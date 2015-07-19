package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.ColumnsDao;
import fr.epita.sigl.mepa.core.domain.Columns;
import fr.epita.sigl.mepa.core.service.ColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ColumnsServiceImpl implements ColumnsService {

    @Autowired
    private ColumnsDao columnsDao;

    @Override
    public void createColumns(Columns columns) {
        this.columnsDao.create(columns);
    }

    @Override
    public void updateColumns(Columns columns) {
        this.columnsDao.update(columns);
    }

    @Override
    public void deleteColumns(Columns columns) {
        this.columnsDao.delete(columns);
    }

    @Override
    @Transactional(readOnly = true)
    public Columns getColumnsById(Long id) {
        return this.columnsDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Columns> getAllColumns() {
        return this.columnsDao.getAll();
    }

}
