var mainAccountsRequests = {
		getUser : function($rootScope, $http) {
			$http({
				
				method : 'GET',
				url : '/public/user',
			}).success(function(data, status, headers, config) {
				if (data) {
					delete $rootScope.expired_message;
					console.log("user dto")
					console.log(data)

				 if (data.enabled && !data.anomymous && data.anomymous != true) {
						console.log("Enabled user");
						$rootScope.header = "/protected/partials/header";
						$rootScope.home_path = "/protected/partials/home_content";
						//$rootScope.settings_navbar_path = "protected/partials/settings_navbar";
						$rootScope.user = data;
						$rootScope.authenticated = true;
						if (data.roles["ROLE_ADMIN"]) {
							//$rootScope.menu_path = "/admin/partials/user_navbar";
							//$rootScope.home_path = "/admin/partials/home_content";
							//$rootScope.settings_navbar_path = "protected/partials/settings_navbar";
							$rootScope.authenticated = false;
							console.log("User is admin");
						}
					} else if (data.anonymous) {
						$rootScope.header = null;
						$rootScope.home_path = "/public/partials/home_content";
						$rootScope.authenticated = false;
						console.log("User is anonymous");
					}

				} else {
					console.log("No user data");
				}
			}).error(function(data, status, headers, config) {
				$rootScope.error_message = "Failed request to server";
			});
		},
		logout : function($rootScope, $http, $location){
			$http({
				method: 'POST',
			url: 'protected/user/logout'
				}).success(function(){
					$rootScope.user ={};
				mainAccountsRequests.getUser($rootScope, $http);
				$location.path('/');
			}).error(function(){
				toastr.error("Failed request ot server");
			})
		}
}