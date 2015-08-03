package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class AppJPQLParameters {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
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
					"from Transaction t"
							+ " where (t.amount between ?1 and ?2) and t.title like '%s'"
							+ " order by t.title", Transaction.class);

            System.out.println("Please provide the first amount:");
            // positional parameters in JPQL are 1 based.
            query.setParameter(1, new BigDecimal(scanner.next()));

            System.out.println("Please provide the second amount:");
            query.setParameter(2, new BigDecimal(scanner.next()));

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
