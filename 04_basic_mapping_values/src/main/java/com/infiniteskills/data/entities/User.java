package com.infiniteskills.data.entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="finances_user")
public class User {

	@Id // denote our field that corresponds to the primary key within the table.
	/** Identities are how we uniquely identify entities in our domain model.
		Entities in Java are plain old Java objects. We have a few ways of
		comparing them. First is the '==' operator which compares two objects
		to see if they are the same in memory. Another way is using the
		equals() method which is more the idea of equivalence. These two
	 	objects don't need to be the same instance but are the values of
	 	these objects the same.  We'll use surrogate keys because they are
	 	the preferred method of keying tables when we are using hibernate*/
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // we are going to generate the key value using 'strategy'.

	/** When Hibernate needs to obtain the primary key for an entity it searches
	 * that table to find the associated record that stores the next value for the key.*/
	//@GeneratedValue(strategy=GenerationType.TABLE, generator = "user_table_generator")
	//@TableGenerator(name="user_table_generator",
	//      table="ifinances_keys", pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE")

    /** AUTO strategy has Hibernate pick the appropriate strategy based upon the database provided.
     *  For Oracle it would pick a sequence and for MySQL it would pick the IDENTITY generation type.
     *  AUTO is very compatible because we then don't need to change our GeneratedValue annotation if
     *  the database vendor is changed.
     */
    //@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long userId;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="BIRTH_DATE", nullable = false)
	private Date birthDate;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="CREATED_DATE", updatable = false)
	private Date createdDate;

	@Column(name="CREATED_BY", updatable = false)
	private String createdBy;

    /** Hibernate checks the different fields within the class and it applies defaults to them. So although we haven't
     *  annotated it with the @COLUMN annotation by default Hibernate tries to add a valid column to all of our  INSERTs
     *  UPDATEs or DELETEs. To ignore this field add the @Transient annotation.  When you do not want to include a field
     *  in Hibernates mapping metadata yet you want to put it within the entity class annotate it with @Transient so
     *  Hibernate doesn't try to treat that field as a column when it tries to create the different SQL statements.*/
    @Transient
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**Hibernate annotation!
     * Caution: When specifying native SQL we have the ability to tie ourself to the underlying database.
     * Important to use standard SQL when writing these formula annotations.
     *
     * When we run a SELECT statement for the UserHibernateAPI entity, when the columns are listed in the SQL statement, instead
     *  of just having the normal column name this calculation will be inserted into that SELECT clause. We will
     *  not see this calculation for inserts or updates. It's important to note that this is only after a SELECT has
     *  been executed against the database.*/
    @Formula("lower(datediff(curdate(), birth_date)/365)")
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /****************************************/
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

}
