package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.DataDao;
import fr.epita.sigl.mepa.core.domain.Data;
import fr.epita.sigl.mepa.core.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Override
    public void createDataSet(Data data) {
        this.dataDao.create(data);
    }

    @Override
    public void updateDataSet(Data data) {
        this.dataDao.update(data);
    }

    @Override
    public void deleteDataSet(Data data) {
        this.dataDao.delete(data);
    }

    @Override
    public Data getById(String id) {
        return this.dataDao.getById(id);
    }
}
