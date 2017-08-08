<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">

	<%
		Map<Integer, String> typeMap = (Map<Integer, String>) request.getAttribute("typeMap");
	%>
	<div class="data_list_title">
		<img class="img"
			src="${pageContext.request.contextPath }/images/list.png">&nbsp;&nbsp;日志列表
	</div>

	<jsp:useBean id="curDiary" class="com.rex.model.Diary" scope="page"></jsp:useBean>
	<nav aria-label="...">
		<ul class="pager">${pageCode }
		</ul>
	</nav>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<td>日期</td>
				<td>日志标题</td>
				<td>日志类别</td>
			</tr>
		</thead>


		<tbody>
			<c:forEach var="diary" items="${diaryList }">
				<c:set target="${curDiary }" value="${diary.typeId }"
					property="typeId"></c:set>
				<tr>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${diary.releaseDate }" type="date" /></td>
					<td><span>&nbsp;<a href="#">${diary.title }</a></span></td>
					<td><span class="label label-success"
						style="font-size: 12.5px">&nbsp;<%=typeMap.get(curDiary.getTypeId())%></span></td>
				</tr>
			</c:forEach>
	</table>
	<nav aria-label="...">
		<ul class="pager">${pageCode }</ul>
	</nav>
</div>