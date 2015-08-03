package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.BondMappedSuperclass;
import com.infiniteskills.data.entities.StockMappedSuperclass;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class AppMappedSuperclassInheritence {

	public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try{
            // Create an SessionFactory
            sessionFactory = HibernateUtil.getSessionFactory();
            // Create a session, open Persistence Context 1
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

         	StockMappedSuperclass stock = createStock();
            // make persistent, will cascade the transactions associated with the account
            session.save(stock);

            BondMappedSuperclass bond = createBond();
            session.save(bond);

            // Persist the entity to the database
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            // Close Persistent Context 1
            session.close();
            sessionFactory.close();
        }
	}

    private static BondMappedSuperclass createBond() {
		BondMappedSuperclass bond = new BondMappedSuperclass();
        bond.setInterestRate(new BigDecimal("123.22"));
        bond.setIssuer("JP Morgan Chase");
        bond.setMaturityDate(new Date());
        bond.setPurchaseDate(new Date());
        bond.setName("Long Term Bond Purchases");
        bond.setValue(new BigDecimal("10.22"));
        return bond;
    }

    private static StockMappedSuperclass createStock(){
		StockMappedSuperclass stock = new StockMappedSuperclass();
        stock.setIssuer("Allen Edmonds");
        stock.setName("Private American Stock Purchases");
        stock.setPurchaseDate(new Date());
        stock.setQuantity(new BigDecimal("1922"));
        stock.setSharePrice(new BigDecimal("100.00"));
        return stock;
    }
}
