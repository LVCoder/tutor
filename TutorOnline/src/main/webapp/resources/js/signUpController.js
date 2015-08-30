function SignUpController($http, $scope, $rootScope,$location){

	
	$scope.signUp = function(){
		$http({
			method : 'POST',
			url : 'public/user/sign_up',
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

function ConfirmSignUpController($http, $scope, $rootScope,$location,$routeParams){
	$scope.$on('$viewContentLoaded', function() {
		$scope.learnSelects = [];
		$scope.teachSelects = [];
		$scope.avatar_image = null;
		$scope.wantLearn = false;
		$scope.wantTeach= false;
		getAllCategories();
		
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
	
	$scope.loadLearnSubjects = function(index){
		$scope.learnSelects[index].subjects = $scope.learnSelects[index].selectedCategory.subjects;
		$scope.learnSelects[index].selectedSubject = $scope.learnSelects[index].subjects[0];
	}
	
	$scope.loadTeachSubjects = function(index){
		$scope.teachSelects[index].subjects = $scope.teachSelects[index].selectedCategory.subjects;
		$scope.teachSelects[index].selectedSubject = $scope.teachSelects[index].subjects[0];
	}
	
	$scope.addTeachSubject = function(){
		$scope.teachSelects.push({selectedCategory:$scope.categories[0],selectedSubject:$scope.categories[0].subjects[0], subjects:$scope.categories[0].subjects });
	}
	
	$scope.addLearnSubject = function(){
		$scope.learnSelects.push({selectedCategory:$scope.categories[0],selectedSubject:$scope.categories[0].subjects[0], subjects:$scope.categories[0].subjects });
	}
	function getAllCategories(){
		$http.get("public/category/get_all").success(function(result){
			if (result){
				$scope.categories= result;
				$scope.learnSelects.push({selectedCategory:$scope.categories[0],selectedSubject:$scope.categories[0].subjects[0], subjects:$scope.categories[0].subjects });
				$scope.teachSelects.push({selectedCategory:$scope.categories[0],selectedSubject:$scope.categories[0].subjects[0], subjects:$scope.categories[0].subjects });

			} else{
				toastr.error("No avaible categories");
			}
		}).error(function(){
			toastr.error("Failed request to server")
		})
	}
	
	$scope.fileChanged = function(element){
		$scope.avatar_image = element.files[0];
		var formData = new FormData();
		formData.append('file', $scope.avatar_image);
		$http({
			method : 'POST',
			url : 'public/user/avatar/save/'+$routeParams.token,
			transformRequest: angular.identity,
	        headers: {'Content-Type': undefined},
			data: formData
		}).success(function(result){
			
		})
	
	}
	
	$scope.confirmSignUp = function(){
		var learnSubjectsIds = [];
		for (i=0; i<$scope.learnSelects.length;i++){
			learnSubjectsIds.push($scope.learnSelects[i].selectedSubject.id);
		}
		var teachSubjectsIdPrice = [];
		for (i=0; i<$scope.teachSelects.length;i++){
			teachSubjectsIdPrice.push({
				subjectId:$scope.teachSelects[i].selectedSubject.id, 
				price:$scope.teachSelects[i].price});
		}
		var user ={
				wantLearn: $scope.wantLearn,
				wantTeach: $scope.wantTeach,
				learnSubjectsIds: learnSubjectsIds,
				teachSubjectsIdPrice: teachSubjectsIdPrice,
				experience: $scope.experience,
				others: $scope.others,
				institution: $scope.institution
		}
		
		$http({
			method:'POST',
			url: 'public/user/confirm_sign_up/'+$routeParams.token,
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
}