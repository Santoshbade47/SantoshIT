package com.santu;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/loginValidation")
public class ValidationServlet extends HttpServlet
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
			String query1="select password,AC_ACTIVATION from sb_students where phone_no=?";
			String query2="select password,AC_ACTIVATION from sb_students where email=?";
			psmt1=con.prepareStatement(query1);
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
	private void reqProcces(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{	PrintWriter pw=resp.getWriter();
		resp.setContentType("Application/json");
		
		String password=req.getParameter("password");
		int buttonVal=Integer.parseInt(req.getParameter("btnValue"));
		try {
			ResultSet rs;
			if(buttonVal==1)
			{
				long phNo=Long.parseLong(req.getParameter("phoneOrMailValue"));
				psmt1.setLong(1,phNo);
				rs=psmt1.executeQuery();
			}else{
				String mail=req.getParameter("phoneOrMailValue");
				psmt2.setString(1,mail);
				rs=psmt2.executeQuery();
			}
			if(rs.next())
			{
				if(rs.getString(1).equals(password))
				{
					if(rs.getString(2).equalsIgnoreCase("active"))
					{
						pw.print("{\"message\":\"ok\"}");			
					}
					else 
					{
						pw.print("{\"message\":\"Your account blocked\",\"event\":\"errorMsg\"}");
					}
				}
				else
				{
					pw.print("{\"message\":\"Please enter correct password\",\"event\":\"passError\"}");
				}
			}
			else
			{
				if(buttonVal==1)
				{
					pw.print("{\"message\":\"Your phone number not found\",\"event\":\"phError\"}");
				}
				else
				{
					pw.print("{\"message\":\"Your Email not found\",\"event\":\"mailError\"}}");
				}
			}
		}
		catch (Exception e) 
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

