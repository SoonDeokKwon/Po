<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 시도: <input type="text" id="sido"> -->
	시도선택: <select id="sidoList">

			  </select>
	<button id="sidoBtn">찾기</button>
	
	<h3>센터현황</h3>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>센터명</th>
				<th>주소</th>
				<th>연락처</th>
				<th>시도</th>
			</tr>
		</thead>
		<tbody id="centerList">

		</tbody>
	</table>

	
	<script>
		let showFields = ['id', 'centerName', 'address', 'phoneNumber', 'sido']

		// row 생성.
		function makeTr(center={}) {
			// tr생성 > td* 여러개.
			let tr = document.createElement('tr');
			// tr태그에 추가적인 정보 담아주기.
			tr.setAttribute('data-lng', center.lng);
			tr.setAttribute('data-lat', center.lat);
			tr.setAttribute('data-name', center.centerName);

			tr.addEventListener('click', openMapFnc);
			// td 생성.
			showFields.forEach(field => {
				let td = document.createElement('td');
				td.innerText = center[field];
				
				tr.append(td);
			})

			return tr;
		}

		//
		function openMapFnc(){
			let tr = this;	// event target.
			let lng = tr.dataset.lng;	//tr.getAttribute('data-lng');
			let lat = tr.dataset.lat;	//tr.getAttribute('data-lat');
			let name = tr.dataset.name;
			
			//location.href = 'map.do?lng=' + lng + '&lat=' + lat;
			window.open('map.do?lng=' + lng + '&lat=' + lat + '&name=' + name, '_blank')
			
		}

		// 전체목록.
		let totalList;	// 다른 함수에서도 활용하기 위함.
		let url = `https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=9dhrcpkhsBtzvAVNlPTRo8vQ8d1Zr983dAcOsaiNP28%2BYMioxLfTNq%2FOzcLZMIwkukhDAwCT%2F3NmM9V8WqHToQ%3D%3D `;
		
		fetch(url)
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result)
			let aryData = result.data;
			totalList = aryData;	// 처리결과를 활용해서  totalList 대입.
			aryData.forEach(function (center) {
				let tr = makeTr(center);
				document.querySelector('#centerList').append(tr);

			});

			// 시도 배열.
			totalList;	//{id, centerName, address, sido...}
			// push, pop, shift, unshift
			
			let sidoAry = [];

			for(let i =0; i < totalList.length; i++){
				if(sidoAry.indexOf(totalList[i].sido) == -1){
					sidoAry.push(totalList[i].sido);
				}
			}
			console.log("sidAry: "+sidoAry);

			sidoAry.forEach(function (sido) {
				let opt = document.createElement('option');
				opt.value = sido;
				opt.innerText = sido;
				document.querySelector('#sidoList').append(opt);
			});

		})
		.catch(reject => console.log(reject));


		// 찾기 버튼.
		document.querySelector('#sidoBtn').addEventListener('click', findSidoFnc);
		function findSidoFnc() {
			// 전체 목록. 검색조건 filter한 결과를  tbody의 하위목록으로 출력.
			document.querySelector('#centerList').innerHTML = "";	// 기존에 있던 내용 지워주기
			let searchWord = document.getElementById('sidoList').value;
			let filterAry = totalList.filter(function (center){
				console.log(center);
				
				return center.sido == searchWord;
			});

			console.log(filterAry);
			// filter한 결과를 포함한 center 내용을 출력.
			filterAry.forEach(center => {
				document.querySelector('#centerList').append(makeTr(center));
			})

		}

	</script>
</body>
</html>