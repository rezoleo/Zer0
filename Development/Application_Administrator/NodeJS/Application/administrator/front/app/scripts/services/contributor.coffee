app.factory "Contributor", ($http, $rootScope, $sessionStorage, config)->
  exports = {}
  base = config.apiUrl + "/contributor"

  exports.post       = (data)   -> $http.post   "#{base}", data
  exports.list       = (params) -> $http.get    "#{base}", {params: params}
  exports.getCache   = (params) -> $http.get    "#{base}?action=get-cache-infos", {params: params}
  exports.cachedList = (params) -> 
    then : (callbackThen) ->
      catch : (callbackCatch) ->
        exports.getCache()
        .then ({data})->
          if data && data.lastUpdate && $sessionStorage.contributor && data.lastUpdate == $sessionStorage.contributor.lastUpdate
            callbackThen({data : $sessionStorage.contributor.data})
          else
            $sessionStorage.contributor = { lastUpdate : data.lastUpdate, data : null }
            exports.list(params)
            .then (arg)->
              $sessionStorage.contributor.data = arg.data
              callbackThen(arg)
            .catch (res)->
              $sessionStorage.contributor = { lastUpdate : null, data : null }
              callbackCatch(res)
        .catch ()->
          $sessionStorage.contributor = { lastUpdate : null, data : null }
          exports.list(params).then(callbackThen).catch(callbackCatch)
  exports.get        = (_id)    -> $http.get    "#{base}/#{_id}"
  exports.delete     = (_id)    -> $http.delete "#{base}/#{_id}"


  exports
