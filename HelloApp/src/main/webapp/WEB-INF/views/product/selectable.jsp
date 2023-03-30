<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='full/dist/index.global.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', async function() {
	 
	  
	 let events  = [
		{title:'내생일', start:'2023-03-05'},
		{title:'여행', start:'2023-03-08', end:'2023-03-13'}
	 ]; 
	  
    // fetch API를 사용.
    let promise1 = await fetch('calendarAjax.do')
    let promise2 = await promise1.json()
   	for(let i=0; i<promise2.length; i++){
        let result = {title: promise2[i].title, start: promise2[i].startDate, end: promise2[i].endDate}
   		events.push(result);
   	}
    
    
    
	
	  
	var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: new Date(),
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('일정등록:');
        if (title) {
            
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
          
            fetch('calendarInsertAjax.do', {
	    		method:'post',
	    		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	    		body: 'title=' + title + '&startStr='+ arg.startStr  + '&endStr='+ arg.endStr
	    		})
            .then(resolve => resolve.json())
            .then(result => {
            	console.log(result);
            })
            .catch(reject => console.error(reject))
          
        }
        calendar.unselect()
      },
      
     eventClick: function(arg) {
        if (confirm('해당 일정을 삭제하시겠습니까?')) {
          arg.event.remove()
        }
        console.log(arg);
//         fetch('')
//         .then(resolve=> resolve.json())
//         .then(result => {
        	
//         })
//         .catch(reject => console.error(reject)))
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: events

    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>
