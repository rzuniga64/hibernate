package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.List;


public class AppHibernateRestrictions {

	public static void main(String[] args) {		
		SessionFactory sessionFactory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criterion criterion1 = Restrictions.le("amount", new BigDecimal("20.00"));
			Criterion criterion2 = Restrictions.eq("transactionType", "Withdrawl");

			/*List<TransactionHibernateAPI> transactions = session
					.createCriteria(TransactionHibernateAPI.class).add(criterion1).add(criterion2)
					.addOrder(Order.desc("title")).list();*/

            List<Transaction> transactions = session
                    .createCriteria(Transaction.class).add(Restrictions.and(criterion1,criterion2))
                    .addOrder(Order.desc("title")).list();

			for (Transaction transaction: transactions)
				System.out.println(transaction.getTitle());
			
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
