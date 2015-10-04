function HomeController($scope, $rootScope, $location, $http) {
	
	$scope.finishLoading = function(){
		if($("#content-slider")){
			$("#content-slider").lightSlider({
               loop:true,
               keyPress:true,
               enableDrag: false,
               slideMargin:1,
               pager: true,
               responsive: []
           });
		}   
	}
	
};
