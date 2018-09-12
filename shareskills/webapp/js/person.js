window.onload=function (){
	var oPersonMenu=document.getElementById("personMenu");
	var oPerson=document.getElementById("person");
	var aInput=oPersonMenu.getElementsByTagName("input");
	var aSection=oPerson.getElementsByTagName("section");
	
	for (var i=0;i<aInput.length;i++) {
		aInput[i].index=i;
		aInput[i].onclick=function (){
			for (var i=0;i<aInput.length;i++) {
				aInput[i].className="";
				aSection[i].style.display="none";
			}
			this.className="active";
			aSection[this.index].style.display="block";
		};
	}

};
//方式一 Jquery实现上传头像
function saveUser2() {
    var file = document.getElementById("file").files[0];
    var formData = new FormData();

    formData.append('upload', file);

    $.ajax({
        url: "/json/uploaduserImg",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        mimeType: "multipart/form-data",
        success: function (data) {
            alert("上传头像成功!");
        },
        error: function (data) {
            alert("上传头像失败!");
        }
    });
}

//方式一 Jquery实现上传背景
function saveUser1() {
    var file = document.getElementById("bgfile").files[0];
    var formData = new FormData();
    formData.append('type','1')
    formData.append('upload', file);

    $.ajax({
        url: "/json/uploaduserImg",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        mimeType: "multipart/form-data",
        success: function (data) {
            alert("上传背景成功!");
        },
        error: function (data) {
            alert("上传背景失败!");
        }
    });
}