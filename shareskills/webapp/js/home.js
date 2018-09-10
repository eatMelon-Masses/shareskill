window.onload = function(){//使页面加载完后才开始读取
    // 让图片做匀速运动，匀速动画的原理是 当前的位置 + 速度  即 offsetLeft + speed,动画函数 第一个参数，代表谁动  第二个参数 动多少
    function animate(obj,target){
   		clearInterval(obj.timer);// 首先清除掉定时器
   		var speed = obj.offsetLeft < target ? 15 : -15;// 用来判断 是+ 还是 -  即说明向左走还是向右走
   		obj.timer = setInterval(function(){
   			var result = target - obj.offsetLeft;//它们的差值不会超过speed
    		obj.style.left = obj.offsetLeft + speed + "px";			
    	        if (Math.abs(result) <= Math.abs(speed)) {// 有可能有小数的存在，所以在这里要做个判断 	
    	        	clearInterval(obj.timer);
    	      		obj.style.left = target + "px";
    	        }
    	},10);
    };
//获得元素
	var picBox = document.getElementById("picBox");
	var ul = document.getElementById("ul");
	var ulLis = ul.children;// 获得ul的盒子 以此来生成ol中li的个数
	var liWidth = ul.children[0].offsetWidth;// 获得每个li的宽度
	
	// 操作元素
	// 因为要做无缝滚动，所以要克隆第一张，放到最后一张后面
	// 1. 克隆元素
	ul.appendChild(ul.children[0].cloneNode(true));
	
	// 2.创建ol 和li
	var ol = document.createElement("ol");//创建ol元素
	picBox.appendChild(ol);// 把ol放到scroll盒子里面去
	for (var i=0;i<ulLis.length-1;i++) {
		var li = document.createElement("li");// 创建li元素
		//li.innerHTML = i + 1;// 给li里面添加文字  1 2 3 4 5
		ol.appendChild(li);// 将li元素添加到ol里面
	}
	ol.children[0].className = "active";
	
	
	// 3. 动画开始  (鼠标经过第几个小圆点，就要展示第几张图片，并且小圆点的颜色也发生变化)
	var olLis = ol.children;
	for (var i = 0; i < olLis.length;i++) {
		// 给ol中的每个li添加一个属性 index  用来获得当前第几个li的索引号
		olLis[i].index = i;
		olLis[i].onmouseover = function(){
			for (var j = 0;j<olLis.length;j++) {
				olLis[j].className = "";
			}
			this.className = "active";
			// 接着调用动画函数 ，根据第几个li 展示第几张图片 第一个参数谁做动画  第二个参数 走多少
			animate(ul,-this.index * liWidth);
			// 接着让小圆点和 key 等于当前的索引号
			circle = key = this.index;
		}
	}
	
	// 4. 添加定时器  让图片自动轮播
	var timer = null;
	var key = 0;// 控制播放的张数
	var circle = 0;// 控制小圆点
	
	timer = setInterval(autoplay,2500);// 自动轮播
	function autoplay(){
		/*自动轮播时,要对播放的张数key进行一个判断,如果播放的张数超过ulLis.length-1,
		就要重头开始播放.  因为我们克隆了第一张并将其放在最后面,所以我们要从第二张图片开始播放*/
		key++; // 先++
		if(key > ulLis.length-1){// 后判断
			
			ul.style.left = 0; // 迅速调回
			key = 1; // 因为第6张是第一张，所以播放的时候是从第2张开始播放
		}
		// 动画部分
		animate(ul,-key*liWidth);
		// 小圆点circle   当显示第几张图片是，对应的小圆点的颜色也发生变化 
		
		/*同理,对小圆点也要有一个判断*/
		circle++;
		if (circle > olLis.length-1) {
			circle = 0;
		}
		// 小圆点颜色发生变化
		for (var i = 0 ; i < olLis.length;i++) {
			// 先清除掉所用的小圆点类名
			olLis[i].className = ""; 
		}
		// 给当前的小圆点 添加一个类名
		olLis[circle].className = "active";
		
	}
	
	// 最后，鼠标经过大盒子停止定时器
	picBox.onmouseover = function(){
		clearInterval(timer);
	}
	// 鼠标离开大盒子时 开启定时器
	picBox.onmouseout = function(){
		timer = setInterval(autoplay,2500);  	
	} 
};



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
			
//			var json=eval('('+xmlrequest.responseText+')');
//			//var data=JSON.parse(xmlrequest.responseText);
//			var content="<tbody>";
//			for(var i=0;i<data.length;i++){
//				content+="<tr>";
//				for(var j in data[i]){
//					content+="<td>"+data[i][j]+"</td>";
//				}
//				content+="</tr>";
//			}
//			content="</tbody>";
//			document.getElementById("accountTab").innerHTML=content;
		}
	};
	xmlrequest.send();
};
