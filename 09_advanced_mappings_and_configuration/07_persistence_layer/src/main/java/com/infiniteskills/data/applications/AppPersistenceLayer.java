package com.infiniteskills.data.applications;

import com.infiniteskills.data.dao.UserHibernateDao;
import com.infiniteskills.data.dao.interfaces.UserDao;
import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.User;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.Date;

public class AppPersistenceLayer {

	public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try{
            // Create an SessionFactory
            sessionFactory = HibernateUtil.getSessionFactory();
            // Create a session, open Persistence Context 1
            session = sessionFactory.openSession();

            UserDao dao = new UserHibernateDao();
			dao.setSession(session);

			tx = session.beginTransaction();

			User user = createUser();

			dao.save(user);

            // Persist the entities to the database
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

	private static User createUser() {
		User user = new User();
		Address address = createAddress();
		user.setAddresses(Arrays.asList(new Address[]{createAddress()}));
		user.setBirthDate(new Date());
		user.setCreatedBy("Kevin Bowersox");
		user.setCreatedDate(new Date());
		user.setCredential(createCredential(user));
		user.setEmailAddress("test@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setLastUpdatedBy("system");
		user.setLastUpdatedDate(new Date());
		return user;
	}

	private static Credential createCredential(User user) {
		Credential credential = new Credential();
		credential.setUser(user);
		credential.setUsername("test_username");
		credential.setPassword("test_password");
		return credential;
	}

	private static Address createAddress() {
		Address address = new Address();
		address.setAddressLine1("101 AddressHibernateAPI Line");
		address.setAddressLine2("102 AddressHibernateAPI Line");
		address.setCity("New York");
		address.setState("PA");
		address.setZipCode("10000");
		address.setAddressType("PRIMARY");
		return address;
	}
}
