package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.DataSetDao;
import fr.epita.sigl.mepa.core.domain.DataSet;
import fr.epita.sigl.mepa.core.service.DataSetService;
import org.bson.types.ObjectId;
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

        if (!isValidToInsert(dataSet))
            return false;

        this.dataSetDao.create(dataSet);
        return true;
    }

    @Override
    public Boolean updateDataSet(DataSet dataSet) {
        dataSet.setLastModified(new Date());
        if (!isValidToUpdate(dataSet))
            return false;

        this.dataSetDao.update(dataSet);
        return true;
    }

    @Override
    public void deleteDataSet(ObjectId id) {
        this.dataSetDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataSet getDataSetById(ObjectId id) {
        return this.dataSetDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataSet> getAllDataSets() {
        return this.dataSetDao.getAll();
    }

    private boolean isValidToInsert(DataSet dataSet) {

        return (dataSet.getName() != null && !dataSet.getName().isEmpty())
                && (dataSet.getOwner() != null && !dataSet.getOwner().isEmpty())
                && (dataSet.getTheme() != null && !dataSet.getTheme().isEmpty())
                && dataSet.getLastModified() != null && dataSet.getIsCarto() != null
                && dataSet.getIsGraphic() != null;
    }

    private boolean isValidToUpdate(DataSet dataSet) {

        return isValidToInsert(dataSet) && (dataSet.get_id().toString() != null && !dataSet.get_id().toString().isEmpty());
    }
}
