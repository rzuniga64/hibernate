package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.*;

import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class AppUniDirectionalManyToMany {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();
			
			AccountUniDirectionalManyToMany account = createNewAccount();
			AccountUniDirectionalManyToMany account2 = createNewAccount();
			User user = createUser();
			User user2 = createUser();

			account.getUsers().add(user);
			account.getUsers().add(user2);
			account2.getUsers().add(user);
			account2.getUsers().add(user2);
			
			session.save(account);
			session.save(account2);
			
			transaction.commit();
			
			AccountUniDirectionalManyToMany dbAccount = (AccountUniDirectionalManyToMany) session.
					get(AccountUniDirectionalManyToMany.class, account.getAccountId());
			System.out.println(dbAccount.getUsers().iterator().next().getEmailAddress());

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
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
		return address;
	}

	private static AccountUniDirectionalManyToMany createNewAccount() {
		AccountUniDirectionalManyToMany account = new AccountUniDirectionalManyToMany();
		account.setCloseDate(new Date());
		account.setOpenDate(new Date());
		account.setCreatedBy("Kevin Bowersox");
		account.setInitialBalance(new BigDecimal("50.00"));
		account.setName("Savings AccountHibernateAPI");
		account.setCurrentBalance(new BigDecimal("100.00"));
		account.setLastUpdatedBy("Kevin Bowersox");
		account.setLastUpdatedDate(new Date());
		account.setCreatedDate(new Date());
		return account;
	}
}
