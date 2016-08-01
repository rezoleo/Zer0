app.factory "Authentification", ($http, $rootScope, config) ->
  exports = {}
  base = config.apiUrl + "/authentification"

  exports.post   = (data)        -> $http.post   "#{base}", data
  exports.list   = (params)      -> $http.get    "#{base}", {params: params}
  exports.get    = (login)       -> $http.get    "#{base}/#{login}"
  exports.put    = (login, data) -> $http.put    "#{base}/#{login}", data
  exports.delete = (login)       -> $http.delete "#{base}/#{login}"


  exports

