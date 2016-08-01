app.controller 'MainCardViewCtrl', ($rootScope, $scope, $state, Card, People, resolved)->
  $scope.data = angular.copy resolved

  $rootScope.arduino.stopListener()

MainCardResolve =
  resolved: [
    '$q'
    '$stateParams'
    'Card'
    'People'
    ($q, $stateParams, Card, People) ->
      deferred = $q.defer()
      data = {}
      Card.get($stateParams._id)
      .then ({data})->
        People.getLogin(data.owner)
        .then (res)->
          data.person = res.data
          deferred.resolve(data)
        .catch (res)->
          deferred.resolve(data)
      .catch ({data})->
        $rootScope.toastError data
        deferred.reject()
      return deferred.promise
  ]
