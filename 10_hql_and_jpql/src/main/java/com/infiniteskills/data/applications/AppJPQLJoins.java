package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Account;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;


public class AppJPQLJoins {

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

            // This form of the join is known as the explicit form of the join.
            // This is the preferred method of doing a query.
            TypedQuery<Account> query = em.createQuery(
                    "select distinct a from Transaction t"
                            + " join t.account a "
                            + "where t.amount > 500 and t.transactionType = 'Deposit'", Account.class);

			List<Account> accounts = query.getResultList();

			for (Account account : accounts)
				System.out.println(account.getName());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			factory.close();
		}
	}
}
