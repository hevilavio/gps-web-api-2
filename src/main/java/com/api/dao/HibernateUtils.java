package com.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hevilavio on 2/10/15.
 */

public class HibernateUtils {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtils.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        logger.info("M=getSessionFactory");
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> listAndCast(Criteria criteria) {
        logger.info("M=listAndCast");
        return criteria.list();
    }

    public static Session getSession() {
        logger.info("M=getSession");
        Session session = sessionFactory.openSession();
        return session;
    }

    public static void finish() {
        logger.info("M=finish, Finalizando HibernateUtils.");

        getSessionFactory().close();

        logger.info("M=finish, Finalizado HibernateUtils.");
    }
}
