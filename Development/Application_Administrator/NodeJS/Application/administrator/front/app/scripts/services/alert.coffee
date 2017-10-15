app.factory "Alert", ($http, $rootScope, $sessionStorage, config) ->
  exports = {}
  base = config.apiUrl + "/alert"

  exports.list     = (minimumlevel, month) -> $http.get    "#{base}?minimumlevel=#{minimumlevel}&month=#{month}"
  exports.getCache = (params)              -> $http.get    "#{base}?action=get-cache-infos", {params: params}

  exports.get      = (_id)                 -> $http.get    "#{base}/#{_id}"


  exports

