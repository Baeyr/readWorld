<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<% 
		String s 
			= "<img alt=\"\" src=\"https://image.aladin.co.kr/product/27008/6/coversum/e602538706_1.jpg\" style=\"height:135px; width:85px\" />";
			System.out.println(s);
	%>
	
	<c:out value="<%=s %>" escapeXml="false" />
	
</body>
</html>