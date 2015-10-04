<div ng-controller="MessagingController">
<div class="messageHead">
            <div class = "messageHeadMain">
                <h1>Messages</h1>
                <h2>{{mainUser.username}}</h2>
               
            </div>
        </div>
        
    <div id="content">
        <div class="sendersBlock" >
        <div ng-repeat="user in users track by $index" >
           <a href="" id="oneMessage" ng-class = "active[$index]"    ng-click="getMessages(user);">
                <img src="{{user.imagePath}}" alt="">
                <p id = "sendersName">{{user.username}}</p>
                
              
            </a>
            </div>
            
        </div>
        <div class="messagesBlock" id="chat">
          <div class="sentMessage" ng-repeat="message in messages track by $index" on-finish-render="ngRepeatFinished">
               <img src="{{user.avatarPath}}" ng-show="message.userFromId==user.id" alt="" class="senderAva">
               <img src="{{mainUser.imagePath}}" ng-show="message.userFromId!=user.id" alt="" class="senderAva">
                <p id = "messageSender" ng-show="message.userFromId==user.id">{{user.username}}</p>
                 <p id = "messageSender" ng-show="message.userFromId!=user.id">{{mainUser.username}}</p>
                <span id="dateOfSending">{{message.creationDate| date:'dd.MM.yyyy hh:mm'}}</span>
                <p id = "messageText">{{message.text}}</p>
            </div>
           
        </div>
        <form action="" id="sendMessageForm">
            <textarea name="messageInput" id="messageInput" cols="30" rows="10" ng-model="message" ng-keyup="messageKeyup($event);"></textarea>
            <button ng-click="sendMessage();" id="sendMessageBut">Send</button>
            
        </form>
    </div>
    </div>