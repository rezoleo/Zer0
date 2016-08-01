app.constant 'config',
  title: 'Console d\'administration'
  apiUrl: if window.location.port == "3310" then 'https://localhost:9101/api' else '/api'
  arduinoTime: 1500
  toastLast: 5000
  errorMessage: 'Une erreur est survenue : %s'
