app.factory "Arduino", ($http, $rootScope, config) ->
  exports = {}
  base = config.apiUrl + "/arduino"

  exports.readSignal = (params) -> $http.get "#{base}/signal/", {params: params}
  exports.readSystem = (params) -> $http.get "#{base}/system/", {params: params}


  exports

