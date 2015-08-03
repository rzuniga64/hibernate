package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Account;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class AppHQLNamedQueries {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            //Query query = session.createQuery("select distinct t.account from TransactionEnumeratedTypes t"
            //        + " where t.amount > 500 and lower(t.transactionType) = 'deposit'");

            Query query = session.getNamedQuery("Account.largeDeposits");

            List<Account> transactions = query.list();

            for (Account account : transactions) {
                System.out.println(account.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }
}
