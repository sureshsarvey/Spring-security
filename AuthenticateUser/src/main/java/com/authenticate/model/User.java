package com.authenticate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_info")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6658119351774536170L;
	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String username;
	
	@Column
	private String password;
	
	@Column(name ="user_role")
	private String userrole;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", userrole=" + userrole
				+ "]";
	}

	public User(String username, String password, String userrole) {
		super();
		this.username = username;
		this.password = password;
		this.userrole = userrole;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
