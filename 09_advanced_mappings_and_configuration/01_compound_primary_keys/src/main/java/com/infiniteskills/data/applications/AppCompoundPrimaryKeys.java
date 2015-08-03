package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Currency;
import com.infiniteskills.data.entities.ids.CurrencyId;
import com.infiniteskills.data.utilities.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppCompoundPrimaryKeys {

	public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session1 = null;
        Session session2 = null;
        org.hibernate.Transaction tx1 = null;
        org.hibernate.Transaction tx2 = null;

        try{
            // Create an SessionFactory
            sessionFactory = HibernateUtil.getSessionFactory();
            // Create a session
            session1 = sessionFactory.openSession();
            tx1 = session1.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United States");
            currency.setName("Dollar");
            currency.setSymbol("$");

            session1.persist(currency);
            // Cause the persistence manager to flush all of the changes to the database and it will synchronize
            // our entity model and our database.
            tx1.commit();

            session2 = sessionFactory.openSession();
            tx2 = session2.beginTransaction();

            // CurrencyID class is used here. We need to specify an id for the Currency class so we can retrieve
            // the entity. Here is where we create an instance of CurrencyId.
            Currency dbCurrency = (Currency) session2.get(Currency.class, new CurrencyId("Dollar", "United States"));
            System.out.println(dbCurrency.getName());
            tx2.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx1.rollback();
            if (tx2 != null)
                tx2.rollback();
        }finally {
            session1.close();
            sessionFactory.close();
        }
	}
}
