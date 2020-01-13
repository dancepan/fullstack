sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent",
    "sap/ui/model/json/JSONModel",
], function (Controller, UIComponent, JSONModel) {
    "use strict";
    
    return Controller.extend("my.application.controller.BaseController", 
    {
    	getRouter: function ()
    	{
            return UIComponent.getRouterFor(this);
        },
 
        getModel: function (sName)
        {
            return this.getView().getModel(sName);
        },
 
        setModel: function (oModel, sName)
        {
            return this.getView().setModel(oModel, sName);
        },
 
        getResourceBundle: function ()
        {
            return this.getOwnerComponent().getModel("i18n").getResourceBundle();
        },
        
        callAjax : function(oParam)
        {  
        	var that   = this;
            var oModel = new sap.ui.model.json.JSONModel();
            
            jQuery.ajax({
                type        : oParam.type,
                data        : JSON.stringify(oParam.data),
                contentType : "application/json",
                url         : oParam.url,
                dataType    : "json",
                success     : function(oData, textStatus, jqXHR)
                              {
                	              var oModel = new JSONModel();
					              oModel.setData(oData); 
                    
                                  var proxyFunc = jQuery.proxy(that, oParam.callback, oModel);
                                  proxyFunc();
                              },
                error       : function()
                              {
                	              var proxyFunc = jQuery.proxy(that, oParam.error);
                                  proxyFunc();
                              }
            });
		}

    });
});