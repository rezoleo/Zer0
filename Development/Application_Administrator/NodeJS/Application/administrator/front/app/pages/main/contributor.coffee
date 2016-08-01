app.controller 'MainContributorCtrl', ($rootScope, $scope, $state, $mdDialog, $window, Contributor, People)->
  $scope.params = {}
  $scope.data = {search: '', page:0}

  $rootScope.arduino.stopListener()

  People.cachedList()
  .then ({data})->
    $scope.people = _.map(data, (elmt)->{login: elmt.login, firstname: elmt.firstname, lastname: elmt.lastname, sex: elmt.sex, mail: elmt.mail})
    $scope.update();
  .catch (res)->

  $scope.update = ->
    Contributor.cachedList($scope.params)
    .then ({data})->
      $scope.list = data
      $scope.filter()
    .catch ({data})->
      $rootScope.toastError data

  $scope.delete = (ev, item)->
    confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation suppression').content('Voulez-vous retirer cette personne de la liste des côtisants ?').ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
    $mdDialog.show(confirm).then (->
      Contributor.delete(item._id)
      .then ->
        $scope.update()
      .catch ({data})->
        $rootScope.toastError data
      return
    ), ->
      return
    return

  $scope.submit = (data)->
    if data then data.person = null
    action = Contributor.post(data)
    action.then ({data})->
      $scope.data.search=''
      $scope.update()
    .catch ({data})->
      $rootScope.toastError data

  $scope.filter = ->
    filtered_auxi = $rootScope.filterList
      list: $scope.list,
      fields: (item)-> [item.login, item.firstname, item.lastname, item.mail]
      search: $scope.data.search
    $scope.filtered_length = filtered_auxi.length
    $scope.filtered = filtered_auxi.slice(24 * $scope.data.page, 24 * ($scope.data.page + 1))
    i = 0
    while i < $scope.filtered.length
      j = 0
      if $scope.people
        while j < $scope.people.length
          if $scope.filtered[i].login == $scope.people[j].login
            $scope.filtered[i].firstname = $scope.people[j].firstname
            $scope.filtered[i].lastname = $scope.people[j].lastname
            $scope.filtered[i].sex = $scope.people[j].sex
            $scope.filtered[i].mail = $scope.people[j].mail
          j++
      i++

  # go to the item en keypress enter
  $scope.checkEnter = ($event)->
    if $event.which == 13 and $scope.filtered.length > 0 then $state.go 'contributorView', $scope.filtered[0]

  $scope.export = ->
    $window.location.href = '/api/contributor?format=csv'

  $scope.$watch 'data', $scope.filter, true
  $scope.$watch 'data.search', -> $scope.data.page = 0

  $scope.update()

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
      $scope.data.login = item.value

  # Correction of a bug : when the item is selected in the list sometimes the text used is 'display' attribute not the 'value' attribute
  $scope.$watch 'data.login', (value) ->
    if value and value.indexOf(' [')>=0
      $scope.data.login=value.substring(0, value.indexOf(' ['))
    return

