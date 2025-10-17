const form=document.getElementById("form");
const error=document.querySelector(".error");
const utrNo=document.getElementById("utrNum");

form.addEventListener('submit',e=>{
	e.preventDefault();
	validateInputs();
})

const setError = (mssg) =>{
	error.innerText=mssg;
}

const validateInputs=()=>{
	const utrNoValue=utrNo.value.trim();
	let isOk=false;
	if(utrNoValue === '')
	{
		setError('Please enter UTR No / UPI Txn No');
	}
	else if(utrNoValue.length < 8)
	{
		setError('Please enter minimum 8 digits');
	}
	else if(utrNoValue.length !== 12)
	{
		setError('Please enter valid UTR');
	}
	else{
		setError('');
		isOk=true;
	}
	
	if(isOk)
	{
		form.action='./PaymentDetInsert';
		form.submit();
	}
}