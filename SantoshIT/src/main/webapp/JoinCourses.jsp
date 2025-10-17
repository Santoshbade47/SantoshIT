<%@page import="java.util.ArrayList"%>
<%@page import="com.santu.Course"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Course> CoursesList=(ArrayList<Course>) request.getAttribute("joinOfCoursesList");
	HttpSession s=request.getSession();
	
	String UserName=(String) s.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<script type="text/javascript">
		const userName="<%=UserName%>";
	</script>
	<script defer src="homeHeader.js"></script>
	<link rel="stylesheet" href="./Home.css">
	<style type="text/css">
		body{
			background-color: rgb(230,230,230);
		}
		.CourseDiv{
			text-decoration: none;
			width: 390px;
			height: 120px;
			display: flex;
			border-radius: 10px;
			overflow: hidden;
			background-color: white;
		}
		main{
		    display:flex;
		    flex-wrap:wrap;
			padding-top: 30px;
		}
		#a{
			text-decoration: none;
			color: black;
			margin:10px;
			margin-left: 150px;
			border-radius: 10px;
			background-color: white;
		}
		#a:hover {
			box-shadow: 0px 0px 10px black;
		}
	</style>
</head>
<body>
	<header class="topBar"></header>
	<div class="navBr"></div>
	<main>
		<%
		if(CoursesList != null)
		{
			for(Course course:CoursesList)
			{
		%>
				<a id="a" href="checkCourse?courseID=<%=course.getCoursrId()%>" >
				<div class="CourseDiv">
					<img alt="img" src="images/<%=course.getImg_url()%>" width="150px">
					<div style="width: 100%;">
						<div align="center" style="text-transform: uppercase;color: rgb(130,130,130);font-size: 20px; "><%=course.getCourseName() %></div>
					 	<div style="margin:5px 5px 0px 8px;font-size: 14px;">Thank you for joining <%=course.getCourseName() %>, You get more subject in Santosh IT we have lot of corses you can join</div>
					</div>
				</div>
				</a>
		<%
			}
		}
		else
		{
		%>
			<div align="center" style="font-size: 25px;color: red;font-weight: bold;">You didn't join any course </div>
		<%
		}
		%>
		
	</main>
	
</body>
</html>