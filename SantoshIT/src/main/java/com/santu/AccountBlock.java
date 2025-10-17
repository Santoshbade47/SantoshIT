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

@WebServlet("/blockAccount")
public class AccountBlock extends HttpServlet
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
			psmt1=con.prepareStatement("update sb_students set AC_ACTIVATION=? where id=? ");
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
	{	
		resp.setContentType("Application/json");
		 PrintWriter pw=resp.getWriter();
		try {
			int stuId=Integer.parseInt(req.getParameter("stuId"));
			String buttonValue= req.getParameter("blockOrActive");
			
			psmt1.setString(1,buttonValue);
			psmt1.setInt(2, stuId);
			int res=psmt1.executeUpdate();
			if(res>0)
			{
				pw.write("{\"message\":\"Acount "+buttonValue+" Successfully\",\"errMsg\":\"\"}");
			}
			else pw.write("{\"message\":\"\",\"errMsg\":\"No student found with ID: " + stuId + "\"}");
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
	}
}
