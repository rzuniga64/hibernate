package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppModifyingEntities {

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
			Bank bank = em.getReference(Bank.class, 1L);
			System.out.println(em.contains(bank));

            // The entity is dirty so when we commit our transaction Hibernate will synch our entity model with the
            // database.
            bank.setName("Another Demonstration");

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
