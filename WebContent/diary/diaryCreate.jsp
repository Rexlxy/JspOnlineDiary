<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm() {
		var title = document.getElementById("diary_title").value
		var type = document.getElementById("typeId").value
		var content = CKEDITOR.instances.content.getData()
		if (title == null || title == "") {
			document.getElementById("form_error").innerHTML = "请输入日志标题"
			return false
		} else if (content == null || content == "") {
			document.getElementById("form_error").innerHTML = "请输入日志内容"
			return false
		} else if (type == null || type == "") {
			console.log("type error")
			document.getElementById("form_error").innerHTML = "请选择日志类别"
			return false
		}
		return true
	}
</script>
<div class="data_list">

	<div class="data_list_title">
		<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;写日记
	</div>
	<form action="diary?action=save" method="post"
		onsubmit="return checkForm()">
		<div
			style="text-align: center; padding-top: 30px; padding-bottom: 20px">
			<font size="4px">日志标题：</font><input type="text" class="form-control"
				style="width: 400px; display: inline" id="diary_title"
				name="diary_title" placeholder="日志标题" value="${diary.title }">
		</div>
		<div>
			<textarea id="content" name="content" class="ckeditor">${diary.content }</textarea>
		</div>
		<div class="type_select" style="margin-top: 20px">
			<font size="4px">日志类别：</font> <select
				style="height: 30px; width: 150px; display: inline" id="typeId"
				name="typeId">
				<option value="">选择日志类别</option>
				<c:forEach var="diaryType" items="${diaryTypeCountList }">
					<option value="${diaryType.diaryTypeId }">${diaryType.typeName }</option>
				</c:forEach>
			</select>
		</div>
		<div style="margin-top: 20px">
			<input type="submit" class="btn btn-primary" value="保存" />
			<button class="btn btn-primary" onclick="javascript:history.back()">返回</button>
			<font color="red" id="form_error"></font>
		</div>

	</form>

</div>