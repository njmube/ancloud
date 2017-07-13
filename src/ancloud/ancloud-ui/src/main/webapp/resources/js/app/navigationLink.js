(function($){
	"use strict";

	$.namespace("$.bb.navigationLink");

	$.bb.navigationLink.removeLink = function(e){
		$.bb.ar.removeItem({
							link : e.target
						});
	};
	
	$.bb.navigationLink.addLink = function(e){
		$.bb.ar.addItem({
							containerId:"navigationLinkTable",
							templateId:"navigationLinkTemplate",
							callback : $.bb.navigationLink.addLinkCallback,
						});
	};

	$.bb.navigationLink.addLinkBelow = function(e){
		$.bb.ar.addItem({
							container:$(e.target).closest('tbody'),
							templateId:"navigationLinkTemplate",
							templateData:{groupId:$(e.target).closest(".ar-item").data("ar-groupid")},
							callback : $.bb.navigationLink.addLinkCallback,
							position:{anchor:$(e.target).closest(".ar-item"),string:"after"}
						});
	};

	$.bb.navigationLink.addNestedLinkBelow = function(e){
		$.bb.ar.addItem({
							container:$(e.target).closest('tbody'),
							templateId:"navigationLinkTemplate",
							templateData:{groupId:$(e.target).closest(".ar-item").data("ar-groupindex")},
							callback : $.bb.navigationLink.addLinkCallback,
							position:{anchor:$(e.target).closest(".ar-item"),string:"after"}
						});
	}

	$.bb.navigationLink.addLinkCallback = function(container,direction,item){
		if(direction == "add"){
			var $item = $(item);
			$item.find(".bb-navigationLink-addLinkButton").click($.bb.navigationLink.addLinkBelow);
			$item.find(".bb-navigationLink-addNestedLinkButton").click($.bb.navigationLink.addNestedLinkBelow);
			//$.bb.navigationLink.restyleRow($item);
			
			$item.find(".bb-navigationLink-removeLinkButton").click($.bb.navigationLink.removeLink);
		}
	};
	
	$.bb.navigationLink.restyleRow = function(row){
		var firstTh = $(row).closest("table").find("th:first-child");
		var rowFirstTd = $(row).find("td:first-child");
		
		var paddingDivStyle = {
			width : firstTh.width() + "px",
			padding : firstTh.css("padding") + "px",
			'border-right' : firstTh.css("boder-right-style"),
			height : '100%'
		};
		
		var controlDivStyle = {
				'min-width' : (rowFirstTd.width() - firstTh.outerWidth()) + "px",
				padding : firstTh.css("padding") + "px",
				'float':'right'
			};
		
		var $containerCell = $(row).find("td:first-child");
		$containerCell.css({
							'padding-top':'0px',
							'padding-bottom':'0px'
						});
		
		var $indexDiv = $(row).find(".ar-groupIndex");
		var $controlDiv = $indexDiv.next("div");
		$controlDiv.css(controlDivStyle);
		$indexDiv.css(paddingDivStyle);
		
	};
	
	$.bb.navigationLink.initialize = function(){
		$("#navigationLinkTable>tbody>tr").each(function(i){
			var $item = $(this);
			$item.find(".bb-navigationLink-addLinkButton").click($.bb.navigationLink.addLinkBelow);
			$item.find(".bb-navigationLink-addNestedLinkButton").click($.bb.navigationLink.addNestedLinkBelow);
			//$.bb.navigationLink.restyleRow($item);
			
			$item.find(".bb-navigationLink-removeLinkButton").click($.bb.navigationLink.removeLink);
		});
		$(".bb-table-action-plus .bb-navigationLink-addLinkButton").click($.bb.navigationLink.addLink);
	};
	
})(jQuery);