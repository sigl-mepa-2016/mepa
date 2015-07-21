package fr.epita.sigl.mepa.core.dao.impl;

import com.google.common.collect.Lists;
import fr.epita.sigl.mepa.core.dao.DataSetDao;
import fr.epita.sigl.mepa.core.domain.DataSet;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.List;

@Repository
public class DataSetDaoImpl extends Dao implements DataSetDao {

    private MongoCollection datasetCollection;


    public DataSetDaoImpl() throws UnknownHostException {
        super();
        Jongo jongo = new Jongo(this.db);
        this.datasetCollection = jongo.getCollection("dataset");
    }

    @Override
    public void create(DataSet dataSet) {
        this.datasetCollection.insert(dataSet);
    }

    @Override
    public void update(DataSet dataSet) {
        this.getSession().saveOrUpdate(dataSet);
    }

    @Override
    public void delete(DataSet dataSet) {
        this.getSession().delete(dataSet);
    }

    @Override
    public DataSet getById(String id) {
        return this.datasetCollection.findOne("{_id: #}", new ObjectId(id)).as(DataSet.class);
    }

    @Override
    public List<DataSet> getAll() {
        Iterable<DataSet> sets = this.datasetCollection.find().as(DataSet.class);
        return Lists.newArrayList(sets);
    }

}
