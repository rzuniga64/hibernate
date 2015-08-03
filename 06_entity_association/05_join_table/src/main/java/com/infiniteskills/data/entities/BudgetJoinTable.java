package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BUDGET")
public class BudgetJoinTable {

	@Id
	@GeneratedValue
	@Column(name = "BUDGET_ID")
	private Long budgetId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "GOAL_AMOUNT")
	private BigDecimal goalAmount;

	@Column(name = "PERIOD")
	private String period;

	@OneToMany(cascade=CascadeType.ALL)
	/** @JoinTable is a JPA annotation
     *  Start with join column of the owning entity(Budget). You can choose which side you want to place the
     *  @JoinTable annotation. Then specify the inverse join column of TransactionHibernateAPI entity. */
	@JoinTable(name="BUDGET_TRANSACTION", joinColumns=@JoinColumn(name="BUDGET_ID"), 
		inverseJoinColumns=@JoinColumn(name="TRANSACTION_ID"))
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(BigDecimal goalAmount) {
		this.goalAmount = goalAmount;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
