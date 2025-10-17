<%@page import="com.santu.Course"%>
<%@page import="java.util.ArrayList"%>
<%
	HttpSession s=request.getSession();
	String name=(String)s.getAttribute("name");
	Course cour=(Course) request.getAttribute("course");
	
	@SuppressWarnings("unchecked")
	ArrayList<Course> courseList=( ArrayList<Course> ) request.getAttribute("courseList");
	
	String video_url=cour.getVideo_url();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Santosh IT</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" href="./Home.css" >
	
	<script type="text/javascript">
		const userName="<%=name%>";
		
	</script>
	<script defer src="homeHeader.js"></script>
	 <style type="text/css">
	 	body{
	 		background: linear-gradient(to right,rgb(14,14,14), rgb(21,21,21),rgb(30,30,30), rgb(21,21,21), black) no-repeat center fixed;
	 	}
	 	iframe{
	 		width: 100%;
	 		height: 350px;
	 		margin-top: 20px;
	 		margin-bottom:20px;
	 		border-radius:10px;
	 		box-shadow: 0px 0px 600px rgb(80,80,80);
	 	}
	 	#n{
	 		width: 100%;
	 		height: 40px;
	 		display: flex;
	 	}
	 	.video{
	 		margin: 10px 30px 10px 40px;
	 		width: 57%;
	 	}
	 	.sideCourses{
	 		border-left: 1.5px solid rgb(110,110,110);
	 		margin-top: 20px;
	 		padding-left:30px;
	 		padding-top: 40px;
	 	}
	 	.courseCard{
	 		width: 400px;
	 		display: flex;
	 		border-radius: 10px;
	 		overflow: hidden;
	 		color: white;
	 		margin:20px;
	 		text-decoration: none;
	 		transition: transform 0.5s ease,box-shadow 0.5s ease;
	 	}
	 	.courseCard:hover {
			transform:scale(1.03);
			box-shadow:0px 0px 10px rgb(250,250,250);
		}
	 </style>
</head>
<body>
	<header class="topBar" ></header>
	<div class="navBr"></div>
	<main class="d-flex">
		<div class="video">
			<iframe
			src="<%=video_url%>"
	        title="YouTube video player"
	        allowfullscreen>
			</iframe>
			<div style="color: white;"><%=cour.getAbout_course() %></div>
		</div>
		<div class="sideCourses">
			<%
				for(Course course:courseList)
				{
			%>
					<a class="courseCard" href="checkCourse?courseID=<%=course.getCoursrId()%>">
						<img style="width: 40%;" alt="img" src="images/<%=course.getImg_url()%>" height="130">
						<div style="width: 60%;text-align: center;">
							<div style="font-size: 20px;"><%=course.getCourseName()%></div>
							<div style="font-size: 20px;color: rgb(180,180,180)">
								<i class="bi bi-currency-rupee"></i><%=(course.getFee())-(course.getFee()*(course.getDiscount()/100)) %>
								<del style="font-size: 13px;margin-left: 7px;"><i class="bi bi-currency-rupee"></i><%=course.getFee() %></del>
							</div>
							<div style="color: green;"><%=course.getDiscount()%>% Discount get now</div>
							<div style="font-size: 20px;margin-top: 10px;">JOIN NOW</div>
						</div>
					</a>
			<%
				}
			%>
		</div>
	</main>
</body>
</html>