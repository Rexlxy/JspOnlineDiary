Ball = function(game) {
  var img = game.getImage("ball")
  var o = {
    x: 130,
    y: 100,
    speedX: 1,
    speedY: 1,
    fired: false
  }
  o.image = img.image
  o.w = img.w
  o.h = img.h
  o.fire = function() {
    o.fired = true
  }

  o.pause = function() {
    o.fired = false
  }

  o.move = function() {
    if (o.x < 0 || o.x > 400 - o.w) {
      o.speedX = -o.speedX
    }
    if (o.y < 0 || o.y > 400 - o.image.height) {
      o.speedY = -o.speedY
    }
    o.x += o.speedX
    o.y += o.speedY
  }

  o.bounceVertical = function() {
    o.speedY = -o.speedY
  }

  o.bounceHorizontal = function() {
    o.speedX = -o.speedX
  }

  o.setImage = function(newImg) {
    if (o.fired) {
      return
    }
    o.image = imageFromPath(newImg)
  }

  return o
}
