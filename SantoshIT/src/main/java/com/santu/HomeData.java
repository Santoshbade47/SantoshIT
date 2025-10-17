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

@WebServlet("/HomeDataRetrive")
public class HomeData extends HttpServlet
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
			String query1="select lname,id from sb_students where PHONE_NO =?";
			psmt1=con.prepareStatement(query1);
			String query2="select lname,id from sb_students where Email =?";
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
		String name="";
		int id=0;
		int btnValue=Integer.parseInt(req.getParameter("btnValue"));
		
		try {
			if(btnValue==1)
			{
				long phNo=Long.parseLong(req.getParameter("phNo"));
				psmt1.setLong(1,phNo);
				ResultSet rs=psmt1.executeQuery();
				if(rs.next())
				{   
					name= rs.getString(1);
					id= rs.getInt(2);
				}
			}
			else 
			{
				String email=req.getParameter("email");
				psmt2.setString(1, email);
				ResultSet rs=psmt2.executeQuery();
				if(rs.next())
				{   
					name= rs.getString(1);
					id= rs.getInt(2);
				}
			}
				HttpSession session=req.getSession();
				session.setAttribute("name",name);
				session.setAttribute("id",id);
				req.getRequestDispatcher("./Home.jsp").forward(req, resp);
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
