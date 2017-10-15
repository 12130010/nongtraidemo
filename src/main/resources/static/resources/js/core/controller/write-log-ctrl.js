'use strict';

var writeLogController = ['$state', '$scope', 'commonService', 'qrscannerService', 'captureService', function ($state, $scope, commonService, qrscannerService, captureService){
	function init(){
		if (window.cordova) {
			qrscannerService.scan().then(function (result) {
				 if(!result.cancelled){
					if(result.format == "QR_CODE"){
						$scope.qrData = {text : result.text};
						$scope.getActionByQR($scope.qrData.text);
					}
				 } else { //result.cancelled
					$state.go('home');
				 }
			}, function (error) { 
				alert("Scanning failed: " + error);
			});
		} else { // mock data
			$scope.qrData = {text : 'abcdef'};
			$scope.getActionByQR($scope.qrData.text);
		}
		
	};
	
	$scope.getActionByQR = function (qrcode) {
		var self = this;
		self.actions = commonService.dataConfig.action.tt;
	};
	
	$scope.chooseAcction = function (action) {
		var self = this;
		if(action.hasOwnProperty('needImage') && action.needImage) {
			captureService.capture(25).then( function (imageURI) {
				$scope.showImage=true;
				var image = document.getElementById('capturedImage');
				image.src ="data:image/png;base64," + imageURI;
			}, function (errorMessage) {
				alert(errorMessage);
			})
		} else {
			alert("SEND DATA:\n" +
				"maqr:" + self.qrData.text + "; " + 
				"hd:" + action.key);
		}
	};
	
	init();
}];