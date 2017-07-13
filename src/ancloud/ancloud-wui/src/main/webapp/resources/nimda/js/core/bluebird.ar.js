/*@Author AnLT */
(function($){
	'use strict';
	jQuery.namespace('$.bb.ar');
	
	var CONST = {
			ATTR_PRECHECK_CALLBACK : "data-ar-precheck",
			ATTR_INDEXCHANGE_CALLBACK : "data-ar-indexchange",
			ATTR_MAXIMUM_ITEMS : "data-ar-mitems",
			ATTR_IGNORE_ITEMS : "data-ar-iitems",
			ATTR_ITEM_GROUPID : "data-ar-groupid",
			ATTR_ITEM_GROUPINDEX : "data-ar-groupindex",
			ATTR_ITEM_TEMPLATE : "data-ar-templateid",
			ATTR_ITEM_INDEX : "data-ar-rindex",
			ATTR_TREE_LEVEL :"data-ar-tlevel",
			DIRECTION_ADD : "add",
			DIRECTION_REMOVE : "remove",
			ANCHOR_STRING_BEFORE : "before",
			ANCHOR_STRING_AFTER : "after",
			ANCHOR_STRING_INSIDE_BEGIN : "inside-begin",
			ANCHOR_STRING_INSIDE_END : "inside-end",
			REMOVE_TYPE_ALL :"all",
			REMOVE_TYPE_ONLYDESC: "onlyDescendants",
			AR_CLASS_GROUPINDEX: "ar-groupIndex",
			AR_CLASS_ITEMINDEX: "ar-itemIndex",
			AR_CLASS_GROUPID: "ar-groupId",
			AR_CLASS_CONTAINER: "ar-dataContainer",
			AR_CLASS_DATAITEM : "ar-dataItem",
			AR_CLASS_ITEM : "ar-item",
			ARR_REINDEX_ATTRIBUTES : ["name","id","for"],
	};
	$.bb.ar.CONST=CONST;
	$.bb.ar.addItem = function(param) {
		var $container = param.container;
		var $template = null;
		var $newItem = null;
		var templateData = param.templateData || {};
		if(!$container){
			$container = $("#"+param.containerId);
		} else {
			$container = $($container);
		}
		if($container) {
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
			if($container.is("table")){
				if($container.find(">tbody>*").length-ignoreItems >= maxItems) {
					passCheck = false;
				}
				if($container.find(">tbody").length==0){
					$container.append($("<tbody>"))
				}
				$container = $container.find("tbody");
			}
			
			if(!param.position){
				param.position = {
									string:CONST.ANCHOR_STRING_INSIDE_END
								};
			}
			
			if(!param.templateId) {
				$template = $("#" + $container.attr("id") + "-template");
			} else {
				$template = $("#"+param.templateId);
			}
			
			$newItem = $($.bb.ar.template($template.html(),templateData));
			if(passCheck && $newItem.toString()){
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
					$(param.position.anchor).before($newItem);
					break;
				case CONST.ANCHOR_STRING_AFTER:
					$(param.position.anchor).after($newItem);
					break;
				case CONST.ANCHOR_STRING_INSIDE_END:
					$container.append($newItem);
					break;
				case CONST.ANCHOR_STRING_INSIDE_BEGIN:
					$container.prepend($newItem);
					break;
				}
				if(!$newItem.hasClass(CONST.AR_CLASS_ITEM)){
					$newItem.addClass(CONST.AR_CLASS_ITEM)
				}
				if(!$container.hasClass(CONST.AR_CLASS_CONTAINER)){
					$container.addClass(CONST.AR_CLASS_CONTAINER)
				}
				$.bb.ar.recalculateItemIndex($container,param.indexClass,templateData.groupId);
				$.bb.ar.renameAttributes($container);
				$.bb.ar.defaultCallback($container,CONST.DIRECTION_ADD,$newItem,param);

				if(typeof param.callback=="function"){
					param.callback($container,CONST.DIRECTION_ADD,$newItem,param);
				}
			}
		}
		return $newItem;
	};

	$.bb.ar.removeItem = function(param) {
		var $container;
		if(!$.contains($(param.container),$(param.link))){
			$container = $(param.link).closest("."+CONST.AR_CLASS_CONTAINER);
		} else {
			$container = $(param.container);
		}
		
		var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
		if (isNaN(ignoreItems)) {
			ignoreItems = 0;
		}
		var $removeItem = $(param.link).closest("."+CONST.AR_CLASS_ITEM);
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
			
			var $newItem ;
			if (param.isReserved) {
				var templateId = $removeItem.attr(CONST.ATTR_ITEM_TEMPLATE);
				var $template;
				if(!templateId) {
					$template = $("#" + $container.attr("id") + "-template");
				} else {
					$template = $("#"+templateId);
				}
				if ($container.find("tr").size() <= ignoreItems) {
					$newItem = $($.bb.ar.template($template.html()));
					$newItem.attr(CONST.ATTR_ITEM_TEMPLATE,$removeItem.attr(CONST.ATTR_ITEM_TEMPLATE));
					$container.append($newItem);
				}
			}
			$.bb.ar.defaultCallback($container,CONST.DIRECTION_REMOVE,$removeItem);

			if(typeof param.callback=="function"){
				param.callback($container,CONST.DIRECTION_REMOVE,$removeItem,param);
			}
			$.bb.ar.recalculateItemIndex($container,$removeItem.attr(CONST.ATTR_ITEM_GROUPID));
			$.bb.ar.renameAttributes($container);
			if (param.isReserved) {
				if ($newItem) {
					$.bb.ar.defaultCallback($container,CONST.DIRECTION_ADD,$newItem,param);
					if(typeof $.namespace(callback)=="function"){
						$.namespace(callback)($container,CONST.DIRECTION_ADD,$newItem,param);
					}
				}
			}
		}
		return $removeItem;
	};
	$.bb.ar.recalculateItemIndex = function(container,groupId) {
		var $container=$(container);
		if($container.hasClass(CONST.AR_CLASS_CONTAINER)){
			var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
			var itemIndexClass;
			var indexChangeCallback = $container.attr(CONST.ATTR_INDEXCHANGE_CALLBACK);
			if(isNaN(ignoreItems)) {
				ignoreItems = 0;
			}
			
			$container.find(">*").each(function(i) {
				if(i>=ignoreItems){
					$(this).attr(CONST.ATTR_ITEM_INDEX,i-ignoreItems);
					$(this).find("input."+CONST.AR_CLASS_ITEMINDEX).val(i+1);
				}
			});
			
			$.bb.ar.recalculateGroupIndex($container.find(">."+CONST.AR_CLASS_ITEM),groupId,indexChangeCallback);
		}
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
			inGroupSelector = "["+CONST.ATTR_ITEM_GROUPID+"='"+groupId+"']";
		} else {
			inGroupSelector = "["+CONST.ATTR_ITEM_GROUPID+"=''],*:not(["+CONST.ATTR_ITEM_GROUPID+"])";
		}
		
		$groupScope.filter(inGroupSelector).each(function(i) {
			if(typeof $.namespace(indexChangeCallback)=="function"){
				 $.namespace(indexChangeCallback)($groupScope,this,$(this).attr(ATTR_ITEM_GROUPINDEX),indexPrefix+(i+1));
			}
			var $scope = $(this).nextAll("["+CONST.ATTR_ITEM_GROUPID+"='"+$(this).attr(CONST.ATTR_ITEM_GROUPINDEX)+"']");
			$scope.attr(CONST.ATTR_ITEM_GROUPID,indexPrefix+(i+1));
			$(this).attr(CONST.ATTR_ITEM_GROUPINDEX,indexPrefix+(i+1));
			
			$(this).find("*:not(input)."+CONST.AR_CLASS_GROUPID).html(indexPrefix+(i+1));
			$(this).find("input."+CONST.AR_CLASS_GROUPID).val(groupId);
			$(this).find("*:not(input)."+CONST.AR_CLASS_GROUPINDEX).html(indexPrefix+(i+1));
			$(this).find("input."+CONST.AR_CLASS_GROUPINDEX).val(indexPrefix+(i+1));
			
			$.bb.ar.recalculateGroupIndex($scope,$(this).attr(CONST.ATTR_ITEM_GROUPINDEX),indexChangeCallback);
		});
	};
	$.bb.ar.renameAttributes = function(container) {
		var $container=$(container);
		var dataSelector = ">."+CONST.AR_CLASS_DATAITEM;
		var ignoreItems = parseInt($container.attr(CONST.ATTR_IGNORE_ITEMS));
		var treeLevel = parseInt($container.attr(CONST.ATTR_TREE_LEVEL));
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

								for(var j=treeLevel-1;j>-1;j--) {
									tempTable=$(tempTable).parents("."+CONST.AR_CLASS_CONTAINER+":first");
									replacements[j] ='[' + tempTable.parents("."+CONST.AR_CLASS_DATAITEM+":first").attr(CONST.ATTR_ITEM_INDEX)+ ']';
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
	$.bb.ar.defaultCallback = function(){};
	$.bb.ar.setTemplateFunction = function(templateFunc){
		$.bb.ar.template = function(template,data){
			return templateFunc(template,data);
		};
	};
	$.bb.ar.initialize = function(){
		$(function(){
			if(!$.bb.ar.template){
				$.bb.ar.template = function(template,data){
					return $(template.html());
				};
			}
		});
	};
})(jQuery);