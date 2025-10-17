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

@WebServlet("/addCourse")
public class AddCourse extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement psmt1;
	PreparedStatement psmt2;
	@Override
	public void init() throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","santu","manager");
			String query="insert into sb_course values(?,?,?,?,?,?,?)";
			psmt1=con.prepareStatement(query);
			String query1="insert into about_course values(?,?)";
			psmt2=con.prepareStatement(query1);
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
	private void reqProcces(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{	PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		
		int courseId=Integer.parseInt(req.getParameter("id"));
		String CourseName=req.getParameter("name");
		long fee=Long.parseLong(req.getParameter("fee"));
		int discount=Integer.parseInt(req.getParameter("discount"));
		String imgURL=req.getParameter("imgURL");
		String videoURL=req.getParameter("videoURL");
		String category=req.getParameter("category");
		String aboutCourse=req.getParameter("abouCourse");
		try {
			psmt1.setInt(1,courseId);
			psmt1.setString(2,CourseName);
			psmt1.setLong(3,fee);
			psmt1.setInt(4,discount);
			psmt1.setString(5,imgURL);
			psmt1.setString(6,videoURL);
			psmt1.setString(7,category);
			
			psmt2.setInt(1, courseId);
			psmt2.setString(2,aboutCourse);
			
			int res1=psmt1.executeUpdate();
			int res2=psmt2.executeUpdate();
			if(res1>0 && res2>0)
			{
				pw.print("<h2 align='center' style='color:green'>Course added Successfully.</h2>");
			}
			else
			{
				pw.print("<h2 align='center' style='color:red'>Course not added.</h2>");
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
