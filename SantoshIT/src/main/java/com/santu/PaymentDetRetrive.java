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

@WebServlet("/PaymentDetailsRetrive")
public class PaymentDetRetrive extends HttpServlet
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
			String query="select * from sb_course_activation";
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
		
		ArrayList<PaymentDetBean> paymentDelList= new ArrayList<>();
		
		try {
	
			ResultSet rs=psmt.executeQuery();
			while (rs.next())
			{
				paymentDelList.add(new PaymentDetBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			} 
			req.setAttribute("paymentDelList", paymentDelList);
			req.getRequestDispatcher("Admin/CourseActivation.jsp").forward(req, resp);
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
