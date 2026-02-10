<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	</head>
	<body>
		<h2>회원가입</h2>
		<form action="/member/doJoin" method="post" name="frm">
			<input type="text" name="id" placeholder="아이디를 입력하세요."><br>
			<input type="text" name="pw" placeholder="비밀번호를 입력하세요."><br>
			<input type="text" name="name" placeholder="이름을 입력하세요."><br>
			<input type="text" name="phone" placeholder="전화번호를 입력하세요."><br>
			<input type="text" name="email" placeholder="이메일을 입력하세요."><br>
			
			<input type="radio" id="male" name="gender" value="남자"><label for="male">남자</label>
			<input type="radio" id="female" name="gender" value="여자"><label for="female">여자</label><br>

			<input type="checkbox" name="hobby" id="game" value="게임"><label for="game">게임</label>
			<input type="checkbox" name="hobby" id="golf" value="골프"><label for="golf">골프</label>
			<input type="checkbox" name="hobby" id="swim" value="수영"><label for="swim">수영</label>
			<input type="checkbox" name="hobby" id="run" value="조깅"><label for="run">조깅</label>
			<input type="checkbox" name="hobby" id="book" value="독서"><label for="book">독서</label>
			<input type="submit" value="회원가입">
		</form>
	</body>
</html>