function ForgotPasswordController($http, $scope, $routeParams, $location, $rootScope){
	$scope.forgotPassword = function(){
		$http({
			method:'POST',
			url: 'public/password/forgot',
			data :{
				email:$scope.email
			}
		}).success(function(result){
			if (result.message){
				toastr.success(result.message);
			} else {
				toastr.error(result.errorMessage);
			}
		}).error(function(){
			toastr.error("failed request to server")
		})
	}
	
	$scope.changePassword = function(){
		if ($scope.password == $scope.confirmPassword){
			$http({
				method:'POST',
				url: 'public/password/change/'+$routeParams.token,
				data: {
					password: $scope.password,
					confirmPassword: $scope.confirmPassword
				}
			}).success(function(result){
				if (result.message){
					$rootScope.message = result.message;
				$location.path('/sign_in');
				} else {
					toastr.error(result.errorMessage)
				}
			}).error(function(){
				toastr.error("Failed request to server")
			})
		}else{
			toastr.warning("Passwords aren't equals")
		}
	}
}