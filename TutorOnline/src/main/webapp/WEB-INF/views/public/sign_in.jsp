
<div ng-controller="SignInController">
 <a href="" class="logo">
       <span class="tut">Tutoring</span>  <img src="resources/images/logo.svg" alt="logo"> <span  class = "port">Portal</span>
    </a>
   <form ng-submit="signIn();">
       <fieldset class="inputs">
           <input type="email" id = "_email" ng-model="signInUser.email"><label for="_email">E-mail</label>
           <input type="password" id = "_password" ng-model="signInUser.password"><label for="_password">Password</label>
           <a href="forgotPassword.htm" id = "pasRecovery">Forgot your password?</a>
       </fieldset>
       <fieldset class="createAc">
       <button type="submit">
           LogIn
       </button>
        <a href="">Or login with facebook in one click</a>
       </fieldset>
      
       
   </form>
    
</div>