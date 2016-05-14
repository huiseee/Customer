<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a
		href="${pageContext.request.contextPath }/Servlet1?id=1PdRjVnmnlhEC+FNfIbcdQ==">Servlet1</a>
	<form action="${pageContext.request.contextPath }/Servlet1"
		method="post">
		<input type="text" name="id" value="1PdRjVnmnlhEC+FNfIbcdQ==">
		<input type="submit" value="提交">
	</form>
</body>
</html>