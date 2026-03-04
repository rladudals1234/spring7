<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>메인페이지</title>
	</head>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	function logoutBtn(){
		location.href="/member/logout";
	}
	</script>
	<body>
	 	<h2>메인페이지</h2>
	 	<ul>
	 		<c:if test="${session_id == null}">
			 	<li><a href="/member/login">로그인</a></li>
			 	<li><a href="/member/membership">회원가입</a></li>
		 	</c:if>
		 	<c:if test="${session_id != null}">
			 	<li><a href="/member/mypage">${session_id}님 환영합니다.</a></li>
			 	<li><a href="/member/mlist">전체회원리스트</a></li>
			 	<li><a style="cursor:pointer;" onclick="logoutBtn();">로그아웃</a></li>
			 	<li><a href="/board/blist">게시판</a></li>
			 	<li><a href="/board/bwrite">글쓰기</a></li>
		 	</c:if>
	 	</ul>
	</body>
</html>