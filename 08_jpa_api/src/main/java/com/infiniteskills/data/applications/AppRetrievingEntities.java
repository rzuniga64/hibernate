package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppRetrievingEntities {

	public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try{
            // Create an EntityManagerFactory
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            // Create an entity manager which is like a session
            em = factory.createEntityManager();

            tx =  em.getTransaction();

            tx.begin();

			// Return an entity from the database. We are executing a SELECT against the database and having that
			// result set turned into either a list of entities or a single entity.
            //BankHibernateAPI bank = em.find(BankHibernateAPI.class, 1L);

            // Gives a NPE because using a JPA method. Find or get method are generic so we don't have to cast
            // them to the type or our entity.
            // BankHibernateAPI bank = em.find(BankHibernateAPI.class, 123L);

            // Similar to the load method within Hibernate. The method will not return a null reference but will
            // throw an ObjectNotFoundException if the entity does not exist. That is because Hibernate always tries
            // to return a proxy.
            // BankHibernateAPI bank = em.getReference(BankHibernateAPI.class, 123L);
			Bank bank = em.getReference(Bank.class, 1L);
			System.out.println(em.contains(bank));
            System.out.println(bank.getName());

            // Cause the persistence manager to flush all of the changes to the database and it will synchronize
            // our entity model and our database.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
            factory.close();
        }
	}
}
