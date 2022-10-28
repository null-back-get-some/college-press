/**
 * 
 */

var saveBtn = document.querySelector(".btn-addAdmin");


saveBtn.addEventListener('click', function() {
	var email = document.getElementById('input-email').value;
	console.log(email);
	
	var data ={
		"email" : email
	}
	var newdata = JSON.stringify(data);
	
	addAdmin(newdata);
	
})

function addAdmin(jsondata){
	const url = "/manager/manager"
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		data :jsondata,
		dataType : "json",
		traditinal:true
	}).done(function(result, status) {
		console.log(status);
		alert("관리자 권한이 부여되었습니다.");
	}).fail(function(request, status, error) {
		alert("update 에러 발생 : " + error +",  "+request);
		console.log(request);
	})
			
};

