app.controller("loginController", function($scope, $http, $location) {

	$scope.cliente = {};

	$scope.login = function() {
		if ($scope.frmLogin.$valid) {
			$http.post("/authenticate", $scope.cliente).then(function(response) {
				localStorage.setItem("clienteToken", response.data.token);
				$location.path('/clientes');
			}, function(response) {
				openAlert("#modal3");
				$scope.clean();
			});
		} else {
			openAlert("#modal2");
		}
	};

	$scope.clean = function() {
		$scope.cliente = {};
	}

});