
<div ng-controller="EditProfileController">
	<a href="" class="logo"> <span class="tut">Tutoring</span> <img
		src="resources/images/logo.svg" alt="logo"> <span class="port">Portal</span>
	</a>

	<form class="reg-form" ng-submit="edit();">
		<fieldset class="inputs reg-fieldset">
			<input type="text" id="_first_name" value="{{user.firstName}}"
				ng-model="user.firstName"><label for="_first_name">First
				Name</label> <input type="text" id="_last_name" value="{{user.lastName}}"
				ng-model="user.lastName"><label for="_last_name">Last
				Name</label> <input type="text" id="_username" ng-model="user.username"><label
				for="_username" value="{{user.username}}">Username</label>
		</fieldset>
		<fieldset class="conf-fiedset">
			<div class="col-sm-4"></div>
			<div class="col-sm-4 ">
				<label class="checkbox-inline"> <input type="checkbox"
					ng-model="wantLearn">I want learn
				</label>
			</div>
			<div class="col-sm-12">

				<div class="row conf-fiedset"
					ng-repeat="learnSelect in learnSelects track by $index">
					<div class="col-md-12">
						<select ng-disabled="!wantLearn" class="form-control"
							ng-model="learnSelect.selectedCategory"
							ng-options="category.name for category in categories"
							ng-change="loadLearnSubjects($index);">
						</select>
					</div>
					<div class="col-md-12">
						<select ng-disabled="!wantLearn" class="form-control"
							ng-model="learnSelect.selectedSubject"
							ng-options="subject.name for subject in learnSelect.subjects">

						</select>
					</div>
				</div>
				<div class="row conf-fiedset">
					<button type="button" ng-disabled="!wantLearn"
						ng-click="addLearnSubject();">Add Subject</button>
				</div>
			</div>
		</fieldset>
		<fieldset class="conf-fiedset">
			<div class="col-sm-4"></div>
			<div class="col-sm-4 ">
				<label class="checkbox-inline"> <input type="checkbox"
					ng-model="wantTeach">I want teach
				</label>
			</div>
			<div class="col-sm-12">

				<div class="row conf-fiedset"
					ng-repeat="teachSelect in teachSelects track by $index">
					<div class="col-md-12">
						<select ng-disabled="!wantTeach" class="form-control"
							ng-model="teachSelect.selectedCategory"
							ng-options="category.name for category in categories"
							ng-change="loadTeachSubjects($index);">
						</select>
					</div>
					<div class="col-md-12">
						<select ng-disabled="!wantTeach" class="form-control"
							ng-model="teachSelect.selectedSubject"
							ng-options="subject.name for subject in teachSelect.subjects">

						</select>
					</div>
					<div class="col-md-12">
						<label>Price <input ng-disabled="!wantTeach" type="number"
							ng-model="teachSelect.price" required>
						</label>
					</div>

				</div>
				<div class="row conf-fiedset">
					<button type="button" ng-disabled="!wantTeach"
						ng-click="addTeachSubject();">Add Subject</button>
				</div>

			</div>
		</fieldset>
		<fieldset class="createAc">
			<button type="submit">Edit</button>
			<a href="#">Or login with facebook in one click</a>
		</fieldset>


	</form>
</div>