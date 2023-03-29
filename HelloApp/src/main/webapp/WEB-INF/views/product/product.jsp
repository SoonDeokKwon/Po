<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="e"%>

        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="img/${code.image }" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1">code: ${code.productCode }</div>
                        <h1 class="display-5 fw-bolder">${code.productName }</h1>
                        <div class="fs-5 mb-5">
                            <span class="text-decoration-line-through">${code.originPrice }</span>
                            <span>${code.salePrice }</span>
                        </div>
                        <p class="lead">${code.productDesc }</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <div style="width: 1500px; margin: 0 auto;">
        
        <input type="text" id="writer" value="user02">
        <input type="text" id="content" >
        <button id="addContent">댓글등록</button>
        <br>
        
        <table class="table">
        	<thead>
        		<tr>
        			<th>댓글번호</th>
        			<th>작성자</th>
        			<th>댓글내용</th>
        			<th>삭제</th>
        		</tr>
        	</thead>
        	<tbody id="list">
        		        	
        	</tbody>
        </table>
        </div>
        
        <div style="display: none;">
        	<table>
        		<tr id="template">
        			<td class="replyNo"></td>
        			<td class="replyWriter"></td>
        			<td><input class="replyContent" type="text"></td>
        			<td><button onclick='updateReply(this)'>저장</button></td>
        		</tr>
        	</table>
        </div>
        
        
		<script>
			function makeEditRow3(data){
				let cloneTr = document.getElementById('template').cloneNode(true);
				cloneTr.querySelector('.replyNo').innerText = data.replyNo;
				cloneTr.querySelector('.replyWriter').innerText = data.replyWriter;
				cloneTr.querySelector('.replyContent').value = data.replyContent ;
				console.log(cloneTr);	
				
				return cloneTr;
			}
			
		    function makeEditRow2(data){
		    	console.log(data);
		    	let str = "";
		    	str += "<td>"+ data.replyNo +"</td>";	// <td>10</td><td>user03</td><td><input value=""><button>저장</button></td>
		    	str += "<td>"+ data.replyWriter +"</td>";
		    	str += "<td><input value='"+ data.replyContent +"'></td>";
		    	str += "<td><button onclick='updateReply(this)'>저장</button></td>";
		    		
		    	return str;
		    }			
			
		    // 목록데이터.
		    //let promiseResult = fetch('')
		    //promiseResult.then(function(){});
		    fetch('replyListAjax.do?code=${code.productCode}')
		        .then(resolve => resolve.json()) // [{"id":"user1","name":"Hong"}]->[{id:'user1',name:'Hong'}]
		        .then(result => {
		            // 값을 이용해서 tr 생성.
		            result.forEach(function (reply) {
		            	let tr = makeRow(reply);
		                document.querySelector('#list').append(tr);
		            });
		        })
		        .catch(reject => console.error(reject));
		
		    let showProps = ['replyNo', 'replyWriter', 'replyContent'];
		    function makeRow(reply = {}) {// tr>td*4
		        let tr = document.createElement('tr');
		        tr.addEventListener('dblclick', modifyTr);
		        tr.id = reply.replyNo; // tr의 id의 attribute에 댓글 번호.
		        
		        // td 생성해주는 부분.
		        showProps.forEach(function (prop) {
		            let td = document.createElement('td');
		            td.innerText = reply[prop]; console.log(reply[prop]);
		            tr.append(td);
		        })
		        // 항목 외에 추가하는 기능.
		        let td = document.createElement('td');
		        let btn = document.createElement('button');
		        btn.addEventListener('click', removeReply); // 삭제처리.
		        btn.innerText = '삭제';
		        td.append(btn);
		        tr.append(td);
		        // document.querySelector('#list').append(tr);
		        // makeRow를 호출하는 영역에서 처리하도록 tr을 반환.
		        return tr;
		    }
		    
		    // 화면을 수정할 수 있도록 처리.
		    function modifyTr() {
		    	console.log(this);
		    	let id = this.id;
		    	let oldTr = this;
		    	
		    	
		    	// 댓글 한건 조회.
		    	fetch('replySearchAjax.do?replyNo=' + id)
		    	.then(resolve => resolve.json())
		    	.then(result => {
		    		console.log(result);
		    		let data = result;
		    		
		    		//oldTr.innerHTML = makeEditRow2(data);
		    		
		    		let tr = makeEditRow3(data);
		    		document.getElementById('list').replaceChild(tr,oldTr);
		    	})
		    	.catch(reject => console.error(reject));
		    	

		    }
		    
		    
		    function makeEditRow(){
	    		let newTr = document.createElement('tr');
		    	let td = document.createElement('td');
		    	td.innerText = data.replyNo;
		    	newTr.append(td);
		    	
		    	td = document.createElement('td');
		    	td.innerText = data.replyWriter;
		    	newTr.append(td);
		    	
		    	td = document.createElement('td');
		    	let input = document.createElement('input');
		    	input.value = data.replyContent;
		    	td.append(input);
		    	newTr.append(td);
		    	
		    	td = document.createElement('td');
		    	let btn = document.createElement('button');
		    	btn.innerText = '저장';
		    	btn.addEventListener('click', updateReply);
		    	td.append(btn);
		    	newTr.append(td);
		    	
		    	console.log(newTr);
		    	document.getElementById('list').replaceChild(newTr, oldTr); 		    	
		    }
		    
		    
		    function updateReply(btn) {
		    	// 
		    	console.log(btn);
		    	let oldTr = btn.parentElement.parentElement
		    	let id = oldTr.children[0].innerText;
		    	let con = oldTr.children[2].children[0].value;
		    	
		    	fetch('replyModifyAjax.do', {
		    		method:'post',
		    		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    		body: 'replyId=' + id + '&replyContent='+ con
		    		})
		    	.then(resolve => resolve.json())
		    	.then(result => {
		    			console.log(result);
		    	      let newTr = makeRow(result.reply);//replac가 아니라 새로 tr하나 밑에 생성하는거여서
		    	      document.querySelector('#list').replaceChild(newTr, oldTr);
		    	})
		    	.catch()		    	
		    	

		    }
		    
		    // 댓글번호() 데이터를 삭제기능(컨트롤) + 화면에서 삭제(elem.remove()).
		    function removeReply() {
		    	let id = this.parentElement.parentElement.id;
		    	
		    	fetch('replyRemoveAjax.do?replyId='+id)
		    	.then(resolve => resolve.json())
		    	.then(result => {
		    		if (result.retCode == 'Success') {
		    			alert('성공');
		    			document.getElementById(id).remove();
		
		    		} else if (result.retCode == 'Fail') {
		    			alert('실패');
		    			
		    		} else {
		    			alert('retCode값을 확인하세요!!');
		
		    		}
		    	})
		    	.catch(reject => console.error(reject));
		    }
		    
		    // 댓글등록.
		    document.querySelector('#addContent').addEventListener('click', addReply);
		    
		    function addReply () {
		    	// writer, content의 value.
		    	let writer = document.querySelector('#writer').value;
		    	let content = document.querySelector('#content').value;
		    	
		    	fetch('replyAddAjax.do', {
		    		method: 'post',
		    		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    		body: 'writer='+writer+'&content='+content+'&pcode=${code.productCode}'
		    	})
		    	.then(resolve => resolve.json())
		    	.then(result => {
		    		console.log(result);
		    		if (result.retCode == 'Success') {
		    			alert('성공');
		    			let tr = makeRow(result.reply);
		    			document.querySelector('#list').append(tr);
		    			
		    			// 댓글 등록 후 초기화
		    			document.querySelector('#content').value = '';
		    			
		    		} else if (result.retCode == 'Fail') {
		    			alert('실패');
		    		} else {
		    			alert("retCode 를 확인하세요.");
		    		}
		    	})
		    	.catch(reject => console.error(reject))
		    }
		    
		</script>       
        
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Fancy Product</h5>
                                    <!-- Product price-->
                                    $40.00 - $80.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Special Item</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">$20.00</span>
                                    $18.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Sale Item</h5>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">$50.00</span>
                                    $25.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Popular Item</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    $40.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>