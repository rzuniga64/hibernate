package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class AppJPARestrictions {

	public static void main(String[] args) {

		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			factory = Persistence.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			//select t from transaction t where amount <= 20.00 and transaction_type="Withdrawl"
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

			// A Root is a means of qualifying access to attributes. The root must be added to criteria query.
			// The root is saying when a query is executed and you want to access a property (title on transaction)
            // it can be done with root.get("title").
			Root<Transaction> root = criteriaQuery.from(Transaction.class);
            // Create a path to the different fields that we want to access on the transaction. We are going to use
			// those fields within our restrictions.
			Path<BigDecimal> amountPath = root.get("amount");
			Path<String> transactionTypePath = root.get("transactionType");

			criteriaQuery.select(root).where(cb.and(cb.le(amountPath, new BigDecimal("20.00")),
					cb.equal(transactionTypePath, "Withdrawl")));

			TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
			List<Transaction> transactions = query.getResultList();

			for (Transaction t : transactions)
				System.out.println(t.getTitle());

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
