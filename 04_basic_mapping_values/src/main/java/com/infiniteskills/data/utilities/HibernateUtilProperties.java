package com.infiniteskills.data.utilities;

import com.infiniteskills.data.entities.User;
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
public class HibernateUtilProperties {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			/** The configuration object will hold all of our Hibernate specific properties.
			 *  So it's going to know how we want Hibernate to perform.  Another purpose of
			 *  our configuration is to hold all of the mapping information. */
			Configuration configuration = new Configuration();

			/** Add an annotated class to our configuration that corresponds with the UserHibernateAPI
			 * class that we have provided the mapping metadata for.*/
			configuration.addAnnotatedClass(User.class);

			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
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
