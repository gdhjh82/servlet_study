<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = (String)request.getAttribute("name");
	String userPhone = (String)request.getAttribute("phone");
	String userEmail = (String)request.getAttribute("email");
	String[] book = (String[])request.getAttribute("book");
	int price = (int)request.getAttribute("price");
	String date = (String)request.getAttribute("date");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>도서 대출 내역</h1>
	<h4>[고객 정보]</h4>
	<ul>
		<li>고객명 : <%=userName%></li>
		<li>전화번호 : <%=userPhone%></li>
		<li>이메일 : <%=userEmail%></li>
	</ul>
	<h4>대출 정보</h4>
	<ul>
		<%
			for(int i = 0; i < book.length ; i++){
				String text = "";
				switch(book[i]){
				case "1" : text = "자바 프로그래밍 입문"; break;
				case "2" : text = "웹 개발의 기초"; break;
				case "3" : text = "데이터베이스 시스템"; break;
				}%>
				<li><%=text%></li>
			<%}%>
		<li>대출 기간 : <%=date%>일</li>
	</ul>
	<h4>대출 금액:<%=price %>원</h4>
	<h4>도서를 즐겁게 읽으세요!</h4>
</body>
</html>