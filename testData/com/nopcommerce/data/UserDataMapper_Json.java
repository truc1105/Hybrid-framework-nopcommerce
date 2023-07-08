package com.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserDataMapper_Json {
	
	@JsonProperty("login")
	private Login login;
	
	static class Login{
		@JsonProperty("username")
		private String username;
		
		@JsonProperty("password")
		private String password;
	}
	
	public String getLoginUsername() {
		return login.username;
	}
	
	public String getLoginPassword() {
		return login.password;
	}
	
	@JsonProperty("firstname")
	private String firstName;
	
	@JsonProperty("lastname")
	private String lastName;
	
	@JsonProperty("email")
	private String emailAddress;
	
	@JsonProperty("password")
	private String password;
	
	public static UserDataMapper_Json getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "/src/UserData.json"), UserDataMapper_Json.class);
		}catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getPassword() {
		return password;
	}
}
