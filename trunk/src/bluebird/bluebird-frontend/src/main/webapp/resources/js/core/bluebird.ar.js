/*@Author AnLT */
(function($){
	'use strict';
	jQuery.namespace('$.bb.ar');
	
	var CONST = {
			ATTR_PRECHECK_CALLBACK : "data-ar-precheck",
			ATTR_FINISHED_CALLBACK : "data-ar-callback",
			ATTR_INDEXCHANGE_CALLBACK : "data-ar-indexchange",
			ATTR_MAXIMUM_ITEMS : "data-ar-mitems",
			ATTR_IGNORE_ITEMS : "data-ar-iitems",
			ATTR_ITEM_GROUPID : "data-ar-rgroup",
			ATTR_ITEM_GROUPINDEX : "data-ar-rgroupindex",
			ATTR_ITEM_TEMPLATE : "data-ar-templateid",
			ATTR_ITEM_INDEX : "data-ar-rindex",
			DIRECTION_ADD : "add",
			DIRECTION_REMOVE : "remove",
			ANCHOR_STRING_BEFORE : "before",
			ANCHOR_STRING_AFTER : "after",
			ANCHOR_STRING_INSIDE_BEGIN : "inside-begin",
			ANCHOR_STRING_INSIDE_END : "inside-end",
			REMOVE_TYPE_ALL :"all",
			REMOVE_TYPE_ONLYDESC: "onlyDescendants",
			AR_CLASS_GROUPINDEX: "ar-groupIndex",
			AR_CLASS_RINDEX: "ar-rIndex",
			AR_CLASS_GROUPID: "ar-groupId",
			ARR_REINDEX_ATTRIBUTES : ["name","id","for"]
	};
	$.bb.ar.CONST=CONST;
	$.bb.ar.addItem = function(param) {
		var $container = param.container;
		var $template = null;
		var $newItem = null;
		var templateData = param.templateData || {};
		if(!$container){
			if(!param.containerId) {
				$container = $(param.link).closest("div").prev("table");
			} else {
				$container = $("#"+param.containerId);
			}
		} else {
			$container = $($container);
		}
		if($container!="undefined") {
			var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
			if(isNaN(ignoreItems)) {
				ignoreItems = 0;
			}
			var maxItems = parseInt($container.attr(CONST.ATTR_MAXIMUM_ITEMS));
			if(isNaN(maxItems)) {
				maxItems = 200;
			}
			var precheckFunction = $container.attr(CONST.ATTR_PRECHECK_CALLBACK);
			var passCheck = true;
			if(typeof $.namespace(precheckFunction)=="function"){
				passCheck = $.namespace(precheckFunction)($container,param.link,CONST.DIRECTION_ADD);
			}
			if($container.is("table") & !param.position){
				param.position = {anchor:'>tbody',string:CONST.ANCHOR_STRING_INSIDE_END};
				if($container.find(">tbody>*").length-ignoreItems >= maxItems) {
					passCheck = false;
				}
				if($container.find(">tbody").length==0){
					$container.append($("<tbody>"))
				}
			} 
			if(!param.templateId) {
				$template = $("#" + $container.attr("id") + "-template");
			} else {
				$template = $("#"+param.templateId);
			}
			$newItem = $template.tmpl(templateData);
			if(passCheck && $newItem.toString()){
				var callback = $container.attr(CONST.ATTR_FINISHED_CALLBACK);
				var templateId = $template.attr("id");
				if(!templateId){
					templateId = '';
				}
				$newItem.attr(CONST.ATTR_ITEM_TEMPLATE, templateId);
				if(!$newItem.attr(CONST.ATTR_ITEM_GROUPID)){
					$newItem.attr(CONST.ATTR_ITEM_GROUPID,"");
				}
				switch(param.position.string){
				case CONST.ANCHOR_STRING_BEFORE:
					$container.find(param.position.anchor).before($newItem);
					break;
				case CONST.ANCHOR_STRING_AFTER:
					$container.find(param.position.anchor).after($newItem);
					break;
				case CONST.ANCHOR_STRING_INSIDE_END:
					$container.find(param.position.anchor).append($newItem);
					break;
				case CONST.ANCHOR_STRING_INSIDE_BEGIN:
					$container.find(param.position.anchor).prepend($newItem);
					break;
				}
				$.bb.ar.recalculateItemIndex($container,param.indexClass,templateData.groupId);
				$.bb.ar.renameAttributes($container);
				$.bb.ar.callbackDefault($container,CONST.DIRECTION_ADD,$newItem,param);
				if(typeof $.namespace(callback)=="function"){
					$.namespace(callback)($container,CONST.DIRECTION_ADD,$newItem,param);
				}
			}
		}
		return $newItem;
	};

	$.bb.ar.removeItem = function(param) {
		var $container;
		if(!$.contains($(param.container),$(param.link))){
			$container = $(param.link).closest("table");
		} else {
			$container = $(param.container);
		}
		
		var callback = $container.attr(CONST.ATTR_FINISHED_CALLBACK);
		var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
		if (isNaN(ignoreItems)) {
			ignoreItems = 0;
		}
		var $removeItem = $(param.link).closest("tr");
		var precheckFunction = $container.attr(CONST.ATTR_PRECHECK_CALLBACK);
		var passCheck = true;
		if(typeof $.namespace(precheckFunction)=="function"){
			passCheck = $.namespace(precheckFunction)($container,param.link,CONST.DIRECTION_REMOVE,$removeItem);
		}
		if(passCheck){
			if(param.removeType && param.removeType!=CONST.REMOVE_TYPE_ALL){
				if(param.removeType == CONST.REMOVE_TYPE_ONLYDESC){
					$container.find("["+CONST.ATTR_ITEM_GROUPID+"^='"+$removeItem.attr(CONST.ATTR_ITEM_GROUPINDEX)+"']").remove();
				}
			} else {
				$container.find("["+CONST.ATTR_ITEM_GROUPID+"^='"+$removeItem.attr(CONST.ATTR_ITEM_GROUPINDEX)+"']").remove();
				$removeItem.remove();
			}
			
			var templateId = $removeItem.attr(CONST.ATTR_ITEM_TEMPLATE);
			if(!templateId) {
				$template = $("#" + $container.attr("id") + "-template");
			} else {
				$template = $("#"+templateId);
			}
			
			var $newItem ;
			if (param.isReserved) {
				if ($container.find("tr").size() <= ignoreItems) {
					$newItem = $template.tmpl();
					$newItem.attr(CONST.ATTR_ITEM_TEMPLATE,$removeItem.attr(CONST.ATTR_ITEM_TEMPLATE));
					$container.append($newItem);
				}
			}
			$.bb.ar.callbackDefault($container,CONST.DIRECTION_REMOVE,$removeItem);
			if(typeof $.namespace(callback)=="function"){
				$.namespace(callback)($container,CONST.DIRECTION_REMOVE,$removeItem,param);
			}
			$.bb.ar.recalculateItemIndex($container,$removeItem.attr(CONST.ATTR_ITEM_GROUPID));
			$.bb.ar.renameAttributes($container);
			if (param.isReserved) {
				if ($newItem) {
					$.bb.ar.callbackDefault($container,CONST.DIRECTION_ADD,$newItem,param);
					if(typeof $.namespace(callback)=="function"){
						$.namespace(callback)($container,CONST.DIRECTION_ADD,$newItem,param);
					}
				}
			}
		}
		return $removeItem;
	};
	$.bb.ar.recalculateItemIndex = function(container,groupId) {
		$container=$(container);
		var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
		var itemIndexClass;
		var indexChangeCallback = $container.attr(CONST.ATTR_INDEXCHANGE_CALLBACK);
		if(isNaN(ignoreItems)) {
			ignoreItems = 0;
		}
		
		
		if(!itemIndexClass){
			itemIndexClass = CONST.AR_CLASS_RINDEX;
		}
		
		$container.find(">tbody>tr").each(function(i) {
			if(i>=ignoreItems){
				$(this).attr(CONST.ATTR_ITEM_INDEX,i-ignoreItems);
				$(this).find("input."+itemIndexClass).val(i+1);
			}
		});
		
		$.bb.ar.recalculateGroupIndex($container.find(">tbody>tr"),groupId,indexChangeCallback);
	};
	$.bb.ar.recalculateGroupIndex = function(groupScope,groupId,indexChangeCallback){
		var $groupScope = $(groupScope);
		if(!groupId){
			groupId = '';
		}
		var indexPrefix=groupId;
		if(indexPrefix){
			indexPrefix += '.';
		}
		
		var inGroupSelector;
		if(groupId){
			inGroupSelector = "tr[data-ar-rgroup='"+groupId+"']";
		} else {
			inGroupSelector = "tr[data-ar-rgroup=''],tr:not([data-ar-rgroup])";
		}
		
		$groupScope.filter(inGroupSelector).each(function(i) {
			if(typeof $.namespace(indexChangeCallback)=="function"){
				 $.namespace(indexChangeCallback)($groupScope,this,$(this).attr("data-ar-rgroupindex"),indexPrefix+(i+1));
			}
			var $scope = $(this).nextAll("tr[data-ar-rgroup='"+$(this).attr(CONST.ATTR_ITEM_GROUPINDEX)+"']");
			$scope.attr(CONST.ATTR_ITEM_GROUPID,indexPrefix+(i+1));
			$(this).attr("data-ar-rgroupindex",indexPrefix+(i+1));
			$(this).find("td."+CONST.AR_CLASS_GROUPINDEX).html(indexPrefix+(i+1));
			$(this).find("span."+CONST.AR_CLASS_GROUPINDEX).html(indexPrefix+(i+1));
			$(this).find("input."+CONST.AR_CLASS_GROUPID).val(groupId);
			$(this).find("input."+CONST.AR_CLASS_GROUPINDEX).val(indexPrefix+(i+1));
			$.bb.ar.recalculateGroupIndex($scope,$(this).attr(CONST.ATTR_ITEM_GROUPINDEX),indexChangeCallback);
		});
	};
	$.bb.ar.renameAttributes = function(container) {
		$container=$(container);
		var dataSelector = ">.ar-dataItem";
		var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
		var treeLevel = parseInt($container.attr("data-ar-tlevel"));
		if(isNaN(ignoreItems)) {
			ignoreItems = 0;
		}
		if(isNaN(treeLevel)) {
			treeLevel = 0;
		}
		var regex = /\[\d*\]/g;
		
		var attributesName;
		for(var i=0;i<CONST.ARR_REINDEX_ATTRIBUTES.length;i++){
			attributesName = (attributesName?attributesName+", ":"") + "[" +CONST.ARR_REINDEX_ATTRIBUTES[i]+"*='[']["+CONST.ARR_REINDEX_ATTRIBUTES[i]+"*='].']";
		}
		
		$container	.find(dataSelector)
					.each(function(i) {
							if(i>=ignoreItems) {
								var replacements = [];
								var tempTable = $container;
								if(!CONST.ARR_REINDEX_ATTRIBUTES){
									CONST.ARR_REINDEX_ATTRIBUTES = ["name"];
								}
								for(var j=treeLevel-1;j>-1;j--) {
									tempTable=$(tempTable).parents(".ar-dataContainer:first");
									replacements[j] ='[' + tempTable.parents(".ar-dataItem:first").attr(CONST.ATTR_ITEM_INDEX)+ ']';
								}
								$(this)	.find(attributesName)
										.each(function() {
											for(var j in CONST.ARR_REINDEX_ATTRIBUTES){
												if($(this).attr(CONST.ARR_REINDEX_ATTRIBUTES[j])){
													var matches = $(this).attr(CONST.ARR_REINDEX_ATTRIBUTES[j]).match(regex);
													if (matches && matches[treeLevel]) {
														var index=0;
														$(this).attr(CONST.ARR_REINDEX_ATTRIBUTES[j],$(this).attr(CONST.ARR_REINDEX_ATTRIBUTES[j]).replace(regex,function(match,stringIndex){
															var replacement = "[]";
															if(index==treeLevel){
																replacement = '[' + (i-ignoreItems) + ']';
															} else {
																if(index>treeLevel){
																	replacement =  match;
																} else replacement=replacements[index];
															}
															index++;
															return replacement;
														}));
													}
												}
											}
										});
							}
					});
	};
	$.bb.ar.callbackDefault = function(){};
	$.bb.ar.initialize = function(){
		$(function(){
		});
	};
})(jQuery);