var main = angular
		.module('main', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider, $locationProvider, $provide) {
					for ( var path in window.routes) {
						$routeProvider.when(path, window.routes[path]);
					}

					$routeProvider.otherwise('/');
					$httpProvider.defaults.withCredentials = true;
					$httpProvider.interceptors.push(function($q, $location) {
						return {
							'responseError' : function(rejection) {
								var defer = $q.defer();
								if (rejection.status == 401) {
									location.reload();
									console.log("401 redirecting to login")
									$location.path("/index.html");
									console.dir(rejection);
								} else if (rejection.status == 403) {
									console.log("403 status")
									$location.path("/index.html");
								}
								defer.reject(rejection);
								return defer.promise;
							}
						};
					});
					$provide.decorator('$sniffer', [ '$delegate', function($delegate) {
						$delegate.history = false;
						return $delegate;
					} ]);
				});


main.service('SessionService', [ '$http', '$location', '$rootScope', function($http, $location, $rootScope) {
	var userIsAuthenticated = false;
	this.setUserAuthenticated = function(value) {
		userIsAuthenticated = value;
	};
	this.isAdmin = function() {
		if ($rootScope.user && $rootScope.user.email) {
			if ($rootScope.user.roles && $rootScope.user.roles["ROLE_ADMIN"]) {
				console.log("is admin");
				return true;
			}
		} else {
			console.log($rootScope.user);
		}

		return false;
	};
	this.getUserAuthenticated = function() {
		if ($rootScope.user && $rootScope.user.email) {
			console.log($rootScope.user.roles["ROLE_PAYED_USER"]);
			return true;
		} else {
			console.log("getUser");
			mainAccountsRequests.getUser($rootScope, $http, $location);
		}
		return userIsAuthenticated;
	};
} ]);
main.run(function($rootScope, $location, $http, SessionService) {
	
	toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"newestOnTop" : false,
			"progressBar" : true,
			"positionClass" : "toast-top-right",
			"preventDuplicates" : true,
			"onclick" : null,
			"showDuration" : "3000",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
	
	$rootScope.$on('$viewContentLoaded', function() {
		delete $rootScope.error;
	});
	/*mainAccountsRequests.getUser($rootScope, $http, $location, null);
	$rootScope.logout = function() {
		mainAccountsRequests.logout($rootScope, $http, $location);
	}*/

	

});

main.controller('HomeController', HomeController);
main.controller('SignUpController', SignUpController);
main.controller('SignInController', SignInController);

main.service('paymentVariablesService', function() {
	var plan;
	var setPlan = function(newObj) {
		plan = newObj;
	};
	var getPlan = function() {
		return plan;
	};
	var type;
	var setType = function(newObj) {
		type = newObj;
	};
	var getType = function() {
		return type;
	};
	return {
		setPlan : setPlan,
		getPlan : getPlan,
	};

});



