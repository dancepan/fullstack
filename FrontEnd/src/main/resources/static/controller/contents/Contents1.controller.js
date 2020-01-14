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
		
			//var restApi = "http://localhost:8088/http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D&numOfRows=100&pageNo=1&sidoName=%EC%9D%B8%EC%B2%9C&ver=1.3&_returnType=json";
			//var restApi = "http://localhost:8088/http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D&numOfRows=100&pageNo=1&sidoName=인천&ver=1.3&_returnType=json";
			//var restApi = "http://localhost:8088/https://services.odata.org/V2/Northwind/Northwind.svc/"
			var restApi = "http://localhost:8088/http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D&numOfRows=100&pageNo=1&sidoName=인천&ver=1.3";
			
			var oParam = {
              //url     : "http://localhost:8081/list",
              //url     : "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D&numOfRows=100&pageNo=1&sidoName=%EC%9D%B8%EC%B2%9C&ver=1.3&_returnType=json",
                url     : restApi,
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
