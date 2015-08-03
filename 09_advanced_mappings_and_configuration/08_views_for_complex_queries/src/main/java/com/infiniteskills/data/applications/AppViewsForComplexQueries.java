package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.UserCredentialView;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppViewsForComplexQueries {

	public static void main(String[] args) {		
		SessionFactory sessionFactory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			UserCredentialView view = (UserCredentialView) session.get(UserCredentialView.class, 1L);
			System.out.println(view.getFirstName());
			System.out.println(view.getLastName());
			
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
