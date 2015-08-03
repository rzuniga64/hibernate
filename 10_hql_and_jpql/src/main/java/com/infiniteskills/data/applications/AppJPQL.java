package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;

import javax.persistence.*;
import java.util.List;


public class AppJPQL {

	public static void main(String[] args) {

		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			factory = Persistence.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

            //Query query = em.createQuery("from TransactionEnumeratedTypes t order by t.title");

			TypedQuery<Transaction> query = em.createQuery(
					"from Transaction t order by t.title", Transaction.class);
			
			List<Transaction> transactions = query.getResultList();

			for (Transaction transaction : transactions) {
				System.out.println(transaction.getTitle());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			factory.close();
		}
	}
}
