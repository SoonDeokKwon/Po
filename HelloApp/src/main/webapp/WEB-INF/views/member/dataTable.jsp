<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="jquery/jquery-3.6.4.min.js"></script>
<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function () {
	    var t = $('#example').DataTable({
	        ajax: 'dataTableAjax.do',
	    });
	    
	    $('#addRow').on('click', function () {
	    	$.ajax({
			    url: "dataTableAdd.do", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
			    data: { firstName: $('#first').val(), lastName:  $('#last').val(), email: $('#email').val(), hireDate: $('#hireDate').val(), salary:  $('#salary').val() },		// HTTP 요청과 함께 서버로 보낼 데이터
			    method: "post",		 // HTTP 요청 방식(GET, POST)
			    dataType: "json",
			    success: function(result) {
			    	if (result.retCode =='Success') {
			    		// 성공.
			    		alert("성공!!!");
			    		console.log(result);
			    		t.row.add([ result.employee.employeeId, $('#first').val(),  $('#last').val(),  $('#email').val(),  $('#hireDate').val(),  $('#salary').val()]).draw(false);
			    	} else if(result.retCode =='Fail') {
			    		// 처리중 에러.
			    		alert("실패!!");
			    	} else {
			    		// 반환코드 확인.
			    		alert("레코드를 확인하세요!!!");
			    	}
			    },
			    error: function (rejec) {
			    	console.error(rejec);
			    }
	 		
	       
	    });
	});
	

	    $('#example tbody').on('click', 'tr', function () {
	        if ($(this).hasClass('selected')) {
	            $(this).removeClass('selected');
	        } else {
	            t.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    });
	 
	    $('#button').click(function () {
	    	$.ajax({
	    		 url: ""
	    		 data: 
	    		 method: "post",
	    		 dataType: "json",
	    		 success: function(result) {
	    			 t.row('.selected').remove().draw(false);
	    		 } else if(result.retCode =='Fail') {
	    			// 처리중 에러.
			    	alert("실패!!");
	    		 } else {
	    			// 반환코드 확인.
			    	alert("레코드를 확인하세요!!!");
	    		 },
				 error: function (rejec) {
				 	console.error(rejec);
				 }
	    	})
	        
	    });	    
		
	 
	});
</script>
</head>
<body>
	<p>dataTable</p>
	<input type="text" id="first"><br>
	<input type="text" id="last"><br>
	<input type="email" id="email"><br>
	<input type="date" id="hireDate"><br>
	<input type="text" id="salary"><br>
	<button id="addRow">등록</button><br>
	<br>
	<button id="button">삭제</button>
	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>사원번호</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>HireDate</th>
                <th>Salary</th>
            </tr>
        </thead>
		<tfoot>
            <tr>
                <th>사원번호</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>HireDate</th>
                <th>Salary</th>
            </tr>		
		</tfoot>
    </table>	
	
</body>
</html>