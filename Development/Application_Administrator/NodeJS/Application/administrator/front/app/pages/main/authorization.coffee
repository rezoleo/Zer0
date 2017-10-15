app.controller 'MainAuthorizationCtrl', ($rootScope, $scope, $state, $mdDialog, Authentification, People, Roles)->
  $scope.params = {}
  $scope.data = {}

  $rootScope.arduino.stopListener()

  $scope.auth = []
  $scope.people = []

  loadPeopleList = ()->
    People.cachedList()
    .then ({data})->
      $scope.people = _.map(data, (elmt)->{_id: elmt._id, login: elmt.login, firstname: elmt.firstname, lastname: elmt.lastname})
      i=0
      while i<$scope.auth.length
        j=0
        while j<$scope.people.length
          if $scope.auth[i].login==$scope.people[j].login
            $scope.auth[i]=$scope.people[j]
          j++
        i++
      $scope.update()
    .catch (res)->
      $rootScope.toastError data
      $scope.update()

  Authentification.list($scope.params)
  .then ({data})->
    $scope.auth = _.map(data, (elmt)->{login: elmt.login})
    loadPeopleList()
  .catch (res)->
    $rootScope.toastError data
    loadPeopleList()


  category = [{label:'alert',            prefix:'ALERT'},
              {label:'authentification', prefix:'AUTH'},
              {label:'card',             prefix:'CARD'},
              {label:'contributor',      prefix:'CONTRIBUTOR'},
              {label:'group',            prefix:'GROUP'},
              {label:'people',           prefix:'PEOPLE'}]

  $scope.authorizations = {}

  hasSuffix = (txt, suffix)->
    if txt && suffix
      pos=txt.indexOf(suffix, -1)
      if pos>-1 && (pos+suffix.length)==txt.length
        return true
    return false

  $scope.items = [{label:'LECTURE',        suffix:'_READ'},
                  {label:'ECRITURE',       suffix:'_WRITE'},
                  {label:'ADMINISTRATION', suffix:'_ADMIN'}];
  $scope.items_alert = [{label:'LECTURE',        suffix:'_READ'},
                        {label:'ADMINISTRATION', suffix:'_ADMIN'}];
  translateRoles = (category_label, roles)->
    if category_label=="alert"
      items=$scope.items_alert
    else
      items=$scope.items
    aux_roles = []
    if roles && roles.length>0
      i=0
      while i<roles.length
        j=0
        while j<items.length
          idx = aux_roles.indexOf(items[j].label)
          if idx<=0
            aux_roles.push items[j].label
          if hasSuffix(roles[i], items[j].suffix)
            break
          j++
        i++
    aux_roles

  sortFunction = (a,b)->
    if (!a && !b)||(!a.login && !b.login)
      return  0
    if (!a && b) ||(!a.login && b.login)
      return -1
    if (a && !b) ||(a.login && !b.login)
      return +1
    if (a.login<b.login)
      return -1
    if (a.login==b.login)
      return  0
    if (a.login>b.login)
      return +1
    return 0

  auxiFunction = (category_label, pos, decrease)->
    if pos<0
      return
    label=category[pos].label
    Roles.get(label)
    .then ({data})->
      $scope.authorizations[label] = data.sort(sortFunction)
      i=0
      while i<$scope.authorizations[label].length
        if $scope.authorizations[label][i].roles
          $scope.authorizations[label][i].roles=translateRoles(category_label, $scope.authorizations[label][i].roles)
        else
          $scope.authorizations[label][i].roles=[]
        j=0
        while j<$scope.auth.length
          if $scope.authorizations[label][i].login==$scope.auth[j].login
            $scope.authorizations[label][i].person=$scope.auth[j]
            break
          j++
        i++
      if decrease
        auxiFunction(category_label, pos-1, true)
    .catch ({data})->
      if decrease
        auxiFunction(category_label, pos-1, true)

  $scope.update = (category_label)->
    if category_label
      i=0
      while i<category.length
        if category[i].label==category_label
          auxiFunction(category_label, i, false)
        i++
    else
      auxiFunction(category_label, category.length-1, true)

  $scope.querySearch = (txt)->
    filtered_auxi = $rootScope.filterList
      list: $scope.auth,
      fields: (item)-> [item.login, item.firstname, item.lastname]
      search: txt
    filtered_auxi = filtered_auxi.slice(0, 4)
    _.map filtered_auxi, (elmt) ->
      aux = 
        value: elmt.login
        display: elmt.login
      if elmt.firstname && elmt.lastname
        aux =
          value: elmt.login
          display: elmt.login + ' [' + elmt.firstname + ' ' + elmt.lastname + ']'
      aux

  $scope.selectedItemChange = (category_label, item)->
    if item
      $scope.data[category_label].login = item.value

  $scope.searchTextChange = (category_label, text)->
    if text
      $scope.data[category_label].login = text


  $scope.add = (category_label, login)->
    if category_label=="alert"
      items=$scope.items_alert
    else
      items=$scope.items
    i=0
    while i<category.length
      if category[i].label==category_label
        j=0
        flag=true
        while j<$scope.authorizations[category_label].length
          if $scope.authorizations[category_label][j].login==login
            flag=false
            break
          j++
        if flag
          Roles.post(category_label, login, category[i].prefix+items[0].suffix)
          .then ({data})->
            $scope.update(category_label)
          .catch ({data})->
            $rootScope.toastError data
            $scope.update(category_label)
        break
      i++


  $scope.exists = (category_label, item, list) ->
    list.indexOf(item.label) > -1

  $scope.toggle = (ev, category_label, login, item, list) ->
    if category_label=="alert"
      items=$scope.items_alert
    else
      items=$scope.items
    auxi_function = ->
      final_role=null
      i=0
      while i<category.length
        if category[i].label==category_label
          final_role=category[i].prefix+item.suffix
          j=0
          while j<items.length
            if items[j].label==item.label
              if j+1==list.length
                if j>0
                  final_role=category[i].prefix+items[j-1].suffix
                else if list.length!=0
                  final_role=null
                break
            j++
          break
        i++
      if final_role
        Roles.post(category_label, login, final_role)
        .then ({data})->
          $scope.update(category_label)
        .catch ({data})->
          $rootScope.toastError data
          $scope.update(category_label)

    isAdmin = _.contains(list,"ADMINISTRATION")
    adminCheckboxClicked = item.suffix=="_ADMIN"

    confirm_msg=null
    if isAdmin
      confirm_msg="Êtes-vous sûr de retirer les droits d\'administration à cet utilisateur ?"
    else if adminCheckboxClicked && !isAdmin
      confirm_msg="Êtes-vous sûr de vouloir attribuer les droits d\'administration à cet utilisateur ?"

    if confirm_msg
      confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation action').content(confirm_msg).ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
      $mdDialog.show(confirm).then (->
        auxi_function()
      ), ->
        return
    else
      auxi_function()

  $scope.delete = (category_label, login) ->
    Roles.delete(category_label, login)
    .then ({data})->
      $scope.update(category_label)
    .catch ({data})->
      $rootScope.toastError data
      $scope.update(category_label)


  $scope.update()

