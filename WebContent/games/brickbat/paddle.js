var Paddle = function(game) {
  var img = game.getImage("paddle")
  var o = {
    x: 200,
    y: 350,
    speed: 3
  }
  o.image = img.image
  o.w = img.w
  o.h = img.h
  o.moveLeft = function() {
    if (o.x < 0) {
      return
    }
    o.x -= o.speed
  }

  o.moveRight = function() {
    if (o.x > 400 - o.w) {
      return
    }
    o.x += o.speed
  }

  o.setImage = function(newImg) {
    o.image = imageFromPath(newImg)
  }
  return o
}
