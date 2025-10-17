
const header=document.querySelector(".head");
const leftBar=document.querySelector(".leftBar");

header.innerHTML=`
			<nav>
				<a href="Admin/Admin.html"><img src="./images/logoSantu.png" height="50px" width="130px"></a>
				<div class="admin">
					<p>Admin</p>
					<i class="ms-3 bi bi-person-circle"></i>
				</div>
			</nav>
			`;
leftBar.innerHTML=`
				<a href="#">&nbsp;&nbsp;Add Exam paper</a>
				<a href="PaymentDetailsRetrive">&nbsp;&nbsp;Course Activation</a>
				<a href="Admin/AddCourse.html">&nbsp;&nbsp;Course Add</a>
				<a href="Admin/RemoveCourse.html">&nbsp;&nbsp;Course Delete</a>
				<a href="#">&nbsp;&nbsp;Course Update</a>
				<a href="Admin/AccountBlock.html">&nbsp;&nbsp;Student Account block</a>
				<a href="StudentsDetails">&nbsp;&nbsp;View Students</a>
				<a href="#">&nbsp;&nbsp;View courses</a>
			`;