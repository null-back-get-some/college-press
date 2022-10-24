/**
 * 
 */
 
 function goWrite(frm) {
	var title = frm.title.value;
	var content = frm.contents.value;
	
	if (title.trim() == ''){
		alert("제목을 입력해주세요");
		return false;
	}
	if (content.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	}
	frm.submit();
}

