package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	/** @OneToMany is a JPA annotation. Cascade means that when we persist the credential we also want to persist the
	 *  transactions that are stored within this field. This will cause both entities to be persisted at the same time.*/
	@OneToMany(cascade=CascadeType.ALL)
    /** AccountHibernateAPI is our target entity. Transactions is the source because it holds the foreign key in the TransactionHibernateAPI
     *  table. @OneToMany unidirectional annotation. There is no inverse association from transaction to account.
     *  So we would not have an AccountHibernateAPI field on TransactionHibernateAPI in a unidirectional relationship. We need someplace
     *  to specify the join column. In this case the join column would go on the TransactionHibernateAPI field. Point to the
     *  ACCOUNT_ID on the transaction table. Hibernate is smart enough to know that we need to inspect the transaction
     *  entity to find out what table we will be joining to and then determine that need to use the ACCOUNT_ID
     *  column within our join clause.
     *
     *  Not Well Documented:
     *  Because we are using a unidirectional relationship we have not mapped the inverse side of the relationship.
     *  That causes the foreign key column ACCOUNT_ID not to be populated. Hibernate's constraints kick in an throw
     *  an error. We add the nullable attribute.  By default when you map a one-to-many unidirectional association
     *  the nullable attribute on the join column is set to true. In this case, we don't want that to occur. */
	@JoinColumn(name="ACCOUNT_ID", nullable=false)
	List<Transaction> transactions = new ArrayList<Transaction>();
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "INITIAL_BALANCE")
	private BigDecimal initialBalance;

	@Column(name = "CURRENT_BALANCE")
	private BigDecimal currentBalance;

	@Column(name = "OPEN_DATE")
	private Date openDate;

	@Column(name = "CLOSE_DATE")
	private Date closeDate;

	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	
}
