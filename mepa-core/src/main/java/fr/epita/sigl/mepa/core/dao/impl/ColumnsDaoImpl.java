package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.ColumnsDao;
import fr.epita.sigl.mepa.core.domain.Columns;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColumnsDaoImpl implements ColumnsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Columns columns) {
        this.getSession().save(columns);
    }

    @Override
    public void update(Columns columns) {
        this.getSession().saveOrUpdate(columns);
    }

    @Override
    public void delete(Columns columns) {
        this.getSession().delete(columns);
    }

    @Override
    public Columns getById(Long id) {
        Query query = this.getSession().getNamedQuery("Columns.findById");
        query.setParameter("id", id);
        return (Columns) query.uniqueResult();
    }

    @Override
    public List<Columns> getAll() {
        Query query = this.getSession().getNamedQuery("Columns.findAll");
        return query.list();
    }

}
