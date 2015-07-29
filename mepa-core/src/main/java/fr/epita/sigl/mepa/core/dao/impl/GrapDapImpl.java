package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.GraphDao;
import fr.epita.sigl.mepa.core.domain.Graph;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Repository;


@Repository
public class GrapDapImpl extends Dao implements GraphDao {

    private MongoCollection graphCollection;

    public GrapDapImpl() {
        super();
        Jongo jongo = new Jongo(this.db);
        this.graphCollection = jongo.getCollection("graph");
    }

    @Override
    public void create(Graph graph) {
        this.graphCollection.insert(graph);
    }

    @Override
    public void update(Graph graph) {
        this.graphCollection.update(graph.get_id()).with(graph);
    }

    @Override
    public void delete(String id) {
        this.graphCollection.remove(id);
    }

    @Override
    public Graph getById(String id) {
        return this.graphCollection.findOne("{_id: #}", id).as(Graph.class);
    }
}
