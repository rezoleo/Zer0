app.factory "People", ($http, $rootScope, $sessionStorage, config)->
  exports = {}
  base = config.apiUrl + "/people"

  exports.post       = (data)      -> $http.post   "#{base}", data
  exports.list       = (params)    -> $http.get    "#{base}", {params: params}
  exports.getCache   = (params)    -> $http.get    "#{base}?action=get-cache-infos", {params: params}
  exports.get        = (_id)       -> $http.get    "#{base}/#{_id}"
  exports.getLogin   = (login)     -> $http.get    "#{base}/login/#{login}"
  exports.getMail    = (mail)      -> $http.get    "#{base}/mail/#{mail}"
  exports.cachedList = (params) -> 
    then : (callbackThen) ->
      catch : (callbackCatch) ->
        exports.getCache()
        .then ({data})->
          if data && data.lastUpdate && $sessionStorage.people && data.lastUpdate == $sessionStorage.people.lastUpdate
            callbackThen({data : $sessionStorage.people.data})
          else
            $sessionStorage.people = { lastUpdate : data.lastUpdate, data : null }
            exports.list(params)
            .then (arg)->
              $sessionStorage.people.data = arg.data
              callbackThen(arg)
            .catch (res)->
              $sessionStorage.people = { lastUpdate : null, data : null }
              callbackCatch(res)
        .catch ()->
          $sessionStorage.people = { lastUpdate : null, data : null }
          exports.list(params).then(callbackThen).catch(callbackCatch)
  exports.put        = (_id, data) -> $http.put    "#{base}/#{_id}", data
  exports.delete     = (_id)       -> $http.delete "#{base}/#{_id}"
  exports.addTag     = (_id, tag)  -> $http.post   "#{base}/#{_id}/tag/#{tag}"
  exports.delTag     = (_id, tag)  -> $http.put    "#{base}/#{_id}/tag/#{tag}"


  exports
