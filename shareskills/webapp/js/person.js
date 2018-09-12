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
            if (data[0].result==true){
                alert("头像上传成功！");
            }
        },
        error: function (data) {
            alert("头像上传失败！");
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
            if (data[0].result==true) {
                alert("背景上传成功！");
            }
        },
        error: function (data) {
            alert("背景上传失败！");
        }
    });
}