<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <c:if test="${session_id == null}">
  	<script>
  		alert("로그인을 하셔야 회원정보를 확인할 수 있습니다.");
  		location.href="/member/login";
  	</script>
  </c:if>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>VLAST Shop - 전체회원리스트</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" >  
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script>
</head>
<body>
<script>
	function writeBtn(){
		location.href="/board/bwrite";
	}
</script>
  <!-- Header -->
  <div class="header">
    <div class="top-menu">
      <a href="#"><i class="fa-regular fa-chess-queen"></i> 회원가입</a> |
      <a href="#">로그인</a> |
      <a href="#">주문조회</a> |
      <a href="#">최근본상품</a>
    </div>
    <div class="icons">
      <i class="fas fa-search"></i>
      <i class="fas fa-user"></i>
      <i class="fas fa-shopping-bag"></i>
    </div>
  </div>
  <!-- nav -->
  <nav>
    <ul>
      <li><a href="#">포레스트</a></li>
      <li><a href="#">스파</a></li>
      <li><a href="#">객실</a></li>
      <li><a href="#">특별한 경험</a></li>
      <li><a href="#">부대시설</a></li>
      <li><a href="#">브랜드소개</a></li>
    </ul>
  </nav>
  <!-- banner -->
  <div class="banner"></div>
  <script>
  $(function(){
	  $(document).on("click",".delBtn",function(){
		  console.log($(this).closest("tr").children("td").text());
		  const tr = $(this).closest("tr");
		  const id = $(this).closest("tr").children("td").eq(0).text();
		  if(confirm("회원을 삭제하시겠습니까?")){
			//get방식 사용하지 말것
			$.ajax({
				url:"/member/mdelete",
				type:"delete",			//get,post,put,delete
				data:{"id":id},
				dataType:"text",		//받는타입 text,json,xml
				success: function(data){
					console.log(data);
					alert(data);
					//tr.remove();	//화면에서만 삭제
					location.href="/member/mlist";
				},
				error: function(e){
					alert(e.message);
				}
			})
		  }
	  });
  });
  </script>

  <!-- Board List -->
  <div class="board-container">
    <div class="board-title">전체회원리스트</div>
    <table class="board-table">
      <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>전화번호</th>
          <th>성별</th>
          <th>취미</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="member" items="${list}" step="1">
        <tr>
          <td id="${member.id}">${member.id}</td>
          <td class="title"><a href="/member/mview?id=${member.id}">${member.name}</a></td>
          <td>${member.phone}</td>
          <td class="date">${member.gender}</td>
          <td class="views">${member.hobby}</td>
          <td class="views"><button type="button" class="delBtn">삭제</button></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="write-buttons">
		<button type="button" onclick="writeBtn()">글쓰기</button>
    </div>
    <!-- Pagination & Search -->
    <div class="board-footer" >
        <%-- <div class="pagination">
		    <!-- 1. 처음 페이지로 이동 -->
		    <a href="/board/blist?page=1">&laquo;&laquo;</a>
		
		    <!-- 2. 이전 페이지로 이동 (1페이지가 아닐 때만 노출) -->
		    <c:if test="${map.page > 1}">
		        <a href="/board/blist?page=${map.page - 1}">&laquo;</a>
		    </c:if>
		
		    <!-- 3. 숫자 페이지 반복 -->
		    <c:forEach var="nowpage" begin="${map.startPage}" end="${map.endPage}">
		        <c:choose>
		            <c:when test="${map.page} == ${nowpage}">
		                <a href="#" class="active">${nowpage}</a>
		            </c:when>
		            <c:otherwise>
		                <a href="/board/blist?page=${nowpage}">${nowpage}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		
		    <!-- 4. 다음 페이지로 이동 (마지막 페이지가 아닐 때만 노출) -->
		    <c:if test="${map.page < map.maxPage}">
		        <a href="/board/blist?page=${map.page + 1}">&raquo;</a>
		    </c:if>
		
		    <!-- 5. 끝 페이지로 이동 -->
		    <a href="/board/blist?page=${map.maxPage}">&raquo;&raquo;</a>
		</div> --%>
        <div class="search-box">
            <input type="text" placeholder="검색어 입력">
            <button>검색</button>
        </div>
    </div>
    
  </div>


  <!-- Footer -->
  <footer>
    Copyright © VLAST Shop. All rights reserved.
  </footer>

</body>
</html>
