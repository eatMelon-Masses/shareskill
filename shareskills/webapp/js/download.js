window.onload=function (){
	var oHeader=document.getElementById("downloadTopbar");
	var aA=oHeader.getElementsByTagName("a");
	var aSection=document.getElementsByTagName("section");
	
	for (var i=0;i<aA.length;i++){
		aA[i].index=i;
		aA[i].onclick=function (){
			for (var i=0;i<aA.length;i++){
				aSection[i].style.display="none";
			}
			aSection[this.index].style.display="block";
		}
	}
};

