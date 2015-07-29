package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Graph;

public interface GraphService {
    void create(Graph graph);

    void update(Graph graph);

    void delete(String id);

    Graph getById(String id);
}
