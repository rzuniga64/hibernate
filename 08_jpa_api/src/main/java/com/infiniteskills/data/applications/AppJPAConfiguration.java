package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class AppJPAConfiguration {

	public static void main(String[] args) {

        // Create an EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("infinite-finances");
		// Create an entity manager which is like a session
        EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx =  em.getTransaction();

		tx.begin();
		
		Bank bank = createBank();

        // Persist an new instance of a bank entity into the database
		em.persist(bank);em.close();
		emf.close();
	}

	private static Bank createBank() {
		Bank bank = new Bank();
		bank.setName("First United Federal");
		bank.setAddressLine1("103 Washington Plaza");
		bank.setAddressLine2("Suite 332");
		bank.setAddressType("PRIMARY");
		bank.setCity("New York");
		bank.setCreatedBy("Kevin Bowersox");
		bank.setCreatedDate(new Date());
		bank.setInternational(false);
		bank.setLastUpdatedBy("Kevin Bowersox");
		bank.setLastUpdatedDate(new Date());
		bank.setState("NY");
		bank.setZipCode("10000");
		return bank;
	}
}
