package com.santu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoveCourse")
public class RemoveCourse extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement psmt1;
	PreparedStatement psmt2;
	PreparedStatement psmt3;
	@Override
	public void init() throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","santu","manager");
			System.out.println("init start");
			String query1="delete about_course where course_id=?";
			String query2="delete sb_stu_join_courses where COURSE_ID=?";
			String query3="delete sb_course where id=?";
			psmt1=con.prepareStatement(query1);
			psmt2=con.prepareStatement(query2);
			psmt3=con.prepareStatement(query3);
			System.out.println("init end");
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		reqProcces(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		reqProcces(req,resp);
	}
	private void reqProcces(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{	
		PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");	
		try {
			
			int courseId=Integer.parseInt(req.getParameter("courseID"));
			
			psmt1.setInt(1, courseId);
			psmt2.setInt(1, courseId);
			psmt3.setInt(1, courseId);
			
			int res1=psmt1.executeUpdate();
			int res2=psmt2.executeUpdate();
			int res3=psmt3.executeUpdate();
			if(res3>0)
			{
				pw.print("<h2 align='center' style='color:green'>Course deleted</h2>");
			}
			else
			{
				pw.print("<h2 align='center' style='color:red'>Course not deleted</h2>");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() 
	{
		try {
			psmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			psmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
