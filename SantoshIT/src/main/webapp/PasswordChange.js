const form=document.getElementById("form");
const password1=document.getElementById("pass1");
const password2=document.getElementById("pass2");

const msg1=document.querySelector(".msg1");
const msg2=document.querySelector(".msg2");
const error=document.querySelector(".errMsg");
const success=document.querySelector(".SuccMsg");

form.addEventListener('submit',e =>{
	e.preventDefault();
	validateInputs();
})

const setError=(massege)=>{
	msg2.innerText=massege;
}
const setErr=(massege)=>{
	msg1.innerText=massege;
}

const setErrorMsg=(massege)=>{
	error.innerText=massege;
}
const setSuccess=(massege)=>{
	success.innerText=massege;
}


const validateInputs =()=>{
	const pass1Value=password1.value.trim();
	const pass2Value=password2.value.trim();
	
	let pass1OK=false;
	let pass2OK=false;
	if(pass1Value ==='')
	{
		setErr('*Please Enter password');
		setErrorMsg('');
		setSuccess('');
	}
	else{
		setErr('');
		pass1OK=true;
	}
	
	if(pass2Value ==='')
	{
		setError('*Pease Enter password');
		setErrorMsg('');
		setSuccess('');
	}
	else if(pass1Value != pass2Value){
		setError('*Dose not match password');
		setErrorMsg('');
		setSuccess('');
	}
	else{
		setError('');
		pass2OK=true;
	}
	
	if(pass1OK && pass2OK)
	{
		updateOrNot(pass2Value);
	}
}

const updateOrNot=(password)=>{
	fetch(`http://localhost:8147/SantoshIT/changPassword`,{
		method:"POST",
		headers:{
			"content-type":"Application/x-www-form-urlencoded"
		},
		body:`password=${encodeURIComponent(password)}`
	})
	.then(res=> res.json())
	.then(data=>{
		setErrorMsg(data.errMsg);
		setSuccess(data.message);
	})
}