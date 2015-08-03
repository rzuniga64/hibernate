package com.infiniteskills.data.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 	Helper class to provide a single implementation of a SessionFactory
 * 	to the application. When our application needs to perform a persistence
 * 	operation it will obtain a singleton SessionFactory from this Hibernate
 * 	util class. Using this singleton SessionFactory it can obtain a Session
 * 	and Session is basically the interface between our application and
 * 	Hibernate.  It is what we use to perform different persistence operations.*/
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            /** The configuration object will hold all of our Hibernate specific properties.
             *  So it's going to know how we want Hibernate to perform.  Another purpose of
             *  our configuration is to hold all of the mapping information. */
            Configuration configuration = new Configuration();
            configuration.configure();
            //configuration.addAnnotatedClass(UserHibernateAPI.class);
            /** Need to pass in the configuration to the StandardServerRegistryBuilder()
             *  and then that builder pattern invoke the build method and pass
             *  the ServiceRegistry into the BuildSessionFactoryMethod and eventually
             *  we'll end up with a SessionFactory.*/
            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error building the factory");
        }
    }

    /** Provide access to the singleton sessionFactory so we create a public method.
     *  This will provide the application with access to the singleton */
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}

