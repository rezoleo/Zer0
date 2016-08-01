app.factory 'Roles', ($http, $rootScope, config)->
  exports = {}
  base = config.apiUrl + "/roles"

  exports.get    = (category)              -> $http.get    "#{base}/#{category}"
  exports.post   = (category, login, role) -> $http.post   "#{base}/#{category}/#{login}", {role: role}
  exports.delete = (category, login)       -> $http.delete "#{base}/#{category}/#{login}"


  exports
