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
import jakarta.servlet.http.HttpSession;

@WebServlet("/changPassword")
public class PasswordUpdate extends HttpServlet
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
			String query="update sb_students set password=? where id=?";
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
		resp.setContentType("Application/json");
		PrintWriter pw=resp.getWriter();
		
		HttpSession s=req.getSession();
		int id=(int)s.getAttribute("id");
		String password=req.getParameter("password");
		try {
			psmt.setString(1,password);
			psmt.setInt(2,id);
			int res=psmt.executeUpdate();
			if(res>0)
			{
				pw.write("{\"message\":\"Password updated successfull\",\"errMsg\":\"\"}");
			}
			else {
				pw.write("{\"message\":\"\",\"errMsg\":\"Password updated unsuccessfull\"}");
			}
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
