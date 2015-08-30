<!doctype html>
<html
	class=" js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths">
<head>

<!-- Title -->
 <title>Tutoring Portal</title>

<!-- Styles -->
<link rel="stylesheet"  href="resources/css/bootstrap.css"/>
        <link rel="stylesheet"  href="resources/css/lightslider.css"/>
        <link rel="stylesheet"  href="resources/css/style.css"/>
    <link rel="stylesheet"  href="resources/css/reg.css"/>
    <link rel="stylesheet"  href="resources/toastr/toastr.min.css"/>

<!-- Angular! -->

<script type="text/javascript" src="resources/angularjs/angular.js"></script>
<script type="text/javascript" src="resources/angularjs/angular-resource.js"></script>
<script type="text/javascript" src="resources/angularjs/angular-route.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-2.1.4.min.js"></script>

<script type="text/javascript" src="resources/js/index.js"></script>


<script type="text/javascript" src="resources/js/homeController.js"></script>
<script type="text/javascript" src="resources/js/signUpController.js"></script>
<script type="text/javascript" src="resources/js/signInController.js"></script>
<script type="text/javascript" src="resources/js/mainAccountRequests.js"></script>
<script type="text/javascript" src="resources/js/userHomeController.js"></script>
<script type="text/javascript" src="resources/js/editProfileController.js"></script>
<script type="text/javascript" src="resources/js/editProfilePage.js"></script>
<script type="text/javascript" src="resources/js/routes.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
  <script type="text/javascript" src="resources/js/lightslider.js"></script>
  <script type="text/javascript" src="resources/toastr/toastr.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'>



    <style>
    	ul{
			list-style: none outside none;
		    padding-left: 0;
            margin: 0;
		}
        .demo .item{
            margin-bottom: 60px;
        }
		.content-slider li{
		    background-color: white;
		    text-align: center;
		    color: #FFF;
		}

		.demo{
			width: 400px;
		}
    </style>

<meta content="width=device-width, initial-scale=1" name="viewport">
<meta charset="UTF-8">
<meta name="description" content="Tutor">
<meta name="keywords" content="">
<meta name="author" content="PMI Elita">






</head>
<!--  class="page-header-fixed" -->
<body ng-app="main" class="blue" ng-cloak>
	 <header ng-cloak>
       <img src="resources/images/logo.svg" alt="logo">
       <span>Tutoring Portal</span>
       <nav>
            <a href ="#/sign_in" class= "logIn">LogIn</a>
            <a  href = "#/sign_up" class = "signIn">CREATE FREE ACCOUNT</a>
        </nav>
    </header>
	<div class="" ng-view></div>
	 <footer>
        <div class="leftFooter">
            <div class="firstBlock">
                <a href=""><h4>Create free account</h4></a>
                <ul>
                    <li><a href="">View courses</a></li>
                    <li><a href="">Suggest course</a></li>
                    <li><a href="">Support</a></li>
                </ul>
            </div>
            <div class="secondBlock">
                <a href=""><h4>Become a tutor</h4></a>
                <ul>
                    <li><a href="">Rules and Policy</a></li>
                    <li><a href="">About</a></li>
                </ul>
            </div>
            <p class = "rights">© 2015 Tutoring Portal All rights reserved.</p>
        </div>
        <div class="rightFooter">
            <div class = "logoDiv">
            <span>Tutoring</span>
            <img src="resources/images/logoWhite.svg" alt="logo">
            <span> Portal</span>
            </div>
             <p>We help teachers and learners
to find each other.</p> 
        </div>
    </footer>
</body>

</html>
