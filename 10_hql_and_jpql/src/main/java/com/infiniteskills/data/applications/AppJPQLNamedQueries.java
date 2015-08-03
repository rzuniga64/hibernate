package com.infiniteskills.data.applications;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AppJPQLNamedQueries {

    /** in this example we learn
	 * 1. How to use concat function.
     * 2. How to walk through multiple associations.
     * 3. How to use a projection.
     *
     * To find a list of JPQL function is the Java EE tutorial (JEETT Section 39.5.5.12)
     * For HQL got to docs.jboss.org/hibernate/orm/5.0/manual/en-US/html/ch16.html#Queryhql-expressions
     */
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

            /*Query query = em.createQuery("select distinct t.account.name, "
                    + "concat(concat(t.account.bank.name, ' '),t.account.bank.addressHibernateAPI.state)"
                    + " from TransactionEnumeratedTypes t"
                    + " where t.amount > 500 and t.transactionType = 'Deposit'");*/

            Query query = em.createNamedQuery("Account.byWithdrawlAmount");
            // Set the amount parameter in the query
            query.setParameter("amount", new BigDecimal("99"));

			List<Object[]> accounts = query.getResultList();

			for (Object[] account : accounts) {
                System.out.println(account[0]);
                System.out.println(account[1]);
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
