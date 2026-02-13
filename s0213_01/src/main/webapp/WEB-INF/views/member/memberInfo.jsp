<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="../css/style_header.css">
		<link rel="stylesheet" type="text/css" href="../css/style_modifing_member_info.css">
		<link rel="stylesheet" type="text/css" href="../css/style_footer.css">
		<title>개인정보수정</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="//t1.kakaocdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		function zipCode(){
			new kakao.Postcode({
		        oncomplete: function(data) {
		            $("#f_postal").val(data.zonecode);
		            $("#address1").val(data.address);
		        }
		    }).open();
		}
		</script>
	</head>
	<body>
		<header>
			<div id="nav_up">
				<ul>
					<c:if test="${session_id == null}">
						<li><a href="/member/join01">회원가입</a></li>
						<li><a href="/member/login">로그인</a></li>
					</c:if>
					<c:if test="${session_id != null}">
						<li><a href="/member/memberInfo">${session_name}님</a></li>
						<li><a href="/member/logout">로그아웃</a></li>
					</c:if>
					<li><a href="/board/blist">고객행복센터</a></li>
					<li><a href="#">배송지역검색</a></li>
					<li><a href="#">기프트카드 등록</a></li>
				</ul>
			</div>	
			<nav>
				<a href="/"></a>
				<ul>
					<li><a href="#">COOKIT소개</a></li>
					<li><a href="#">COOKIT메뉴</a></li>
					<li><a href="#">리뷰</a></li>
					<li><a href="#">이벤트</a></li>
					<li><a href="#">MY쿡킷</a></li>
				</ul>
				<ul>
					<li>
						<a href="#"><span>장바구니</span></a>
					</li>
					<li>
						<a href="#"><span>메뉴찾기</span></a>
					</li>
				</ul>
			</nav>
		</header>
		
		
		
		
		
		<section>
			<form action="/member/memberInfo" name="agree" method="post">
				<div id="subBanner"></div>
				<div id="locationN">
					<ul>
						<li>HOME</li>
						<li>마이페이지</li>
						<li>개인정보수정</li>
					</ul>
				</div>
				
				<div id="sub_top_area">
					<h3>개인정보수정</h3>
				</div>
				
				<h4>
					필수 정보 입력 
					<span>(* 항목은 필수 항목입니다.)</span>
				</h4>
				<fieldset class="fieldset_class">
					<dl id="modify_name_dl">
						<dt>
							<div></div>
							<label for="name">이름</label>
						</dt>
						<dd>
							${member.name}
						</dd>
					</dl>
					<dl id="modify_id_dl">
						<dt>
							<div></div>
							<label for="id">아이디</label>
						</dt>
						<dd>
							${member.id}
						</dd>
					</dl>
					<dl id="modify_pw1_dl">
						<dt>
							<div></div>
							<label for="pw">비밀번호</label>
						</dt>
						<dd>
							<input type="password" id="pw" name="pw" minlength="8" required />
							<span>영문, 숫자, 특수문자 중 2종류 조합 시 10자리 이상 입력</span>
							<span>영문, 숫자, 특수문자 모두 조합 시 8자리 이상 입력</span>
						</dd>
					</dl>
					<dl id="modify_pw2_dl">
						<dt>
							<div></div>
							<label for="pw2">비밀번호 확인</label>
						</dt>
						<dd>
							<input type="password" id="pw2" name="pw2" minlength="8" required />
							<span>비밀번호를 다시 한번 입력해 주세요.</span>
						</dd>
					</dl>
					<dl id="modify_tell_dl">
						<dt>
							<div></div>
							<label for="f_tell">휴대전화</label>
						</dt>
						<dd>
							<input type="text" id="f_tell" name="phone1" maxlength="3" value="${fn:split(member.phone, '-')[0]}" required />
							<span> - </span>
							<input type="text" id="m_tell" name="phone2" maxlength="4" value="${fn:split(member.phone, '-')[1]}" required />
							<span> - </span>
							<input type="text" id="l_tell" name="phone3" maxlength="4" value="${fn:split(member.phone, '-')[2]}" required />
						</dd>
					</dl>
					<dl id="modify_mail_dl">
						<dt>
							<div></div>
							<label for="mail_id">이메일</label>
						</dt>
						<dd>
							<input type="text" id="email1" name="email1" value="${fn:split(member.email, '@')[0]}" required />
							<span>@</span>
							<input type="text" id="email2" name="email2" value="${fn:split(member.email, '@')[1]}" required />
							<select>
								<option selected>직접입력</option>
								<option>지메일</option>
								<option>네이버</option>
								<option>네이트</option>
								<option>핫메일</option>
								<option>파란</option>
								<option>엠팔</option>
								<option>야후</option>
								<option>드림위즈</option>
								<option>한메일(다음)</option>
							</select>
						</dd>
					</dl>
					<dl id="modify_gender_dl">
						<dt>
							<div></div>
							<label for="">성별</label>
						</dt>
						<dd>
							<div>
								<input type="radio" name="gender" id="male" value="male" <c:if test="${member.gender eq 'M'}">checked</c:if> />
								<label for="male">남성</label>
								<input type="radio" name="gender" id="female" value="female" <c:if test="${member.gender eq 'F'}">checked</c:if> />
								<label for="female">여성</label>
							</div>
						</dd>
					</dl>
					<dl id="modify_address_dl">
						<dt> 
							<div></div>
							<label for="">주소</label>
						</dt>
						<dd>
							<input type="text" id="f_postal" name="f_postal" value="12345" required />
							<span>-</span>
							<input type="text" id="l_postal" name="l_postal" required />
							<input type="button" value="우편번호" onclick="zipCode();"/>
							<input type="text" id="address1" name="address1" value="서울시 금천구 가산디지털1로 186." required />
							<input type="text" id="address2" name="address2" value="(가산동. 제이플라츠 5F. 515,516호)" required />
						</dd>
					</dl>
					<dl id="modify_interests_dl">
						<dt>
							<label for="">취미</label>
						</dt>
						<dd>
							<ul>
								<li>
									<input type="checkbox" name="hobby" id="game" value="게임" <c:if test="${fn:contains(member.hobby, '게임')}">checked</c:if> />
									<label for="game">게임</label>
								</li>
								<li>
									<input type="checkbox" name="hobby" id="golf" value="골프" <c:if test="${fn:contains(member.hobby, '골프')}">checked</c:if> />
									<label for="golf">골프</label>
								</li>
								<li>
									<input type="checkbox" name="hobby" id="swim" value="수영" <c:if test="${fn:contains(member.hobby, '수영')}">checked</c:if> />
									<label for="swim">수영</label>
								</li>
								<li>
									<input type="checkbox" name="hobby" id="run" value="조깅" <c:if test="${fn:contains(member.hobby, '조깅')}">checked</c:if> />
									<label for="run">조깅</label>
								</li>
								<li>
									<input type="checkbox" name="hobby" id="book" value="독서" <c:if test="${fn:contains(member.hobby, '독서')}">checked</c:if> />
									<label for="book">독서</label>
								</li>
							</ul>
						</dd>
					</dl>
				</fieldset>
				<div id="info_input_button">
					<input type="reset" onclick="javascript:location.href='/';" value="취소하기" />
					<input type="submit" value="수정하기" />
				</div>
				
			</form>
		</section>
		
		
		
		
		
		
		
		<footer>
			<div id="footer_wrap">
				<div id="footer_cont">
					<div id="fl_l">
						<a href="#"></a>
						<p>© COOKIT ALL RIGHTS RESERVED</p>
					</div>
					<div id="fl_c">
						<ul>
							<li><a href="#">이용약관</a></li>
							<li><a href="#">개인정보처리 방침</a></li>
							<li><a href="#">법적고지</a></li>
							<li><a href="#">사업자정보 확인</a></li>
						</ul>
						<div id="fl_c_info">
							<p>씨제이제일제당(주)</p>
							<p>대표이사 : 손경식,강신호,신현재</p>
							<p>사업자등록번호 : 104-86-09535</p>
							<p>주소 : 서울 중구 동호로 330 CJ제일제당 센터 (우) 04560</p>
							<p>통신판매업신고 중구 제 07767호</p>
							<p>개인정보보호책임자 : 조영민</p>
							<p>이메일 : cjon@cj.net</p>
							<p>호스팅제공자 : CJ올리브네트웍스㈜</p>
							<p>고객님은 안전거래를 위해 현금등으로 결제시 LG U+ 전자 결제의 매매보호(에스크로) 서비스를 이용하실 수 있습니다. <a href="#">가입 사실 확인</a></p>
						</div>
					</div>
					<div id="fl_r">
						<span>cj그룹계열사 바로가기 ▼</span>
						<dl>
							<dt>고객행복센터</dt>
								<dd>1688-1920</dd>
						</dl>
						<a href="#">1:1문의</a>						
					</div>
				</div>
			</div>
		
		
		
		</footer>
	</body>
</html>