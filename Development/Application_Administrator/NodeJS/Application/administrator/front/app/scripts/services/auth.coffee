app.factory "Auth", ($http, $rootScope, $state, config) ->
  exports = {}
  base = config.apiUrl + "/login"

  exports.me = ->
    $http.get("" + base).error (data, status, headers, config) ->
      $rootScope.toastError data

  exports.login = (data) ->
    $http.post(base + "/" + data.login,
      password: data.password
    ).error((data, status, headers, config) ->
      $rootScope.toastError data
      ()-> null
    ).then ({data}) ->
      $rootScope.changeAuth data
      ()-> null

  exports.logout = ->
    $http.delete("" + base
    ).error((data, status, headers, config) ->
      $rootScope.toastError data
      ()-> null
    ).then ({data}) ->
      $rootScope.changeAuth()
      $state.go('prehome')
      ()-> null


  exports

