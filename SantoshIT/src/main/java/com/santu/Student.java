package com.santu;

public class Student {

	int studentId=0;
	String surName=null;
	String name=null;
	String gender=null;
	long phoneNo=0;
	String email=null;
	String doj=null; // doj=>date of join
	String acActivation=null;
	public Student() {
		super();
	}
	public Student(int studentId, String surName, String name, String gender, long phoneNo, String email, String doj,String acActivation) {
		super();
		this.studentId = studentId;
		this.surName = surName;
		this.name = name;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		this.doj = doj;
		this.acActivation=acActivation;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getAcActivation() {
		return acActivation;
	}
	public void setAcActivation(String acActivation) {
		this.acActivation = acActivation;
	}
}
