app.directive 'myFocus', ($timeout)->
  restrict: 'A'
  link: (scope, element, attr) ->
    scope.$watch attr.myFocus, (ok) -> if ok then $timeout ->
      element[0].focus()
