app.controller 'MainAuthentificationViewCtrl', ($rootScope, $scope, $state, Authentification, People, resolved)->
  $scope.data = angular.copy resolved

  $rootScope.arduino.stopListener()

MainAuthentificationResolve =
  resolved: [
    '$q'
    '$stateParams'
    'Authentification'
    'People'
    ($q, $stateParams, Authentification, People) ->
      deferred = $q.defer()
      data = {}
      Authentification.get($stateParams.login)
      .then ({data})->
        People.getLogin($stateParams.login)
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
