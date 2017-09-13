<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>主页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/style/diary.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath }/bootstrap3/js/jquery-3.2.1.js">
	
</script>
<script
	src="${pageContext.request.contextPath }/bootstrap3/js/bootstrap.min.js"></script>
<!--  游戏部分 -->
<script
	src="${pageContext.request.contextPath }/games/brickbat/paddle.js"></script>
<script src="${pageContext.request.contextPath }/games/brickbat/ball.js"></script>
<script
	src="${pageContext.request.contextPath }/games/brickbat/Brick.js"></script>
<script src="${pageContext.request.contextPath }/games/brickbat/Util.js"></script>
<script src="${pageContext.request.contextPath }/games/brickbat/game.js"></script>
<script
	src="${pageContext.request.contextPath }/games/brickbat/scene.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ckeditor/ckeditor.js"></script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main">召唤师日记本</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li ><a href="main?all=true"><span
						class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;主页 <span
						class="sr-only">(current)</span></a></li>
				<li><a href="diary?action=preSave"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;写日记</a></li>
				<li><a href="diaryTypeManage?action=showTypes"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;日记分类管理</a></li>
				<li><a href="game"><span
						class="glyphicon glyphicon-sunglasses"></span>&nbsp;&nbsp;弹球游戏</a></li>
				<form class="navbar-form navbar-left" name="searchForm"
					method="post" action="main?all=true">
					<div class="form-group">
						<input type="text" id="search_key" name="search_key"
							class="form-control" placeholder="我的日记...">
					</div>
					<button type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span>&nbsp;搜索日记
					</button>
				</form>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;个人中心</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div class="container-fluid" style="padding-top: 50px">
		<div class="row">
			<div class="col-md-9">
				<jsp:include page="${mainPage }"></jsp:include>
			</div>
			<div class="col-md-3">
				<div class="data_list">

					<div class="data_list_title">
						<img class="img"
							src="${pageContext.request.contextPath }/images/user.png">&nbsp;&nbsp;个人中心
					</div>
					<div class="userImage">
						<img src="${pageContext.request.contextPath }${user.imageName }">
					</div>
					<div class="nickName">昵称：${user.nickName }</div>
					<div class="mood">个人签名：${user.mood }</div>
				</div>
				<div class="data_list">
					<div class="data_list_title">
						<img class="img"
							src="${pageContext.request.contextPath }/images/drawer.png">&nbsp;&nbsp;按日记类别
					</div>
					<div class="typeOrDate">
						<ul>
							<c:forEach var="diaryType" items="${diaryTypeCountList }">
								<li><span><a
										href="main?s_typeId=${diaryType.diaryTypeId }">${diaryType.typeName }
											(${diaryType.diaryCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="data_list">
					<div class="data_list_title">
						<img class="img"
							src="${pageContext.request.contextPath }/images/calendar.png">&nbsp;&nbsp;按日记时间
					</div>
					<div class="typeOrDate">
						<ul>
							<c:forEach var="diary" items="${diaryCountList }">
								<li><span><a
										href="main?s_releaseDateStr=${diary.releaseDateStr }">${diary.releaseDateStr }
											(${diary.diaryCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>