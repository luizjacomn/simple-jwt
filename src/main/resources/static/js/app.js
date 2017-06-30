var app = angular.module('app', [ 'ngRoute' ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider
	.when("/clientes", {
		templateUrl : 'view/clientes.html',
		controller : 'clienteController'
	}).when("/login", {
		templateUrl : 'view/login.html',
		controller : 'loginController'
	}).otherwise({
		rediretTo : '/'
	});

	$locationProvider.html5Mode(true);
});

app.config(function($httpProvider) {
	$httpProvider.interceptors.push("tokenInterceptor");
});