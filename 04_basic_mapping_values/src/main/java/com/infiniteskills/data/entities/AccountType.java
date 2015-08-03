package com.infiniteskills.data.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 	The class and fields are annotated with these annotations that provide with the
 * 	information needed to instruct SQL to persist this object within a database.
 * 	The annotations on this class such as the @Entity, @Table and @Column can also be
 * 	used to perform other types of operations such as SELECT, UPDATE or DELETE.
 *
 * 	Another best practice when it comes to entities is to follow Javabean naming
 * 	conventions. So when we create those POJO we will have these private fields
 * 	and we will create the accessors for these fields. The accessors reflect the
 * 	field name prefixed with set or get and we camelcase the fieldname.
 */

@Entity
@Table(name = "account_type")
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_TYPE_ID")
	private Long accountTypeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_UPDATED_DATE")
	private Date LastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	public Long getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
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

}
