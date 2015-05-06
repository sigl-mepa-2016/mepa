package fr.epita.sigl.mepa.core.dao;

import java.util.List;

import fr.epita.sigl.mepa.core.domain.Model;

public interface ModelDao {

    void create(Model model);

    void update(Model model);

    void delete(Model model);

    Model getById(Long id);

    List<Model> getAll();

}
