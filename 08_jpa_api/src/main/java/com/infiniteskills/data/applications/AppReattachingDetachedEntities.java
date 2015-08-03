package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppReattachingDetachedEntities {

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

            // Detach all entities within the persistence context
			//em.clear();
            // Detach a single entity within the persistence context
            em.detach(bank);
            System.out.println(em.contains(bank));

            bank.setName("Something else");
            // Hibernate will act as persistence provider. When we invoke the merge operator on the entity manager
            // it goes back to Hibernate and invokes Hibernate's merge operation. By default, it tries to look in
            // the persistence context to determine if it already within the persistence context. If it isn't then
            // it's going to query the database to retrieve that entity. If it finds that entity then it is going
            // to overwrite any changes such as the change to the setName field. It is going to take that change
            // and overwrite it on the entity just pulled from the database. If it does not find the entity in
            // the database then it is going to invoke a persist operation it is going to treat it like a new
            // entity and make it transient and we are going to fire off an INSERT statement to the database.
            // Merge back an entity of type bank that is now in the persistent state.
			Bank bank2 = em.merge(bank);

            // Don't set a field on the detached bank entity!! It will not get persisted to the database.
            // bank.setName("Something else 2");

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