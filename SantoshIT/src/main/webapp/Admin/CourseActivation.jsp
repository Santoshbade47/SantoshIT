<%@page import="com.santu.PaymentDetBean"%>
<%@page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
  	ArrayList<PaymentDetBean> paymentDelList=(ArrayList<PaymentDetBean>) request.getAttribute("paymentDelList");
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
		table {
			border: 1px solid;
			margin: 0px auto;
			margin-top: 60px;
		}
		tr,td,th{
			border: 1px solid;
			padding: 3px 10px;
		}
	</style>
</head>
<body>
	<header class="head"></header>
	<main>
		<div class="leftBar"></div>
		
		<div style="width: 82%;">
		<table border="1" >
		<%
		if(paymentDelList!=null && !paymentDelList.isEmpty())
		{
		%>
				<tr style="background-color:  rgb(200,200,200);">
					<th>Student ID</th>
					<th>SCourse ID</th>
					<th>UTR number</th>
					<th>Payment time</th>
					<th>Active request</th>
					<th>Delete request</th>
				</tr>
		<%
			for(PaymentDetBean pl:paymentDelList)
			{
		%>
				<tr>
					<td><%=pl.getStuId() %></td>
					<td><%=pl.getCourseId() %></td>
					<td><%=pl.getUtrNo() %></td>
					<td><%=pl.getPaymentTime() %></td>
					<td>
						<form action="./CourseActivate" method="post" style="display:inline;">
						  <input type="hidden" name="stuId" value="<%=pl.getStuId()%>">
						  <input type="hidden" name="courseId" value="<%=pl.getCourseId()%>">
						  <button type="submit" style="background:green;color:white;border:none;padding:3px 10px;border-radius:5px;width: 100px;">
						    Approve
						  </button>
						</form>
					</td>
					<td>
						<form action="./RequestCancle" method="post" style="display:inline;">
						  <input type="hidden" name="stuId" value="<%=pl.getStuId()%>">
						  <input type="hidden" name="courseId" value="<%=pl.getCourseId()%>">
						  <button type="submit" style="background:red;color:white;border:none;padding:3px 10px;border-radius:5px;width: 100%;">
						    <i class="bi bi-trash3"></i>
						  </button>
						</form>
					</td>
				</tr>
		<%
			}
		}
		else
		{
		%>
			<tr>
				<th style="border: 0px solid;font-size: 30px;color: red;" >No any requests right now</th>
			</tr>
		<%
		}
		%>
		</table>
		</div>
	</main>
</body>
</html>