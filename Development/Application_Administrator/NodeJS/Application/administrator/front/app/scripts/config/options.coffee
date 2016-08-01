app.run ($rootScope)->
  $rootScope.getOptionLabel = (options, value)->
    item = _.find options, {value: value}
    item?.label

  $rootScope.getOptionIcon = (options, value)->
    try
      item = _.find options, {value: value}
      icon = item.icon
    catch
      icon='/images/unknown.png'
    icon

  $rootScope.options = {
    authentification:
      status: [
        {value: 'ON',  label: 'Actif',       icon: '/images/auth-unlocked.png'}
        {value: 'OFF', label: 'Verrouillée', icon: '/images/auth-locked.png'}
      ],
    card:
      status: [
        {value: 'ON',  label: 'Activée',    icon: '/images/card-unlocked.png'}
        {value: 'OFF', label: 'Désactivée', icon: '/images/card-locked.png'}
      ],
    people:
      major: [
        {value: true,  label: 'Majeur(e)'}
        {value: false, label: 'Mineur(e)'}
      ],
      sex: [
        {value: 'M', label: 'Homme', icon: '/images/user.png'}
        {value: 'F', label: 'Femme', icon: '/images/user.png'}
      ],
  }
