package com.socioffice.grabmenu;


import android.app.Application;

public class InitializeGrabMenu extends Application {

	private String city = "Mumbai";
	private String whatItem = "";
	private String whereItem = "";

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public void setWhatItem(String whatItem) {
		this.whatItem = whatItem;
	}

	public String getWhatItem() {
		return whatItem;
	}

	public void setWhereItem(String whereItem) {
		this.whereItem = whereItem;
	}

	public String getWhereItem() {
		return whereItem;
	}
}
