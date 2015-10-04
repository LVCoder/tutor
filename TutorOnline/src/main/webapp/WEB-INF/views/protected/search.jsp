<div ng-controller="SearchController">
<div id="content" class = "advancedSearch" >
           <div class="leftSearch">
               <p id="foundAmount">
                   {{users.length}} results found 
               </p>
               <select>
                  <option value="Score">Score</option>
                  <option value="Price">Price</option>
                  <option value="Rating">Rating</option>
               </select>
               <div class="foundItem" ng-repeat="user in users track by $index">
                   <img ng-src="{{user.avatarPath}}" alt="">
                   <a id="foundPersonName">{{user.firstName}} {{user.lastName}}</a>
                   <div class="imgBlock">
                       <img src="resources/images/mark1.png" alt="">
                        <img src="resources/images/mark1.png" alt="">
                        <img src="resources/images/mark1.png" alt="">
                        <img src="resources/images/mark1.png" alt="">
                        <img src="resources/images/mark1.png" alt="">
                   </div>
<!--                    <span class="hourlyRate">HOURLY RATE</span> -->
                   <p class="hourlyRateAmount">
 <button class="btn-success btn"  ng-click="openSendMessage($index)">Send message</button>                 
                   </p>
 
                   <!--<p class="universityInSearch">LVIV NATIONAL FRANKO`S UNIVERSITY</p>-->
<!--                    <ul id = "subjects"> -->
<!--                        <li><a href="">Math</a></li> -->
<!--                        <li><a href="">Java</a></li> -->
<!--                        <li><a href="">C++</a></li> -->
<!--                    </ul> -->
   
               </div>
                              
           </div>
           <div class="rightSearch">
               
               <form action="" id = "searchOptions">
                 <fieldset class="keywords">
                      <h2>KEYWORDS</h2>
                       <input type="text" id="keywords">
                   </fieldset>
                   <fieldset class="ratingRate">
                       <h2>RATING</h2>
                       <div><label for="ratingMin">Min:</label><input type="text" id="ratingMin"></div>
                        <div><label for="ratingMax">Max:</label><input type="text" id="ratingMax"></div>
                   </fieldset>
                    <fieldset class="hourRate">
                       <h2>HOUR RATE</h2>
                       <div><label for="hourMin">Min:</label><input type="text" id="hourgMin"></div>
                       <div><label for="hourMax">Max:</label><input type="text" id="hourMax"></div>
                   </fieldset>
                    <buttton id="advancedSearchBut">
                        SEARCH
                    </buttton>
               </form>
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
</div>