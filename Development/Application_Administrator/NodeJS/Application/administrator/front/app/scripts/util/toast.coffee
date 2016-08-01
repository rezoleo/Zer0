app.run ($rootScope, $mdToast, $animate, $state, config)->
  $rootScope.toastError = (data)->
    toast = $mdToast.simple().content(data.message + " [" + data.code + "]").hideDelay(config.toastLast).highlightAction(false)
    toast.action "DETAILS"  if data.stack
    $mdToast.show(toast).then ->
      $mdToast.show $mdToast.simple().content(data.stack).hideDelay(config.toastLast)

