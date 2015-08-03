package com.infiniteskills.data.applications;

import com.infiniteskills.data.entities.AccountBiDirectionalManyToMany;
import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.User;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class AppBiDirectionalManyToMany {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();

			AccountBiDirectionalManyToMany account = createNewAccount();
            AccountBiDirectionalManyToMany account2 = createNewAccount();
			User user = createUser();
			User user2 = createUser();
			
			account.getUsers().add(user);
			account.getUsers().add(user2);
			user.getAccounts().add(account);
			user2.getAccounts().add(account);

            /** Establish relationship from Account to User */
			account2.getUsers().add(user);
			account2.getUsers().add(user2);
            /** Establish relationship from UserHibernateAPI to Account. If not done there will be errors! */
			user.getAccounts().add(account2);
			user2.getAccounts().add(account2);

			session.save(user);
			session.save(user2);
			
			transaction.commit();

            User dbUser = (User) session.
					get(User.class, user.getUserId());
			System.out.println(dbUser.getAccounts().iterator().next().getName());
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
		address.setAddressLine1("101 Address Line");
		address.setAddressLine2("102 Addreess Line");
		address.setCity("New York");
		address.setState("PA");
		address.setZipCode("10000");
		return address;
	}

	private static AccountBiDirectionalManyToMany createNewAccount() {
        AccountBiDirectionalManyToMany account = new AccountBiDirectionalManyToMany();
		account.setCloseDate(new Date());
		account.setOpenDate(new Date());
		account.setCreatedBy("Kevin Bowersox");
		account.setInitialBalance(new BigDecimal("50.00"));
		account.setName("Savings Account");
		account.setCurrentBalance(new BigDecimal("100.00"));
		account.setLastUpdatedBy("Kevin Bowersox");
		account.setLastUpdatedDate(new Date());
		account.setCreatedDate(new Date());
		return account;
	}
}
