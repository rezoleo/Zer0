app.factory "Group", ($http, $rootScope, $sessionStorage, config)->
  exports = {}
  base = config.apiUrl + "/group"

  exports.post           = (data)       -> $http.post   "#{base}", data
  exports.list           = (params)     -> $http.get    "#{base}", {params: params}
  exports.getCache       = (params)     -> $http.get    "#{base}?action=get-cache-infos", {params: params}
  exports.get            = (_id)        -> $http.get    "#{base}/#{_id}"
  exports.getName        = (name)       -> $http.get    "#{base}/name/#{name}"
  exports.cachedList     = (params) -> 
    then : (callbackThen) ->
      catch : (callbackCatch) ->
        exports.getCache()
        .then ({data})->
          if data && data.lastUpdate && $sessionStorage.group && data.lastUpdate == $sessionStorage.group.lastUpdate
            callbackThen({data : $sessionStorage.group.data})
          else
            $sessionStorage.group = { lastUpdate : data.lastUpdate, data : null }
            exports.list(params)
            .then (arg)->
              $sessionStorage.group.data = arg.data
              callbackThen(arg)
            .catch (res)->
              $sessionStorage.group = { lastUpdate : null, data : null }
              callbackCatch(res)
        .catch ()->
          $sessionStorage.group = { lastUpdate : null, data : null }
          exports.list(params).then(callbackThen).catch(callbackCatch)
  exports.search         = (login)      -> $http.get    "#{base}/search/#{login}"
  exports.put            = (_id, data)  -> $http.put    "#{base}/#{_id}", data
  exports.delete         = (_id)        -> $http.delete "#{base}/#{_id}"
  exports.addMember      = (_id, login) -> $http.post   "#{base}/#{_id}/member", {login: login}
  exports.delMember      = (_id, login) -> $http.put    "#{base}/#{_id}/member", {login: login}
  exports.addResponsible = (_id, data)  -> $http.post   "#{base}/#{_id}/responsible", data
  exports.delResponsible = (_id, data)  -> $http.put    "#{base}/#{_id}/responsible", data


  exports
