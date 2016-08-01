app.run ($rootScope, $previousState, $state, $modal)->

  # remove modal on state change
  # todo change it to material
  $rootScope.$on '$stateChangeStart', ($event, toState)->
    # check if it's needed to have a modal
    if toState.data?.modal
      # open modal if necessary
      if !$rootScope.modalInstance
        $previousState.memo("modalInvoker")
        $rootScope.modalInstance = $modal.open
          template: '<div ui-view="modal"/>'
          windowTemplateUrl: 'page/modal'

      # on close
      closingSource = null
      $rootScope.modalInstance.result
      .catch (source)->
        closingSource = source
      .finally ->
        delete $rootScope.modalInstance
        if closingSource != 'state change'
          if $previousState.get("modalInvoker").state.name then $previousState.go "modalInvoker"
          else $state.go $state.current.data.parent

    else if $rootScope.modalInstance
      # close modal
      $rootScope.modalInstance.dismiss('state change')
