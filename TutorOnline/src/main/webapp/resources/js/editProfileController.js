function editProfileController($http, $scope, $rootScope,$location){
	
	$scope.edit = function(){
		$http({
			method: "POST",
			url: "protected/user/edit",
			data: $scope.user,
		}).success(function(result){
			if(result.message){				
				$location.path = "/user_home";
			}
		})
	}
	
}