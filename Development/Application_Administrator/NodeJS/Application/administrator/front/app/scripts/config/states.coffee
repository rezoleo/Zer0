# define routes
app.config ($stateProvider, $urlRouterProvider) ->
# set redirects
  $urlRouterProvider
  .when('', '/')
  .otherwise('/')
  #.otherwise('/404')

  # preset roles
  user = ['user']

  # default state setter
  addState = (view, name, params = {})->
    type = view.replace '@', ''
    templateUrl = params.templateUrl ? "#{type}/#{_.dasherize name}"
    controller = params.controller ? "#{_.capitalize type}#{_.capitalize name}Ctrl"
    views = {}
    views[view] = {templateUrl: templateUrl, controller: controller}
    $stateProvider.state _.extend
      name: name
      url: "/#{_.dasherize name}"
      parent: 's' if type != view
      views: views
    , params

  # set groups
  addMain = (name, params)-> addState 'main@', name, params
  addUserMain = (name, params)-> addMain name, _.extend roles: user, params

  # set states
  $stateProvider.state 's',
    abstract: true
    resolve: {auth: ['$rootScope', ($rootScope)-> $rootScope.authPromise()]}

  addMain 'prehome', url: '/'
  addUserMain 'home'

  addUserMain 'authorization'

  addUserMain 'authentification'
  addUserMain 'authentificationNew', {
    url: '/authentification/new',
    controller: 'MainAuthentificationEditCtrl',
    templateUrl: 'main/authentification-edit',
    resolve: {resolved: -> {}}
  }
  addUserMain 'authentificationEdit', {url: '/authentification/:login/edit', resolve: MainAuthentificationResolve}
  addUserMain 'authentificationView', {url: '/authentification/:login', resolve: MainAuthentificationResolve}

  addUserMain 'card'
  addUserMain 'cardNew', {
    url: '/card/new',
    controller: 'MainCardEditCtrl',
    templateUrl: 'main/card-edit',
    resolve: {resolved: -> {}}
  }
  addUserMain 'cardEdit', {url: '/card/:_id/edit', resolve: MainCardResolve}
  addUserMain 'cardView', {url: '/card/:_id', resolve: MainCardResolve}

  addUserMain 'contributor'

  addUserMain 'group'
  addUserMain 'groupNew', {
    url: '/group/new',
    controller: 'MainGroupEditCtrl',
    templateUrl: 'main/group-edit',
    resolve: {resolved: -> {}}
  }
  addUserMain 'groupEdit', {url: '/group/:_id/edit', resolve: MainGroupResolve}
  addUserMain 'groupView', {url: '/group/:_id', resolve: MainGroupResolve}

  addUserMain 'people'
  addUserMain 'peopleNew', {
    url: '/people/new',
    controller: 'MainPeopleEditCtrl',
    templateUrl: 'main/people-edit',
    resolve: {resolved: -> {}}
  }
  addUserMain 'peopleEdit', {url: '/people/:_id/edit', resolve: MainPeopleResolve}
  addUserMain 'peopleView', {url: '/people/:_id', resolve: MainPeopleResolve}

