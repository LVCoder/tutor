<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js";;
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<div ng-controller="SignInController">
 <a href="" class="logo">
       <span class="tut">Tutoring</span>  <img src="resources/images/logo.svg" alt="logo"> <span  class = "port">Portal</span>
    </a>
   <form class="reg-form" ng-submit="signIn();">
       <fieldset class="inputs reg-fieldset">
           <input type="email" id = "_email" ng-model="signInUser.email"><label for="_email">E-mail</label>
           <input type="password" id = "_password" ng-model="signInUser.password"><label for="_password">Password</label>
           <a href="#/forgot_password" id = "pasRecovery">Forgot your password?</a>
       </fieldset>
       <fieldset class="createAc">
       <button type="submit">
           LogIn
       </button>
        <a ng-click="facebookClick();">Or login with facebook in one click</a>
        <div class="fb-login-button" data-max-rows="1" data-size="icon" data-show-faces="false" data-auto-logout-link="false"></div>
       </fieldset>
      
       
   </form>
    
</div>