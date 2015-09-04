<div ng-controller="ForgotPasswordController">
 <a href="" class="logo">
       <span class="tut">Tutoring</span>  <img src="resources/images/logo.svg" alt="logo"> <span  class = "port">Portal</span>
    </a>
   <form class="reg-form" ng-submit="changePassword();">
       <fieldset class="inputs reg-fieldset">
           <input type="password" id = "_password" ng-model="password"><label for="_password">Password</label>
           <input type="password" id = "_password" ng-model="confirmPassword"><label for="_password">Confirm Password</label>
       </fieldset>
       <fieldset class="createAc">
       <button type="submit">
           Save
       </button>
       </fieldset>
      
       
   </form>
    
</div>