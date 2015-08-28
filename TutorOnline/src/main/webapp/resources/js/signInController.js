function SignInController($http, $scope, $rootScope,$location){	
	
	$scope.signIn = function(){
		$http({
			method : 'POST',
			url : 'public/user/sign_in',
			data : $scope.signInUser
		}).success(function(data){
			if (data) {
				console.log("data from auth request ");
				console.log(data)
				mainAccountsRequests.getUser($rootScope, $http);
				
				if (data.anonymous) {
					console.log("User anonymous auth " + data);
					//$rootScope.menu_path = "/public/partials/user_navbar";
					//$rootScope.home_path = "/public/partials/home_content";
					$rootScope.authenticated = false;
					$rootScope.error = true;
					$rootScope.error_message = data.message;
					toastr["error"](data.message, 'Error');
				} else if (data.enabled) {
					toastr.clear();
					console.log("User enabled auth " + data);
					$location.path("/user_home");
					//$rootScope.menu_path = "/protected/partials/user_navbar";
					//$rootScope.home_path = "/protected/partials/home_content";
					//$rootScope.settings_navbar_path = "protected/partials/settings_navbar";
					$rootScope.user = data;
					$rootScope.authenticated = true;
					toastr.success(data.message);
				} else {
					console.log(" " + data);

					//$rootScope.menu_path = "/public/partials/user_navbar";
					//$rootScope.home_path = "/public/partials/home_content";
					$location.path("/sign_in");
					$rootScope.authenticated = false;
					$rootScope.error = true;
					$rootScope.error_message = data.message;
			
					toastr["error"](data.message, 'Error');
				}
			}$scope.button_disable = false;
		}).error(function(){
			toastr.error('Failed request to server');
		})
	}
	
	
}