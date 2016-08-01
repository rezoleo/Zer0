angular.module('arduinoApp', [])
       .controller("arduinoCtrl", function($scope, $http, $interval, $location){
                // INIT
                //Definition of the data sent the arduino card
                $scope.signal = null;
                $scope.system = null;
                $scope.status = "UNKNOWN";

                // Path used for HTTP request
                var protocol = $location.protocol();
                var path = "";
                if(protocol === "https"){
                        path = "https://";
                }
                else if(protocol === "http"){
                        path = "http://";
                }
                path = path + "localhost/arduino/api";

                var signal_path = path + "/signal/";
                var system_path = path + "/system/";

                // Settings for schedulers
                var time = 1000;
                $scope.flag_signal = true;
                $scope.flag_system = true;

                $interval(function(){
                        if($scope.flag_signal){
                                $scope.flag_signal = false;

                                // Read the information about the last card detected
                                $http.get(signal_path)
                                     .success(function(dataFromServer, status, headers, config){
                                                $scope.signal = dataFromServer;
                                                $scope.flag_signal = true;
                                     })
                                     .error(function(data, status, headers, config){
                                                $scope.signal = null;
                                                $scope.flag_signal = true;
                                     });
                        }
                        if($scope.flag_system){
                                $scope.flag_system = false;

                                // Read the information about the host system running the Python program
                                $http.get(system_path)
                                     .success(function(dataFromServer, status, headers, config){
                                                $scope.system = dataFromServer;
                                                $scope.status = "ONLINE";
                                                $scope.flag_system = true;
                                     })
                                     .error(function(data, status, headers, config){
                                                $scope.system = null;
                                                $scope.signal = null;
                                                $scope.status = "OFFLINE";
                                                $scope.flag_system = true;
                                     });
                        }
                }, time);
        });

