const form=document.getElementById("form");
const stuId=document.getElementById("stuId");
const error=document.querySelector(".error");
const suc=document.querySelector(".success");


let buttonVal;
function getValue(val){
	buttonVal=val.value.trim();
}

form.addEventListener('submit',e=>{
	e.preventDefault();
	validateInputs();
});
const setError=(msg)=>{
	error.innerText=msg;
}
const setSuccess=(msg)=>{
	suc.innerText=msg;
}
const validateInputs=()=>{

	
	let idValue=stuId.value.trim();
	let ok=false;
	if(idValue ==='')
	{
		setError('*Student Id Required');
	}
	else 
	{
		setError('');
		ok=true;
	}
	if(ok)
	{
		updateOrNot(idValue,buttonVal);
	}
}

const updateOrNot=(stuId,blockOrActive)=>{
	fetch(`http://localhost:8147/SantoshIT/blockAccount`,{
		method:"POST",
		headers:{
			"content-type":"Application/x-www-form-urlencoded"
		},
		body:`stuId=${encodeURIComponent(stuId)}&blockOrActive=${encodeURIComponent(blockOrActive)}`
	})
	.then(res=> res.json())
	.then(data=>{
		setError(data.errMsg);
		setSuccess(data.message);
	})
}