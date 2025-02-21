<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈화면</title>
</head>
<body>
	<ol>
		<li>
			<a href="/boardList">목록 조회</a>	
		</li>
		<li>
			<c:url value="/boardDetail" var="url">
				<c:param name="board_no" value="7"></c:param>
			</c:url>
			<a href="<c:url value='/boardDetail?boardNo=7'/>">상세조회</a>
		</li>
		<li>
			<c:url value="/boardDetail" var="datailUrl">
				<c:param name="board_title" value="제목"></c:param>
				<c:param name="board_content" value="내용"></c:param>
			</c:url>
			<a href="${datailUrl }">상세조회(2)</a>
		</li>
		<li>수정</li>
		<li>삭제</li>
	<form action="<c:url value='/boardCreate'/>" method="post">
	<fieldset>
		<legend>게시글</legend>
		<input type="text" name="board_title"><br>
		<input type="text" name="board_content"><br>
		<input type="number" name="board_writer"><br>
		<input type="submit" value="등록">
	</fieldset>
</form>
		<li>
			<a href="<c:url value='/boardManyInsert'/>">등록</a>	
		</li>
	</ol>
</body>
</html>