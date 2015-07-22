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
    public Boolean createDataSet(DataSet dataSet) {
        dataSet.setLastModified(new Date());

        if (!dataSet.isValidToInsert())
            return false;

        this.dataSetDao.create(dataSet);
        return true;
    }

    @Override
    public Boolean updateDataSet(DataSet dataSet) {
        dataSet.setLastModified(new Date());
        if (!dataSet.isValidToUpdate())
            return false;

        this.dataSetDao.update(dataSet);
        return true;
    }

    @Override
    public void deleteDataSet(String id) {
        this.dataSetDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataSet getDataSetById(String id) {
        return this.dataSetDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataSet> getAllDataSets() {
        return this.dataSetDao.getAll();
    }
}
