<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function deleteDiary(diaryId) {
		if (confirm("确认删除日志？")) {
			window.location = "diary?action=delete&diaryId="+diaryId;
		} else {
			alert("还来得及反悔");
		}
	}
</script>
<div class="data_list">

	<div class="data_list_title">
		<img class="img"
			src="${pageContext.request.contextPath }/images/list.png">&nbsp;&nbsp;日志内容

	</div>
	<div class="diary_title">
		<h2>${diaryToShow.title }</h2>
	</div>
	<div class="diary_info">
		日志时间：
		<fmt:formatDate pattern="yyyy-MM-dd HH:mm" type="date"
			value="${diaryToShow.releaseDate }" />
		&nbsp;&nbsp;日志类别：${diaryToShow.typeName }
	</div>
	<div class="diary_content">${diaryToShow.content }</div>
	<!-- 按钮 -->
	<div style="margin-left: 45px">
		<a href="diary?action=preUpdate&diaryId=${diaryToShow.diaryId}"><button
				type="button" class="btn btn-primary">修改日志</button></a>
		<button type="button" class="btn btn-primary"
			onclick="javascript:history.back()">返回</button>
		<button type="button" class="btn btn-danger"
			onclick="deleteDiary(${diaryToShow.diaryId})">删除日志</button>
	</div>

</div>