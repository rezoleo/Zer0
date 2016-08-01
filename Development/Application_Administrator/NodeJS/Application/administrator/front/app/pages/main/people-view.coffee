app.controller 'MainPeopleViewCtrl', ($rootScope, $scope, $mdDialog, $state, moment, People, Picture, resolved)->
  $scope.data = angular.copy resolved

  $scope.picture = '/images/user.png'
  if $scope.data.picture && $scope.data.picture!=''
    random = new Date().getTime()
    $scope.picture = '/api/picture/'+$scope.data._id+'/'+random

  $rootScope.arduino.stopListener()

  $scope.delete = (ev)->
    confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation suppression').content('Voulez-vous supprimer le portrait ?').ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
    $mdDialog.show(confirm).then (->
      Picture.delete($scope.data._id)
      .then ({data})->
        $scope.picture = '/images/user.png'
        $scope.data = data
      .catch ({data})->
        $rootScope.toastError data
      return
    ), ->
      return
    return

MainPeopleResolve =
  resolved: [
    '$q'
    '$stateParams'
    'People'
    ($q, $stateParams, People) ->
      deferred = $q.defer()
      People.get($stateParams._id)
      .then ({data})->
        if data.birthdate
          data.birthdate = moment(data.birthdate).format('DD-MM-YYYY')
        if !data.major
          data.major = false

        deferred.resolve(data)
      .catch ({data})->
        $rootScope.toastError data
        deferred.reject()
      return deferred.promise
  ]
