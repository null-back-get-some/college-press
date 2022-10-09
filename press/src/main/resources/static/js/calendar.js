/**
 * 
 */



var calendar = null;

document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');

	calendar = new FullCalendar.Calendar(calendarEl, {
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'SaveButton dayGridMonth,timeGridWeek,timeGridDay'
		},
		customButtons: {
			SaveButton: { //커스텀 저장버튼
				text: 'save',
				click: function() {
					allSave();
				}
			}
		},
		initialDate: '2022-09-12',
		navLinks: true, // can click day/week names to navigate views
		selectable: true,
		selectMirror: true,
		select: function(arg) {
			var title = prompt('Event Title:');
			if (title) {
				calendar.addEvent({
					title: title,
					start: arg.start,
					end: arg.end,
					allDay: arg.allDay
				})
			}
			calendar.unselect()
		},
		eventClick: function(arg) {
			/*if (confirm('Are you sure you want to delete this event?')) {
			  arg.event.remove()
			} */


			if (confirm("'" + arg.event.title + "' 일정을 삭제하시겠습니까 ?")) {
				// 확인 클릭 시
				arg.event.remove();


				console.log(arg.event.id);
				var events = new Array(); // Json 데이터를 받기 위한 배열 선언
				var obj = new Object();
				obj.id = arg.event.id;
				obj.title = arg.event._def.title;
				obj.start = arg.event._instance.range.start;
				obj.end = arg.event._instance.range.end;
				events.push(obj);

				console.log(events);
				var deletedata = JSON.stringify(events)
				console.log("delete data : " + deletedata);
				deleteData(deletedata);
			}
		},
		editable: true,
		locale: 'ko',
		dayMaxEvents: true, // allow "more" link when too many events
		events: [
			{
				title: 'All Day Event',
				start: '2022-09-01'
			},
			{
			  title: 'Long Event',
			  start: '2022-09-07',
			  end: '2022-09-10'
			},
			{
			  groupId: 999,
			  title: 'Repeating Event',
			  start: '2022-09-09T16:00:00'
			},
			{
			  groupId: 999,
			  title: 'Repeating Event',
			  start: '2022-09-16T16:00:00'
			},
			{
			  title: 'Conference',
			  start: '2022-09-11',
			  end: '2022-09-13',
			  backgroundColor : 'gray'
			},
			{
			  title: 'Meeting',
			  start: '2022-09-12T10:30:00',
			  end: '2022-09-12T12:30:00'
			},
			{
			  title: 'Lunch',
			  start: '2022-09-12T12:00:00'
			},
			{
			  title: 'Meeting',
			  start: '2022-09-12T14:30:00'
			},
			{
			  title: 'Happy Hour',
			  start: '2022-09-12T17:30:00'
			},
			{
			  title: 'Dinner',
			  start: '2022-09-12T20:00:00'
			},
			{
			  title: 'Birthday Party',
			  start: '2022-09-13T07:00:00'
			},
			{
			  title: 'Click for Google',
			  url: 'http://google.com/',
			  start: '2022-09-28'
			}
		]
	});

	calendar.render();
});


//////////////////////////////////////////////////////
///////////////데이터 저장, 수정, 삭제 메소드////////////////
//////////////////////////////////////////////////////

//데이터 json 형태로 가공하는 메소드
function allSave() {
	var allEvent = calendar.getEvents();
	//console.log(allEvent);
	var events = new Array();
	for (var i = 0; i < allEvent.length; i++) {

		var obj = new Object();
		obj.writer = 'name';
		obj.title = allEvent[i]._def.title;
		//moment.js 이용하여 날짜 포맷 변환
		obj.start = moment(allEvent[i]._instance.range.start).format('YYYY/MM/DD hh:mm:ss');
		obj.end = moment(allEvent[i]._instance.range.end).format('YYYY/MM/DD hh:mm:ss');

		//obj.startMonth = calendar.currentData.currentDate.getMonth()+1;
		//		alert("현재 달 : "+(allEvent[i]._context.currentDate.getMonth())+1);
		//			alert(typeof(parseInt(allEvent[i]._context.currentDate.getMonth())));
		//alert(calendar.currentData.currentDate.getMonth()+1);
		events.push(obj);

	}
	var jsondata = JSON.stringify(events);
	console.log("jsondata : " + jsondata);
	savedata(jsondata);
}

//ajax사용하여 저장할 데이터 controller로 전송
function savedata(jsondata) {
	const url = "/manager/calendar"
	$.ajax({
		type: "POST",
		url: url,
		contentType: "application/json",
		data: jsondata,
		dataType: "json",
		async: false
	}).done(function(result, status) {
		//location.reload();
		alert(status);
	}).fail(function(request, status, error) {
		alert("save 에러 발생 : " + error);
	})
}

//데이터 삭제 메소드
function deleteData(deletedata) {
	const url = "/manager/calendar"
	$.ajax({
		type: "DELETE",
		url: url,
		contentType: "application/json",
		data: deletedata,
		dataType: "json",
		async: false

	}).done(function(result, status) {
		location.reload();
		alert("delete " + status);
	}).fail(function(request, status, error) {
		alert("delete 에러 발생 : " + error);
	})
}
