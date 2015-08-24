function HomeController($scope, $rootScope, $location, $http) {
	$scope.$on('$viewContentLoaded', function() {


			$("#content-slider").lightSlider({
               loop:true,
               keyPress:true,
               enableDrag: false,
               slideMargin:1,
               pager: true,
               responsive: []
           });
           
		
	})
};