const courseContainer=document.getElementById("courseContainer");

fetch(`http://localhost:8147/SantoshIT/DisplayData`)
.then(res=> res.json())
.then(courses=>{
	
	courseDisplay(courses);
	
})
 function courseDisplay(courses){
	 courses.forEach(course => {
		 const discountFee=(course.fee)*((course.discount)/100.0);
		 const finalFee=(course.fee)-discountFee;
		 const card=document.createElement("div");
		 card.innerHTML=`
		 	<div id="inCard">
			 	<div> <img alt="hi" src="images/${course.imgUrl}" height="200px" width="170px"></div>
				<div id="textCard" class="d-flex flex-wrap"  style="height:120px">
					<h4>${course.name}</h4>
					<div class="feeDiv">
						<div class="finalFee">
							<i class="bi bi-currency-rupee"></i>${finalFee}
						</div>
						<div class="discount">
						    <i class="bi bi-currency-rupee"></i>
							<strike>${course.fee}</strike><br>${course.discount}% discount
						</div>
					</div>
					<div class="cardButt" onclick="btnClick()">Join Now</div>
				</div>
			</div>
		 `;
		 courseContainer.appendChild(card); 
	 })
 }