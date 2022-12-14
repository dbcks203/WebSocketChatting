<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style_chatInfo.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
	<h1>
		ID: [ <small>${memberBean.id}</small> ]
	</h1>
	<section>
		<h3>이름: ${memberBean.name}</h3>
	</section>
	<section>
		<h3>email: ${memberBean.email}</h3>
	</section>
	<section>
		<h3>주소: ${memberBean.address}</h3>
	</section>
	<section>
		<h3>전화번호: ${memberBean.phone}</h3>
	</section>
	<section>
		<h3>가입일: ${memberBean.joinDate}</h3>
	</section>
	<button type="button" onclick="location.href='memberdrop.zan';">회원탈퇴</button>
	<button type="button" onclick="location.href='/WebSocketChatting/';">돌아가기</button>
</body>

<jsp:include page="/html/footer.html" />
</html>