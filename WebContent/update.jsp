<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!-- http://blog.csdn.net/jasper_success/article/details/6693434 这个地方的错误处理-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
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
	border: 1px solid gray;
}
</style>
</head>
<body>
	<h1 align="center">修改客户信息</h1>
	<hr>
	<form action="${pageContext.request.contextPath }/Controller?op=update"
		method="post">
		<table id="t1" align="center">
			<tr>
				<td align="left" colspan="2"><input type="hidden" name="id"
					value="${customer.id }"></td>
			</tr>
			<tr>
				<td align="right" width="40%">姓名:</td>
				<td align="left"><input type="text" name="name"
					value="${customer.name }"></td>
			</tr>
			<tr>
				<td align="right" width="40%">性别:</td>
				<td align="left"><input type="radio" name="gender" value="1"
					${customer.gender=="1"?"checked":"" }>男 <input type="radio"
					name="gender" value="0" ${customer.gender=="0"?"checked":"" }>女</td>
			</tr>
			<tr>
				<td align="right" width="40%">生日:</td>
				<td align="left"><input type="text" name="birthday"
					onfocus="new Calendar().show(this)" readonly="readonly"
					value="${customer.birthday }"></td>
			</tr>
			<tr>
				<td align="right" width="40%">电话:</td>
				<td align="left"><input type="text" name="cellphone"
					value="${customer.cellphone }"></td>
			</tr>
			<tr>
				<td align="right" width="40%">邮箱:</td>
				<td align="left"><input type="text" name="email"
					value="${customer.email }"></td>
			</tr>
			<tr>
				<td align="right" width="40%">爱好:</td>
				<td align="left"><input type="checkbox" name="hobby" value="吃饭"
					${fun:contains(customer.hobby,"吃饭")?"checked":"" }>吃饭 <input
					type="checkbox" name="hobby" value="睡觉"
					${fun:contains(customer.hobby,"睡觉")?"checked":"" }>睡觉 <input
					type="checkbox" name="hobby" value="学java"
					${fun:contains(customer.hobby,"学java")?"checked":"" }>学Java</td>
			</tr>
			<tr>
				<td align="right" width="40%">类型:</td>
				<td align="left"><input type="radio" name="type" value="vip"
					${customer.type=="vip"?"checked":"" }>贵宾 <input
					type="radio" name="type" value="common"
					${customer.type!="vip"?"checked":"" }>普通用户</td>
			</tr>
			<tr>
				<td align="right" width="40%">描述:</td>
				<td align="left"><textarea rows="5" cols="20"
						name="description">${customer.description }</textarea></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="保存">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>