<div class = "col-sm-12" ng-controller="MessagingController">
<div class = "col-sm-2"></div>
<div class = "col-sm-8">
<div class = "row" style="height:500px;  border: 2px solid grey; ">
<div id="chat" class = "col-sm-8" style = "height:100%; border-right: 2px solid grey; overflow-y:scroll; ">
<div class="row" ng-repeat="message in messages track by $index" on-finish-render="ngRepeatFinished">
<span ng-show="message.userFromId==user.id" style="font-weight:bold">{{user.username}}</span>
<span ng-show="message.userFromId!=user.id" style="font-weight:bold">{{mainUser.username}}</span>
<span>{{message.text}}  {{message.creationDate| date:'dd.MM.yyyy hh:mm'}}</span>
</div>
</div>
<div class = "col-sm-4">
<div class="row" ng-repeat="user in users track by $index">
<a ng-click="getMessages(user);">{{user.username}}  </a>
</div></div>
</div>
<div class = "row">
<input type="text" ng-model="message" ng-keyup="messageKeyup($event);">
<button ng-click="sendMessage();">Send</button>
</div>
</div>
<div class = "col-sm-2"></div>
</div>