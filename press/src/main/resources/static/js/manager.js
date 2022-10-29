/**
 * 
 */

var saveBtn = document.querySelector(".btn-addAdmin");
var deleteBtn = document.querySelector(".btn-admindelete");
var inputemail = document.querySelector("#input-email");

inputemail.addEventListener('keyup', function(e) {
	if (e.keyCode === 13) {
		var email = document.getElementById('input-email').value;
		console.log(email);

		var data = {
			"email": email
		}
		var newdata = JSON.stringify(data);

		addAdmin(newdata);
	}
})

saveBtn.addEventListener('click', function() {
	var email = document.getElementById('input-email').value;
	console.log(email);

	var data = {
		"email": email
	}
	var newdata = JSON.stringify(data);

	addAdmin(newdata);

});




deleteBtn.addEventListener('click', function() {
	var email = document.querySelector('.member-email').innerHTML;
	var data = {
		"email": email
	}
	var newdata = JSON.stringify(data);
	deleteAdmin(newdata);
});

function addAdmin(jsondata) {
	const url = "/manager/manager"
	$.ajax({
		type: "POST",
		url: url,
		contentType: "application/json",
		data: jsondata,
		dataType: "json",
		async: false
	}).done(function(result, status) {
		console.log(status);
		alert("관리자 권한이 부여되었습니다.");
		location.reload();
	}).fail(function(request, status, error) {
		alert("update 에러 발생 : " + error + ",  " + request);
		console.log(request);
	})

};

function deleteAdmin(jsondata) {
	const url = "/manager/manager"
	$.ajax({
		type: "PATCH",
		url: url,
		contentType: "application/json",
		data: jsondata,
		dataType: "json",
		async: true
	}).done(function(result, status) {
		console.log(status);
		alert("관리자 권한이 삭제되었습니다.");
		location.reload();
	}).fail(function(request, status, error) {
		alert("update 에러 발생 : " + error + ",  " + request);
		console.log(request);
	})

};
