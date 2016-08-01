app.controller 'MainPeopleCtrl', ($rootScope, $scope, $state, $window, People)->
  $scope.params = {}
  $scope.data = {search: '', page: 0}

  $rootScope.arduino.stopListener()

  $scope.update = ->
    People.cachedList($scope.params)
    .then ({data})->
      $scope.list = data
      $scope.filter()
    .catch ({data})->
      $rootScope.toastError data

  $scope.filter = ->
    filtered_auxi = $rootScope.filterList
      list: $scope.list,
      fields: (item)-> [item.login, item.firstname, item.lastname, item.mail]
      search: $scope.data.search
    $scope.filtered_length = filtered_auxi.length
    $scope.filtered = filtered_auxi.slice(24 * $scope.data.page, 24 * ($scope.data.page + 1))

  # go to the item en keypress enter
  $scope.checkEnter = ($event)->
    if $event.which == 13 and $scope.filtered.length > 0 then $state.go 'peopleView', $scope.filtered[0]

  $scope.export = ->
    $window.location.href = '/api/people?format=csv'

  $scope.$watch 'data', $scope.filter, true
  $scope.$watch 'data.search', -> $scope.data.page = 0

  $scope.update()
