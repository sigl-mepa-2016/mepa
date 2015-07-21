package fr.epita.sigl.mepa.core.dao.impl;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Dao {

    protected DB db;
    private static String url = "127.0.0.1";
    private static int port = 27017;

    @Autowired
    private SessionFactory sessionFactory;

    public Dao() {
        MongoClient mongoclient = new MongoClient(url, port);
        db = mongoclient.getDB("mepa");
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
