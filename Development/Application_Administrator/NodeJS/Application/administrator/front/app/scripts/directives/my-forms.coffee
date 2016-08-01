app.directive 'myForms', ($rootScope, $timeout, $window, $location, config)->
  restrict: 'A',
  priority: -10,
  link: ($scope, element, attr)->
    # set form variable
    root = if attr.myForms then $scope[attr.myForms] else $scope
    root ?= {}
    root.data ?= {}
    form = $scope[attr.name] ? null

    # update error
    $scope.$watch (->root.error), (error)->
      element.find('[my-forms-error]').hide()
      if error?.message
        withAlert = 0
        $el = element.find('[my-forms-error]').filter (i, el)-> $(el).attr('my-forms-error') == error.code || $(el).attr('my-forms-error') == error.message
        # display the matching error
        if $el.length then $el.show().focus()
        else
          withAlert = 1
          $el = element.find('[my-forms-error=""]')
          if $el.length then $el.show().focus().html(config.errorMessage.replace('%s', error.message))
          else alert(config.errorMessage.replace('%s', error.message))
        if $window.ga
          $window.ga 'send', 'event', 'error', withAlert + error.message + $location.path()

    # update success
    $scope.$watch (-> root.success), (success)->
      $el = element.find('[my-forms-success]')
      if success then $el.show() else $el.hide()

    # disable sending button
    $scope.$watch (-> root.sending), (sending)->
      element.find('[type="submit"]').attr("disabled", sending)

    # scroll to top
    unless attr.myFormsNoScroll?
      $scope.$watch (-> root.success or root.error), (ok)-> if ok
        $rootScope.scrollTo(element)

    # check form
    checkForm = ->
      element.addClass 'my-sent'
      element.find('input, textarea, select').trigger('input').trigger('keydown').trigger('change')
    element.find('button[type="submit"]').on 'click', checkForm

    # submit action
    element.on 'submit', -> $timeout submit

    submit = ->
      checkForm()
      # check if already sending
      if root.sending then return
      # check form validity
      if form?.$invalid
        root.error = 'Invalid Form'
        return
      # reset flags
      root.sending = root.success = root.error = false
      # perform action
      action = root.submit root.data
      if 'string' == typeof action then root.error = message: action # if error
      else if !action or !action.then? or !action?.catch then root.success = true # if not promise
      else # if promise
        # change state
        root.sending = true
        # pipe state actions
        action
        .then ->
          root.success = true
        .catch (res)->
          # check error code
          if res.data == 'Bad Request' then root.error = {code: 40001, message: 'Invalid Request'}
          else if res.status == 413 then root.error = {code: 41301, message: 'Too Large'}
          else if res.status == 502 then root.error = {code: 50201, message: 'Request Failed'}
          else if !res.data?.message then root.error = {code: 0, message: res.data}
          else root.error = res.data
        .finally -> root.sending = false

app.directive 'myFormsError', ->
  restrict: 'A',
  link: ($scope, element, attr)-> element.hide()

app.directive 'myFormsSuccess', ->
  restrict: 'A',
  link: ($scope, element, attr)-> element.hide()
