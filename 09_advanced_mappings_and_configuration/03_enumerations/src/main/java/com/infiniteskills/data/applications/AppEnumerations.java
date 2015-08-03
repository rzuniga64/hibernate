package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.AccountEnumeratedTypes;
import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Date;

public class AppEnumerations {

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

           	AccountEnumeratedTypes account = createNewAccount();
            account.setAccountType(AccountType.SAVINGS);

            // make persistent, will cascade the transactions associated with the account
            session.save(account);
            // Persist the entity to the database
            tx.commit();

            AccountEnumeratedTypes dbAccount = (AccountEnumeratedTypes) session.
                    get(AccountEnumeratedTypes.class, account.getAccountId());
            System.out.println(dbAccount.getName());
            System.out.println(dbAccount.getAccountType());
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            // Close Persistent Context 1
            session.close();
            sessionFactory.close();
        }
	}

	private static AccountEnumeratedTypes createNewAccount() {
		AccountEnumeratedTypes accountHibernateAPI = new AccountEnumeratedTypes();
		accountHibernateAPI.setCloseDate(new Date());
		accountHibernateAPI.setOpenDate(new Date());
		accountHibernateAPI.setCreatedBy("Kevin Bowersox");
		accountHibernateAPI.setInitialBalance(new BigDecimal("50.00"));
		accountHibernateAPI.setName("Savings AccountHibernateAPI");
		accountHibernateAPI.setCurrentBalance(new BigDecimal("100.00"));
		accountHibernateAPI.setLastUpdatedBy("Kevin Bowersox");
		accountHibernateAPI.setLastUpdatedDate(new Date());
		accountHibernateAPI.setCreatedDate(new Date());
		return accountHibernateAPI;
	}
}
