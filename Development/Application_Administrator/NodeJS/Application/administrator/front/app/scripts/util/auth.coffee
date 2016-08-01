app.run ($rootScope, $state, $q, Auth, People, roles)->
  # auth state
  $rootScope.auth = null
  $rootScope.picture = '/images/user.png'
  $rootScope.myinfo = null
  to = {}

  # get the personnal info
  $rootScope.getMyInfo = ()->
    if $rootScope.hasPermission({category : "PEOPLE", require : "READ"}) && $rootScope.auth.login
      People.getLogin($rootScope.auth.login)
      .then ({data})->
        $rootScope.myinfo = data
        if data.picture && data.picture!=''
          $rootScope.picture = '/api/picture/'+data._id
      .catch ({data})->
        null

  # change auth state
  $rootScope.changeAuth = (data)->
    # check connected
    if !data || !data.login? then data = {_id: null}
    # set roles
    data.is = {}
    for key, test of roles then data.is[key] = !test data
    # set auth state
    $rootScope.auth = data

    # emit event
    $rootScope.$emit 'auth'

    if data
      $rootScope.getMyInfo()

  # load user state
  $rootScope.authPromise = ->
    if $rootScope.auth then return $rootScope.auth
    Auth.me()
    .then ({data})->
      $rootScope.changeAuth data
    .catch ->
      $rootScope.changeAuth()
    .finally ->
      # reload page to check permission
      $state.transitionTo(to.state, to.params, {reload: true, inherit: true, notify: true});

  # role checker
  $rootScope.authIs = (role)->
    if !$rootScope.auth then return false
    undefined == roles[role] $rootScope.auth

  # check roles
  $rootScope.$on '$stateChangeStart', (event, toState, toStateParams)->
    if !$rootScope.auth
      to = {state: toState, params: toStateParams}
      return
    if !toState.roles then return
    stateRoles = toState.roles
    if !angular.isArray stateRoles then stateRoles = [stateRoles]
    for role in stateRoles
      if angular.isString role then role = roles[role]
      if action = role $rootScope.auth
        event.preventDefault()
        $state.go action
        return

  $rootScope.logout = ->
    Auth.logout()
    .then ->
      $state.transitionTo(to.state, to.params, {reload: true, inherit: true, notify: true});
