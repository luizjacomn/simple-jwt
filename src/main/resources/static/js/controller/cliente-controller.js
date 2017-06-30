app.controller('clienteController', function($scope, $http, $location) {
	$scope.cliente = {};
	$scope.clientes = [];
	var baseUrl = '/authenticated/clientes';

	$scope.saveCliente = function() {
		if ($scope.frmCadastro.$valid) {
			$http({
				method : 'POST',
				url : baseUrl,
				data : $scope.cliente
			}).then(function(response) {
				$scope.listClientes();
				$scope.clean();
			}, function(response) {
				console.log(response.data);
				console.log(response.status);
			});
		} else {
			openAlert("#modal1");
		}
	};

	$scope.listClientes = function() {
		$http({
			method : 'GET',
			url : baseUrl
		}).then(function(response) {
			$scope.clientes = response.data;
			console.log(response.data);
			console.log(response.status);
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
	};

	$scope.clean = function() {
		$scope.cliente = {};
	}

	$scope.listClientes();
});