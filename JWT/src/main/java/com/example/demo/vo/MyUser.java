package com.example.demo.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name ="user_acct")
@Entity
public class MyUser implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423091666255245230L;

	@Id
	@GeneratedValue
    private Integer id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column
	private String password;
	
	@Column(name = "user_role")
	private String userRole;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	public MyUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	public MyUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	
    
}
