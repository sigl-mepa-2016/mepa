package fr.epita.sigl.mepa.core.dao.impl;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import fr.epita.sigl.mepa.core.dao.DataSetDao;
import fr.epita.sigl.mepa.core.domain.DataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class DataSetDaoImpl implements DataSetDao{

    private MongoCollection datasetCollection;
    private DB db;
    private static String url = "127.0.0.1";
    private static int port = 6375;
    @Autowired
    private SessionFactory sessionFactory;

    public DataSetDaoImpl() throws UnknownHostException {
        MongoCredential credential = MongoCredential.createCredential("Ludo", "mepa", "1234".toCharArray());
        List<MongoCredential> credentials = new LinkedList<MongoCredential>();
        credentials.add(credential);
        MongoClient mongoclient = new MongoClient(new ServerAddress(url, port));

        db = mongoclient.getDB("mepa");

        Jongo jongo = new Jongo(db);
        this.datasetCollection = jongo.getCollection("dataset");
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
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
    public DataSet getById(Long id) {
//        new ObjectId().;
//        return this.datasetCollection.findOne(ObjectId.messageToObjectId(id)).as(DataSet.class);
        return null;
    }

    @Override
    public List<DataSet> getAll() {
//        Query query = this.getSession().getNamedQuery("DataSet.findAll");
//        return query.list();

        Iterable<DataSet> sets = this.datasetCollection.find().as(DataSet.class);
        return Lists.newArrayList(sets);
    }

}
