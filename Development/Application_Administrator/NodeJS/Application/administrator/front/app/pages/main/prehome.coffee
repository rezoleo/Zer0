app.controller 'MainPrehomeCtrl', ($rootScope, $scope, $state, Auth)->

  $rootScope.arduino.stopListener()

  if $scope.auth.is.user
    $state.go('home')
  $scope.submit = (data)->
    Auth.login(data)
    .then ->
      $state.go('home')
