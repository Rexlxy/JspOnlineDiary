<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="data_list">
	<div class="data_list_title">
		<img class="img"
			src="${pageContext.request.contextPath }/images/list.png">&nbsp;&nbsp;日志分类管理
			<div style="display:inline;margin-left:350px"><a href="diaryTypeManage?action=preAdd"><button class="btn btn-success" >添加新的分类</button></a></div>
	</div>

	<table class="table table-striped">
	
	<thead>
	<tr>
	<td>编号</td>
	<td>分类名</td>
	<td>操作</td>	
	</tr>
	</thead>
	
	<c:forEach items="${diaryTypeList }" var="diaryType">
	<tr>
	<td>${diaryType.diaryTypeId }</td>
	<td>${diaryType.typeName }</td>
	<td>
	<a href="diaryTypeManage?action=modify"><button class="btn btn-primary">修改</button></a>
	<a href="diaryTypeManage?action=delete&diaryTypeId=${diaryType.diaryTypeId }"><button class="btn btn-danger">删除</button></a>
	</td>
	</tr>
	</c:forEach>
	</table>
	<nav aria-label="...">
		<ul class="pager">${pageCode }</ul>
	</nav>
</div>