var imageFromPath = function(path) {
  var img = new Image()
  img.src = path
  return img
}

var ifPointInRect = function(point, rect) {
  if (point[0] > rect.x && point[0] < (rect.x + rect.w)) {
    if (point[1] > rect.y && point[1] < (rect.y + rect.h)) {
      return true
    }
  }
  return false
}

var checkBallIntersectB = function(A, B) {
  var width = A.w
  var height = A.h

  var topPoints = getHorizonFivePoints(A.x, A.y, width)
  var botPoints = getHorizonFivePoints(A.x, A.y + height, width)
  var leftPoints = getVerticalTHreePoints(A.x, A.y, height)
  var rightPoints = getVerticalTHreePoints(A.x + width, A.y, height)

  //上边的点
  for (var i = 0; i < topPoints.length; i++) {
    //console.log(point)
    if (ifPointInRect(topPoints[i], B)) {
      return "vertical"
    }
  }
  //下边的点
  for (var i = 0; i < botPoints.length; i++) {
    if (ifPointInRect(botPoints[i], B)) {
      return "vertical"
    }
  }
  //左边的点
  for (var i = 0; i < leftPoints.length; i++) {
    if (ifPointInRect(leftPoints[i], B)) {
      return "horizontal"
    }
  }
  //右边的点
  for (var i = 0; i < rightPoints.length; i++) {
    if (ifPointInRect(rightPoints[i], B)) {
      return "horizontal"
    }
  }
  return null
}

var getHorizonFivePoints = function(x, y, width) {
  points = new Array(3)
  var segament = Math.floor(width / 4)
  points[0] = [x, y]
  points[1] = [x + segament, y]
  points[2] = [x + 2 * segament, y]
  points[3] = [x + 3 * segament, y]
  points[4] = [x + 4 * segament, y]
  return points
}

var getVerticalTHreePoints = function(x, y, height) {
  points = new Array(3)
  var segament = Math.floor(height / 4)
  points[0] = [x, y + segament]
  points[1] = [x, y + 2 * segament]
  points[2] = [x, y + 3 * segament]
  return points
}
