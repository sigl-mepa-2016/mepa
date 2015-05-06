package fr.epita.sigl.mepa.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.service.ModelService;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelDao modelDao;

    @Override
    public void createModel(Model model) {
        model.setCreated(new Date());
        this.modelDao.create(model);
    }

    @Override
    public void updateModel(Model model) {
        this.modelDao.update(model);
    }

    @Override
    public void deleteModel(Model model) {
        this.modelDao.delete(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Model getModelById(Long id) {
        return this.modelDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> getAllModels() {
        return this.modelDao.getAll();
    }
}
