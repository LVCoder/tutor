<div class="col-sm-12"style="min-height:300px;">
<div class="col-sm-4"></div><div class="col-sm-6"><h4>Search</h4></div>
<div class = "row">
<div class = "col-sm-3"></div>
<div class = "col-sm-6">
<input type="text" ng-keyup="searchUsers();" ng-model="search_query" class="form-control">
</div>
<div class = "col-sm-3"></div>
</div>
<div class="col-sm-12">
<div class = row style="margin-top:20px;" ng-repeat="user in users track by $index">
<div class = "col-sm-2"></div>
<div class = "col-sm-6" style="border: 1px grey solid;">
<div class="col-sm-2"><img ng-src="{{user.avatarPath}}" width="100%"></div>
<div class = "col-sm-4">
<span>{{user.firstName}} {{user.lastName}}</span>
</div>
<div class="col-sm-2"><button class="btn-default" ng-click="openSendMessage($index)">Send message</button></div>
</div>
</div>
</div>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="sendMessageModal">
  <div class="modal-dialog modal-sm">
    <div class="modal-content" >
      <h4 class="modal-title" style="margin-left:10px;" >Message</h4>
      <h5 style="margin-left:10px;">To {{userTo.firstName}} {{userTo.lastName}}</h5>
      <div class="modal-body">
      <textarea class="form-control" style="height:150px; margin-bottom:10px;" ng-model="messageText">dasdsadsa</textarea>
      <button class="btn-success" ng-click="sendMessage();">Send</button>
      </div>
    </div>
  </div>
</div>