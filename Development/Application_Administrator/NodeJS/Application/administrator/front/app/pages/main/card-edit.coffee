app.controller 'MainCardEditCtrl', ($rootScope, $scope, $state, $mdDialog, $interval, Card, People, resolved)->
  $scope.data = angular.copy resolved

  $rootScope.arduino.startListener()

  People.cachedList()
  .then ({data})->
    $scope.people = _.map(data, (elmt)->{login: elmt.login, firstname: elmt.firstname, lastname: elmt.lastname})
  .catch (res)->

  $scope.lastSeq = null

  $scope.delete = (ev, item)->
    confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation suppression').content('Voulez-vous effacer cette carte ?').ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
    $mdDialog.show(confirm).then (->
      Card.delete(item._id)
      .then ->
        $state.go 'card'
      .catch ({data})->
        $rootScope.toastError data
      return
    ), ->
      return
    return


  $scope.submit = (data)->
    if data then data.person = null
    action = if data._id then Card.put(data._id, data) else Card.post(data)
    action.then ({data})->
      $state.go 'cardView', {_id: data._id}
    .catch ({data})->
      $rootScope.toastError data

  $scope.querySearch = (txt)->
    filtered_auxi = $rootScope.filterList
      list: $scope.people,
      fields: (item)-> [item.login, item.firstname, item.lastname]
      search: txt
    filtered_auxi=filtered_auxi.slice(0, 4)
    _.map(filtered_auxi, (elmt)->{value: elmt.login, display: elmt.login+' ['+elmt.firstname+' '+elmt.lastname+']'})

  $scope.searchTextChange = (text)->
    if text
      $scope.data.owner = text

  $scope.selectedItemChange = (item)->
    if item
      $scope.data.owner = item.value

  $scope.$watch (-> if $rootScope.arduino.lastSignal && $rootScope.arduino.lastSignal.signal && $rootScope.arduino.lastSignal.signal.event == 'reading'
    ($rootScope.arduino.lastSignal)), (value)-> 
      if !$scope.lastSeq && value
        $scope.lastSeq = value.seq
      else if value && $scope.lastSeq!=value.seq
        $scope.data.code=value.signal.message

  # Correction of a bug : when the item is selected in the list sometimes the text used is 'display' attribute not the 'value' attribute
  $scope.$watch 'data.owner', (value) ->
    if value and value.indexOf(' [')>=0
      $scope.data.owner=value.substring(0, value.indexOf(' ['))
    return

