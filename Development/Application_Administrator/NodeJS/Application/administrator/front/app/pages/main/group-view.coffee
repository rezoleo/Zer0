app.controller 'MainGroupViewCtrl', ($rootScope, $scope, $state, Group, resolved)->
  $scope.data = angular.copy resolved

  $rootScope.arduino.stopListener()

MainGroupResolve =
  resolved: [
    '$q'
    '$stateParams'
    'Group'
    ($q, $stateParams, Group) ->
      deferred = $q.defer()
      Group.get($stateParams._id)
      .then ({data})->
        deferred.resolve(data)
      .catch ({data})->
        $rootScope.toastError data
        deferred.reject()
      return deferred.promise
  ]
