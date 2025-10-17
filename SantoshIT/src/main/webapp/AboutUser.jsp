
<%@page import="com.santu.Student"%>
<%
   	HttpSession sess=request.getSession();
	Student stu=(Student) sess.getAttribute("Student");
	String gender=null;
	if(stu.getGender().equalsIgnoreCase("m"))
		gender="Male";
	else if(stu.getGender().equalsIgnoreCase("f")) 
		gender="Female";
	else 
		gender="Others";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>BS E-Institute</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	
	<script type="text/javascript">
		const userName="<%=stu.getName()%>";
	</script>
	<script defer src="homeHeader.js"></script>
	
 	<link rel="stylesheet" href="./Home.css">
 	<style type="text/css">
 		body{
 			background-color: rgb(230,230,230);
 		}
 		table{
 			background-color: white;
 			margin: 50px auto;
		}
		table th{
			padding: 20px;
		}
		table td{
		 	padding: 20px;
		}
		.user{
			text-align: center;
			font-size: 20px;
		}
 	</style>
</head>
<body>
	<header class="topBar"></header>
	<div class="navBr"></div>
	<main>
		<table>
			<tr>
				<th class="user" colspan="2">User Details</th>
			</tr>
			<tr>
				<th>Student Id No</th><td>:<%=stu.getStudentId() %></td>
			</tr>
			<tr>
				<th>SurName</th><td>:  <%=stu.getSurName() %></td>
			</tr>
			<tr>
				<th>Name</th><td>:  <%=stu.getName() %></td>
			</tr>
			<tr>
				<th>Gender</th><td>:  <%=gender %></td>
			</tr>
			<tr>
				<th>E mail</th><td>: <%=stu.getEmail() %></td>
			</tr>
			<tr>
				<th>Phone number</th><td>: <%=stu.getPhoneNo() %></td>
			</tr>
			<tr>
				<th>Date of join</th><td>: <%=stu.getDoj() %></td>
			</tr>
		</table>
	</main>
	<footer>
	
	</footer>
</body>
</html>