(function($) {
	"use strict";

	$.namespace("$.bb.common");
	$.fn.initializeAutocomplete = function() {
		return this.each(function(){
			var $this = $(this);
			var substringMatcher = function(strs) {
				return function findMatches(q, cb) {
					var matches, substringRegex, substrRegex;

					// an array that will be populated with substring matches
					matches = [];

					// regex used to determine if a string contains the substring
					// `q`
					substrRegex = new RegExp(q, 'i');

					// iterate through the pool of strings and for any string that
					// contains the substring `q`, add it to the `matches` array
					$.each(strs, function(i, str) {
						if (substrRegex.test(str)) {
							matches.push(str);
						}
					});

					cb(matches);
				};
			};

			var states = [ 'Alabama', 'Alaska', 'Arizona', 'Arkansas',
					'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida',
					'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa',
					'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland',
					'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi',
					'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
					'New Jersey', 'New Mexico', 'New York', 'North Carolina',
					'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania',
					'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee',
					'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington',
					'West Virginia', 'Wisconsin', 'Wyoming' ];
			var dataSource = new Bloodhound({
				datumTokenizer : Bloodhound.tokenizers.obj.whitespace('value'),
				queryTokenizer : Bloodhound.tokenizers.whitespace,
				remote : {
					url : CONTEXT_PATH + $this.data("sourcePath"),
					wildcard : '%QUERY',
					prepare : function(query, settings) {
						settings.type = "GET";
						settings.contentType = "application/json; charset=UTF-8";
						settings.headers = {"X-CSRF-TOKEN":""};
						var parameters = $this.data("parameter");
						parameters[$this.data("queryStringName")] = query;
						settings.data = "parameter="+encodeURIComponent(JSON.stringify(parameters));
						return settings;
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
					empty : [
							'<div class="empty-message">',
							'Nothing found!',
							'</div>' ].join('\n'),
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
			}).on('typeahead:selected', 
				function(e, item) {
					var $input = $(e.target);
					$input.closest(".twitter-typeahead").next("input[type=hidden]").val(JSON.stringify(item));
					$input.data("selectedItem",item);
				}
			).blur(function(e) {
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
