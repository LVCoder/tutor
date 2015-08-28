
<div ng-controller="EditProfileController">
	<a href="" class="logo"> <span class="tut">Tutoring</span> <img
		src="resources/images/logo.svg" alt="logo"> <span class="port">Portal</span>
	</a>

	<form class="reg-form" ng-submit="editProfile();">
		<fieldset class="inputs reg-fieldset">
			<input type="text" id="_first_name" value="{{user.firstName}}" ng-model="user.firtsName"><label
				for="_first_name">First Name</label> <input type="text"
				id="_last_name" value="{{user.lastName}}" ng-model="user.lastName"><label
				for="_last_name">Last Name</label> 
				<input
				type="text" id="_username" ng-model="user.username"><label
				for="_username" value="{{user.username}}">Username</label> 
		</fieldset>
		<fieldset class="createAc">
			<button type="submit">Edit</button>
			<a href="#">Or login with facebook in one click</a>
		</fieldset>


	</form>
</div>