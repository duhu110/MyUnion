package com.qhduhu.myunion.sortlist;

public class SortModel {

	private String sortLetters;  //显示数据拼音的首字母
	private String name;   //显示的数据
	private String tel;   //显示的数据
	private String dept;   //显示的数据
	private String mail;   //显示的数据
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
