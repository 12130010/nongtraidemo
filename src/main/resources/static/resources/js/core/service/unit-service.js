'use strict';
app.service('unitService', ['$q', 'commonService', 'connectorService', function($q, commonService, connectorService) {
	function UnitService(){
		
	}
	
	UnitService.prototype.getAllUnitLv1 = function getAllUnitLv1(){
		var self = this;
    	var deferred = $q.defer();
    	
		connectorService.get(
				{
					actionName: "UNIT_GET_ALL",
					actionParams : []
				}
		).then(function success(response){
			deferred.resolve(response.data);
		});
		
		return deferred.promise; 
	};
	
	UnitService.prototype.initUnit = function initUnit(){
		var self = this;
		var deferred = $q.defer();
		
		connectorService.get(
				{
					actionName: "UNIT_INIT",
					actionParams : []
				}
		).then(function success(response){
			deferred.resolve(response.data);
		});
		
		return deferred.promise; 
	};
	
	UnitService.prototype.addUnit = function addUnit(unit, unitParentId){
		var self = this;
		var deferred = $q.defer();
		
		connectorService.post(
				{
					actionName: "UNIT_ADD",
					actionParams : [unitParentId],
					data : unit
				}
		).then(function success(response){
			deferred.resolve(response.data);
		});
		
		return deferred.promise; 
	};
	UnitService.prototype.updateUnit = function updateUnit(unit){
		var self = this;
		var deferred = $q.defer();
		
		connectorService.put(
				{
					actionName: "UNIT_UPDATE",
					actionParams : [unit.id],
					data : unit
				}
		).then(function success(response){
			deferred.resolve(response.data);
		});
		
		return deferred.promise; 
	};
	
	return new UnitService();
}]);