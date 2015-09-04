<div ng-controller="UserHomeController">
	<div class="intro">
		<div class="row">
			<div class="col-xs-9">Hourly Rate</div>
			<div class="col-xs-9">feedbacks</div>
			<div class="col-xs-9">reviews </div>
			<div class="col-xs-9"><a href="#/edit_profile">Edit</a> </div>
			<div class="col-xs-9"><a href="#/search">Search</a> </div>
			<div class="col-xs-9"><a href="#/messaging">Messages</a> </div>
			<div class="col-md-offset-9"><img width="70%" src="{{user.avatarPath}}"></img></div>
		</div>
		<div class="row">
			<div class="col-md-offset-9">{{user.firstName}}</div>
		</div>
		<div class="row">
			<div class="col-md-offset-9">{{user.lastName}}</div>
		</div>

	</div>
</div>