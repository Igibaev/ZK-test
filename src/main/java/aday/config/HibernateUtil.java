package aday.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    private HibernateUtil() {
    }
    public static synchronized SessionFactory  getSessionFactory(){
        if(sessionFactory==null){
            sessionFactory=new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static synchronized EntityManager getEntityManager(){
        if(entityManager==null){
            sessionFactory=new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        }
        return entityManager;
    }
}
