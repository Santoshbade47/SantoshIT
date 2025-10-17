package com.santu;

public class Course 
{
	int coursrId;
	String courseName;
	double fee;
	int discount;
	String img_url;
	String video_url;
	String about_course;
	String category;
	public Course() {
		super();
	}
	public Course(int coursrId, String courseName, double fee, int discount, String img_url) {
		super();
		this.coursrId = coursrId;
		this.courseName = courseName;
		this.fee = fee;
		this.discount = discount;
		this.img_url = img_url;
	}
	public int getCoursrId() {
		return coursrId;
	}
	public void setCoursrId(int coursrId) {
		this.coursrId = coursrId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getAbout_course() {
		return about_course;
	}
	public void setAbout_course(String about_course) {
		this.about_course = about_course;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
