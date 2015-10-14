var sampleApp = angular.module('glocalMeApp', 
				['ngRoute', 'pascalprecht.translate']);


sampleApp.config(['$routeProvider' , '$translateProvider',

	  function($routeProvider, $translateProvider) {
	    $routeProvider.
			  when('/personal', {
			    templateUrl: 'partials/personal.html',
			    controller: 'PersonalCtrl'
			  }).
			  when('/login', {
			    templateUrl: 'partials/login.html',
			    controller: 'LoginCtrl'
			  }).
			  when('/topuphistory', {
			    templateUrl: 'partials/topuphistory.html',
			    controller: 'TopupHistoryCtrl'
			  }).      
			  when('/coverage', {
			    templateUrl: 'partials/coverage.html',
			    controller: 'CoverageCtrl'
			  }).        
			  when('/home', {
			    templateUrl: 'partials/home.html',
			    controller: 'HomePageCtrl'
			  }).
			  when('/partner/deviceupload', {
			    templateUrl: 'partials/deviceuploads.html',
			    controller: 'PartnerCtrl'      
			  }).
			  when('/partner/retailerbuy', {
			    templateUrl: 'partials/buy.html',
			    controller: 'RetailerCtrl'      
			  }).
			  otherwise({
			    redirectTo: '/home'
			  });

			$translateProvider.translations('en', {
				FIRST_NAME: 'First Name'
			});
			$translateProvider.translations('ch', {
				FIRST_NAME: '名字'
			});
			$translateProvider.preferredLanguage('en');
	  }

 ]);
 
sampleApp.controller('PersonalCtrl', function ($scope, $http) {
		$scope.personalInfoSubmit = function() {
                $http({
                        method : 'POST',
                        url : 'personal/save'
                }).success(function(data, status, headers, config) {
                        $scope.person = data;
                }).error(function(data, status, headers, config) {
                });
        };
});

sampleApp.controller('TopupHistoryCtrl', function ($scope) {

});

sampleApp.controller('LoginCtrl', function ($scope) {
	console.log("Login is called");
});

sampleApp.controller('CoverageCtrl', function ($scope) {

});

sampleApp.controller('HomePageCtrl', function ($scope) {

});

sampleApp.controller('MainCtrl', function ($scope, $translate) {
	$scope.changeLanguage = function (langKey) {
		$translate.use(langKey);
	};	
});
