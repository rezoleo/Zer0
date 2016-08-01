app.controller 'MainPeopleEditCtrl', ($rootScope, $scope, $state, $mdDialog, $timeout, config, Upload, People, Picture, resolved)->
  $scope.data = angular.copy resolved

  $scope.picture = '/images/user.png'
  if $scope.data._id && $scope.data.picture && $scope.data.picture!=''
    random = new Date().getTime()
    $scope.picture = '/api/picture/'+$scope.data._id+'/'+random
  $scope.maxSize = config.pictureMaxSize
  $scope.picFile = null

  if !$scope.data.tags
    $scope.data.tags = []

  if !$scope.data.major
    $scope.data.major = false

  $scope.saved_tags = $scope.data.tags
  if $scope.saved_tags
    i = 0
    while i < $scope.saved_tags.length
      $scope.saved_tags[i] = $scope.saved_tags[i].toUpperCase();
      i++
    $scope.saved_tags = _.uniq $scope.saved_tags

  $rootScope.arduino.stopListener()

  $scope.delete = (ev, item)->
    confirm = $mdDialog.confirm().parent(angular.element(document.body)).title('Confirmation suppression').content('Voulez-vous effacer cette personne ?').ariaLabel('Lucky day').ok('Oui').cancel('Non').targetEvent(ev)
    $mdDialog.show(confirm).then (->
      deleteAuxiFunc = ()->
        People.delete(item._id)
        .then ->
          $state.go 'people'
        .catch ({data})->
          $rootScope.toastError data
        return

      if $scope.data && $scope.data.picture && $scope.data.picture!=''
        Picture.delete(item._id)
        .then ({data})->
          deleteAuxiFunc()
        .catch ({data})->
          $rootScope.toastError data
          deleteAuxiFunc()
      else
        deleteAuxiFunc()

      return
    ), ->
      return
    return

  $scope.submit = (data)->
    if data.birthdate
      data.birthdate = moment(data.birthdate, 'DD-MM-YYYY').toDate();

    i = 0
    while (i < data.tags.length)
      data.tags[i] = data.tags[i].toUpperCase()
      i++
    data.tags = _.uniq(data.tags)
    tags_to_add = _.difference(data.tags, $scope.saved_tags)
    tags_to_remove = _.difference($scope.saved_tags, data.tags)

    updatePicture = (_id, dataUrl)->
      if dataUrl && dataUrl!='' && $scope.picFile
        Picture.upload(_id, dataUrl, 'png')
        .then ({data})->
          $state.go 'peopleView', {_id: _id}
        .catch ({data})->
          $rootScope.toastError data
          $state.go 'peopleView', {_id: _id}
      else
        $state.go 'peopleView', {_id: _id}

    delTags = (_id, i)->
      if i<0
        updatePicture(_id, $scope.croppedDataUrl)

      else
        People.delTag(_id, tags_to_remove[i])
        .then ({data})->
          delTags(_id, i-1)
        .catch ({data})->
          $rootScope.toastError data
          $state.go 'peopleView', {_id: _id}

    addTags = (_id, i)->
      if i<0
        delTags(_id, tags_to_remove.length-1)
      else
        People.addTag(_id, tags_to_add[i])
        .then ({data})->
          addTags(_id, i-1)
        .catch ({data})->
          $rootScope.toastError data
          $state.go 'peopleView', {_id: _id}

    action = if data._id then People.put(data._id, data) else People.post(data)

    action.then ({data})->
      addTags(data._id, tags_to_add.length-1)
    .catch ({data})->
      $rootScope.toastError data

  year = new Date().getFullYear()
  known_tags = ['AGR', 'EXTERN', 'ING-G1', 'ING-G2', 'ING-G3', 'ITEEM-I1', 'ITEEM-I2', 'ITEEM-I3', 'ITEEM-I4', 'ITEEM-I5']
  i = 0
  while i<4
    known_tags.push 'PROMO-'+(year+i)
    i++
  i = 0
  while i<2
    known_tags.push 'CESURE-'+(year+i)
    known_tags.push 'DD-'+(year+i)
    known_tags.push 'DOCTORANT-'+(year+i)
    i++

  $scope.querySearch = (text)->
    if text
      text=text.toUpperCase()

    filtered_auxi = _.filter _.map(known_tags, (elmt)->elmt.toUpperCase()), (item)->
      item.indexOf(text) != (-1)
    filtered_auxi=filtered_auxi.slice(0, 5)

  $scope.searchTextChange = (text)->
    text

  $scope.selectedItemChange = (item)->
    item



  $scope.$watch (->
    if $scope.data.firstname and $scope.data.lastname
      login = ($scope.data.firstname[0] + $scope.data.lastname).substr(0, 8).toUpperCase()
      accent = [/[\300-\306]/g, /[\310-\313]/g, /[\314-\317]/g, /[\322-\330]/g, /[\331-\334]/g, /[\321]/g, /[\307]/g]
      noaccent = ['A', 'E', 'I', 'O', 'U', 'N', 'C']
      i = 0
      while i < accent.length
        login = login.replace(accent[i], noaccent[i])
        i++
      login = login.replace(' ', '_')
      login = login.toLowerCase()
      return login
    return
  ), (value, old) ->
    if !$scope.data.login or $scope.data.login == old
      return $scope.data.login = value
    return

