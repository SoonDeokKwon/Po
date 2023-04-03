<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="jquery/jquery-3.6.4.min.js"></script>

    <script>
        // document 로딩 후 처리.
        $(document).ready(function () {
            // fetch('url', {option})
            $.ajax({

                url: "memberListJquery.do", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
                data: {
                    name: "홍길동", //request.getParameter('name');  
                    id: 'user01' //request.getParameter('name'); 
                }, // HTTP 요청과 함께 서버로 보낼 데이터                                            
                method: "GET", // HTTP 요청 방식(GET, POST)
                dataType: "json", // 서버에서 보내줄 데이터의 타입: JSON.parse() 실행.
                success: function (result) { // 서버 요청의 성공 시 실행.
                    //Json.parse(result) => dataType: "json" 설정을 해주지 않았을 때는 해줘야 함.
                    console.log(result);
                    $(result).each(function (idx, member) {
                        console.log(idx, member);
                        $('#list').append(
                            // tr>rd*4 생성.
                            $('<tr />').append($('<td />').text(member.memberId),
                                $('<td />').text(member.memberName),
                                $('<td />').text(member.memberPw),
                                $('<td />').text(member.memberAddr),
                                $('<td />').text(member.memberTel),
                                $('<td />').append($('<button />')
                                    .text('삭제')
                                    .on('click', rowDeleteFnc)
                                ),
                                $('<td />').append($('<input type="checkbox" />'))
                            )
                        )
                    });
                },
                error: function (err) { // 서버 요청의 실패 시 실행.
                    console.error(err);
                }
            }) // $.ajax() 끝부분

            // 추가버튼 이벤트 & 이벤트 핸들러
            $('#addBtn').on('click', function (e) {
                e.preventDefault(); // 전송기능 차단.
                // 사용자가 필수입력값을 입력했는지 validation 하고
                let idVal = $('#id').val();
                //let idVal = $('form>input[id="id"]').val()
                console.log($('form input[id="id"]'));
                let nameVal = $('#name').val();
                let pwVal = $('#passwd').val();
                if (!idVal||!nameVal||!pwVal) {
                	alert("ID, 이름, pw는 필수 입력 항목입니다.");
                }else{
                $.ajax({
    			    url: "memberAddJquery.do", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
    			    data: { memberId:$('#id').val() , memberName:$('#name').val() , memberAddr:$('#addr').val() ,  memberTel:$('#tel').val() , memberPw:$('#passwd').val()},		// HTTP 요청과 함께 서버로 보낼 데이터
    			    method: "post",		 // HTTP 요청 방식(GET, POST)
    			    dataType: "json",		// 서버에서 보내줄 데이터의 타입
    			    success: function(result) {
    			    	if (result.retCode =='Success') {
    			    		// 성공.
    			    		alert("성공!!!");
    		                $('#list').append( $('<tr />').append($('<td />').text($('#id').val()),
    		                            			   			  $('<td />').text($('#name').val()),
    		                            		  	 			  $('<td />').text($('#passwd').val()),
    		                            			 			  $('<td />').text($('#addr').val()),
    		                            			 			  $('<td />').text($('#tel').val()),
    		                            	   		 			  $('<td />').append($('<button />').text('삭제').on('click', rowDeleteFnc)),
    		                           				 			  $('<td />').append($('<input type="checkbox" />'))
    		                        							 )
    		                    			);    			    		
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
                	
                })

            }
            })
            

            

            // 삭제버튼 이벤트 핸들러.
            function rowDeleteFnc() {
                $(this).parentsUntil('tbody').remove();
            }

            // 선택삭제 이벤트 핸들러.
            $('#delSelected').on('click', function(e) {
                e.preventDefault(); // 전송기능 차단.

                $('#list input:checked').closest('tr').remove();

            })

            // 전체 선택, 해제 이벤트 핸들러.
            $('th>input[type="checkbox"]').on('change', function() {
                // $('td>input').attr('checked', 'checked');
                
                $('td>input').prop({
                    checked: this.checked
                });
                
            })
            
            // th>input 과  td>input  을 비교해서 전체선택이 되도록.
            //	선택된 갯수를 비교?
            // ajax 호출의 결과로 만들어지는 부분.
            $('#list').on('change', 'td>input[type="checkbox"]', function() {
                console.log(this);
                let checkCnt = $('td>input[type="checkbox"]:checked').length;
                let allCnt = $('td>input[type="checkbox"]').length;
                // 전체 갯수 vs 선택된 갯수
                if(checkCnt==allCnt) {
                	$('th>input[type="checkbox"]').prop({checked: true});
                } else {
                	$('th>input[type="checkbox"]').prop({checked: false});
                }
                
            })


        });
    </script>

</head>

<body>
    <div>
        <form>
            <table class="table" border="1">
                <tr>
                    <td>Id:</td>
                    <td><input type="text" id="id"></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" id="name"></td>
                </tr>
                <tr>
                    <td>Passwd:</td>
                    <td><input type="text" id="passwd"></td>
                </tr>
                <tr>
                    <td>Addr:</td>
                    <td><input type="text" id="addr"></td>
                </tr>
                <tr>
                    <td>Tel:</td>
                    <td><input type="text" id="tel"></td>
                </tr>
                <tr>
                    <td colspan='2' align="center">
                        <button id="addBtn">등록</button>
                        <button id="delSelected">선택삭제</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!-- 목록 -->
    <div>
        <table class="table" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Passwd</th>
                    <th>주소</th>
                    <th>연락처</th>
                    <th>삭제</th>
                    <th><input type="checkbox"></th>
                </tr>
            </thead>
            <tbody id="list">
            </tbody>
        </table>
    </div>
</body>

</html>