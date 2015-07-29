package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.GraphDao;
import fr.epita.sigl.mepa.core.domain.Graph;
import fr.epita.sigl.mepa.core.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GraphServiceImpl implements GraphService {

    @Autowired
    private GraphDao graphDao;
    
    @Override
    public void create(Graph graph) {
        this.graphDao.create(graph);
    }

    @Override
    public void update(Graph graph) {
        this.graphDao.update(graph);
    }

    @Override
    public void delete(String id) {
        this.graphDao.delete(id);
    }

    @Override
    public Graph getById(String id) {
        return this.graphDao.getById(id);
    }
}
