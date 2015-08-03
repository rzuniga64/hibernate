package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.*;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppCompoundJoinColumns {

	public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try{
            // Create an SessionFactory
            sessionFactory = HibernateUtil.getSessionFactory();
            // Create a session
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United Kingdom");
            currency.setName("Pound");
            currency.setSymbol("Pound Sign");

			Market market = new Market();
            market.setMarketName("London Stock Exchange");
            market.setCurrency(currency);

            // persist the market and currency because we specified cascade type.
            session.persist(market);
            // Cause the persistence manager to flush all of the changes to the database and it will synchronize
            // our entity model and our database.
            tx.commit();

            Market dbMarket = (Market) session.get(Market.class, market.getMarketId());
            System.out.println(dbMarket.getCurrency().getName());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
	}
}
