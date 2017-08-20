var MainScene = function(game){

  var s = {
  }

  var paddle = Paddle(game)
  var ball = Ball(game)
  s.context = game.context
  //初始化砖块
  var bricks = []
  for (var i = 0; i < 23; i++) {
    brick = Brick(game)
    if (i % 5 == 0) {
      brick.x = 50
      brick.y += Math.floor(i / 5) * 30
    } else {
      brick.x += (i % 5) * 70
      brick.y += Math.floor(i / 5) * 30
    }
    bricks.push(brick)
  }
  //注册左箭头
  game.registerKey(37, function() {
    paddle.moveLeft()
  })
  //注册右箭头
  game.registerKey(39, function() {
    paddle.moveRight()
  })
  //注册a
  game.registerKey(65, function() {
    paddle.moveLeft()
  })
  //注册d
  game.registerKey(68, function() {
    paddle.moveRight()
  })
  //注册空格键
  game.registerKey(70, function() {
    ball.fire()
    game.gameStarted = true
    document.getElementById("status").innerHTML = "游戏中..."
  })

//画该画的东西
  s.draw = function() {
    s.clear()
    //画砖块
    for (var i = 0; i < bricks.length; i++) {
      var b = bricks[i]
      if (b.alive) {
        s.drawComponent(b)
      }
    }
    //画ball和paddle
    s.drawComponent(ball)
    s.drawComponent(paddle)
    s.drawScore()
    s.checkGG()
  }

  //更新球的坐标和砖块存活信息，包括分数
  s.update = function() {
    //移动球
    if (ball.fired) {
      game.collide(ball, paddle)
      ball.move()
    } else {
      if (!game.gameStarted) {
        ball.x = paddle.x + Math.floor(paddle.w / 2) - Math.floor(ball.w / 2)
        ball.y = paddle.y - ball.image.height - 5
      }
    }

    for (var i = 0; i < bricks.length; i++) {
      var b = bricks[i]
      if (b.alive) {
        if (game.collide(ball, b)) {
          b.kill()
          game.count++
          game.score += 100
        }
      }
    }
  }


  //判断是否游戏结束
  s.checkGG = function() {
    //判断无敌模式
    var godMode = false
    if (document.getElementById("godMode").checked) {
      godMode = true
    }
    //球掉下去，游戏结束
    if (ball.y > 350 && !godMode) {
      document.getElementById("status").innerHTML = "呵呵，垃圾"
      s.drawColorLayer()
      //Game over
      s.drawText('Game Over', 10, 205, '#FF6A6A', 'bold 70px arial')
      //分数
      s.drawText("Score:", 160, 280, 'FF6A6A', 'bold 20px arial')
      s.drawText(game.score, 230, 280, 'FF6A6A', 'bold 20px arial')
      game.gameOver = true
    }
    //砖块打光，过关
    if (game.count == bricks.length) {
      document.getElementById("status").innerHTML = "You win"
      // window.alert("GG")
      s.drawColorLayer()
      s.drawText('You win', 50, 205, 'FF6A6A', 'bold 70px arial')
      game.gameOver = true
    }
  }

  //绘图部分
  //清空画布
  s.clear = function() {
    s.context.clearRect(0, 0, 400, 400)
  }
  //在特定坐标画上蚊子
  s.drawText = function(text, x, y, color, font) {
    s.context.font = font
    s.context.fillStyle = color
    s.context.fillText(text, x, y)
  }
  //draw游戏结束时的一层色板
  s.drawColorLayer = function() {
    s.context.fillStyle = "rgba(255,165,0,0.8)"
    s.context.fillRect(0, 0, 400, 400)
  }
  //draw游戏中组件
  s.drawComponent = function(img) {
    s.context.drawImage(img.image, img.x, img.y)
  }
  //draw游戏中分数
  s.drawScore = function() {
    s.drawText("Score:", 10, 380, 'blue', 'bold 16px arial')
    s.drawText(game.score, 60, 380, 'blue', 'bold 16px arial')
  }
  return s
}
