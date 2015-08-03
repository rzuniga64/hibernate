package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Scanner;


public class AppHibernatePaging {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SessionFactory sessionFactory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;

        int pageNumber = 2;
        int pageSize = 4;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Transaction.class);
			criteria.addOrder(Order.desc("title"));
            criteria.setFirstResult((pageNumber -1) * pageSize);
            criteria.setMaxResults(pageSize);

			List<Transaction> transactions = criteria.list();

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
