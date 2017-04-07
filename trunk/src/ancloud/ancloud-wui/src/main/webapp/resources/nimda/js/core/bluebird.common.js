(function($) {
	"use strict";
	
	$.namespace("$.bb.common");
	$.autocomplete = function(element, options) {
		// To avoid scope issues, use 'base' instead of 'this'
		// to reference this class from internal events and functions.
		var base = this;
		// Access to jQuery and DOM versions of element
		base.$element = $(element);
		base.element = element;
		
		base.defaultOptions = {
				submitProperty : base.$element.data("submitProperty"),
				queryStringProperty : base.$element.data("queryStringProperty"),
				sourcePath : base.$element.data("sourcePath"),
				parameter:base.$element.data("parameter"),
				queryStringProperty: base.$element.data("queryStringProperty"),
				displayProperties:base.$element.data("displayProperties"),
				mustMatch : base.$element.data("mustMatch"),
		};


		// Add a reverse reference to the DOM object
		if (base.$element.data("autocomplete"))
			base.$element.data("autocomplete").remove();
		base.$element.data("autocomplete", base);

		base.options = $.extend({
			submitProperty : '',
			queryStringProperty : '',
			sourcePath : '',
			parameter:{},
			queryStringProperty: '',
			displayProperties:'',
			mustMatch : true,
		}, base.defaultOptions, options);
		
		base.initialize = function() {
			var dataSource = new Bloodhound({
				identify: function(obj) { 
					return obj[base.options.submitProperty];
				},
				datumTokenizer : Bloodhound.tokenizers.whitespace,
				queryTokenizer : Bloodhound.tokenizers.whitespace,
				remote : {
					url : CONTEXT_PATH + base.options.sourcePath,
					wildcard : '%QUERY',
					prepare : function(query, settings) {
//						settings.type = "POST"; // JQuery ajax settings
						var parameters = base.options.parameter;
						parameters[base.options.queryStringProperty] = query;
						parameters['r'] = Math.random();
						settings.data = "parameter="+encodeURIComponent(JSON.stringify(parameters));
						return settings;
					},
					transform : function(response){
						return response.content;
					}
				},
				prefetch : {
					url : CONTEXT_PATH + base.options.sourcePath,
					prepare : function(settings) {
						var parameters = base.options.parameter;
						parameters[base.options.queryStringProperty] = "";
						settings.url += "?parameter="+encodeURIComponent(JSON.stringify(parameters))+"&r="+encodeURIComponent(Math.random())+"&size=1000";
						return settings;
					},
					transform : function(response){
						return response.content;
					}
				}
			});
			
			dataSource.clearPrefetchCache();
			dataSource.initialize();
			
			base.$element.typeahead({
				hint : false,
				highlight : true,
				minLength : 0,
			}, {
				source : dataSource,
				displayKey : base.options.queryStringProperty,
				//display: function(item){ return item.message;},
				limit : 100,//default : 5
				templates : {
					empty : ['<div class="empty-message">',
								'Nothing found!',
							'</div>' ].join('\r\n'),
					suggestion : function(data){
						var displayProperties = base.options.displayProperties.split(",");
						if(displayProperties){
							var templateString = "<div>";
							$.each(displayProperties,function(index, value){
								if(index == 0){
									templateString += "<strong>{{" + value + "}}</strong>";
								} else {
									templateString += " - {{" + value + "}}";
								}
							});
							templateString += "</div>";
							return Handlebars.compile(templateString)(data);
						}
					}
				}
			}).on('typeahead:selected', function(e, item) {
					var $input = $(e.target);
					var $submitInput = $input.closest(".twitter-typeahead").next("input[type=hidden]");
					var submitProperty = base.options.submitProperty;
					if(submitProperty) {
						if(typeof item[submitProperty] != "object" && typeof item[submitProperty] != "function"){
							$submitInput.val(item[submitProperty]);
						} else {
							$submitInput.val(JSON.stringify(item[submitProperty]));
						}
					} else {
						$submitInput.val(JSON.stringify(item));
					}
					$input.data("selectedItem",item);
					
			}).on('typeahead:idle', function(e, item) {
				var $input = $(e.target);
				var dataSource = dataSource;
				var selectedItem = $input.data("selectedItem");
				var queryStringProperty = base.options.queryStringProperty;
				var submitProperty = base.options.submitProperty;
				var mustMatch = $input.data("mustMatch") || true;
				
				var $submitInput = $input.closest(".twitter-typeahead").next("input[type=hidden]");
				if(selectedItem){
					if(queryStringProperty){
						if(selectedItem[queryStringProperty] != $(e.target).val()){
							$input.typeahead('val', '');
							$submitInput.val("");
							$input.data("selectedItem",null);
						}
					} else {
						if(selectedItem != $(e.target).val()){
							$input.typeahead('val', '');
							$submitInput.val("");
							$input.data("selectedItem",null);
						}
					}
				} else {
					if(!$input.val() || !$submitInput.val() &&
							mustMatch == "true"){
						$input.typeahead('val', '');
						$submitInput.val("");
					}
				}
			}).on('typeahead:change', function(e, item) {
			    // find whole selected object and save to $input's data object
				var $input = $(e.target);
				var dataSource = dataSource;
				var selectedItem = $(e.target).data("selectedItem");
				var queryStringProperty = base.options.queryStringProperty;
				var submitProperty = base.options.submitProperty;
				var mustMatch = base.options.mustMatch || true;
				var suggestions = base.$element.data("suggestions");
				var $submitInput = $input.closest(".twitter-typeahead").next("input[type=hidden]");
				if(!(!suggestions || suggestions.length <= 0)){
					for(var i=1;i<suggestions.length;i++){
						if(!selectedItem && suggestions[i][queryStringProperty] == $input.val()){
							$input.data("selectedItem",suggestions[i]);
							selectedItem = suggestions[i];
							$submitInput.val(suggestions[i][submitProperty]);
							break;
						}
					}
				}
				if((!selectedItem || selectedItem[submitProperty] != $submitInput.val()) &&
						mustMatch){
					$input.typeahead('val', '');
					$submitInput.val("");
				}
			}).on(
			'typeahead:render',
			function(e, suggestions, b, c) {
				base.$element.data("suggestions", arguments);
				var $dropDownMenu = $(e.target).nextAll(
						".tt-menu:first")[0];
			});
		};
		
		base.initialize();
	};
	
	$.fn.autocomplete = (function(options){
		return this.each(function(){
			$.autocomplete(this,options);
			return $(this);
		});
	});
	
	$.fn.confirmation = function(parameters) {
        return $(this).each(function() {
        	var $link = $(this);
        	$link.click(function() {
                $("#confirmationBox").find(".modal-title").text(parameters.title);
                $("#confirmationBox").find(".modal-body").text(parameters.content);
                $("#confirmationBox").find(".modal-footer .btn-primary").off();
                $("#confirmationBox").find(".modal-footer .btn-primary").click(function(){
                	window.location = $link.attr("href");
                });
                $("#confirmationBox").modal('show');
                return false;
            });
        });
    };
	
	$.bb.common.initialize = function(param) {
		$(".bb-autocomplete").autocomplete();
		$("a.bb-confirm-delete").confirmation({
		    title : "Confirm",
		    content : "Are you sure you want to delete it?"
		});
		$("a.bb-confirm-approve").confirmation({
		    title : "Confirm",
		    content : "Are you sure you want to approve this account?"
		});
		$("a.bb-confirm-reject").confirmation({
		    title : "Confirm",
		    content : "Are you sure you want to reject this account?"
		});
		
		$(".bb-datepicker").each(function() {
		    $(this).daterangepicker({
		    	autoUpdateInput: false,
		        singleDatePicker: true,
		        showDropdowns: true,
		        locale : {
					format : 'DD/MM/YYYY'
				}
		    },function(start, end, label) {
				$(this.element).val(start.format('DD/MM/YYYY'));
			});
		});
		
		$(".bb-daterangepicker").each(function() {
            $(this).daterangepicker({
                timePicker: true, 
                timePickerIncrement: 30,
                locale : {
					format : 'DD/MM/YYYY HH:mm:ss'
				}
            });
		});
		
		
		$(".bb-daterangepicker").each(function() {
            $(this).daterangepicker({
                timePicker: true, 
                timePickerIncrement: 30,
                locale : {
					format : 'DD/MM/YYYY HH:mm:ss'
				}
            });
		});
		
		$(".bb-table-resizable").colResizable({
			liveDrag:true,
		    gripInnerHtml:"<div class='grip'></div>", 
		    draggingClass:"dragging",
		});
		
		$.bb.ar.defaultCallback = function(container,direction,item){
			if(direction == $.bb.ar.CONST.DIRECTION_ADD){
				$(item).find(".bb-autocomplete").initializeAutocomplete();
			}
		};
//			$("#periodDate").daterangepicker({
//			autoApply: true,
//			autoUpdateInput: true,
//			minDate:moment(),
//			endDate : moment(),
//			locale : {
//				format : 'DD/MM/YYYY'
//			},
//			showCustomRangeLabel : false,
//			alwaysShowCalendars : true
//		},function(start, end, label) {
//			$("#fromDate").val(start.format('DD/MM/YYYY'));
//			$("#toDate").val(end.format('DD/MM/YYYY'));
//			$("#periodDate").val(start.format('DD/MM/YYYY') + " - " + end.format('DD/MM/YYYY'));
//		});
//		if($("#fromDate").val()){
//			$('#periodDate').data('daterangepicker').setStartDate($("#fromDate").val());
//		}
//		if($("#toDate").val()){
//			$('#periodDate').data('daterangepicker').setEndDate($("#toDate").val());
//		}
	};

})(jQuery);
