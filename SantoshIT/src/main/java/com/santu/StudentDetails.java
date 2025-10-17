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

@WebServlet("/StudentsDetails")
public class StudentDetails extends HttpServlet
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
			String query="select id,fname,lname,gender,PHONE_NO,EMAIL,to_char(date_of_join,'dd/Mon/yyyy'),AC_ACTIVATION from Sb_Students order by id";
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
		resp.setContentType("text/html");
		
		ArrayList<Student> studentList= new ArrayList<>();
		
		try {
	
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				studentList.add(new Student(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getLong(5), rs.getString(6), rs.getString(7),rs.getString(8)));
			}
			req.setAttribute("listOfStudents", studentList);
			req.getRequestDispatcher("Admin/DispayStudentDet.jsp").forward(req, resp);
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
