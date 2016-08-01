app.constant 'roles',
  user: (user)-> unless user.login then return 'prehome'
  visitor: (user)-> if user.login then return 'home'
