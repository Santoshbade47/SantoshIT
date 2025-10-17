const loginPop= document.querySelector(".login");
const loginClick= document.querySelector(".clickLogin");
const loginClose=document.querySelector(".close");

loginClose.addEventListener('click', ()=>{
	loginPop.classList.add('pop');
})
loginClick.addEventListener('click', ()=>{
	loginPop.classList.remove('pop');
})


