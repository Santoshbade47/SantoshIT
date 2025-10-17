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

@WebServlet("/userData")
public class UserData extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement psmt1;
	@Override
	public void init() throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","santu","manager");
			String query1="select * from sb_students where id=?";
			psmt1=con.prepareStatement(query1);
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
		HttpSession session = req.getSession();
		int id=(int) session.getAttribute("id");
		
		Student s = new Student();
		
		try {
			psmt1.setInt(1, id);
			ResultSet rs=psmt1.executeQuery();
			
			if(rs.next())
			{
				s.setStudentId(rs.getInt(1));
				s.setSurName(rs.getString(2));
				s.setName(rs.getString(3));
				s.setGender(rs.getString(4));
				s.setPhoneNo(rs.getLong(5));
				s.setEmail(rs.getString(6));
				s.setDoj(rs.getString(8));
			}
			session.setAttribute("Student",s);
			
			req.getRequestDispatcher("./AboutUser.jsp").forward(req, resp);
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
			psmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
