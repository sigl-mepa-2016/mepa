package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.domain.DataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.epita.sigl.mepa.core.dao.DataSetDao;

import java.util.List;

@Repository
public class DataSetDaoImpl implements DataSetDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(DataSet dataSet) {
        this.getSession().save(dataSet);
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
        Query query = this.getSession().getNamedQuery("DataSet.findById");
        query.setParameter("id", id);
        return (DataSet) query.uniqueResult();
    }

    @Override
    public List<DataSet> getAll() {
        Query query = this.getSession().getNamedQuery("DataSet.findAll");
        return query.list();
    }

}
