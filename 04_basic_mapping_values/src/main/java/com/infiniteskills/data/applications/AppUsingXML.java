package com.infiniteskills.data.applications;

import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;

public class AppUsingXML {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.close();
    }
}