
(function($) {
	"use strict";

	$.namespace("$.bb.util");
	$.bb.util.initialize = function(param) {

	};
	var escapseHtmlEntityMap = {
		"&" : "&amp;",
		"<" : "&lt;",
		">" : "&gt;",
		'"' : '&quot;',
		"'" : '&#39;',
		"/" : '&#x2F;'
	};
	$.bb.util.escapseHtml = function(string) {
		return String(string).replace(/[&<>"'\/]/g, function(s) {
			return escapseHtmlEntityMap[s];
		});
	}
})(jQuery);
