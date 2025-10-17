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

@WebServlet("/joinStu")
public class SignUpDS extends HttpServlet  //DS - data storage
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
			String query="insert into sb_students values(seq_sb.nextval,?,?,?,?,?,?,sysdate,'active')";
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
	{	PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		String fName=req.getParameter("fName");
		String lName=req.getParameter("lName");
		String gender=req.getParameter("gender");
		
		long phNo=Long.parseLong(req.getParameter("phNo"));
		String mail=req.getParameter("email");
		String password=req.getParameter("password");
		try {
			psmt.setString(1,fName);
			psmt.setString(2,lName);
			psmt.setString(3,gender);
			psmt.setLong(4,phNo);
			psmt.setString(5,mail);
			psmt.setString(6,password);
			int res=psmt.executeUpdate();
			if(res>0)
			{
				req.getRequestDispatcher("./HomeDataRetrive").include(req, resp);
			}
			else
			{
				pw.print("<h2 align='center' style='color:red'>Registration UnSuccessfull.</h2>");
			}
		}
		catch (SQLException | ServletException e) 
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
