app.controller 'MainGroupEditCtrl', ($rootScope, $scope, $state, $mdDialog, Group, resolved)->
  $scope.data = angular.copy resolved

  $rootScope.arduino.stopListener()

  $scope.delete = (ev, item)->
    confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation suppression').content('Voulez-vous effacer ce groupe ?').ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
    $mdDialog.show(confirm).then (->
      Group.delete(item._id)
      .then ->
        $state.go 'group'
      .catch ({data})->
        $rootScope.toastError data
      return
    ), ->
      return
    return

  $scope.submit = (data)->
    action = if data._id then Group.put(data._id, data) else Group.post(data)
    action.then ({data})->
      $state.go 'groupView', {_id: data._id}
    .catch ({data})->
      $rootScope.toastError data

