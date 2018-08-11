function createXMLHttpRequest(){
	var xmlrequest;
	if(window.XMLHttpRequest){
		xmlrequest=new XMLHttpRequest();
	}else if(window.ActiveXObject){
		try{
			xmlrequest=new ActiveXObject("Msxm12.XMLHTTP");
		}catch(e){
			try{
				xmlrequest=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				xmlrequest="";
			}
		}
	}
	return xmlrequest;
}

//获取数据的函数
function change(){
	var xmlrequest=createXMLHttpRequest();
	xmlrequest.open("GET","http://222.210.116.32:10002/browseMember/json",true);
	xmlrequest.onreadystatechange=function (){
		
		
		if(xmlrequest.readyState==4&&xmlrequest.status==200){
			
			var json=eval('('+xmlrequest.responseText+')');
			//var data=JSON.parse(xmlrequest.responseText);
			var content="<tbody>";
			for(var i=0;i<json.length;i++){
				content+="<tr>";
				for(var j in json[i]){
					content+="<td>"+json[i][j]+"</td>";
				}
				content+="</tr>";
			}
			content="</tbody>";
			document.getElementById("accountTab").innerHTML=content;
		}
	};
	xmlrequest.send();
};



window.onload=function (){
	var oMenuUl=document.getElementById("menu");
	var aMenuLi=oMenuUl.children;
	var aMenuLiUl=oMenuUl.getElementsByTagName("ul");
	var aSection=document.getElementsByTagName("section");
	
	for(var i=0;i<aMenuLi.length;i++){
		
		aMenuLi[i].index=i;
		aSection[0].style.display="block";
		aSection[1].style.display="none";
		aSection[2].style.display="none";
		aSection[3].style.display="none";
		aMenuLi[i].onclick=function (){
			for(var i=0;i<aMenuLi.length;i++){
				aSection[i].style.display="none";
			}
			aSection[this.index].style.display="block";
		};
	}
	
	
	var json={"num":"1","account":"yu","sex":"nv","time":"2018"};
	
	document.getElementById("addAccount").onclick=function (){
		
	};
	
	var aTr=document.getElementsByTagName("tr");
	for(var i=0;i<aTr.length;i++){
		var oldColor="";
		aTr[i].onmouseover=function (){
			oldColor=this.style.background;
			this.style.background="#6BC9D0";
		};
		aTr[i].onmouseout=function (){
			this.style.background=oldColor;
		};
		if (i%2==0){
			aTr[i].style.background="whitesmoke";
		}else{			
			aTr[i].style.background="white";
		}
	}	
};
/*
	for (var i=0;i<aMenuLi.length;i++){
		var timer=null;
		aMenuLi[i].index=i;
		aMenuLi[i].onmouseover=function (){
			//clearTimeout(timer);
			aMenuLiUl[this.index].style.display="block";
		}
		aMenuLi[i].onmouseout=function (){
			//timer=setTimeout(function (){
				aMenuLiUl[this.index].style.display="none";
			//},500);
		}
	}
*/