package com.infiniteskills.data.entities;

import javax.persistence.*;

@Entity
@Table(name="CREDENTIAL")
public class Credential {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CREDENTIAL_ID")
	public Long credentialId;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	public User user;
	
	@Column(name="USERNAME")
	private String username;

	@Column(name="PASSWORD")
	private String password;

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
