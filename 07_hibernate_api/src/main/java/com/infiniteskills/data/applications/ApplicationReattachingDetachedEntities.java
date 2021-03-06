package com.infiniteskills.data.applications;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.infiniteskills.data.entities.*;
import com.infiniteskills.data.utilities.HibernateUtil;
import org.hibernate.Session;

public class ApplicationReattachingDetachedEntities {

	public static void main(String[] args) {
		try {
            // Open Persistence Context 1
			Session session = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			// Put the entity in the persistence context
			Bank bank = (Bank) session.get(Bank.class, 1L);
			// Persist the entity to the database
            transaction.commit();
            // Close Persistent Context 1. Our bank entity is detached. It is not attached to any persistence context.
			session.close();

            // Open Persistence Context 2
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction2 = session2.beginTransaction();

            // Bank entity is not contained within the second persistence context.
			System.out.println(session2.contains(bank));
            // Update will cause bank entity to be reattached to the persistence context causing it to go from a
            // a detached state to a persistent state.  Once an entity is reattached Hibernate will think its dirty
            // (entity has a different state or some of the fields have been changed).An update statement to the
            // database which  will cause the database to be synched to the new state of the entity.
			session2.update(bank);
            // Set one of the fields on the bank entity after we have reattached it.
			bank.setName("Test Bank");
			System.out.println("Update Method Invoked");
            // Bank entity is now contained within the second persistence context.
			System.out.println(session2.contains(bank));

            // Persist the entity to the database
			transaction2.commit();
			session2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernateUtil.getSessionFactory().close();
		}
	}

	private static Bank createBank() {
		Bank bank = new Bank();
		bank.setName("First United Federal");
		bank.setAddressLine1("103 Washington Plaza");
		bank.setAddressLine2("Suite 332");
		bank.setAddressType("PRIMARY");
		bank.setCity("New York");
		bank.setCreatedBy("Kevin Bowersox");
		bank.setCreatedDate(new Date());
		bank.setInternational(false);
		bank.setLastUpdatedBy("Kevin Bowersox");
		bank.setLastUpdatedDate(new Date());
		bank.setState("NY");
		bank.setZipCode("10000");
		return bank;
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
		address.setAddressLine2("102 Address Line");
		address.setCity("New York");
		address.setState("PA");
		address.setZipCode("10000");
		address.setAddressType("PRIMARY");
		return address;
	}

	private static Transaction createNewBeltPurchase(Account account) {
		Transaction beltPurchase = new Transaction();
		beltPurchase.setAccount(account);
		beltPurchase.setTitle("Dress Belt");
		beltPurchase.setAmount(new BigDecimal("50.00"));
		beltPurchase.setClosingBalance(new BigDecimal("0.00"));
		beltPurchase.setCreatedBy("Kevin Bowersox");
		beltPurchase.setCreatedDate(new Date());
		beltPurchase.setInitialBalance(new BigDecimal("0.00"));
		beltPurchase.setLastUpdatedBy("Kevin Bowersox");
		beltPurchase.setLastUpdatedDate(new Date());
		beltPurchase.setNotes("New Dress Belt");
		beltPurchase.setTransactionType("Debit");
		return beltPurchase;
	}

	private static Transaction createShoePurchase(Account account) {
		Transaction shoePurchase = new Transaction();
		shoePurchase.setAccount(account);
		shoePurchase.setTitle("Work Shoes");
		shoePurchase.setAmount(new BigDecimal("100.00"));
		shoePurchase.setClosingBalance(new BigDecimal("0.00"));
		shoePurchase.setCreatedBy("Kevin Bowersox");
		shoePurchase.setCreatedDate(new Date());
		shoePurchase.setInitialBalance(new BigDecimal("0.00"));
		shoePurchase.setLastUpdatedBy("Kevin Bowersox");
		shoePurchase.setLastUpdatedDate(new Date());
		shoePurchase.setNotes("Nice Pair of Shoes");
		shoePurchase.setTransactionType("Debit");
		return shoePurchase;
	}

	private static Account createNewAccount() {
		Account account = new Account();
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
