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
		templateUrl : "/protected/user/edit",
		controller : EditProfileController,
	},
	
	"/edit_profile" : {
		templateUrl : "protected/user/edit_profile",
		controller : EditProfileController,
	},
	"/messaging" : {
		templateUrl : "protected/partials/messaging",
		controller : MessagingController
	},
	"/forgot_password":{
		templateUrl: "public/partials/forgot_password",
		controller: ForgotPasswordController
	},
	"/change_password/:token":{
		templateUrl: "public/partials/change_password",
		controller: ForgotPasswordController
	}, 
	"/search":{
		templateUrl: "protected/partials/search",
		controller: SearchController	
	}

}