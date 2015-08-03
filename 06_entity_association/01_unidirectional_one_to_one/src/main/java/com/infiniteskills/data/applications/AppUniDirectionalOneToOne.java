package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.User;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppUniDirectionalOneToOne {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			User user = new User();
			user.setFirstName("Kevin");
			user.setLastName("Bowersox");
			user.setAge(20);
			user.setBirthDate(new Date());
			user.setCreatedBy("Kevin Bowersox");
			user.setCreatedDate(new Date());
			user.setEmailAddress("kevin.bowersox@navy.mil");
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy("Kevin Bowersox");

			Credential credential = new Credential();
            credential.setPassword("kevinspassword");
            credential.setUsername("kmb385");

            credential.setUser(user);
			/** Only persist the credential. Because we have @OneToOne(cascade=CascadeType.ALL) in CredentialHibernateAPI class
			 *  both credentialUniDirectionalOneToOne and userHibernateAPI will both be persisted. */
			session.save(credential);
			transaction.commit();
			
			User dbUser = (User)
					session.get(User.class, user.getUserId());
			System.out.println(dbUser.getFirstName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
}
