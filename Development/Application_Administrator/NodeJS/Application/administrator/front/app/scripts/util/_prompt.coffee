app.run ($rootScope, $modal)->
  $rootScope.prompt = (templateUrl, data = {}, actions = {})->
    # todo change it to material
    $modal.open
      size: 'sm'
      templateUrl: templateUrl
      resolve: {data: (-> data), actions: (-> actions)}
      controller: ['$scope', 'data', 'actions', ($scope, data, actions)->
        $scope.data = data;
        $scope.actions = actions;
      ]
    .result
