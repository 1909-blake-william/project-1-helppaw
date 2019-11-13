package com.revature.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {
	private int ersUserId;
	private String username;
	private String password;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	
	

	// allow jackson to convert passwords in json to the java object
	// but don't allow it to convert the password in object to json
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	
	
	private int UserRoleId;



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(int ersUserId, String username, String password, String userFirstName, String userLastName,
			String userEmail, int userRoleId) {
		super();
		this.ersUserId = ersUserId;
		this.username = username;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		UserRoleId = userRoleId;
	}



	public int getErsUserId() {
		return ersUserId;
	}



	public void setErsUserId(int ersUserId) {
		this.ersUserId = ersUserId;
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



	public String getUserFirstName() {
		return userFirstName;
	}



	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}



	public String getUserLastName() {
		return userLastName;
	}



	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public int getUserRoleId() {
		return UserRoleId;
	}



	public void setUserRoleId(int userRoleId) {
		UserRoleId = userRoleId;
	}



	@Override
	public String toString() {
		return "User [ersUserId=" + ersUserId + ", username=" + username + ", password=" + password + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail + ", UserRoleId="
				+ UserRoleId + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + UserRoleId;
		result = prime * result + ersUserId;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (UserRoleId != other.UserRoleId)
			return false;
		if (ersUserId != other.ersUserId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}