sap.ui.define([
	"OpenUI5/controller/common/BaseController",
	"sap/ui/model/json/JSONModel"
], function(BaseController, JSONModel) {
	"use strict";
		
	return BaseController.extend("OpenUI5.controller.common.BaseController",
	{
		onInit : function ()
		{
			console.log("Contents1.js OnInit()");
		
			var oParam = {
                url     : "http://localhost:8081/list",
            	type    : "GET",
                data    : "",
                callback: "callbackFunction",
                error   : "errorCallbackFunction"
            };
			
			//this.callAjax(oParam);
			
			this.callAjax2();
			
			console.log("Contents1.js OnInit() After..");
		},
		
		callbackFunction : function(oModel)
		{
			console.log("Content1.controller.js callbackFunction()");
			
			console.log(JSON.stringify(oModel, null, 2));
			
			var oTable = this.byId("idTable");
			
			oTable.setModel(oModel);
			oTable.setVisibleRowCount(oModel.getData().length);
		},
		
		errorCallbackFunction : function()
		{
			console.log("error callback");
		}
	});
}, true);
