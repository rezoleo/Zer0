app.run ($rootScope, $state, $location, $window, config)->

  $rootScope.$state = $state

  $rootScope.pageTitle = config.title

  # google analytics
  $rootScope.$on '$stateChangeSuccess', (e)->
    if !$window.ga then return
    $window.ga 'send', 'pageview', {page: $location.path()}
