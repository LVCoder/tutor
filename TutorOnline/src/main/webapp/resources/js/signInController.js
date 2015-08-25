function SignInController($http, $scope, $rootScope,$location){	
	
	$scope.signUp = function(){
		$http({
			method : 'POST',
			url : 'public/user/sign_in',
			data : $scope.user
		}).success(function(result){
			if (result.message){
				$location.path = "/";
				toastr.success(result.message);
			} else {
				toastr.error(result.errorMessage);
			}
		}).error(function(){
			toastr.error('Failed request to server');
		})
	}
}