function EditProfilePage($http, $scope, $rootScope,$location){
	$scope.$on('$viewContentLoaded', function() {
		$scope.learnSelects = [];
		$scope.teachSelects = [];
		$scope.wantLearn = false;
		$scope.wantTeach = false;
		getAllCategories();

	})

	$scope.loadLearnSubjects = function(index) {
		$scope.learnSelects[index].subjects = $scope.learnSelects[index].selectedCategory.subjects;
		$scope.learnSelects[index].selectedSubject = $scope.learnSelects[index].subjects[0];
	}

	$scope.loadTeachSubjects = function(index) {
		$scope.teachSelects[index].subjects = $scope.teachSelects[index].selectedCategory.subjects;
		$scope.teachSelects[index].selectedSubject = $scope.teachSelects[index].subjects[0];
	}

	$scope.addTeachSubject = function() {
		$scope.teachSelects.push({
			selectedCategory : $scope.categories[0],
			selectedSubject : $scope.categories[0].subjects[0],
			subjects : $scope.categories[0].subjects
		});
	}

	$scope.addLearnSubject = function() {
		$scope.learnSelects.push({
			selectedCategory : $scope.categories[0],
			selectedSubject : $scope.categories[0].subjects[0],
			subjects : $scope.categories[0].subjects
		});
	}
	function getAllCategories() {
		$http.get("public/category/get_all").success(function(result) {
			if (result) {
				$scope.categories = result;
				$scope.learnSelects.push({
					selectedCategory : $scope.categories[0],
					selectedSubject : $scope.categories[0].subjects[0],
					subjects : $scope.categories[0].subjects
				});
				$scope.teachSelects.push({
					selectedCategory : $scope.categories[0],
					selectedSubject : $scope.categories[0].subjects[0],
					subjects : $scope.categories[0].subjects
				});

			} else {
				toastr.error("No avaible categories");
			}
		}).error(function() {
			toastr.error("Failed request to server")
		})
	}

}