 <div ng-controller="SignUpController">
  <a href="" class="logo">
       <span class="tut">Tutoring</span>  <img src="resources/images/logo.svg" alt="logo"> <span  class = "port">Portal</span>
    </a>
   <form ng-submit="signUp();">
       <fieldset class="inputs">
       		<input type="text" id = "_first_name" ng-model="user.firtsName"><label for="_first_name">First Name</label>
       		<input type="text" id = "_last_name"  ng-model="user.lastName"><label for="_last_name">Last Name</label>
           <input type="email" id = "_email"  ng-model="user.email"><label for="_email">E-mail</label>
           <input type="text" id = "_username"  ng-model="user.username"><label for="_username">Username</label>
           <input type="password" id = "_password"  ng-model="user.password"><label for="_password">Password</label>
           <input type="password" id = "_password"  ng-model="user.confirmPassword"><label for="_password">Confirm Password</label>
       </fieldset>
       <fieldset class="createAc">
       <button type="submit">
           Create free account
       </button>
        <a href="#">Or login with facebook in one click</a>
       </fieldset>
      
       
   </form>
  </div>