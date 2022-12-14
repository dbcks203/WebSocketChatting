<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/WebSocketChatting/css/style_boardList.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style_boardLobby.css">
<link rel="stylesheet" href="/WebSocketChatting/css/style.css">
<title>Youzan's Project</title>
<jsp:include page="/html/loginned_header.html" />
</head>
<body>
	<h2>Message Board</h2>
	<h3>List</h3>
	<div class="table-wrapper">
		<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>읽음</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="boardDTO" items="${articleList}" varStatus="listArticleStatus">
				<tr>
				<td>${boardDTO.seq}</td>
					<td><a href="articleview.zan?seq=${boardDTO.seq}">${boardDTO.subject}</a>></td>
					<td>${boardDTO.userid}</td>
					<td>${boardDTO.regdate }</td>
					<td>${boardDTO.readcount }</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
				<tr></tr>
			</tfoot>
		</table>

		<div class="btns">

			<button type="button" class="btn btn-primary"
				onclick="location.href='/WebSocketChatting/jsp/board/articleWrite.jsp';">글쓰기</button>
		</div>

		<!-- 검색기능 추가하기 -->
		<div class="searchbox">
			<form method="GET" action="/myapp/board/list.do">
				<select name="column" id="column" class="form-control">
					<option value="subject">제목</option>
					<option value="content">내용</option>
					<option value="name">이름</option>
					<option value="all">제목+내용</option>
				</select> <input type="text" name="search" id="search" class="form-control"
					required placeholder="검색어를 입력하세요." /> <input type="submit"
					value="검색하기" class="btn btn-default" />
			</form>
		</div>


</div>
</body>
<jsp:include page="/html/footer.html" />
</html>