 sampleApp.controller('PartnerCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
	$scope.uploadFile = function(){
	   var file = $scope.myFile;
	   
	   console.log('file is ' );
	   console.dir(file);
	   
	   var uploadUrl = "/GlocalMe/partner/fileUpload";
	   fileUpload.uploadFileToUrl(file, uploadUrl);
	};
 }]);
 
  sampleApp.service('fileUpload', ['$http', function ($http) {
	this.uploadFileToUrl = function(file, uploadUrl){
	   var fd = new FormData();
	   fd.append('file', file);
	
	   $http.post(uploadUrl, fd, {
		  transformRequest: angular.identity,
		  headers: {'Content-Type': undefined}
	   })
	
	   .success(function(){
	   })
	
	   .error(function(){
	   });
	}
 }]);
 
 sampleApp.directive('fileModel', ['$parse', function ($parse) {
	return {
	   restrict: 'A',
	   link: function(scope, element, attrs) {
		  var model = $parse(attrs.fileModel);
		  var modelSetter = model.assign;
		  
		  element.bind('change', function(){
			 scope.$apply(function(){
				modelSetter(scope, element[0].files[0]);
			 });
		  });
	   }
	};
 }]);
 
 /*
sampleApp.controller('PartnerCtrl', ['$scope', function($scope, $document){ 
    $scope.uploadFile = function(){
    
		var formData = new FormData();
		
		var fileInputElement = angular.element($('#file-select'));
		formData.append("file", fileInputElement[0]);

		
    	var xhr = new XMLHttpRequest();
    	xhr.open('POST', '/GlocalMe/partner/fileUpload');
    	xhr.send(formData);
    };
}]);
*/