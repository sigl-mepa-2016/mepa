package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.DataSetDao;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DataSetServiceImpl implements DataSetService {

    @Autowired
    private DataSetDao dataSetDao;

    @Override
    public void createDataSet(DataSet dataSet) {
        dataSet.setLastModified(new Date());
        this.dataSetDao.create(dataSet);
    }

    @Override
    public void updateDataSet(DataSet dataSet) {
        dataSet.setLastModified(new Date());
        this.dataSetDao.update(dataSet);
    }

    @Override
    public void deleteDataSet(DataSet dataSet) {
        this.dataSetDao.delete(dataSet);
    }

    @Override
    @Transactional(readOnly = true)
    public DataSet getDataSetById(Long id) {
        return this.dataSetDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataSet> getAllDataSets() {
        return this.dataSetDao.getAll();
    }
}
