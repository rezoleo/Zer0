app.factory 'MyAccount', ($http, $rootScope, config)->
  exports = {}
  base = config.apiUrl + "/my_account"

  exports.post = (data) -> $http.post "#{base}/password", data


  exports
