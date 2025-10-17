
<%
	HttpSession s=request.getSession();
	String name=(String) s.getAttribute("name");
    int id=(int) s.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>BS E-Institute</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	
 	<link rel="stylesheet" href="./Home.css">
 	<script type="text/javascript">
 		const userName="<%=name%>";
 	</script>
 	<script defer src="homeHeader.js"></script>
 	<style type="text/css">
.mainDiv{
	display: flex;
	flex-wrap: wrap;
	margin: 20px auto;
	justify-content: center;
}
.mainDiv a{
	text-decoration: none;
	height: 90px;
 	width: 370px;
 	border: 1px solid black;
 	border-radius: 10px;
 	color: black;
 	display: flex;
 	justify-content: center;
 	align-items: center;
 	margin: 20px 35px;
 	transition: transform 0.5s ease;
}
.mainDiv a:hover {
	transform: scale(1.10);
	box-shadow: 0px 0px 5px black;
}
#logoutA{
 	text-decoration: none;
 	color: white;
 	width: 64%;
	margin: 0px auto;
	height: 40px;
	background-color: red;
	border-radius: 5px;
	display: flex;
	justify-content: center;
	align-items: center;
}
	</style>
</head>

<body>
	<header class="topBar"></header>
	<div class="navBr"></div>
	<div class="mainDiv">
			<a href="./userData">Person Details</a>
			<a href="#">Exams</a>
			<a href="yourCourses">your Courses</a>
			<a href="PasswordChang.jsp">Change Your Password</a>
	</div>
	<a id="logoutA" href="./logout">Log Out</a>
</body>
</html>