sampleApp.controller('RetailerCtrl', function ($scope, $http, $location, $timeout) {
	
	
	$scope.retailer = {};
	$scope.retailer.scan = {};	
	$scope.retailer.idtype = "NRIC";
	$scope.retailer.scan.isRefreshing = false;
	
	
	$scope.doScan = function() {
		console.log("do Scan is called");
		$scope.retailer.scan.isRefreshing = true;
		$http.post('/GlocalMe/retailer/scanid')
		.success(function(data) {
		  $scope.retailer.id = data.idNumber;
		  $scope.retailer.fullname = data.fullName;
		  console.log(data);
		})
		.error(function(data, status) {
			$scope.retailer.scan.isRefreshing = false;
		})
		.finally(function() {
		  	$scope.retailer.scan.isRefreshing = false;
		});
	}
	
	
	$scope.print = function() {
		console.log("Print is called");
		var dataObj = {
				fullName : "test",
				idNumber : "nric1"
		};
		var res = $http.post('/GlocalMe/retailer/print', dataObj);
		res.success(function(data, status, headers, config) {
			console.log(data);
 			var blob = new Blob([data], {type: "application/pdf"});
            var isIE = (navigator.userAgent.indexOf("MSIE") != -1);
            var isIE11 = !!navigator.userAgent.match(/Trident.*rv[ :]*11\./)
            if(isIE || isIE11){
                window.navigator.msSaveOrOpenBlob(blob, 'Registration.pdf');
            }

            else {
                var downloadLink = document.createElement("a");
                downloadLink.href = URL.createObjectURL(blob);
                downloadLink.download = "Registration.pdf";
                document.body.appendChild(downloadLink);
                $timeout(
                    function() {
                        downloadLink.click();
                        document.body.removeChild(downloadLink);
                        //$scope.downloadInProgress = false;
                    },0, false);
            }

		});
		res.error(function(data, status, headers, config) {
			console.log("OOPS!!");
		});		
	}
	
	$scope.cancel = function() {
		console.log("Cancel is called");
	}
	
});