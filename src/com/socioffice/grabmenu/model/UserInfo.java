package com.socioffice.grabmenu.model;

public class UserInfo {
	
	private int zipCode;
	
	private boolean news; 
	private boolean promo;
	private boolean status;
	
	private String email;
	private String password;
	private String gender;
	
	public UserInfo(String email, String password) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isNews() {
		return news;
	}

	public void setNews(boolean news) {
		this.news = news;
	}

	public boolean isPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
