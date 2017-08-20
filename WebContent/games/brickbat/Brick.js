var Brick = function(game) {
  var img = game.getImage("brick")
  var o = {
    x: 50,
    y: 20,
    alive: true,
  }
  o.image = img.image
  o.w = img.w
  o.h = img.h
  o.kill = function() {
    o.alive = false
  }

  o.setImage = function(newImg) {
    o.image = imageFromPath(newImg)
  }

  return o
}
