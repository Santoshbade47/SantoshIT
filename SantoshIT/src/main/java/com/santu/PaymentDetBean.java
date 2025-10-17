package com.santu;

public class PaymentDetBean 
{
	int stuId;
	int courseId;
	String utrNo;
	String paymentTime;
	public PaymentDetBean() {
		super();
	}
	public PaymentDetBean(int stuId, int courseId, String utrNo, String paymentTime) {
		super();
		this.stuId = stuId;
		this.courseId = courseId;
		this.utrNo = utrNo;
		this.paymentTime = paymentTime;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getUtrNo() {
		return utrNo;
	}
	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
}
