package com.cognizant.UserManagement.Entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.UserManagement.Model.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "USER")
@Table(name = "User", schema = "User")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private Long phoneNumber;

	@Column(name = "SECRET_QUESTION")
    private String secretQuestion;
	
	@Column(name = "SECRET_ANSWER")
	private String sercetAnswer;
	
	


	public User(Long id, String username, String fullName, String password, String role, String email, Long phoneNumber,
			String secretQuestion, String sercetAnswer) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.secretQuestion = secretQuestion;
		this.sercetAnswer = sercetAnswer;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public String getSecretQuestion() {
		return secretQuestion;
	}



	public void setSercreteQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	



	public String getSercetAnswer() {
		return sercetAnswer;
	}

	public void setSercetAnswer(String sercetAnswer) {
		this.sercetAnswer = sercetAnswer;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullName=" + fullName + ", password=" + password
				+ ", role=" + role + ", email=" + email + ", phoneNumber=" + phoneNumber + ", secretQuestion="
				+ secretQuestion + ", sercetAnswer=" + sercetAnswer + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Authority> authority = new HashSet<>();

		authority.add(new Authority(getRole()));

		return authority;

	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
