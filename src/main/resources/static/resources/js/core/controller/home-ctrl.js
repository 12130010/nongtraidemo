'use strict';

var homeController = function ($state, $scope, unitService, Lightbox){
	function init(){
		
		//init user model
		$scope.treeData;
		
		$scope.loadTreeData();
	};
	
	$scope.loadTreeData = function treeData(){
		var self = this;
		unitService.getAllUnitLv1().then( function (units) {
			self.treeData = [units];
		});
	};
	
	$scope.selectNode = function (unit) {
		$scope.selectedNode = unit;
	};
	
	$scope.prepareNewChildUnit = function () {
		unitService.initUnit().then( function (initUnit) {
			$scope.newUnit = initUnit;
		});
		
		$scope.isAddNewChildUnit = true;
	};
	
	/*
	 * Update or add. If newUnit has id, we update exist unit.
	 */
	$scope.addNewChildUnit = function () {
		var self = $scope;
		//split into array
		self.newUnit.owners = angular.isArray(self.newUnit.owners) ? self.newUnit.owners : self.newUnit.owners.split(",");
		
		if(self.newUnit.id) { //has id => update
			unitService.updateUnit(self.newUnit).then( function (newUnit) {
				self.closeNewChildUnit();
				self.loadTreeData(); // reload all
			});
		} else {
			// parent level + 1
			self.newUnit.level = self.selectedNode.level+1;
			
			unitService.addUnit(self.newUnit, self.selectedNode.id).then( function (newUnit) {
				if (!self.selectedNode.childUnits)
					self.selectedNode.childUnits = [];
				self.selectedNode.childUnits.push(newUnit);
				self.closeNewChildUnit();
			});	
		}
	};
	
	$scope.closeNewChildUnit = function () {
		$scope.isAddNewChildUnit = false;
	};
	
	$scope.prepareUpdateSelectedUnit = function () {
		var self = $scope;
		
		self.newUnit = self.selectedNode;
		$scope.isAddNewChildUnit = true;
	}
	
	$scope.openLightboxModal = function (imageLinks, index) {
	    Lightbox.openModal(imageLinks, index);
	};
	init();
}