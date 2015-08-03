package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.util.List;


public class AppHibernateSimpleSelections {

	public static void main(String[] args) {		
		SessionFactory sessionFactory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			//Criteria criteria = session.createCriteria(TransactionHibernateAPI.class);
			//criteria.addOrder(Order.desc("title"));

			// List<TransactionHibernateAPI> transactions = criteria.list();

			List<Transaction> transactions = session
					.createCriteria(Transaction.class)
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
