app.run ($rootScope)->
  $rootScope.filterList = (params)->
    _.filter params.list, (item)->
      hash = params.fields(item).join(' ').toLowerCase();
      words = params.search.toLowerCase().split(' ');
      for word in words
        if !_.contains(hash, word) then return false
      return true
