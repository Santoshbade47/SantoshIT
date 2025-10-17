
let imgId=1;

function loadImg(id){
	fetch(`http://localhost:8147/SantoshIT/scrollData`)
	.then(res=> res.json())
	.then(arr=>{
      // 'arr' is an array of image objects
      const img = arr.find(obj => obj.id === id);
      if(img){
        document.getElementById("image").src=img.url;
      }
    })
}

function loadImgAuto(){
	imgId++;
	if(imgId>5)imgId=1;
	loadImg(imgId);
}

function nextClick(){
	imgId++;
	if(imgId>5)imgId=1;
	loadImg(imgId)
}

function preClick(){
	imgId--;
	if(imgId<1)imgId=5;
	loadImg(imgId)
}

setInterval(loadImgAuto,4000);
loadImg(1);
