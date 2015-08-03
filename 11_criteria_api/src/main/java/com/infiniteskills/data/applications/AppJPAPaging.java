package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AppJPAPaging {

	public static void main(String[] args) {

		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		int pageNumber = 1;
		int pageSize = 5;

		try {
			factory = Persistence.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			//select t from transaction t
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

			Root<Transaction> root = criteriaQuery.from(Transaction.class);
            // Select all of the fields on the transaction.
			criteriaQuery.select(root);
			
			TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
			query.setFirstResult((pageNumber - 1) * pageSize );
            query.setMaxResults(pageSize);

			List<Transaction> transactions = query.getResultList();

			for (Transaction t : transactions) {
				System.out.println(t.getTitle());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			factory.close();
		}
	}
}
