function toDou(n){
	if(n<10){return "0"+n;}
	else{return ""+n;}//""为了让它返回的是一个字符串而不能相加
}

window.onload=function (){
	var oDynamic=document.getElementById("dynamic");
	var oDynamicDiv=document.getElementById("dynamicDiv");
	var aInput=oDynamic.getElementsByTagName("input");
	var oreleaseBtn=document.getElementById("releaseBtn");
	var aDiv=oDynamicDiv.children;
	oreleaseBtn.onclick=function (){
		confirm("你点击了发布按钮");
	}
	
	for (var i=0;i<aInput.length;i++){
		aInput[i].index=i;
		aInput[0].className="btnclass";
		aDiv[0].style.display="block";
		aInput[i].onclick=function (){
			for (var i=0;i<aInput.length;i++){
				aInput[i].className="";
				aDiv[i].style.display="none";
			}
			this.className="btnclass";
			aDiv[this.index].style.display="block";
		}
	}
	//时钟
	var oClock=document.getElementById("clock");

	//var aImg=oClock.getElementsByTagName("img");
	var oClockYear=document.getElementById("clockYear");
	var oClockHours=document.getElementById("clockHours");
	var oClockDay=document.getElementById("clockDay");
	var aClockYearP=oClockYear.children;
	var aClockHoursP=oClockHours.children;
	var aClockDayP=oClockDay.children;
		
	function tick(){
		var oDate=new Date();
		var str=toDou(oDate.getFullYear())+"-"+toDou(oDate.getMonth())+"-"+toDou(oDate.getDate());
		var str1=toDou(oDate.getHours())+":"+toDou(oDate.getMinutes())+":"+toDou(oDate.getSeconds());
		var day=new Array(7);
		day[0]="日";day[1]="一";day[2]="二";day[3]="三";
		day[4]="四";day[5]="五";day[6]="六";
		var str2="星期"+day[oDate.getDay()];
		
		for(var i=0;i<aClockYearP.length;i++){
			aClockYearP[i].innerHTML=str.charAt(i);
		}
		for(var i=0;i<aClockHoursP.length;i++){
			aClockHoursP[i].innerHTML=str1.charAt(i);
		}
		for(var i=0;i<aClockDayP.length;i++){
			aClockDayP[i].innerHTML=str2.charAt(i);
		}
	}
	setInterval(tick,1000);
	tick();
	
	// /*点赞*/
	// var oVis=document.getElementById("zan");
	//
	// oZan.onclick=function (){
	// 	var src="../img/zan2.png";
	// 	this.src=src;
	// }
};
