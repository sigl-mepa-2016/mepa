package fr.epita.sigl.mepa.core.dao;


import fr.epita.sigl.mepa.core.domain.Graph;

public interface GraphDao {

    void create(Graph graph);

    void update(Graph graph);

    void delete(String id);

    Graph getById(String id);
}
