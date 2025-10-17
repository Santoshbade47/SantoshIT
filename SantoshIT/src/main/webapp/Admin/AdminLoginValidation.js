const adminId=document.getElementById("aid");
const password=document.getElementById("password");

const form=document.querySelector("form");
const errorAdm=document.querySelector(".errorAdm");
const errorPass=document.querySelector(".errorPass");

form.addEventListener('submit',e=>{
	e.preventDefault();
	validateInputs();
})

const setError = (e,mssg) =>{
	e.innerText=mssg;
}

const validateInputs=()=>{
	const adminIdVal=adminId.value.trim();
	const passwordVal=password.value.trim();
	
	let adm=false;
	let pass=false;
	
	if(adminIdVal==='')
	{
		setError(errorAdm,'* Admin Id is required');
	}
	else 
	{
		setError(errorAdm,'');
		adm=true;
	}
	if(passwordVal==='')
	{
		setError(errorPass,'* Password is required');
	}
	else 
	{
		setError(errorPass,'');
		pass=true;
	}
	
	if(adm && pass)
	{
		checkAdminAndPass(adminIdVal,passwordVal,function (isOk){
			if(isOk){
				form.action='./Admin.html';
				form.submit();
			}
		});
	}
}

const checkAdminAndPass=(admId,pass,isok)=>{
	
	fetch(`http://localhost:8147/SantoshIT/adminLogin`,{
		method:"POST",
		headers:{
			"Content-type":"application/x-www-form-urlencoded"
		},
		body:`amdId=${encodeURIComponent(admId)}&pass=${encodeURIComponent(pass)}`
	})
	.then(res=> res.json())
	.then(data=>{
		if(data.massege && data.massege.trim() !=='')
		{
			if(data.event==='errorPass')setError(errorPass,data.massege);
			else setError(errorAdm,data.massege);
		}
		else
		{
			setError('');
			isok(true);
		}
	})
}