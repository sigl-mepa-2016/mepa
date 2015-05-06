package fr.epita.sigl.mepa.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.domain.Model;

@Repository
public class ModelDaoImpl implements ModelDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Model model) {
        this.getSession().save(model);
    }

    @Override
    public void update(Model model) {
        this.getSession().saveOrUpdate(model);
    }

    @Override
    public void delete(Model model) {
        this.getSession().delete(model);
    }

    @Override
    public Model getById(Long id) {
        Query query = this.getSession().getNamedQuery("Model.findById");
        query.setParameter("id", id);
        return (Model) query.uniqueResult();
    }

    @Override
    public List<Model> getAll() {
        Query query = this.getSession().getNamedQuery("Model.findAll");
        return query.list();
    }
}
