(function($) {
	"use strict";

	$.namespace("$.bb.common");
	$.fn.initializeAutocomplete = function() {
		return this.each(function(){
			var $this = $(this);
			var pageSize = 100;
			var dataSource = new Bloodhound({
				identify: function(obj) { 
					return obj[$this.data("submitProperty")];
				},
				datumTokenizer : Bloodhound.tokenizers.whitespace,
				queryTokenizer : Bloodhound.tokenizers.whitespace,
				remote : {
					url : CONTEXT_PATH + $this.data("sourcePath"),
					wildcard : '%QUERY',
					prepare : function(query, settings) {
//						settings.type = "POST"; // JQuery ajax settings
						var parameters = $this.data("parameter");
						parameters[$this.data("queryStringProperty")] = query;
						parameters['r'] = Math.random();
						settings.data = "parameter="+encodeURIComponent(JSON.stringify(parameters));
						return settings;
					},
					transform : function(response){
						return response.content;
					}
				},
				prefetch : {
					url : CONTEXT_PATH + $this.data("sourcePath"),
					prepare : function(settings) {
						var parameters = $this.data("parameter");
						parameters[$this.data("queryStringProperty")] = "";
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
			
			$this.typeahead({
				hint : false,
				highlight : true,
				minLength : 0,
			}, {
				source : dataSource,
				displayKey : $this.data("queryStringProperty"),
				//display: function(item){ return item.message;},
				limit : 100,//default : 5
				templates : {
					empty : ['<div class="empty-message">',
								'Nothing found!',
							'</div>' ].join('\r\n'),
					suggestion : function(data){
						var displayProperties = $this.data("displayProperties").split(",");
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
			}).on('typeahead:render', function(e, suggestions,b,c) {
				$this.data("suggestions",arguments);
			}).on('typeahead:selected', function(e, item) {
					var $input = $(e.target);
					var $submitInput = $input.closest(".twitter-typeahead").next("input[type=hidden]");
					var submitProperty = $input.data("submitProperty");
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
				var queryStringProperty = $input.data("queryStringProperty");
				var submitProperty = $input.data("submitProperty");
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
				var $input = $(e.target);
				var dataSource = dataSource;
				var selectedItem = $(e.target).data("selectedItem");
				var queryStringProperty = $input.data("queryStringProperty");
				var submitProperty = $input.data("submitProperty");
				var mustMatch = $input.data("mustMatch") || true;
				var suggestions = $this.data("suggestions");
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
			});
			
//			var $input = $this;
//			var $submitInput = $input.closest(".twitter-typeahead").next("input[type=hidden]");
//			var item = dataSource.index.get([$submitInput.val()]);
//			if(item.length > 0) {
//				$input.val(item[0][$input.data("queryStringProperty")]);
//			}
			return $this;
		});
	};
	
	
	$.bb.common.initialize = function(param) {
		$(".bb-autocomplete").initializeAutocomplete();
		$.bb.ar.defaultCallback = function(container,direction,item){
			if(direction == $.bb.ar.CONST.DIRECTION_ADD){
				$(item).find(".bb-autocomplete").initializeAutocomplete();
			}
		}
	};

})(jQuery);
