<%@page import="com.santu.Student"%>
<%@page import="java.util.ArrayList"%>
<% 
 	String gen=null;
    @SuppressWarnings("unchecked")
	ArrayList<Student> studentList=(ArrayList<Student>) request.getAttribute("listOfStudents");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	
	<script defer src="./Admin/AdminHeaderForJsp.js"></script>
	
	<link rel="stylesheet" href="Admin/AdminH.css">

<style type="text/css">
	th,td{
		border:1px solid black;
		padding: 0px 5px;
	}
	table {
		margin: 30px auto;
	}
	th{
	  	text-align: center;
	  	background-color: rgb(200,200,200);
	}
</style>

</head>
<body>
	<header class="head"></header>
	
	<main>
		<div class="leftBar"></div>
		
		<table border="1">
			<tr>
				<th>Student Id</th>
				<th>Name</th>
				<th>gender</th>
				<th>Phone number</th>
				<th>Email</th>
				<th>Date of join</th>
				<th>Account Status</th>
			</tr>
			<%
				if(studentList != null)
				{ 
					for(Student s:studentList)
					{
						if(s.getAcActivation().equalsIgnoreCase("active"))
						{
			%>
						<tr style="background-color: rgb(134, 251, 137);">
							<td align="center"><%=s.getStudentId() %></td>
							<td><%=s.getSurName()%> <%=s.getName() %></td>
							<%
								if(s.getGender().equalsIgnoreCase("m"))
								{
									gen="Male";
								}
								else if(s.getGender().equalsIgnoreCase("f"))
								{
									gen="Female";
								}
								else gen="Others";
							%>
							<td><%=gen %></td>
							<td align="center"><%=s.getPhoneNo() %></td>
							<td><%=s.getEmail() %></td>
							<td><%=s.getDoj() %></td>
							<td align="center"><%=s.getAcActivation() %></td>
						</tr>
						<%} else{ %>
						<tr style="background-color: rgb(247, 98, 94);">
							<td align="center"><%=s.getStudentId() %></td>
							<td><%=s.getSurName()%> <%=s.getName() %></td>
							<%
								if(s.getGender().equalsIgnoreCase("m"))
								{
									gen="Male";
								}
								else if(s.getGender().equalsIgnoreCase("f"))
								{
									gen="Female";
								}
								else gen="Others";
							%>
							<td><%=gen %></td>
							<td align="center"><%=s.getPhoneNo() %></td>
							<td><%=s.getEmail() %></td>
							<td><%=s.getDoj() %></td>
							<td align="center"><%=s.getAcActivation() %></td>
						</tr>
			<%
						}
					}
				}
			%>
		</table>
	</main>
</body>
</html>