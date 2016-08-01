app.factory "Card", ($http, $rootScope, $sessionStorage, config) ->
  exports = {}
  base = config.apiUrl + "/card"

  exports.post     = (data)      -> $http.post   "#{base}", data
  exports.list     = (params)    -> $http.get    "#{base}", {params: params}
  exports.getCache = (params)    -> $http.get    "#{base}?action=get-cache-infos", {params: params}
  exports.cachedList = (params) -> 
    then : (callbackThen) ->
      catch : (callbackCatch) ->
        exports.getCache()
        .then ({data})->
          if data && data.lastUpdate && $sessionStorage.card && data.lastUpdate == $sessionStorage.card.lastUpdate
            callbackThen({data : $sessionStorage.card.data})
          else
            $sessionStorage.card = { lastUpdate : data.lastUpdate, data : null }
            exports.list(params)
            .then (arg)->
              $sessionStorage.card.data = arg.data
              callbackThen(arg)
            .catch (res)->
              $sessionStorage.card = { lastUpdate : null, data : null }
              callbackCatch(res)
        .catch ()->
          $sessionStorage.card = { lastUpdate : null, data : null }
          exports.list(params).then(callbackThen).catch(callbackCatch)
  exports.get      = (_id)       -> $http.get    "#{base}/#{_id}"
  exports.getCode  = (code)      -> $http.get    "#{base}/code/#{_id}"
  exports.put      = (_id, data) -> $http.put    "#{base}/#{_id}", data
  exports.delete   = (_id)       -> $http.delete "#{base}/#{_id}"


  exports

