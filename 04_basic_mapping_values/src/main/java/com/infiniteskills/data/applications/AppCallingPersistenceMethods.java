package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.User;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.Date;

public class AppCallingPersistenceMethods {
    private static Date getMyBirthday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1984);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DATE, 19);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            User user = new User();
            user.setBirthDate(getMyBirthday());
            user.setCreatedDate(new Date());
            user.setCreatedBy("Kevin");
            user.setEmailAddress("kmb385@yahoo.com");
            user.setFirstName("Kevin");
            user.setLastName("Powersox");
            user.setLastUpdatedDate(new Date());
            user.setLastUpdatedBy("Kevin");

            session.save(user);
            session.getTransaction().commit();
            // Refresh the entity against the database in order for the calculation to take effect.
            // We get a refreshed Uesr entity with the calculation in place.
            session.refresh(user);
            System.out.println(user.getAge());

            session.beginTransaction(); // another way to start a transaction
            User dbUser = (User) session.get(User.class, user.getUserId());
            dbUser.setFirstName("Joe");
            session.update(dbUser);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}