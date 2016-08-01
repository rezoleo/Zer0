app.controller 'MainAuthentificationCtrl', ($rootScope, $scope, $state, Authentification)->
  $scope.params = {}
  $scope.data = {search: '', page: 0}

  $rootScope.arduino.stopListener()

  $scope.update = ->
    Authentification.list($scope.params)
    .then ({data})->
      $scope.list = data
      $scope.filter()
    .catch ({data})->
      $rootScope.toastError data

  $scope.filter = ->
    filtered_auxi = $rootScope.filterList
      list: $scope.list,
      fields: (item)-> [item.login]
      search: $scope.data.search
    $scope.filtered_length = filtered_auxi.length
    $scope.filtered = filtered_auxi.slice(24 * $scope.data.page, 24 * ($scope.data.page + 1))

  # go to the item en keypress enter
  $scope.checkEnter = ($event)->
    if $event.which == 13 and $scope.filtered.length > 0 then $state.go 'authentificationView', $scope.filtered[0]

  $scope.$watch 'data', $scope.filter, true
  $scope.$watch 'data.search', -> $scope.data.page = 0

  $scope.update()
