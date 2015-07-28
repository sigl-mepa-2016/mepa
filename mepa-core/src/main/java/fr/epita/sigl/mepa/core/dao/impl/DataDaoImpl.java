package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.DataDao;
import fr.epita.sigl.mepa.core.domain.Data;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Repository;

@Repository
public class DataDaoImpl extends Dao implements DataDao {

    private MongoCollection dataCollection;

    public DataDaoImpl() {
        super();
        Jongo jongo = new Jongo(this.db);
        this.dataCollection = jongo.getCollection("data");
    }

    @Override
    public void create(Data data) {
        this.dataCollection.insert(data);
    }

    @Override
    public void update(Data data) {
        this.dataCollection.update(data.get_id()).with(data);
    }

    @Override
    public void delete(ObjectId dataId) {
        this.dataCollection.remove(dataId);
    }

    @Override
    public Data getById(ObjectId id) {
        return this.dataCollection.findOne("{_id: #}", id).as(Data.class);
    }
}
