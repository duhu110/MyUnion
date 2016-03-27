package com.qhduhu.myunion.entity;

public class TXLEntity {
    public String txl_name;  
    public String txl_dept;  
    public String txl_tel;  
    public String txl_mail;
    private String sortLetters;
		
    public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public TXLEntity() {
		// TODO Auto-generated constructor stub
	}
    public TXLEntity(String txl_name,String txl_tel,  String txl_dept, String txl_mail) {
		this.txl_name = txl_name;
		this.txl_dept = txl_dept;
		this.txl_tel = txl_tel;
		this.txl_mail = txl_mail;
	}
	public String getTxl_name() {
		return txl_name;
	}
	public void setTxl_name(String txl_name) {
		this.txl_name = txl_name;
	}
	public String getTxl_dept() {
		return txl_dept;
	}
	public void setTxl_dept(String txl_dept) {
		this.txl_dept = txl_dept;
	}
	public String getTxl_tel() {
		return txl_tel;
	}
	public void setTxl_tel(String txl_tel) {
		this.txl_tel = txl_tel;
	}
	public String getTxl_mail() {
		return txl_mail;
	}
	public void setTxl_mail(String txl_mail) {
		this.txl_mail = txl_mail;
	}  
    
    
    
}
