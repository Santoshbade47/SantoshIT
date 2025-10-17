<%@page import="com.santu.Course"%>
<%
	Course c=(Course) request.getAttribute("course");
    
	double discount=c.getFee()*(c.getDiscount()/100.0);
	double fee=(c.getFee())-discount;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<script defer src="UTRvalidation.js"></script>
	<style type="text/css">
		body{
			background-color: rgb(240,240,240);
		}
		header {
			background-color: rgb(20, 21, 46);
		}
		main{
			display: flex;
		}
		.aboutCourse{
			width:50%;
		}
		.payment{
			width:50%;
			margin: 10px;
			padding:20px;
			background-color: white;
		}
		.payMethod{
			width: 100%;
			border: 1px solid rgb(200,200,200);
			border-radius: 10px;
		}
		.QRcode{
			margin: 10px;
		}
		#upiText{
			font-size: 25px;
		}
		.upiText1{
			color: rgb(170,170,170);
			font-size: 20px;
			text-align: center;
		}
		.upiDiv{
			height: 40px;
			border: 1px solid black;
			width: 350px;
			display: flex;
			align-items:center;
			padding: 10px;
			margin: 10px;
			border-radius: 10px;
			font-size: 20px;
			color: rgb(100,100,100);
		}
		.copy.add{
			color: rgb(100,100,100);
		}
		input{
			height: 40px;
			border: 2px solid bgb(100,100,100);
			width: 350px;
			display: flex;
			align-items:center;
			padding: 10px;
			margin: 10px 10px 2px 10px;
			border-radius: 10px;
			font-size: 20px;
			color: rgb(100,100,100);
		}
		.utrName{
			font-size: 13px;
			color: red;	
			margin-left: 22px;
		}
		.payDiv{
			height: 60px;
			width: 350px;
			display: flex;
			justify-content:space-between;
			align-items: center;
			box-shadow: 0px 0px 20px rgb(200,200,200);
			border-radius: 10px;
			margin: 10px;
			padding: 15px;
			margin-top: 30px; 
		}
		button{
			height: 40px;
			border: none;
			background-color: rgb(13, 191, 9);
			border-radius: 5px;
			padding: 0px 30px;
			font-size: 23px;
			color: white;
		}
		.error{
			text-align: center;
			font-size: 15px;
			color: red;
		}
		.disCourse{
			background-color: white;
			padding: 10px;
			margin: 10px;
			display: flex;
		}
		#payLater{
			text-decoration: none;
			background-color: rgb(240, 235, 15);
			padding: 4px 100px;
			margin-top:10px;
			border-radius: 5px;
		}
		#paylaterDiv{
			width: 100%;
			display: flex;
			justify-content: center;
		}
	</style>
</head>
<body>
	<header>
		<nav class="d-flex p-2 justify-content-between align-items-center">
			<a href="Home.jsp" class="p-0"><img src="images/logoSantu.png" height="50px" width="130px"></a>
			<div style="color: white;font-size: 30px;">
				Payment
			</div>
		</nav>
	</header>
	<main>
		<div class="aboutCourse">
			<div class="disCourse">
				<img class="m-2" alt="" src="images/<%=c.getImg_url()%>" width="190" height="165">
				<div>
					<h5 style="text-transform: uppercase;color: rgb(160,160,160);margin-left: 80px;'"><%=c.getCourseName() %></h5>
					<div><%=c.getAbout_course() %></div>
					<div style="margin-top: 10px;"><i class="bi bi-tags-fill" style="color: green;font-size: 14px;"></i> Join this course get <%=c.getDiscount() %>% Discount</div>
					<div style="font-size: 14px;"><i class="bi bi-tags-fill" style="color: green;"></i> Payment using with AmazonPay get extra 5% Discount </div>
					<div id="paylaterDiv"><a id="payLater" href="Home.jsp">Pay Later</a></div>
				</div>
			</div>
			<div style="widows: 100%; height:360px; background-color: white;margin: 10px;">
			</div>
		</div>
		<div class="payment">
			<h3>Payment Method</h3>
			<div class="payMethod">
				<div class="d-flex">
					<img class="QRcode" src="images/QRcode.jpg" alt="QRcode" width="150px" height="150px">
					<div class="ms-5 me-5">
						<div id="upiText">Scan the QR using any UPI app on your phone</div>
						<img class="mt-2" alt="upi" src="images/upi.png" width="275px" height="50px">
						<div class="upiText1">Secure Payment with UPI</div>
					</div>
				</div>
				<hr>
				<div class="error"></div>
				<div class="ms-3" style="color: green;font-size: 20px;font-weight: bold;">Paid to:</div>
				<div class="upiDiv">Account Name : Bade Santosh</div>
				<div class="d-flex align-items-center">
					<div class="upiDiv">UPI ID : <div id="upiID">santosh.bade1405@ybl</div><i class="bi bi-clipboard ms-4 mt-1" role="button" onclick="copyUpi()"></i></div>
					<div class="copy"></div>
				</div>
				<form id="form" action="">
					<input type="number" name="utrNum" id="utrNum" placeholder="Enter UTR / UPI Txn Id">
					<div class="utrName">Enter UTR and click to process your payment</div>
					<div  class="payDiv">
						<div class="d-flex align-items-center" style="font-size: 30px;">
							<div class="bi bi-currency-rupee"></div>
							<div><%=fee %></div>
						</div>
						<button type="submit">I have Paid</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<script type="text/javascript">
		function copyUpi(){
			const upiId=document.getElementById("upiID").innerText;
			const copy=document.querySelector(".copy");
			navigator.clipboard.writeText(upiId).then(()=>{
				copy.innerText = 'Copied!';
				copy.classList.add("add");
				
				// Hide after 3 seconds
				setTimeout(()=>{
					copy.innerText = '';
					copy.classList.remove("add");
				}, 3000);
			})
		}
	</script>
</body>
</html>