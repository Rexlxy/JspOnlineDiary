<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">

	<div class="data_list_title">
		<img class="img"
			src="${pageContext.request.contextPath }/images/list.png">&nbsp;&nbsp;日志内容
	</div>
	 <div class="diary_title"><h2>${diaryToShow.title }</h2></div>
	 <div class="diary_info"> 日志时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="date" value="${diaryToShow.releaseDate }" />&nbsp;&nbsp;日志类别：${diaryToShow.typeName }</div>
	 <div class="diary_content">${diaryToShow.content }</div>

</div>