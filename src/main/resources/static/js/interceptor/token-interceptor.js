app.factory("tokenInterceptor", function($q, $location) {
	
	return {
	    'request': function(config) {
	    	config.headers.Authorization = 'Bearer ' + localStorage.getItem("clienteToken");
	      return config;
	    },
	    'responseError': function(rejection) {
	    	if(rejection.status == 401) {
	    		$location.path("/login");
	    	}
	    	return response;
	    }
	};
	
});