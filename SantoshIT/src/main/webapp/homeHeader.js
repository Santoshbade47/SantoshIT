const topBar=document.querySelector(".topBar");
const nav=document.querySelector(".navBr");

topBar.innerHTML=`
		<nav class="d-flex p-2 justify-content-between align-items-center">
			<a href="Home.jsp" class="p-0"><img src="images/logoSantu.png" height="50px" width="130px"></a>
			<div id="searchBox">
				<input type="search" placeholder="Search Here"></input>
				<button class="bi bi-search" id="searchIcon"></button>
			</div>
			<div class="d-flex justify-content-center align-items-center"> 
				<div class="topName"> 
					<h5>Hello</h5> 
					<p id="name" class="name"></p>
				</div> 
				<a href="PersonDet.jsp">
					<i id="per" class="me-3 bi bi-person-circle s-1"></i>
				</a>
			</div>
		</nav>
			   `;
nav.innerHTML=`<div class="navbar">
			<nav class="d-flex justify-content-between align-items-center">
				<a href="#">Java full stack</a>
				<a href="#">Python full stack</a>
				<a href="#">.Net full stack</a>
				<a href="#">UI full stack</a>
			</nav>
		</div>`;
document.getElementById("name").textContent=userName;
