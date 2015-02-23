/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Aluno
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            return  configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed - " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void criarBanco() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SchemaExport se = new SchemaExport(cfg);
        se.create(true, true);

    }
}
