app.run ($rootScope) ->
  enumerateGrantedRoles = (params) ->
    if !params
      return []

    category = params.category
    level = params.level

    prefixs = []
    ## if category == 'ALERT' or category == '*'
    ##  prefixs.push 'ALERT_'

    if category == 'AUTHENTIFICATION' or category == '*'
      prefixs.push 'AUTH_'
    if category == 'CARD' or category == '*'
      prefixs.push 'CARD_'
    if category == 'CONTRIBUTOR' or category == '*'
      prefixs.push 'CONTRIBUTOR_'
    ## if category == 'GROUP' or category == '*'
    ##  prefixs.push 'GROUP_'
    if category == 'PEOPLE' or category == '*'
      prefixs.push 'PEOPLE_'

    i = 0
    roles = []
    while i < prefixs.length
      prefix = prefixs[i]
      if level == 'READ' or level == '*'
        roles.push prefix + 'READ'
        roles.push prefix + 'WRITE'
        roles.push prefix + 'ADMIN'
      if level == 'WRITE'
        roles.push prefix + 'WRITE'
        roles.push prefix + 'ADMIN'
      if level == 'ADMIN'
        roles.push prefix + 'ADMIN'
      i++
    roles

  $rootScope.hasPermission = (params) ->
    expected_roles = enumerateGrantedRoles({category : params.category, level : params.require})
    roles = $rootScope.auth.roles

    if !expected_roles
      expected_roles = []
    if !roles
      roles = []

    i = 0
    j = 0
    while i < roles.length
      j = 0
      while j < expected_roles.length
        if roles[i] == expected_roles[j]
          return true
        j++
      i++

    false

