(function($) {
	"use strict";

	$.namespace("$.bb.common");
	$.fn.initializeAutocomplete = function() {
		return this.each(function(){
			var $this = $(this);

			var dataSource = new Bloodhound({
				datumTokenizer : Bloodhound.tokenizers.obj.whitespace('value'),
				queryTokenizer : Bloodhound.tokenizers.whitespace,
				remote : {
					url : CONTEXT_PATH + $this.data("sourcePath"),
					wildcard : '%QUERY',
					prepare : function(query, settings) {
//						settings.type = "POST";
//						settings.contentType = "application/json; charset=UTF-8";
//						settings.headers = {"X-CSRF-TOKEN":""};
						var parameters = $this.data("parameter");
						parameters[$this.data("queryStringName")] = query;
						settings.data = "parameter="+encodeURIComponent(JSON.stringify(parameters));
						return settings;
					},
					transform : function(response){
						return response.content;
					}
				},
				prefetch : {
					url : CONTEXT_PATH + $this.data("sourcePath"),
					wildcard : '%QUERY',
					prepare : function(query, settings) {
						var parameters = $this.data("parameter");
						parameters[$this.data("queryStringName")] = query;
						settings.data = "parameter="+encodeURIComponent(JSON.stringify(parameters));
						return settings;
					},
					transform : function(response){
						return response.content;
					}
				}
			});
			$this.typeahead({
				hint : true,
				highlight : true,
				minLength : 0
			}, {
				source : dataSource,
				displayKey : $this.data("queryStringName"),
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
				},
				mustSelectItem : true 
			}).on('typeahead:initialized', function(e, item) {
				var $input = $(e.target);
				var $submitInput = $input.closest(".twitter-typeahead").next("input[type=hidden]");
				
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
					
			}).blur(function(e) {
				var selectedItem = $(e.target).data("selectedItem");
				var queryStringName = $this.data("queryStringName");
				var $input = $(e.target);
				if(selectedItem){
					if(queryStringName){
						if(selectedItem[queryStringName] != $(e.target).val()){
							$input.typeahead('val', '');
							$input.data("selectedItem",null);
						}
					} else {
						if(selectedItem != $(e.target).val()){
							$input.typeahead('val', '');
							$input.data("selectedItem",null);
						}
					}
				} else {
					$input.typeahead('val', '');
				}
			});
			
			return $this;
		});
	};
	$.bb.common.initialize = function(param) {
		$(".bb-autocomplete").initializeAutocomplete();
	};

})(jQuery);
