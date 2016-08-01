app.controller 'MainAuthentificationEditCtrl', ($rootScope, $scope, $state, $mdDialog, Authentification, People, resolved)->
  $scope.data = angular.copy resolved
  $scope.data.password = ""

  $scope.switchStatus = true

  $rootScope.arduino.stopListener()

  People.cachedList()
  .then ({data})->
    $scope.people = _.map(data, (elmt)->{login: elmt.login, firstname: elmt.firstname, lastname: elmt.lastname, mail: elmt.mail})
  .catch (res)->

  $scope.delete = (ev, item)->
    confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation suppression').content('Voulez-vous effacer cet identifiant de connexion ?').ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
    $mdDialog.show(confirm).then (->
      Authentification.delete(item.login)
      .then ->
        $state.go 'authentification'
      .catch ({data})->
        $rootScope.toastError data
      return
    ), ->
      return
    return

  $scope.submit = (data)->
    if data then data.person = null
    action = if data._id then Authentification.put(data.login, data) else Authentification.post(data)
    action.then ({data})->
      $state.go 'authentificationView', {_id: data._id, login: data.login}
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
      $scope.data.login = text

  $scope.selectedItemChange = (item)->
    if item
      $scope.data.login=item.value
      i=0
      while i<$scope.people.length
        if $scope.data.login==$scope.people[i].login
          if $scope.people[i].mail && $scope.people[i].mail!=''
            $scope.data.mail=$scope.people[i].mail
          break
        i++

  # Correction of a bug : when the item is selected in the list sometimes the text used is 'display' attribute not the 'value' attribute
  $scope.$watch 'data.login', (value) ->
    if value and value.indexOf(' [')>=0
      $scope.data.login=value.substring(0, value.indexOf(' ['))
    return

