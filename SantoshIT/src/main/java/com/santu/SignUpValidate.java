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

@WebServlet("/SignUpValid")
public class SignUpValidate extends HttpServlet
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
			String query1="select count(*) from sb_students where PHONE_NO =?";
			psmt1=con.prepareStatement(query1);
			String query2="select count(*) from sb_students where Email =?";
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
		String phoneMessage="";
		String mailMessage="";
		
		long phNo=Long.parseLong(req.getParameter("phNo"));
		String mail=req.getParameter("email");
		
		try {
			psmt1.setLong(1,phNo);
			psmt2.setString(1, mail);
			
			ResultSet rs1=psmt1.executeQuery();
			ResultSet rs2=psmt2.executeQuery();
			rs1.next();
			rs2.next();
			
			if (rs1.getInt(1) > 0) {
				phoneMessage = "Phone number already used";
			}
			if (rs2.getInt(1) > 0) {
				mailMessage = "email already exis";
			}
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			String jsonData = "{ \"phoneMessage\": \"" + phoneMessage + "\", \"mailMessage\": \"" + mailMessage + "\" }";
			resp.getWriter().write(jsonData);
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
