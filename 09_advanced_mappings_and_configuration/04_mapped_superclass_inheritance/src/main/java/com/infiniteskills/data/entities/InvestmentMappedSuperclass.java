package com.infiniteskills.data.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

// @MappedSuperclass annotation will cause all the fields within a parent class to be recognized by the persistence
// provider Hibernate. When persistence operations are performed they will be taken into consideration and be
// persisted to the database. We make the class abstract to prevent it from being instantiated.

// There are some limitations to this approach. The mapped superclass is great when we want to share some state between
// our different entities so they have the same fields and we want to reuse those field throughout different entities.
// One of the problems is this is much like an abstract class. We cannot create an instance of an abstract class.
// The mapped superclass is similar in that you cannot create an entity from a mapped superclass. So the Investment
// class cannot be used as the target of one of our associations. So you can't have a list of investments.
// Investment will never have its own persistence lifecycle. So whenever you are using the mapped superclass
// annotation you need to ask two questions. First, will you ever need to query across the class hierarchy. So would
// need a bond and a stock be pulled back in the same query.  If the answer is yes, the mapped superclass is not for
// you. Second, will you ever need to use just the type of the parent class as an abstract type for our concrete
// classes. Would you ever refer to the investment as opposed to the stock and the bond? Maybe you'll have a list
// of investments.  If you have that need within the application then you need to steer away from the mapped
// class.
@MappedSuperclass
public abstract class InvestmentMappedSuperclass {

	@Column(name = "NAME")
	protected String name;

	@Column(name = "ISSUER")
	protected String issuer;

	@Column(name = "PURCHASE_DATE")
	protected Date purchaseDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}