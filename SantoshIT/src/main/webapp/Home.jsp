

<%
	HttpSession s=request.getSession();
	
	String name=(String) s.getAttribute("name");
	int id=(int) s.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Santosh IT</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	
	<script type="text/javascript">
		const userName="<%=name%>";
	</script>
	<script defer src="homeHeader.js"></script>
	<script defer src="ScrollDataImg.js"></script>
	<script defer src="DisplayCardData.js"></script>

 	<link rel="stylesheet" href="./Home.css">
 	
 	<style type="text/css">
body{
	background: linear-gradient(rgb(249, 75, 32), white) no-repeat center fixed;
}

#topCard {
	padding-top: 5px;
	padding: 5px;
	height: 200px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: transparent;
}

#arrowL, #arrowR {
	font-size: 35px;
	cursor: pointer;
}

#arrowR {
	position: absolute;
	right: 15px;
}

#arrowL {
	position: absolute;
	left: 15px;
}

#courseContainer {
	align-items: center;
	padding: 0 10px;
}

#inCard {
	display: flex;
	border-radius: 10px;
	margin: 20px;
	height: 200px;
	width: 370px;
	background-color:white;
	box-shadow: 5px 5px 5px rgb(118, 124, 135);
	overflow: hidden;
	transition: transform 0.5s ease;
	cursor: pointer;
}
#inCard:hover{
	transform: scale(1.05);
}

#image {
	border-radius: 10px;
}

.feeDiv {
	display: flex;
	justify-content: center;
	height: 40px;
}

.finalFee {
	font-size: 26px;
	margin-right: 10px;
}

.cardButt {
	border: 0px;
	background-color: rgb(244, 200, 21);
	border-radius: 4px;
	width: 90%;
	margin-top: 20px;
	text-decoration: none;
	text-align: center;
	padding: 3px 0px;
}

#textCard {
	height: 100%;
	justify-content: center;
	padding: 4px;
}
.discount{
	font-size: 10px;
}
strike{
	color: red;
}
#rupiSymble{
	color: red;
}
h4{
	height: 60px;
}
</style>
</head>
<body>
	<header class="topBar" ></header>
	<div class="navBr"></div>
	<main>
		<div id="topCard">
			<i id="arrowL" onclick="preClick()" class="bi bi-caret-left"></i>
				<img class="mt-3" id="image" src="" height="200px" width="100%">
			<i id="arrowR" onclick="nextClick()" class="bi bi-caret-right" data-bs-target="#carouselExample" data-bs-slide="next"></i>
		</div>
		<div id="courseContainer" class="d-flex flex-wrap ">
		
		</div>
	</main>
	<footer>
	
	</footer>
</body>
</html>