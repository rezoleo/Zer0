exports.config =
# See docs at http://brunch.readthedocs.org/en/latest/config.html.
  conventions:
    assets: /^app(\/|\\)assets(\/|\\)/
#   ignored: /^(vendor\/bootstrap-stylus\/|(.*?\/)?[_]\w*)/
  server:
    port: 3310
  modules:
    definition: false
    wrapper: false
  paths:
    public: '_public'
  files:
    javascripts:
      joinTo:
        'js/app.js': /^app(\/|\\)/
        'js/vendor.js': /^(bower_components|vendor)/

    stylesheets:
      joinTo:
        'css/app.css': /^(app|bower_components|vendor)/
      order:
        before: [
          'app/styles/bootstrap.styl'
          'app\\styles\\bootstrap.styl'
          'app/styles/global.styl'
          'app\\styles\\global.styl'
        ]

    templates:
      joinTo:
        "js/partials.js": /^app(\/|\\)pages/

  plugins:
    autoReload:
      port: 3404
    jadePages:
      pattern: /^app(\/|\\)[^\/\\]*\.jade$/
    jadeNgtemplates:
      modules: [
        pattern: /^app(\/|\\)pages/
        url: (path)-> path.replace(/^app(\/|\\)pages(\/|\\)(.*)\.jade$/, "$3").replace(/\\/g, '/')
      ]

  overrides:
    production:
      paths:
        public: 'dist'
