function EditProfileController($http, $scope, $rootScope, $location) {

	$scope.$on('$viewContentLoaded', function() {
		$scope.learnSelects = [];
		$scope.teachSelects = [];
		$scope.wantLearn = false;
		$scope.wantTeach = false;
		getAllCategories();
		getUser();

	})
$(function () {
$('#institution').autocomplete({
    serviceUrl: 'public/institution/autocomplete', // Страница для обработки запросов автозаполнения
    minChars: 1,
    maxHeight: 400,
    width: 300,
    onSelect: function(suggestion){ 
    	
    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
 
});
});
	
	function getUser() {
		$http
				.get('protected/user/get')
				.success(
						function(result) {
							if (result) {
								$scope.firstName = result.firstName;
								$scope.lastName = result.lastName;
								$scope.username = result.username;
								$scope.experience = result.experience;
								$scope.others = result.others;
								$scope.institution = result.institution;
								if (result.wantLearn) {
									$scope.wantLearn = true;
									for (learnIndex = 0; learnIndex < result.learnSubjects.length; learnIndex++) {
										$scope.learnSelects
												.push(getSelectBySubjectId(result.learnSubjects[learnIndex].id));
									}
								}
								if (result.wantTeach) {
									$scope.wantTeach = true;
									for (index = 0; index < result.teachSubjects.length; index++) {
										var teachSelect =  getSelectBySubjectId(result.teachSubjects[index].subject.id);
										teachSelect.price = result.teachSubjects[index].price;
										$scope.teachSelects
												.push(teachSelect);
									}
								}
							}
						})
	}

	function getSelectBySubjectId(subjectId) {
		for (i = 0; i < $scope.categories.length; i++) {
			for (j = 0; j < $scope.categories[i].subjects.length; j++) {
				if ($scope.categories[i].subjects[j].id == subjectId) {
					return {
						selectedCategory : $scope.categories[i],
						selectedSubject : $scope.categories[i].subjects[j],
						subjects : $scope.categories[i].subjects
					}
				}
			}
		}
	}

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

			} else {
				toastr.error("No avaible categories");
			}
		}).error(function() {
			toastr.error("Failed request to server")
		})
	}

	$scope.edit = function(){
		var learnSubjects = [];
		for (i=0; i<$scope.learnSelects.length;i++){
			learnSubjects.push($scope.learnSelects[i].selectedSubject);
		}
		var teachSubjects = [];
		for (i=0; i<$scope.teachSelects.length;i++){
			teachSubjects.push({
				subject:$scope.teachSelects[i].selectedSubject, 
				price:$scope.teachSelects[i].price});
		}
		var user ={
				firstName: $scope.firstName,
				lastName: $scope.lastName,
				username: $scope.username,
				wantLearn: $scope.wantLearn,
				wantTeach: $scope.wantTeach,
				learnSubjects: learnSubjects,
				teachSubjects: teachSubjects,
				experience: $scope.experience,
				others: $scope.others,
				institution: $scope.institution
		}
		
		$http({
			method:'POST',
			url: 'protected/user/edit',
			data : user
		}).success(function(result){
			if (result.message){
			toastr.success(result.message);
			} if (result.errorMessage){
				toastr.error(result.errorMessage)
			}
		}).error(function(){
			toastr.error("Failed request to server");
		})
	}
	
	$scope.changePassport = function(){
		var password = {
				oldPassword : $scope.oldPassword,
				newPassword: $scope.newPassword,
				confirmPassword: $scope.confirmPassword
		}
		$http({
			method: 'POST',
			url: 'protected/password/change',
			data: password
		}).success(function(result){
			
		})
	}
	
	
	$scope.fileChanged = function(element){
		$scope.avatar_image = element.files[0];
		var formData = new FormData();
		formData.append('file', $scope.avatar_image);
		$http({
			method : 'POST',
			url : 'protected/user/avatar/change',
			transformRequest: angular.identity,
	        headers: {'Content-Type': undefined},
			data: formData
		}).success(function(result){
			location.reload();
		})
	
	}
	
	


}