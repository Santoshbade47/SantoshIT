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

@WebServlet("/scrollData")
public class ScrollDataRet extends HttpServlet
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
			String query1="select * from sb_top_imgs";
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
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		ArrayList<String> imgList= new ArrayList<String>();
		
		try {
			int count=0;
			int rowCount=0;
			ResultSet rs1=psmt1.executeQuery();
			
			while(rs1.next())
			{
				imgList.add("{ \"id\":"+(rs1.getInt(1))+", \"url\": \"" +(rs1.getString(2))+ "\" }");
				rowCount++;
			}
			
			resp.getWriter().print("[");
			for(String imgs : imgList)
			{
				resp.getWriter().print(imgs);
				count++;
				if(rowCount>count) {
					resp.getWriter().print(",");
				}
			}
			resp.getWriter().print("]");
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
