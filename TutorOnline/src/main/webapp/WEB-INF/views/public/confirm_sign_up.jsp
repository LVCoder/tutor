 <div ng-controller="ConfirmSignUpController">
  <a href="" class="logo">
       <span class="tut">Tutoring</span>  <img src="resources/images/logo.svg" alt="logo"> <span  class = "port">Portal</span>
    </a>
        <div class="col-sm-12">
   <form ng-submit="confirmSignUp();">
       <fieldset class="conf-fiedset">
      <div class = "col-sm-2"></div>
       <div class="col-sm-2 " >
       <label class = "checkbox-inline">  <input type="checkbox" ng-model="wantLearn">I want learn</label> 
       </div>
       <div  class = "col-sm-6">
       
       <div class="row conf-fiedset" ng-repeat="learnSelect in learnSelects track by $index">
       <div class="col-md-6">
       		<select  ng-disabled="!wantLearn" class="form-control"  ng-model="learnSelect.selectedCategory"  ng-options="category.name for category in categories"
      ng-change="loadLearnSubjects($index);">
       		</select>
       		</div>
       <div class="col-md-6">
       		<select ng-disabled="!wantLearn" class="form-control"  ng-model="learnSelect.selectedSubject"  ng-options="subject.name for subject in learnSelect.subjects"    >
       		
       		</select>
       		</div>	
       		
       </div>
       <div class= "row conf-fiedset">
       <button type="button"  ng-click="addLearnSubject();"> Add Subject</button>
       </div>
        <div class="row conf-fiedset">
         <div class="col-md-6">
       		<label>Institution<input  ng-disabled="!wantLearn" type="text" id="institution" ng-model="institution"> </label> 
       		</div>	
        </div>
      	</div>
       </fieldset>
          <fieldset class="conf-fiedset">
          <div class = "col-sm-2"></div>
       <div class="col-sm-2 " >
       <label class = "checkbox-inline">  <input type="checkbox" ng-model="wantTeach">I want teach</label> 
       </div>
       <div  class = "col-sm-6">
       
        <div class="row conf-fiedset" ng-repeat="teachSelect in teachSelects track by $index">
       <div class="col-md-6">
       		<select  ng-disabled="!wantTeach" class="form-control"  ng-model="teachSelect.selectedCategory"  ng-options="category.name for category in categories"
      ng-change="loadTeachSubjects($index);">
       		</select>
       		</div>
       <div class="col-md-6">
       		<select ng-disabled="!wantTeach" class="form-control"  ng-model="teachSelect.selectedSubject"  ng-options="subject.name for subject in teachSelect.subjects"    >
       		
       		</select>
       		</div>		
       		   <div class="col-md-6">
       		<label>Price <input ng-disabled="!wantTeach" type="number" ng-model="teachSelect.price" required> </label> 
       		</div>
     
       </div>
       <div class= "row conf-fiedset">
       <button type="button" ng-disabled="!wantTeach" ng-click="addTeachSubject();"> Add Subject</button>
       </div>
            
       </div>
     
       </fieldset>
        <div class="row conf-fiedset">
          <div class = "col-sm-2"></div>
       <div class="col-sm-2 " >
       <label class="control-label">Experience</label>
       </div>
       <div class="col-sm-6">
       <div class="row conf-fiedset">
       <textarea ng-disabled="!wantTeach" ng-model="experience" class="form-control"></textarea>
       </div>
       </div>
     </div>
     <div class="row conf-fiedset">
      
          <div class = "col-sm-2"></div>
       <div class="col-sm-2 " >
       <label class="control-label">Others</label>
       </div>
       <div class="col-sm-6">
       <div class="row conf-fiedset">
       <textarea ng-disabled="!wantTeach" ng-model="others" class="form-control"></textarea>
       </div>
     </div>
     
     </div>
     <div  class="fileUpload btn btn-primary" style="margin-left:300px;">
       <span>Upload Avatar</span>
       <input type="file" accept="image/*" ng-model-instant id="fileToUpload" multiple onchange="angular.element(this).scope().fileChanged(this)" class="upload">
    </div>
      <div class="row conf-fiedset" style="margin-left:300px; margin-bottom: 30px;">
       <fieldset class="createAc " >
       <button type="submit">
           Save changes
       </button>
       </fieldset>
       </div>
      
       
   </form>
   </div>
  </div>