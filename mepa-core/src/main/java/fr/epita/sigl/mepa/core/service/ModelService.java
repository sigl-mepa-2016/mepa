package fr.epita.sigl.mepa.core.service;

import java.util.List;

import fr.epita.sigl.mepa.core.domain.Model;

public interface ModelService {

    void createModel(Model model);

    void updateModel(Model model);

    void deleteModel(Model model);

    Model getModelById(Long id);

    List<Model> getAllModels();

}