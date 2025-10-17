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

@WebServlet("/CourseActivate")
public class CourseActivation extends HttpServlet
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
			String query1="insert into sb_stu_join_courses values(?,?,'Valid')";
			psmt1=con.prepareStatement(query1);
			String query2="delete sb_course_activation where stu_id=? and course_id=?";
			psmt2=con.prepareStatement(query2);
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
		
		int stuId=Integer.parseInt(req.getParameter("stuId"));
		int courseId=Integer.parseInt(req.getParameter("courseId"));
		
		try {
			psmt1.setInt(1,stuId);
			psmt1.setInt(2, courseId);
			
			psmt2.setInt(1,stuId);
			psmt2.setInt(2, courseId);
			
			int res1=psmt1.executeUpdate();
			int res2=psmt2.executeUpdate();
			if(res1>0)
			{
				if(res2>0)
				{
					req.getRequestDispatcher("./PaymentDetailsRetrive").forward(req, resp);
				}
				else
				{
					pw.print("not delete request");
				}
			}
			else
			{
				pw.print("not active course");
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
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	}
}
