
function mouseover(){
	document.getElementById("ts").style.display="block";
}
function mouseout(){
	document.getElementById("ts").style.display="none";
}

function createXMLHttpRequest(){
	var request;
	if(window.XMLHttpRequest){
		request=new XMLHttpRequest();
	}else if(window.ActiveXObject){
		try{
			request=new ActiveXObject("Msxm12.XMLHTTP");
		}catch(e){
			try{
				request=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				request="";
			}
		}
	}
	return request;
}
function XHRequest(){
	var request=createXMLHttpRequest();
	request.open("POST","");
	var json={"username":document.getElementById("username").value
			,"password":document.getElementById("password").value};
	request.send(json);
	request.onreadystatechange=function (){
		if(request.readyState==4&&request.status==200){
			self.location="index.html";
		}
	};
};
/*
window.onload=function (){
function tooltip(){//提示框
	var oUser=document.getElementById("username");
	var oPwd=document.getElementById("password");
	
	if(oUser.value==null || oPwd.value==null){
		alert("请输入用户名和密码");
	}
	else if(){
		
	}
}
};*/