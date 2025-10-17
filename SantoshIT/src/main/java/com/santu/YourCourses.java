package com.santu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/yourCourses")
public class YourCourses extends HttpServlet
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
			String query="select * from sb_course c join sb_stu_join_courses jc on c.id = jc.course_id where jc.stu_id=?";
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
		reqProcces(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		reqProcces(req,resp);
	}
	private void reqProcces(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		HttpSession s=req.getSession();
		
		int cousreId=(int)s.getAttribute("id");
		
		
		ArrayList<Course> courList=new ArrayList<Course>();
		
		try {  
			psmt.setInt(1,cousreId);
			ResultSet rs=psmt.executeQuery();

			if(rs.next())
			{
				do{
					Course c= new Course();
					c.setCoursrId(rs.getInt(1));
					c.setCourseName(rs.getString(2));
					c.setFee(rs.getDouble(3));
					c.setDiscount(rs.getInt(4));
					c.setImg_url(rs.getString(5));
					courList.add(c);
				}while(rs.next());
			}
			else {
				req.setAttribute("joinOfCoursesList",null);
				req.getRequestDispatcher("JoinCourses.jsp").forward(req, resp);
			}
			req.setAttribute("joinOfCoursesList",courList);
			req.getRequestDispatcher("JoinCourses.jsp").forward(req, resp);
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
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
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
