<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!-- http://blog.csdn.net/jasper_success/article/details/6693434 这个地方的错误处理-->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/Birthday-Calendar.js">
	
</script>
<style type="text/css">
#t1 {
	width: 900px;
	border:1px solid gray;
}
</style>
</head>
<body>
	<h1 align="center">添加客户信息</h1>
	<hr>
	<form action="${pageContext.request.contextPath }/Coustomer/Controller?op=add" method="post">
	<table id="t1" align="center">
		<tr>
			<td align="right" width = "40%">姓名:</td>
			<td align="left"><input type="text" name="name"></td>
		</tr>
		<tr>
			<td align="right">性别:</td>
			<td align="left"><input type="radio" name="gender" value="1"
				checked>男<input type="radio" name="gender" value="0">女</td>
		</tr>
		<tr>
			<td align="right">生日:</td>
			<td align="left"><input type="text" name="birthday"
				onfocus="new Calendar().show(this)" readonly="readonly"></td>
		</tr>
		<tr>
			<td align="right">电话:</td>
			<td align="left"><input type="text" name="cellphone"></td>
		</tr>
		<tr>
			<td align="right">邮箱:</td>
			<td align="left"><input type="text" name="email"></td>
		</tr>
		<tr>
			<td align="right">爱好:</td>
			<td align="left"><input type="checkbox" name="hobby" value="吃饭">吃饭
				<input type="checkbox" name="hobby" value="睡觉">睡觉 <input
				type="checkbox" name="hobby" value="学习">学习</td>
		</tr>
		<tr>
			<td align="right">类型:</td>
			<td align="left"><input type="radio" name="type" value="vip"
				checked>贵宾<input type="radio" name="type" value="common">普通用户</td>
		</tr>
		<tr>
			<td align="right">描述:</td>
			<td align="left"><textarea rows="5" cols="20" name="description">大神留点信息吧</textarea>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="submit" value="添加"></td>
		</tr>


	</table>
	</form>
</body>
</html>