<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Cookie c = new Cookie("visit_count","1");
		Cookie[] cookies = request.getCookies();
		c.setMaxAge(24*60*60);
		int visitCount = 1;
		if(cookies != null){
			for(Cookie d : cookies){
				if("visit_count".equals(d.getName())){
					visitCount = Integer.parseInt(d.getValue())+1;
					break;
				}
			}
		}
		Cookie d = new Cookie("visit_count",String.valueOf(visitCount));			
		response.addCookie(d);
	%>
	<p>
	당신은 이 페이지를 
	<strong><%=visitCount%></strong>번 방문했습니다.
	</p>
</body>
</html>