package com.tingler.challenge.util;

public class ContactItem {
	private String name;
	private String mobile;
	private String level;
	//int indexCheckbox;
	//private int flag;
    private boolean checkboxstatus;
	public ContactItem(){
		
	}
	

	public boolean isCheckboxstatus() {
		return checkboxstatus;
	}


	public void setCheckboxstatus(boolean checkboxstatus) {
		this.checkboxstatus = checkboxstatus;
	}


	/*public int getIndexCheckbox() {
		return indexCheckbox;
	}


	public void setIndexCheckbox(int indexCheckbox) {
		this.indexCheckbox = indexCheckbox;
	}
*/

	public ContactItem(String name, String mobile, String level,int index) {
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
