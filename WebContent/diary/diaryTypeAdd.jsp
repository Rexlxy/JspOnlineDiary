<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function formCheck() {
		var str = document.getElementById("newDiaryTypeName").value
		if(str == null){
			document.getElementById("form_error").innerHTML = "日志类别名不能为空"
			return false
		} 
		return true
	}
</script>
<div class="data_list">

	<div class="data_list_title">
		<img class="img"
			src="${pageContext.request.contextPath }/images/list.png">&nbsp;&nbsp;添加新的类别
	</div>
	<div class="form-group" style="margin-top:20px">	
	<form action="diaryTypeManage?action=addType" method="post" onsubmit="return formCheck()">
    <font size="4px">新的日志类别：</font>
    <div style="display:inline;margin-top:40px"><input type="text" class="form-control" style="width:500px" id="newDiaryTypeName" name="newDiaryTypeName" placeholder="新的类别名"></div>
   
    <div><input type="submit" class="btn btn-primary" value="确认"></div>
    <font color="red" id="form_error"></font>
    <button class="btn btn-primary" onclick="javascript:history.back()">返回</button>
	</form>
	
	  </div>
</div>