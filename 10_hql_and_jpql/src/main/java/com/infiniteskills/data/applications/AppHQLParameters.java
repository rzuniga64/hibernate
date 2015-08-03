package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AppHQLParameters {

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

            // User input can be used to determine the query that will run against the database. If done incorrectly,
            // such as when concatenating strings to form a query, the application can be opened up to different
            // security vulnerabilities.  Applications that concatenate strings along with user input for queries are
            // creating the risk for SQL injection. This can allow an attacker to access more of the database than they
            // should or they can execute commands against the database. HQL and JQPL are vulnerable to SQL injection.
            // But both languages allow parameters that allows us to safely supply input to queries. We are going to
            // use positional parameters to safely execute queries.
            /*Query query = session.createQuery("select t from TransactionEnumeratedTypes t "
                    + "where t.amount > ? and t.transactionType = 'Withdrawl'");*/

            // A better method is named parameters.
            Query query = session.createQuery("select t from Transaction t "
                    + "where t.amount > :amount and t.transactionType = 'Withdrawl'");

            System.out.println("Please specify an amount:");

            // if using a temporal type use setDate() or setTime() methods.
            // Positional parameters in HQL are 0 based.
            //query.setParameter("amount", new BigDecimal(scanner.next()));
            query.setParameter("amount", new BigDecimal(scanner.next()));

            List<Transaction> transactions = query.list();

            for (Transaction transaction : transactions) {
                System.out.println(transaction.getTitle());
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
