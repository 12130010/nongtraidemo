
'use strict';


var commonModule = angular.module('commonModule', [])
.provider('commonService', function (){
	this.urlMap = {
		// User Service
		"USER_LOGIN" : {
			"baseUrl" : "?tag=login&email={0}&password={1}",
			"params" : ["email", "password"]
		},
		"UNIT_GET_ALL" : {
			"baseUrl" : "unit/59e21433fe16d2180837f9d5",
			"params" : []
		},
		"UNIT_INIT" : {
			"baseUrl" : "unit/",
			"params" : []
		},
		"UNIT_ADD" : {
			"baseUrl" : "unit/?unitParentId={0}",
			"params" : ["unitParentId"]
		},
		"UNIT_UPDATE" : {
			"baseUrl" : "unit/{0}",
			"params" : []
		}
	}
	
	this.CONSTANTS = {
			
	}
	
	this.dataConfig = {
		action : {
			tt: [
				{
					key : 'trong',
					text : 'Trồng',
					needImage : true
				},
				{
					key : 'tuoi',
					text : 'Tưới'
				},
				{
					key : 'xit_thuoc',
					text : 'Xịt thuốc'
				},
				{
					key : 'cham_soc',
					text : 'Chăm sóc'
				},
				{
					key : 'thu_hoach',
					text : 'Thu hoạch'
				}
			],
			cn: [
				{
					key : 'cho_an',
					text : 'Cho ăn'
				},
				{
					key : 'chich_thuoc',
					text : 'Chích thuốc'
				},
				{
					key : 'tam',
					text : 'Tắm'
				}
			],
			ts: [
				{
					key : 'cho_an',
					text : 'Cho ăn'
				},
				{
					key : 'don_ho',
					text : 'Dọn hồ'
				},
				{
					key : 'quat_nuoc',
					text : 'Quạt nước'
				}
			]
		}
	}
	
	this.$get = function() {
		return new CommonService(this.urlMap, this.CONSTANTS, this.dataConfig);
	};
	
	function CommonService(urlMap, constants, dataConfig) {
		this.CONSTANTS = constants;
		this.urlMap = urlMap;
		this.dataConfig = dataConfig;
		this.baseURL = baseURL //global variable
	};
	
	CommonService.prototype.getUrl = (function(){

		/**
		 * Replace parameter such as {0}, {1}, {2},.. in request url
		 * @param  {string} 	input 		input url
		 * @param  {array} 		params 		array of string which will replace curly params
		 * @param  {boolean} 	keepParams 	if true, will return the input, otherwise ''
		 * @return {string}		the modified input
		 */
		function replaceUrlParams(input, params, keepParams) {
			var result;
			var index = input.match(/\d+(?=\})/g);

			// there is no {\d} in input, we'll keep it
			if (index === null){
				result = input;	
			}else{
				if (CommonService.prototype.hasValueNotEmpty(params[index])) {
					result = input.replace(/\{\d+\}/g, params[index]);
				} else 
					result = keepParams ? input : '';	
			}
			
			return result;
		}

		/**
		 * append arrayOfParams to urlElement
		 * @param  {string} 	urlElement 		the name of url need to be create
		 * @param  {array} 		arrayOfParams 	of string which will replace curly params
		 * @return {string}		the url with parameters
		 */
		return function(urlElement, arrayOfParams) {
			var baseUrl = urlElement.baseUrl;
			var idx = baseUrl.indexOf('?'); 
			var url;//baseUrl before '?'
			var params = [];//list of params in baseUrl after '?'
			var i = 0;
			//map params to url
			if (this.hasValue(baseUrl) && this.hasValue(arrayOfParams)) {

				if (idx < 0){
					url = baseUrl;
					params = [];
				}
				else{
					url =  baseUrl.substr(0, idx);
					params = baseUrl.substr(idx + 1, baseUrl.length-1).split('&');
				}

				//process url
				url = url.replace(/\{\d+\}/g, function(substr){
					return replaceUrlParams(substr, arrayOfParams, true);
				})

				//process parameters in url
				if (params){
					params = params.map(function(param){
						return replaceUrlParams(param, arrayOfParams, false);
					});
				}
				params = params.filter(function(n){return n!="";});

				return this.baseURL +  (params.length > 0 ? url + '?' + params.join('&') : url );
			}
			else
				return urlElement;
		}
	})();
	
	// Utilities function
	/**
	 * @param variable
	 * @returns {Boolean} Note: if variable is an empty string (""), it still return true; 
	 */
	CommonService.prototype.hasValue = function (variable){
		return (typeof variable !== 'undefined') && (variable !== null);
	};
	CommonService.prototype.hasValueNotEmpty = function (variable){
		return (typeof variable !== 'undefined') && (variable !== null) && (variable.length !== 0);
	};
	
	CommonService.prototype.parseInt = function (str){
		var result = parseInt(str);
		if(result.toString() == "NaN")
			result = 0;
		return result;
	};
	
	/**
	 * Copy attributes values from des to src
	 * @param  {Object} src the object have attribute values need to copy to {@code des}
	 * @param  {Object} des the object have attribute values will be updated by {@code src}
	 */
	CommonService.prototype.copyValueFromOther = function copyValueFromOther (src, des) {
		for (var prop in des) {
			delete des[prop];
		};
		for (var prop in src) {
			if(angular.isObject(src[prop])){
				this.copyValueFromOther(src[prop], des[prop]);
			}else
				des[prop] = src[prop];
		};
	}
	
	/**
	*	delete all property of object.
	* 	result will be {}
	*/
	CommonService.prototype.cleanAllProperty = function cleanAllProperty (obj) {
		for (var prop in obj) {
			delete obj[prop];
		};
	}
	
	CommonService.prototype.findElementInElement = function(parent, elementsChain){
		var self = this;
		var ele = parent;
		if (!self.hasValue(elementsChain)) return ele;
		for ( var i = 0; i < elementsChain.length; i++) {
			var eleName = elementsChain[i];
			if (self.hasValue(ele)){
				ele = self.findElement(eleName, ele);
			}else{
				var err = {};
				var errChainNames = "$parent";
				for ( var j = 0; j < i; j++) {
					errChainNames += ("."+elementsChain[j]);
				}
				err.parent = parent;
				err.elementsChain =  elementsChain;
				err.message = "elementsChain got stuck (not found element or element is null: " + errChainNames;
				throw err;
			}
		}
		return ele;
	};
	
	CommonService.prototype.findElement = function(eleName, element){
		
		for(var prop in element) {
		     if(prop === eleName) {
                return element[prop];
            }
            if(angular.isObject(element[prop])) {
                var rs = this.findElement(eleName, element[prop]);
                if (rs !== undefined)
                    return rs;
            }
        }
        return undefined;
    };
})
.service('connectorService', ['$q', '$http', '$log', 'commonService', function($q, $http, $log, commonService) {
		function ConnectorService() {
			this.loadingBarCounter = 0;
		}
		
		/**
		 * Main function, which call the underline connector
		 * @param 	{object}		params 					input for executing actions, which has properties:
		 * @param 	{string}		params.actionName 		the action which connector need to execute
		 * @param 	{array}			params.actionParams 	array of actionParams
		 * @param 	{object}		params.data 			data send for post action (optional)
		 * @return an Angular Promise instance
		 */
		ConnectorService.prototype.get = function get(param){
			var self = this;
			var deferred = $q.defer();
			
			$log.debug("Call with actionName: " + param.actionName + ", and actionParams: " + param.actionParams);
			self.showLoadingBar();
			$http.get(commonService.getUrl(commonService.urlMap[param.actionName], param.actionParams)).then(function success(response){
				$log.debug(response);
				deferred.resolve(response);
				self.hideLoadingBar();
			}, function fail(response){
				$log.debug(response);
				self.hideLoadingBar();
				deferred.reject(response);
			});
			
			return deferred.promise;
		};
		
		/**
		 * Main function, which call the underline connector
		 * @param 	{object}		params 					input for executing actions, which has properties:
		 * @param 	{string}		params.actionName 		the action which connector need to execute
		 * @param 	{array}			params.actionParams 	array of actionParams
		 * @param 	{object}		params.data 			data send for post action (optional)
		 * @return an Angular Promise instance
		 */
		ConnectorService.prototype.post = function post(param){
			var self = this;
			var deferred = $q.defer();
			
			$log.debug("Call with actionName: " + param.actionName + ", and actionParams: " + param.actionParams);
			$log.debug(param.data);
			
			self.showLoadingBar();
			$http.post(commonService.getUrl(commonService.urlMap[param.actionName], param.actionParams), param.data).then( function success( response ){
				$log.debug(response);
				self.hideLoadingBar();
				deferred.resolve(response);
			}, function fail(response){
				$log.debug(response);
				self.hideLoadingBar();
				deferred.reject(response);
			});
			
			return deferred.promise;
		};
		
		/**
		 * Main function, which call the underline connector
		 * @param 	{object}		params 					input for executing actions, which has properties:
		 * @param 	{string}		params.actionName 		the action which connector need to execute
		 * @param 	{array}			params.actionParams 	array of actionParams
		 * @param 	{object}		params.data 			data send for post action (optional)
		 * @return an Angular Promise instance
		 */
		ConnectorService.prototype.put = function post(param){
			var self = this;
			var deferred = $q.defer();
			
			$log.debug("Call with actionName: " + param.actionName + ", and actionParams: " + param.actionParams);
			$log.debug(param.data);
			
			self.showLoadingBar();
			$http.put(commonService.getUrl(commonService.urlMap[param.actionName], param.actionParams), param.data).then( function success( response ){
				$log.debug(response);
				self.hideLoadingBar();
				deferred.resolve(response);
			}, function fail(response){
				$log.debug(response);
				self.hideLoadingBar();
				deferred.reject(response);
			});
			
			return deferred.promise;
		}
		
		/**
		 * Main function, which call the underline connector
		 * @param 	{object}		params 					input for executing actions, which has properties:
		 * @param 	{string}		params.actionName 		the action which connector need to execute
		 * @param 	{array}			params.actionParams 	array of actionParams
		 * @param 	{object}		params.data 			array of file, file is {name: "testCaseInput", file: file}
		 * @return an Angular Promise instance
		 */
		ConnectorService.prototype.postResource = function postResource(param){
			var self = this;
			var deferred = $q.defer();
			
			$log.debug("Call with actionName: " + param.actionName + ", and actionParams: " + param.actionParams);
			$log.debug(param.data);
			
			 var formData = new FormData();
			 angular.forEach(param.data, function (value){
				 /*
				  * value : {name: "testCaseInput", file: file}
				  */
				formData.append(value.name, value.file); 
			 });
			
			self.showLoadingBar();
			$http.post(commonService.getUrl(commonService.urlMap[param.actionName], param.actionParams),
						formData, //file
						{ //config
							transformRequest: angular.identity,
				            headers: {'Content-Type': undefined}
						} 
					).then( function success( response ){
						$log.debug(response);
						self.hideLoadingBar();
						deferred.resolve(response);
					}, function fail(response){
						$log.debug(response);
						self.hideLoadingBar();
						deferred.reject(response);
					});
			
			return deferred.promise;
		}
		
		ConnectorService.prototype.showLoadingBar = function showLoadingBar(){
			this.loadingBarCounter++;
			$("#ipos-full-loading").css("display", "block");
		}
		
		ConnectorService.prototype.hideLoadingBar = function hideLoadingBar(){
			this.loadingBarCounter--;
			if(this.loadingBarCounter <= 0){
				 $("#ipos-full-loading").css("display", "none");
				 this.loaddingBarCounter = 0;
			 }
		}
		 
		return new ConnectorService();
	}
])
