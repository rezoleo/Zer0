app.controller 'MainAlertCtrl', ($rootScope, $scope, $state, $mdDialog, $window, Alert)->
  $scope.params = {}
  $scope.data = {search: '', checkbox: null, month: null}


  $rootScope.arduino.stopListener()

  $scope.items = [{level:'CRITICAL', label:'CRITIQUE',      color:'#FF00FF'},
                  {level:'ERROR',    label:'ERREUR',        color:'#FF0000'},
                  {level:'WARNING',  label:'AVERTISSEMENT', color:'#FFFF00'},
                  {level:'INFO',     label:'INFORMATION',   color:'#00FF00'}];

  $scope.data.checkbox = $scope.items[0]

  $scope.current  = {month: (new Date()).getMonth()+1, year: (new Date()).getFullYear()}
  $scope.maxdate  = {month: (new Date()).getMonth()+1, year: (new Date()).getFullYear()}
  $scope.previous = $scope.current
  $scope.next     = $scope.current

  $scope.update_date_info = ->
    if $scope.current.month==1
      $scope.previous = {month: 12, year: $scope.current.year-1}
    else
      $scope.previous = {month: $scope.current.month-1, year: $scope.current.year}
    if $scope.current.month==12
      $scope.next = {month: 1, year: $scope.current.year+1}
    else
      $scope.next = {month: $scope.current.month+1, year: $scope.current.year}

    $scope.data.month=$scope.current.year+'_'+$scope.current.month

    if $scope.current.year>$scope.maxdate.year
      $scope.disabled_next=true
    if $scope.current.year==$scope.maxdate.year && $scope.current.month>=$scope.maxdate.month
      $scope.disabled_next=true
    else
      $scope.disabled_next=false

    $scope.update()

  $scope.whichColor = (level)->
    i=0
    while i<$scope.items.length
      if $scope.items[i].level==level
         return $scope.items[i].color
      i++
    return ''

  $scope.whichLabel = (level)->
    i=0
    while i<$scope.items.length
      if $scope.items[i].level==level
         return $scope.items[i].label
      i++
    return level

  $scope.isChecked = (item)->
    res = false
    i=$scope.items.length-1
    while i>=0
      if $scope.items[i]==$scope.data.checkbox
        res=true
      if $scope.items[i]==item
        break
      i--
    return res

  $scope.toggle = (item)->
    $scope.data.checkbox=item

  $scope.update = ->
    Alert.list($scope.items[$scope.items.length-1].level, $scope.data.month)
    .then ({data})->
      $scope.list = data
      $scope.filter()
    .catch ({data})->
      $rootScope.toastError data

  $scope.filter = ->
    filtered_auxi = $rootScope.filterList
      list: $scope.list,
      fields: (item)-> [item.created, item.level, item.creatorService, item.message]
      search: $scope.data.search
    i=0
    k=0
    final_filtered=[]
    while i<filtered_auxi.length
      j=0;
      while j<$scope.items.length
        if $scope.items[j].level==filtered_auxi[i].level
          final_filtered[k]=filtered_auxi[i]
          k++
          break
        if $scope.items[j]==$scope.data.checkbox
          break
        j++
      i++
    $scope.filtered = final_filtered

  $scope.previous_month = ->
    $scope.current.month=$scope.previous.month
    $scope.current.year=$scope.previous.year
    $scope.update_date_info()

  $scope.next_month = ->
    $scope.current.month=$scope.next.month
    $scope.current.year=$scope.next.year
    $scope.update_date_info()

  $scope.export = ->
    $window.location.href = '/api/alert?format=csv&minimumlevel='+$scope.data.checkbox.level+'&month='+$scope.data.month

  $scope.$watch 'data', $scope.filter, true

  $scope.update_date_info()

