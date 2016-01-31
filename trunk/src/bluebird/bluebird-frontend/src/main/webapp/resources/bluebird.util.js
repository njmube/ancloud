jQuery.namespace = function() {
	var a = arguments, o = null, i, j, d;
	for (i = 0; i < a.length; i = i + 1) {
		if (a[i]) {
			d = a[i].split(".");
			o = window;
			for (j = 0; j < d.length; j = j + 1) {
				o[d[j]] = o[d[j]] || {};
				o = o[d[j]];
			}
		}
	}
	return o;
};
(function($) {
	"use strict";
	
	$.namespace("$.bb.util");
	$.setting = {};
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
