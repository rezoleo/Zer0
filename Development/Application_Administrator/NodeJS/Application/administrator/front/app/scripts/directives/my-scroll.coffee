app.run ($rootScope, $timeout)->
  $rootScope.scrollTo = (tag)-> $timeout ->
    $('html, body').animate
      scrollTop: $(tag).offset().top - $(".main").offset().top
    , 500

app.directive 'myScroll', ($rootScope)->
  restrict: 'A'
  link: (scope, element, attr) ->
    scope.$watch attr.myScroll, (ok) -> if ok
      $rootScope.scrollTo element[0]
