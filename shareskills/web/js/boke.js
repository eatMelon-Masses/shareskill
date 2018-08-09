window.onload=function (){
	var oMenu=document.getElementById("bokedivMenu");
	var aA=oMenu.getElementsByTagName("a");
	var oBokedivIn=document.getElementById("bokedivIn");
	var aBokedivIn=oBokedivIn.children;
	
	
	
	/*for (var i=0;i<aA.length;i++){
		/!*aBokedivIn[0].style.display="none";
		aBokedivIn[1].style.display="none";
		aBokedivIn[2].style.display="none";
		aBokedivIn[3].style.display="none";
		aBokedivIn[4].style.display="none";
		aBokedivIn[5].style.display="none";
		aBokedivIn[6].style.display="none";
		aBokedivIn[7].style.display="none";
		aBokedivIn[8].style.display="none";
		aBokedivIn[9].style.display="none";*!/
		aA[i].index=i;
		aA[i].onclick=function (){
			for (var i=0;i<aA.length;i++){
				aA[i].className="";
				aBokedivIn[i].style.display="none";
			}
			this.className="click";
			aBokedivIn[this.index].style.display="block";
		}
	}*/
};




////		aA[i].onclick=function (){
////			var request=new XMLHttpRequest();
////			var method="GET";
////			var url=this.href;
////			
////			request.open(method,url);
////			request.send(null);
////			
////			request.onreadystatechange=function (){
////				if (request.readyState==4) {
////					if (request.status==200 ||request.status==304) {
////						var result=request.responseText;
////						var object=eval("("+result+")");
////						var rs=object.result1;
////						alert(rs);					
////					}
////				}
////			};
////			return false;
////		};
////	}
//};
