'use strict'

# integrate underscore.string
_.mixin(_.str.exports());

# declare module and dependencies
app = angular.module 'app', [
  'ngCookies'
  'ngMessages'
  'ngMaterial'
  'ngStorage'
  'ui.utils'
  'ui.router'
  'partials'
  'angular-loading-bar'
  'monospaced.elastic'
  'checklist-model'
  'infinite-scroll'
  'angularMoment'
  'ct.ui.router.extras'
  'materialDatePicker'
  'ngFileUpload'
  'ngImgCrop'
]

# define params
app.config ($httpProvider, $locationProvider, msdElasticConfig, $stickyStateProvider) ->

  # html5mode (not on dev mode)
  if location.protocol != "file:"
    $locationProvider.html5Mode(true)

  # requests with credentials
  $httpProvider.defaults.withCredentials = true;

  # set elastic textarea param
  msdElasticConfig.append = '\n'

  $stickyStateProvider.enableDebug false

app.run ($rootScope, $mdSidenav, amMoment)->
  amMoment.changeLocale('fr');
  $rootScope.notEmpty = '1'
  $rootScope.toggleMenu = ->
    $mdSidenav('left').toggle();
