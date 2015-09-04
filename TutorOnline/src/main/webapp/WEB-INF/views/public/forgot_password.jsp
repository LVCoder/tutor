
<div ng-controller="ForgotPasswordController">
 <a href="" class="logo">
       <span class="tut">Tutoring</span>  <img src="resources/images/logo.svg" alt="logo"> <span  class = "port">Portal</span>
    </a>
   <form class="reg-form" ng-submit="forgotPassword();">
       <fieldset class="inputs reg-fieldset">
           <input type="email" id = "_email" ng-model="email"><label for="_email">E-mail</label>
       </fieldset>
       <fieldset class="createAc">
       <button type="submit">
           Send email
       </button>
       
       </fieldset>
      
       
   </form>
    
</div>