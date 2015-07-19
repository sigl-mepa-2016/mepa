package fr.epita.sigl.mepa.core.dao.impl;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import fr.epita.sigl.mepa.core.dao.DataSetDao;
import fr.epita.sigl.mepa.core.domain.DataSet;
import org.bson.types.ObjectId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.List;

@Repository
public class DataSetDaoImpl implements DataSetDao{

    private MongoCollection datasetCollection;
    private DB db;
    @Autowired
    private SessionFactory sessionFactory;

    public DataSetDaoImpl() throws UnknownHostException {

        db = new MongoClient("mepa.sigl.epita.fr", 6375).getDB("mepa");


        Jongo jongo = new Jongo(db);
        this.datasetCollection = jongo.getCollection("dataset");
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(DataSet dataSet) {
        this.datasetCollection.insert(dataSet);
        this.getSession().save(dataSet);
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
    public DataSet getById(Long id) {
        return this.datasetCollection.findOne(ObjectId.massageToObjectId(id)).as(DataSet.class);
    }

    @Override
    public List<DataSet> getAll() {
        Query query = this.getSession().getNamedQuery("DataSet.findAll");
        return query.list();
    }

}
