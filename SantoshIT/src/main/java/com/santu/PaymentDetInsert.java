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

@WebServlet("/PaymentDetInsert")
public class PaymentDetInsert extends HttpServlet
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
			String query="insert into sb_course_activation values(?,?,?,sysdate)";
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
		
		HttpSession s=req.getSession();
		int stuId= (int) s.getAttribute("id");
		int courseId=(int) s.getAttribute("courseID");
		long utrNo=Long.parseLong(req.getParameter("utrNum"));
		
		try {
			psmt.setInt(1,stuId);
			psmt.setInt(2,courseId);
			psmt.setLong(3,utrNo);
			int res=psmt.executeUpdate();
			if(res>0)
			{
				pw.print("<h2 align='center' style='color:green'>Your payment successfull...</h2>");
				pw.print("<p align='center' style='color: rgb(100,100,100);'>Wait for the course activation..</p>");
			}
			else
			{
				pw.print("<h2 align='center' style='color:red'>Your payment Unsuccessfull...!</h2>");
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
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
