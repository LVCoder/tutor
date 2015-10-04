function SearchController($http, $scope, $rootScope, $location){
	$scope.$on('$viewContentLoaded', function() {
		$scope.search_query="";
		
	})
	
	$scope.searchUsers= function(){
		$location.path('/search');
		if ($scope.search_query&&$scope.search_query.trim()!=""){
			$http.get('protected/user/search?query='+$scope.search_query).success(function(result){
				if (result&&result.length>0){
					$rootScope.users = result;
				} else {
					$rootScope.users = [];
					toastr.warning( "No results");
				}
			});
		}
	}
	
	$scope.openSendMessage = function(index){
		$scope.userTo = $rootScope.users[index];
		$('#sendMessageModal').modal('show');
	}
	
	$scope.sendMessage = function(){
		if ($scope.messageText&&$scope.messageText.trim()!=""&&$rootScope.user.id!= $scope.userTo.id){
			var message = {
					text: $scope.messageText,
					userFromId: $rootScope.user.id,
					userToId: $scope.userTo.id
			}
		 connect(message);
			
			$('#sendMessageModal').modal('hide');
			toastr.success("Message was sent");
		}
	}
	 function sendMessage(message){
			if (checkMessage(message.text)){
		        stompClient.send("/app/send_message", {}, JSON.stringify(message));
			}
			 $scope.messageText = "";
		}
		
		
		function checkMessage(message){
			console.log(message.indexOf('@'));
			if (message.indexOf('@')>0||message.indexOf('skype')>0||message.indexOf('Skype')>0||message.indexOf('SKYPE')>0){
				return false;
			}
			return true;
		}
	function connect(message) {
        var socket = new SockJS('protected/messaging');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            sendMessage(message);
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
	  function disconnect() {
	        if (stompClient != null) {
	            stompClient.disconnect();
	        }
	        setConnected(false);
	        console.log("Disconnected");
	    }
}