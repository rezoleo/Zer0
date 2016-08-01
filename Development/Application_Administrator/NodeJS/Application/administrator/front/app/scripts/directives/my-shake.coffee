app.directive 'myShake', ($timeout)->
  restrict: 'A'
  link: (scope, element, attr) ->
    dist = attr.shakeDist ? 50
    time = attr.shakeTime ? 1
    scope.$watch attr.myShake, (ok)-> if ok then $timeout ->
      element[0]
      .animate({left: '+0'}, time)
      .animate({left: '-' + dist}, 2 * time)
      .animate({left: '+' + dist}, 2 * time)
      .animate({left: '-' + dist}, 2 * time)
      .animate({left: '+' + dist}, 2 * time)
      .animate({left: '+0'}, time)
