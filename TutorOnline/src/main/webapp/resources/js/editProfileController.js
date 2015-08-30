function EditProfileController($http, $scope, $rootScope, $location) {
	
	$scope.edit = function() {

		var learnSubjectsIds = [];
		for (i = 0; i < $scope.learnSelects.length; i++) {
			learnSubjectsIds.push($scope.learnSelects[i].selectedSubject.id);
		}
		var teachSubjectsIdPrice = [];
		for (i = 0; i < $scope.teachSelects.length; i++) {
			teachSubjectsIdPrice.push({
				subjectId : $scope.teachSelects[i].selectedSubject.id,
				price : $scope.teachSelects[i].price
			});
		}		
//		var user = {
//			firstName : $scope.user.firstName,
//			lastName : $scope.lastName,
//			username : $scope.username,
//			wantLearn : $scope.wantLearn,
//			wantTeach : $scope.wantTeach,
//			learnSubjectsIds : learnSubjectsIds,
//			teachSubjectsIdPrice : teachSubjectsIdPrice,
//			experience : $scope.experience,
//			others : $scope.others
//		}
		$http({
			method : "POST",
			url : "/protected/user/edit",
			data : $scope.user,
		}).success(function(result) {
			
				$location.path = "/user_home";
			
		})
	}

}