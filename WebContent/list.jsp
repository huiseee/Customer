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
	function checkAll(flag) {
		//拿到所有的记录
		var ids = document.getElementsByName("ids");
		//循环设置每一个单选框
		for (var i = 0; i < ids.length; i++) {
			ids[i].checked = flag;
		}

	}

	function deleteMore() {
		//拿到所有的记录的复选框
		var ids = document.getElementsByName("ids");
		//循环每一个复选框是否选中，构建id字符串
		var s = "";
		//循环设置每一个单选框
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				//拿到此复选框的value
				s += ids[i].value + ",";
			}
		}
		//alert(s);
		//数据传递到服务器端进行删除
		/* window.location = "${pageContext.request.contextPath }/Controller?op=deleteMore&ids="
				+ s; */
		//数据传递到服务端进行删除
		window.location = "${pageContext.request.contextPath }/Controller?op=deleteMore&ids="
				+ s + "&currentPageIndex=${page.currentPageIndex}"
	}

	function jump(index) {
		if ("undefined" == typeof (index)) {
			//说明是点击的是超链接
			//拿到文本框中的数据
			var n = document.getElementById("pageindex").value;
			//判断是否合法
			if (isNaN(n)) {
				alert("必须填写数字");
				return;
			}
			index = n;
		}

		window.location.href = "${pageContext.request.contextPath }/Controller?op=page&currentPageIndex="
				+ index;
	}
</script>
<body>
	<h1 align="center">客户信息</h1>
	<hr>
	<table id="t1" align="center">
		<tr>
			<td><a href="${pageContext.request.contextPath }/add.jsp">添加</a>&nbsp;&nbsp;&nbsp;
				<a href="Javascript:deleteMore()">删除</a>&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>
				<table width="100%" id="t2">
					<tr>
						<th nowrap><input type="checkbox" id="all"
							onclick="checkAll(this.checked)" />全选/全部选</th>
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
						<c:when test="${empty page.list}">
							<tr>
								<td colspan="10" align="center">暂时没有数据</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${page.list }" var="c">
								<tr>
									<td nowrap><input type="checkbox" name="ids"
										value="${c.id }" /></td>
									<td nowrap>${c.name }</td>
									<td nowrap>${c.gender=="1"?"男":"女" }<!-- ${c.gender=="1"?"男":"女" } --></td>
									<td nowrap>${c.birthday }</td>
									<td nowrap>${c.cellphone }</td>
									<td nowrap>${c.email }</td>
									<td nowrap>${c.hobby }</td>
									<td nowrap>${c.type=="vip"?"贵宾":"普通用户" }</td>
									<td nowrap>${c.description }</td>
									<td nowrap><a
										href="${pageContext.request.contextPath }/Controller?op=toupdate&id=${c.id}">修改</a>&nbsp;&nbsp;&nbsp;
										<%-- 	<a
										href="${pageContext.request.contextPath }/Controller?op=delete&id=${c.id}">删除</a> --%>

										<a
										href="${pageContext.request.contextPath }/Controller?op=delete&id=${c.id}&currentPageIndex=${page.currentPageIndex}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td width="20%">第<font color=red>${page.currentPageIndex}页</font>/共<font
				color=red>${page.pageCount }</font>页
			</td>
			<td width="55%"><a
				href="${pageContext.request.contextPath }/servlet/Controller?op=page&currentPageIndex=${page.currentPageIndex - 1}">|&lt;</a>
				<c:forEach begin="${startIndex }" end="${endIndex }" var="n">
					<a
						href="${pageContext.request.contextPath }/servlet/Controller?op=page&currentPageIndex=${n}">${page.currentPageIndex == n?"<font color =red>":"<font>"}${n }</font></a> &nbsp;&nbsp;
        						 </c:forEach> <a
				href="${pageContext.request.contextPath }/servlet/Controller?op=page&currentPageIndex=${page.currentPageIndex + 1}">&gt;|</a>
			</td>
			<td width="15%"><input type="text" size="5" id="pageindex">&nbsp;
				<a href="javascript:jump()">跳转&nbsp;&nbsp;&nbsp;</a></td>
			<td width="10%"><select name="currentPageIndex"
				onchange="jump(this.value)">
					<c:forEach begin="1" end="${page.pageCount }" var="n">
						<option value="${n}" ${page.currentPageIndex == n?"selected":"" }>第${n }页</option>
					</c:forEach>
			</select></td>
		</tr>
	</table>
</body>
</html>