 window.routes = {

	"/" : {
		templateUrl : "/public/partials/home",
		controller : HomeController,
		requireLogin : false,
		adminPage : false
	},
	"/sign_up" : {
		templateUrl : "/public/partials/sign_up",
		controller : SignUpController,
		requireLogin : false,
		adminPage : false
	}
 }