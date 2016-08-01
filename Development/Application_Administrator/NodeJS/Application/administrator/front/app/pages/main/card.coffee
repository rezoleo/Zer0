app.controller 'MainCardCtrl', ($rootScope, $scope, $state, $interval, $window, Card)->
  $scope.params = {}
  $scope.data = {search: '', page:0}

  $rootScope.arduino.startListener()
  $scope.lastSeq = null

  $scope.update = ->
    Card.cachedList($scope.params)
    .then ({data})->
      $scope.list = data
      $scope.filter()
    .catch ({data})->
      $rootScope.toastError data

  $scope.filter = ->
    filtered_auxi = $rootScope.filterList
      list: $scope.list,
      fields: (item)-> [item.owner, item.code]
      search: $scope.data.search
    $scope.filtered_length = filtered_auxi.length
    $scope.filtered = filtered_auxi.slice(24 * $scope.data.page, 24 * ($scope.data.page + 1))

  # go to the item en keypress enter
  $scope.checkEnter = ($event)->
    if $event.which == 13 and $scope.filtered.length > 0 then $state.go 'cardView', $scope.filtered[0]

  $scope.export = ->
    $window.location.href = '/api/card?format=csv'

  $scope.$watch (-> if $rootScope.arduino.lastSignal && $rootScope.arduino.lastSignal.signal && $rootScope.arduino.lastSignal.signal.event == 'reading'
    ($rootScope.arduino.lastSignal)), (value)-> 
      if !$scope.lastSeq && value
        $scope.lastSeq = value.seq
      else if value && $scope.lastSeq!=value.seq
        $scope.data.search=value.signal.message

  $scope.$watch 'data', $scope.filter, true
  $scope.$watch 'data.search', -> $scope.data.page = 0

  $scope.update()

