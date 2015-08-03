package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.UserMapACollectionOfCompositeValues;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppUserMapACollectionOfCompositeValues {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();

            UserMapACollectionOfCompositeValues user = new UserMapACollectionOfCompositeValues();
            setUserFields(user);

            Address address = new Address();
            Address address2 = new Address();
            setAddressFields(address);
            setAddressFields2(address2);
            user.getAddress().add(address);
            user.getAddress().add(address2);

            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void setUserFields(UserMapACollectionOfCompositeValues user) {
        user.setAge(22);
        user.setBirthDate(new Date());
        user.setCreatedBy("kmb");
        user.setCreatedDate(new Date());
        user.setEmailAddress("kmb385");
        user.setFirstName("Kevin");
        user.setLastName("bowersox");
        user.setLastUpdatedBy("kevin");
        user.setLastUpdatedDate(new Date());
    }

    private static void setAddressFields(Address address) {
        address.setAddressLine1("Line 1");
        address.setAddressLine2("Line 2");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("12345");
    }

    private static void setAddressFields2(Address address) {
        address.setAddressLine1("Line 1");
        address.setAddressLine2("Line 2");
        address.setCity("Corning");
        address.setState("NY");
        address.setZipCode("12345");
    }
}