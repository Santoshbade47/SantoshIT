package com.santu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkCourse")
public class CheckCourse extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement psmt;
	@Override
	public void init() throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","santu","manager");
			String query="select * from sb_stu_join_courses where stu_id=? and course_id=?";
			psmt=con.prepareStatement(query);
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{

		HttpSession s=req.getSession();
		int courseID=Integer.parseInt(req.getParameter("courseID"));
		int stu_id=(int) s.getAttribute("id");
		
		s.setAttribute("courseID",courseID);
		boolean isPaid=false;
		
		try {
			psmt.setInt(1, stu_id);
			psmt.setInt(2, courseID);
			ResultSet rs=psmt.executeQuery();
			if(courseID==100 || rs.next())
			{
				isPaid=true;
				req.setAttribute("isPaid",isPaid);
				req.getRequestDispatcher("courseDisplay").forward(req, resp);
			}
			else 
			{
				req.setAttribute("isPaid",isPaid);
				req.getRequestDispatcher("courseDisplay").forward(req, resp);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req,resp);
	}
	
}
