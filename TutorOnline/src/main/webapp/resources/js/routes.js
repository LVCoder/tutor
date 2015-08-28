window.routes = {

	"/" : {
		templateUrl : "/public/partials/home",
		controller : HomeController,
	},
	"/sign_up" : {
		templateUrl : "/public/partials/sign_up",
		controller : SignUpController,
	},

	"/sign_in" : {
		templateUrl : "/public/partials/sign_in",
		controller : SignInController,
	},
	"/confirm_sign_up/:token" : {
		templateUrl : "/public/partials/confirm_sign_up",
		controller : ConfirmSignUpController,
	},

	"/user_home" : {
		templateUrl : "/protected/partials/user_home",
		controller : UserHomeController,
	},
	
	"/edit" : {
		templateUrl : "/protected/partials/edit",
		controller : EditProfileController,
	}

}