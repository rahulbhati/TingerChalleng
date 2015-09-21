package com.tingler.challenge.util;

public class ContactItem {
	private String name;
	private String mobile;
	private String level;
	//private int flag;

	public ContactItem(String name, String mobile, String level) {
		this.name = name;
		this.mobile = mobile;
		this.level = level;
	
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getLevel() {
		return level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
