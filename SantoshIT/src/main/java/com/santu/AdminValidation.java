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

@WebServlet("/adminLogin")
public class AdminValidation extends HttpServlet
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
			String query="select ADMIN_PASSWORD from sb_admin where ADMIN_ID=?";
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
	private void reqProcces(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{	
		try {
			PrintWriter pw=resp.getWriter();
			resp.setContentType("application/json");
			
			String password=req.getParameter("pass");
			String adminId=req.getParameter("amdId");
			
			String msg="";
			String event="";
			
			psmt.setString(1,adminId);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) 
			{
				if(rs.getString(1).equals(password))
					msg="";
				else
				{
					msg="Password incorrect";
					event="errorPass";
				}
			}
			else 
			{
				msg="Admin Id invalid";
				event="errorAdm";
			}
			
			String jsonData="{\"massege\":\""+msg+"\",\"event\":\""+event+"\"}";
			pw.print(jsonData);
			
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
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
