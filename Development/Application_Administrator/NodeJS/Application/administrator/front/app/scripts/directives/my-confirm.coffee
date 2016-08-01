app.directive 'myConfirm', ->
  priority: -1
  restrict: 'A'
  link: (scope, element, attrs) ->
    element.bind 'click', (e) ->
      message = attrs.myConfirm
      if message and not confirm(message)
        e.stopImmediatePropagation()
        e.preventDefault()
