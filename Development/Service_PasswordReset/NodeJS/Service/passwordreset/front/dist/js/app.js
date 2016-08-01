angular.module('resetApp', [])
       .controller("resetCtrl", function($scope, $http, $location){
		// INIT
		//Definition of form variables
		$scope.myForm = {type: null, login: null, password: null};

		//Definition of text variables
		$scope.myText = {type: null, content:null};

		$scope.isOpen = true;

		// Definition of the submit functions
		$scope.myForm.submitRequest = function(){
			var login = $scope.myForm.login;
			var regex = /^([a-z0-9-_]){1,8}$/;

			// Check if a login was given by the user
			if(!login){
				$scope.myText = {type: "error", content: "Aucun login n'a été précisé."};
			}
			// Check if the login respects a regex
			else if(!regex.test(login)){
				$scope.myText = {type: "error", content: "Le login ne respecte pas les règles de nommage (au plus 8 caractères avec des lettres en minuscule de l'alphabet sans accent, des chiffres, '_' et '-' en caractère spéciaux)."};
			}
			else{
				$scope.myText = {type: "info", content: "Demande en cours ..."};

				// Send the request to the server
				login = login.toLowerCase();
				var path  = "/api/passwd-reset/request/"+login;
				$http.post(path, {}, {})
				     .success(function(dataFromServer, status, headers, config) {
						$scope.myText = {type: "info", content: "Le lien a été transmis à votre boîte mail."};
				     })
				     .error(function(data, status, headers, config) {
						if(data && data.code && data.message){
							$scope.myText = {type: "error", content: data.message+". [Error "+data.code+"]"};
						}
						else{
							$scope.myText = {type: "error", content: "Une erreur est survenue lors de la demande."};
						}
				     });
			}
		};
		$scope.myForm.submitPassword = function(){
			var password = $scope.myForm.password;
			var regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)([\w]{6,})$/;

			// Check if a password was given by the user
			if(!password){
				$scope.myText.type = "error";
				$scope.myText.content = "Aucun mot de passe n'a été précisé.";
			}
			// Check if the password respects a regex
			else if(!regex.test(password)){
				$scope.myText.type = "error";
				$scope.myText.content = "Le nouveau mot de passe ne respecte pas les règles de sécurité.";
			}
			else if($scope.myText.type && $scope.myText.type=="info"){
				$scope.myText.type = "info";
				$scope.myText.content="Vous avez déjà envoyé votre nouveau mot de passe.";
			}
			else{
				$scope.myText.type = "info";
				$scope.myText.content = "Régénération en cours ...";

				// Send the request to the server
				var path  = "/api/passwd-reset/link/"+$location.search().token;
				var dataObject = {
					new_password : password
				};
				$http.post(path, dataObject, {})
				     .success(function(dataFromServer, status, headers, config) {
						$scope.myText.type = "info";
						$scope.myText.content="Votre mot de passe a été mis à jour.";
				     })
				     .error(function(data, status, headers, config) {
						$scope.myText.type = "error";
						if(data && data.code && data.message){
							$scope.myText.content = data.message+". [Error "+data.code+"]";
						}
						else{
							$scope.myText.content = "Une erreur est survenue lors du changement de votre mot de passe.";
						}
				     });
			}
		};


		// Check if the variabled named 'token' is in the url
		// If it is the case then the page was opened from a link sent by mail
		if($location.search().token){
			//Check if the token is still valid
			var path  = "/api/passwd-reset/link/"+$location.search().token;
			$http.get(path, {}, {})
			     .success(function(dataFromServer, status, headers, config) {
					$scope.myForm.type     = "change";
					$scope.myForm.login    = null;
					$scope.myForm.password = null;

					$scope.myText = {type: null, content: "null"};
			     })
			     .error(function(data, status, headers, config) {
					$scope.myForm = {type: null, login: null, password: null};
			     });
		}
		else{
			$scope.myForm.type     = "request";
			$scope.myForm.login    = null;
			$scope.myForm.password = null;

			$scope.myText = {type: null, content: null};
		}
	});

