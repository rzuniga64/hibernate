package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "ACCOUNT")
public class AccountUniDirectionalManyToMany {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;

    /** When we model a many to many relationship we have to pick which side will be the owning side.  It can be either
     *  side. Let's choose this entity, AccountHibernateAPI.
     *
     * @ManyToMany is a JPA annotation.
     * @JoinTable is USER_ACCOUNT. join column is ACCOUNT_ID within AccountHibernateAPI entity. Inverse join column is USER_ID from
     * UserHibernateAPI entity. */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ACCOUNT", joinColumns=@JoinColumn(name="ACCOUNT_ID"), 
		inverseJoinColumns=@JoinColumn(name="USER_ID"))
	private Set<User> users = new HashSet<User>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	List<TransactionUniDirectionalManyToMany> transactions = new ArrayList<TransactionUniDirectionalManyToMany>();

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

	public List<TransactionUniDirectionalManyToMany> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionUniDirectionalManyToMany> transactions) {
		this.transactions = transactions;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
}
