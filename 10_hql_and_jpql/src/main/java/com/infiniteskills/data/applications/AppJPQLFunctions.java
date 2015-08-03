package com.infiniteskills.data.applications;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;


public class AppJPQLFunctions {

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

            // How do we iterate through the result set?  What is that result set going to look like? There is a name
            // for this and its called a projection. Projects allow us to specify the exact properties that we would
            // like returned in our result. They get returned within an object array. And each one of our selected
            // fields will correspond with an index in that array. We are going to have a list of Object arrays. To
            // access the different fields returned we access our array the first index would be zero because arrays are
            // zero indexed. That would correspond with the account name. The index in the array will correspond with
            // the bank name concatenated with the state.
            Query query = em.createQuery("select distinct t.account.name, "
                    + "concat(concat(t.account.bank.name, ' '),t.account.bank.address.state)"
                    + " from Transaction t"
                    + " where t.amount > 500 and t.transactionType = 'Deposit'");

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
