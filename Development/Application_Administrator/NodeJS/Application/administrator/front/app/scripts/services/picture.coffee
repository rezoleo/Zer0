app.factory "Picture", ($http, $rootScope, Upload, config)->
  exports = {}
  base = config.apiUrl + "/picture"

  exports.upload     = (_id, fileUrl, extension) -> Upload.upload {url: "#{base}/#{_id}?extension=#{extension}", data: {file: Upload.dataUrltoBlob(fileUrl)}}
  exports.delete     = (_id)                     -> $http.delete "#{base}/#{_id}"


  exports
