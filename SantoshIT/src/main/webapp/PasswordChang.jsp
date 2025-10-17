
<%
	HttpSession ses=request.getSession();
	String name=(String) ses.getAttribute("name"); 
	int id=(int)ses.getAttribute("id");
	
	ses.setAttribute("id",id);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" href="./Home.css">
	<script defer src="PasswordChange.js"></script>
	
	<script type="text/javascript">
		const userName="<%=name%>"
	</script>
 	<script defer src="homeHeader.js"></script>
	<style type="text/css">
		body{
			background-color:rgb(230,230,230);
		}
		form{
			background-color: rgb(185, 185, 215);
			width: 300px;
			padding: 30px;
			margin: 0px auto;
			margin-top:80px;
			border-radius: 10px; 
		}
		form input{
			width:100%;
			border-radius: 5px;
			margin: 7px 0px;
			border: none;
			height: 35px;
		}
		#sub{
			display:flex;
			margin: 0px auto;
			width: 50%;
			margin-top:20px;
			justify-content: center;
			font-weight: bold;
			font-family: sans-serif;
			border-radius: 2px;
		}
		#sub:hover {
			background-color: rgb(145, 175, 204)
		}
		.msg1,.msg2{
			color: red;
			font-size: 13px;
		}
		.SuccMsg{
			color: rgb(43, 158, 33);
			text-align: center;
			margin-top: 10px;
		}
		.errMsg{
			color: red;
			text-align: center;
		}
	</style>
</head>
<body>
	<header class="topBar"></header>
	<div class="navBr"></div>
	<form id="form" action="" method="post">
		<h3 align="center" class="mb-3">Chang Password</h3>
		<label>Enter password</label><br>
		<input type="password" id="pass1" placeholder="Enter here" ><br>
		<div class="msg1"></div>
		<label>Re enter password</label><br>
		<input type="password" id="pass2" name="password" placeholder="Enter here" >
		<div class="msg2"></div>
		
		<button id="sub" type="submit">Submit</button>
		<div class="errMsg"></div>
		<div class="SuccMsg"></div>
	</form>
</body>
</html>