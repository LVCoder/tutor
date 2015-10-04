function MessagingController ($http, $scope, $rootScope, $location){
	 
	$scope.$on('$viewContentLoaded', function() {
		$scope.active = [];
		$scope.messages = [];
		getMessagingInformation(-1);
		connect();
	});
	function getMessagingInformation(userId){
		$http.get('protected/message/information/'+userId).success(function(result){
			if (result){
				$scope.users = result.users;
				$scope.mainUser = result.mainUser;
				$scope.messages = result.messages;
				setActive($scope.mainUser.userId)
				
			}
		})
	}
	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
		 $('#chat').scrollTop($('#chat').height());
	});
	
	$scope.messageKeyup = function(event){
		if (event.keyCode == 13){
			$scope.sendMessage();
		}
		
	}
	function setActive(userId){
		$scope.active = [];
		for (i=0; i<$scope.users.length; i++){
			if ($scope.users[i].userId==userId){
				$scope.active.push('active');
			} else {
				$scope.active.push('');
			}
		}
	}
	$scope.getMessages = function(user){
		getMessagingInformation(user.userId);
	}
	
	$scope.sendMessage = function(){
		if (checkMessage($scope.message)){
		    var message = { 'text': $scope.message, 'userToId':$scope.mainUser.userId,'userFromId':$rootScope.user.id, 'creationDate': new Date() };
	        $scope.messages.push(message);
	        stompClient.send("/app/send_message", {}, JSON.stringify(message));
		}
		 $scope.message = "";
	}
	
	
	function checkMessage(message){
		console.log(message.indexOf('@'));
		if (message.indexOf('@')>=0||message.indexOf('skype')>=0||message.indexOf('Skype')>=0||message.indexOf('SKYPE')>=0){
			return false;
		}

		
		return true;
	}
	
	function connect() {
        var socket = new SockJS('protected/messaging');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/client/get_message'+$rootScope.user.id, function(message){
                messageIn = JSON.parse(message.body);
                if (messageIn.userFromId == $scope.mainUser.userId){
                getMessagingInformation(messageIn.userFromId);      
                } else {
                	findUser(messageIn.userFromId).unreadedMessageQuantity += 1;
                }
            });
        });
    }

	function findUser(userId){
		for (i=0; i<$scope.users.length; i++){
			if ($scope.users[i].userId == userId){
				return user[i];
			}
		}
		
	}
	
    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }


	
}