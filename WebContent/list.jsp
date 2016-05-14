<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!-- http://blog.csdn.net/jasper_success/article/details/6693434 这个地方的错误处理-->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<title>Insert title here</title>

<style type="text/css">
#t1 {
	width: 900px;
}

#t2 {
	border: 1px solid gray;
	border-collapse: collapse;
	font-size: 15px;
	text-align: center;
}

#t2 td, th {
	border: 1px solid gray;
}

#t2 tr:hover {
	background-color: ffccff;
}
</style>
</head>
<script type="text/javascript">
	function checkAll(flag){
		//拿到所有的记录
		var ids = document.getElementsByName("ids");
		//循环设置每一个单选框
		for (var i = 0; i < ids.length; i++){
			ids[i].checked = flag;
		}
		
	}
</script>
<body>
	<h1 align="center">客户信息</h1>
	<hr>
	<table id="t1" align="center">
		<tr>
			<td><a href="${pageContext.request.contextPath }/add.jsp">添加</a>&nbsp;&nbsp;&nbsp; <a href="">删除</a>&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" id="t2" >
					<tr>
						<th nowrap><input type="checkbox" id="all"  onclick="checkAll(this.checked)"/>全选/全部选</th>
						<th nowrap>姓名</th>
						<th nowrap>性别</th>
						<th nowrap>生日</th>
						<th nowrap>电话</th>
						<th nowrap>邮箱</th>
						<th nowrap>爱好</th>
						<th nowrap>类型</th>
						<th nowrap>描述</th>
						<th nowrap>操作</th>
					</tr>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="10" align="center">暂时没有数据</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${list }" var="c">
								<tr>
									<td nowrap><input type="checkbox" name="ids" value="${c.id }"/></td>
									<td nowrap>${c.name}</td>
									<td nowrap>${c.gender=="1"?"男":"女" }<!-- ${c.gender=="1"?"男":"女" } --></td>
									<td nowrap>${c.birthday}</td>
									<td nowrap>${c.cellphone}</td>
									<td nowrap>${c.email}</td>
									<td nowrap>${c.hobby}</td>
									<td nowrap>${c.type=="vip"?"贵宾":"普通用户" }</td>
									<td nowrap>${c.description }</td>
									<td nowrap><a
										href="${pageContext.request.contextPath }/Controller?op=toupdate&id=${c.id}">修改</a>&nbsp;&nbsp;&nbsp;
										<a
										href="${pageContext.request.contextPath }/Controller?op=delete&id=${c.id}">删除</a>

									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>