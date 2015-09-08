var main = angular
		.module('main', [ 'ngRoute','file-model','facebook' ])
		.config(
				function($routeProvider, $httpProvider, $locationProvider, $provide,FacebookProvider) {
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
					FacebookProvider.init('904548396248097');
				}).directive('onFinishRender', function ($timeout) {
				    return {
				        restrict: 'A',
				        link: function (scope, element, attr) {
				            if (scope.$last === true) {
				                $timeout(function () {
				                    scope.$emit('ngRepeatFinished');
				                });
				            }
				        }
				    }
				});



main.run(function($rootScope, $location, $http) {
	
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
	mainAccountsRequests.getUser($rootScope, $http, $location, null);
	$rootScope.logout = function() {
		mainAccountsRequests.logout($rootScope, $http, $location);
	}

	

});

main.controller('HomeController', HomeController);
main.controller('SignUpController', SignUpController);
main.controller('SignInController', SignInController);
main.controller('ConfirmSignUpController', ConfirmSignUpController);
main.controller('MessagingController', MessagingController);

main.controller('UserHomeController', UserHomeController);
main.controller('EditProfileController', EditProfileController);
main.controller('ForgotPasswordController', ForgotPasswordController);
main.controller('SearchController', SearchController);


