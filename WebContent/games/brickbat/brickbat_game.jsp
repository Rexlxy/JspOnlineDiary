<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div style="float: left; background-color: #E0FFFF">
		<div
			style="padding-left: 30px; padding-right: 30px; padding-bottom: 20px">
			<h1 style="text-align: center">打砖块</h1>
			<h5 style="text-align: center; color: grey" id="status">按f开始游戏</h5>
			<canvas id="myCanvas" width="400px" height="400px" class="myCanvas"
				style="background-color: #F0FFF0"></canvas>
			<h5>左右方向键或'a'和'd'左右移动挡板</h5>
			<button onclick="restart()">Restart</button>
			<span style="padding-left: 50px"><input type="checkbox"
				id="godMode">无敌模式</input></span>
		</div>
	</div>

	<input
		name="${pageContext.request.contextPath }/games/brickbat/images/"
		id="main_path" />
	<script>
	var mainPath = document.getElementById("main_path").getAttribute("name")
		var imageSrc = {
			ball : mainPath + "ball.png",
			paddle : mainPath + "default_paddle.png",
			brick : mainPath + "block.png"
		}

		console.log("here")

		var main = function(imageSrc) {
			var game = Game(200, imageSrc, function() {
				var s = MainScene(game)
				console.log("hhhh")
				game.runWithScene(s)
			})
		}
		main(imageSrc)
	</script>

	<div
		style="float: left; padding-right: 50px; background-color: #FFFACD">
		<div style="padding-left: 20px; padding-bottom: 20px">
			<h3>选择你的球：</h3>
			小球: <img
				src="${pageContext.request.contextPath }/games/brickbat/images/ball.png"
				onclick="ball.setImage('images/ball.png')"> 红色大球:<img
				src="${pageContext.request.contextPath }/games/brickbat/images/ball4.png"
				onclick="ball.setImage('images/ball4.png')"> 周楠:<img
				src="${pageContext.request.contextPath }/games/brickbat/images/ball2.png"
				onclick="ball.setImage('images/ball2.png')">
		</div>
	</div>
</div>