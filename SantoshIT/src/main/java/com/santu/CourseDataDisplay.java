package com.santu;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/courseDisplay")
public class CourseDataDisplay extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement psmt;
	PreparedStatement psmt1;
	PreparedStatement psmt2;
	@Override
	public void init() throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","santu","manager");
			String query="select * from Sb_course where id=?";
			psmt=con.prepareStatement(query);
			
			String query1="select about_course from about_course where COURSE_ID=?";
			psmt1=con.prepareStatement(query1);
			
			String query2="select * from Sb_course where category=? and id != ?";
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
		HttpSession s=req.getSession();
		
		int cousreId=(int)s.getAttribute("courseID");
		
		boolean isPaid=(boolean) req.getAttribute("isPaid");
		
		Course c= new Course();
		
		try {
			psmt.setInt(1,cousreId);
			psmt1.setInt(1, cousreId);
			ResultSet rs=psmt.executeQuery();
			ResultSet rs1=psmt1.executeQuery();
			String category=null;
			if(rs.next())
			{
				category = rs.getString(7);
				c.setCoursrId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				c.setFee(rs.getDouble(3));
				c.setDiscount(rs.getInt(4));
				c.setImg_url(rs.getString(5));
				c.setVideo_url(rs.getString(6));
				if(rs1.next())
					c.setAbout_course(rs1.getString(1));
			}
			else {
				pw.print("detailse not avaliable");
			}
			req.setAttribute("course",c);
			
			if(isPaid)
			{	
				psmt2.setString(1, category);
				psmt2.setInt(2, cousreId);
				ResultSet rs2=psmt2.executeQuery();
				ArrayList<Course> courseList= new ArrayList<Course>();
				if(rs2.next())
				{
					do
					{
						courseList.add(new Course(rs2.getInt(1),rs2.getString(2),rs2.getDouble(3),rs2.getInt(4),rs2.getString(5)));
					}while(rs2.next());
				}
				req.setAttribute("courseList",courseList);
				req.getRequestDispatcher("DisplayCourseVideo.jsp").forward(req, resp);
			}
			else
				req.getRequestDispatcher("Payment.jsp").forward(req, resp);
			
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
		try {
			psmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
