package com.infiniteskills.data.applications;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.infiniteskills.data.entities.*;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;

public class ApplicationFlushPersistenceContext {

	public static void main(String[] args) {
		// Open Persistence Context 1
		Session session = HibernateUtil.getSessionFactory().openSession();
        // When we want to write changes to the database we need a connection. A transaction houses that
        // connection. With a transaction we want to perform all the work as one atomic unit.  If one of the operations
        // fails we need to rollback everything. That's the whole point of a transaction. So every time we are working
        // with a session we are working with a transaction.
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			// Put the entity in the persistence context which keeps track of all changes to all entities.
			Bank bank = (Bank) session.get(Bank.class, 1L);
			bank.setName("Something Different");
			System.out.println("Calling Flush");

            // When flush is called we want Hibernate to take all the changes within our persistence context and synch
            // them with the database.
			session.flush();

            // Make a second change to the entity after calling flush.
			bank.setAddressLine1("Another Address Line");
			System.out.println("Calling commit");
			// Persist the entity to the database. When we work with a transaction one of the ways we cause
            // all of the changes to be made on the database is to call the commit method on the transaction.
            // This commit is forcing a flush on the session. There are three times when a flush will be called on a
            // session:
            // 1. Call session.flush()
            // 2. When a commit is called on a transaction.
            // 3. Sometimes when we run a SELECT operation a flush will occur before it.
			transaction.commit();
		} catch (Exception e) {
            // Make sure that if that there are any exceptions then rollback the transaction.
			transaction.rollback();
			e.printStackTrace();
		}finally{
			// Close Persistence Context.
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
}
