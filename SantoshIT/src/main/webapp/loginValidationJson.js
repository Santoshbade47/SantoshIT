const form=document.getElementById("form");
const buttonValue=document.getElementById("butt").value.trim();
 
  console.log(buttonValue);
let phoneOrMail=null;
	if(buttonValue ==1)
	{
		phoneOrMail=document.getElementById("phNo");
		console.log("hello phone");
	}
	else{
		phoneOrMail=document.getElementById("email");
		console.log("email");
	}
const password=document.getElementById("password");

const errorMsg=document.querySelector(".error");
const phError=document.querySelector(".phError");
const passError=document.querySelector(".passError");

form.addEventListener('submit',e=>{
	e.preventDefault();
	validateInputs();
});

const setError=(event,msg)=>{
	event.innerText=msg;
}

const validateInputs=()=>{

	const phoneOrMailValue=phoneOrMail.value.trim();
	const passwordValue= password.value.trim();
	
	let phnoOk=false;
	let passOk=false;
	if(buttonValue ===1)
	{
		if(phoneOrMailValue ==='')
		{
			setError(phError,'*Phone number Required');
		}
		else 
		{
			setError(phError,'');
			phnoOk=true;
		}
	}
	else{
		if(phoneOrMailValue ==='')
		{
			setError(phError,'*Email Required');
		}
		else 
		{
			setError(phError,'');
			phnoOk=true;
		}
	}
	if(passwordValue ==='')
	{
		setError(passError,'*Password Required');
	}
	else 
	{
		setError(passError,'');
		passOk=true;
	}
	if(phnoOk & passOk)
	{
		updateOrNot(phoneOrMailValue,passwordValue,buttonValue);
	}
}

const updateOrNot=(phoneOrMailValue,password,btnValue)=>{
	fetch(`http://localhost:8147/SantoshIT/loginValidation`,{
		method:"POST",
		headers:{
			"content-type":"Application/x-www-form-urlencoded"
		},
		body:`phoneOrMailValue=${encodeURIComponent(phoneOrMailValue)}&password=${encodeURIComponent(password)}&btnValue=${encodeURIComponent(btnValue)}`
	})
	.then(res=> res.json())
	.then(data=>{
	 if(data.message !== 'ok')
	 {
		 	
		if(data.event === 'phError')
		{
			setError(phError,data.message);
			setError(errorMsg,'');
			setError(passError,'');
		}
		else if(data.event === 'passError')
		{
			setError(passError,data.message);
			setError(errorMsg,'');
			setError(phError,'');
		}
		else
		{ 
			setError(errorMsg,data.message);
			setError(passError,'');
			setError(phError,'');
		}
	 }
	 else{
		 document.getElementById("btnValue").value = buttonValue;
		 form.action='./HomeDataRetrive';
		form.submit();
	 }
	})
}