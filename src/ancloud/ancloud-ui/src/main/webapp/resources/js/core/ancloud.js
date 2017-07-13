(function($){
	"use strict";
	$.namespace = function() {
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
	$.namespace("$.bb");
	$.bb.options = {
			// Add slimscroll to navbar menus
			// This requires you to load the slimscroll plugin
			// in every page before app.js
			navbarMenuSlimscroll : true,
			navbarMenuSlimscrollWidth : "5px", // The width of the scroll bar
			navbarMenuHeight : "200px", // The height of the inner menu
			// General animation speed for JS animated elements such as box
			// collapse/expand and
			// sidebar treeview slide up/down. This options accepts an integer as
			// milliseconds,
			// 'fast', 'normal', or 'slow'
			animationSpeed : 500,
			// Sidebar push menu toggle button selector
			sidebarToggleSelector : "[data-toggle='offcanvas']",
			// Activate sidebar push menu
			sidebarPushMenu : true,
			// Activate sidebar slimscroll if the fixed layout is set (requires
			// SlimScroll Plugin)
			sidebarSlimScroll : true,
			// Enable sidebar expand on hover effect for sidebar mini
			// This option is forced to true if both the fixed layout and sidebar mini
			// are used together
			sidebarExpandOnHover : false,
			// BoxRefresh Plugin
			enableBoxRefresh : true,
			// Bootstrap.js tooltip
			enableBSToppltip : true,
			BSTooltipSelector : "[data-toggle='tooltip']",
			// Enable Fast Click. Fastclick.js creates a more
			// native touch experience with touch devices. If you
			// choose to enable the plugin, make sure you load the script
			// before ancloud's app.js
			enableFastclick : true,
			// Control Sidebar Options
			enableControlSidebar : true,
			controlSidebarOptions : {
				// Which button should trigger the open/close event
				toggleBtnSelector : "[data-toggle='control-sidebar']",
				// The sidebar selector
				selector : ".control-sidebar",
				// Enable slide over content
				slide : true
			},
			// Define the set of colors to use globally around the website
			colors : {
				lightBlue : "#3c8dbc",
				red : "#f56954",
				green : "#00a65a",
				aqua : "#00c0ef",
				yellow : "#f39c12",
				blue : "#0073b7",
				navy : "#001F3F",
				teal : "#39CCCC",
				olive : "#3D9970",
				lime : "#01FF70",
				orange : "#FF851B",
				fuchsia : "#F012BE",
				purple : "#8E24AA",
				maroon : "#D81B60",
				black : "#222222",
				gray : "#d2d6de"
			},
			// The standard screen sizes that bootstrap uses.
			// If you change these in the variables.less file, change
			// them here too.
			screenSizes : {
				xs : 480,
				sm : 768,
				md : 992,
				lg : 1200
			}
		};
	$.bb.layout = {
		activate : function() {
			var thiz = this;
			thiz.fix();
			thiz.fixSidebar();
			$(window, ".wrapper").resize(function() {
				thiz.fix();
				thiz.fixSidebar();
			});
		},
		fix : function() {
			// Get window height and the wrapper height
			var neg = $('.main-header').outerHeight();
			var window_height = $(document).outerHeight();
			var sidebar_height = $(".sidebar").height();
			// Set the min-height of the content and sidebar based on the
			// the height of the document.
			if ($("body").hasClass("fixed")) {
				$(".content-wrapper, .right-side").css('min-height',window_height - neg);
				$(".content-wrapper .content").css('min-height',window_height - neg - $(".content-wrapper .content-header").outerHeight() - $(".content-wrapper .content-footer").outerHeight());
			} else {
				var postSetWidth;
				if (window_height >= sidebar_height) {
					$(".content-wrapper, .right-side").css('min-height', window_height - neg);
					$(".content-wrapper .content").css('min-height',window_height - neg - $(".content-wrapper .content-header").outerHeight() - $(".content-wrapper .content-footer").outerHeight());
					postSetWidth = window_height - neg;
				} else {
					$(".content-wrapper, .right-side").css('min-height', sidebar_height);
					$(".content-wrapper .content").css('min-height',sidebar_height - $(".content-wrapper .content-header").outerHeight() - $(".content-wrapper .content-footer").outerHeight());
					postSetWidth = sidebar_height;
				}

				// Fix for the control sidebar height
				var controlSidebar = $($.bb.options.controlSidebarOptions.selector);
				if (typeof controlSidebar !== "undefined") {
					if (controlSidebar.height() > postSetWidth){
						$(".content-wrapper, .right-side").css('min-height', controlSidebar.height());
						$(".content-wrapper .content").css('min-height',controlSidebar.height() - $(".content-wrapper .content-header").outerHeight() - $(".content-wrapper .content-footer").outerHeight());
					}
				}
			}
		},
		fixSidebar : function() {
			// Make sure the body tag has the .fixed class
			if (!$("body").hasClass("fixed")) {
				if (typeof $.fn.slimScroll != 'undefined') {
					$(".sidebar").slimScroll({
												destroy : true
											}).height("auto");
				}
				return;
			} else if (typeof $.fn.slimScroll == 'undefined' && window.console) {
				console.error("Error: the fixed layout requires the slimscroll plugin!");
			}
			// Enable slimscroll for fixed layout
			if ($.bb.options.sidebarSlimScroll) {
				if (typeof $.fn.slimScroll != 'undefined') {
					// Destroy if it exists
					$(".sidebar").slimScroll({
												destroy : true
											}).height("auto");
					// Add slimscroll
					$(".sidebar").slimscroll({
												height : ($(window).height() - $(".main-header").height()) + "px",
												color : "rgba(0,0,0,0.2)",
												size : "3px"
											});
				}
			}
		}
	};

	/*
	 * PushMenu() ========== Adds the push menu functionality to the sidebar.
	 * 
	 * @type Function @usage: $.bb.pushMenu("[data-toggle='offcanvas']")
	 */
	$.bb.pushMenu = {
		activate : function(toggleBtn) {
			// Get the screen sizes
			var screenSizes = $.bb.options.screenSizes;

			// Enable sidebar toggle
			$(document).on(
					'click',
					toggleBtn,
					function(e) {
						e.preventDefault();

						// Enable sidebar push menu
						if ($(window).width() > (screenSizes.sm - 1)) {
							if ($("body").hasClass('sidebar-collapse')) {
								$("body").removeClass('sidebar-collapse')
										.trigger('expanded.pushMenu');
							} else {
								$("body").addClass('sidebar-collapse').trigger(
										'collapsed.pushMenu');
							}
						}
						// Handle sidebar push menu for small screens
						else {
							if ($("body").hasClass('sidebar-open')) {
								$("body").removeClass('sidebar-open')
										.removeClass('sidebar-collapse')
										.trigger('collapsed.pushMenu');
							} else {
								$("body").addClass('sidebar-open').trigger(
										'expanded.pushMenu');
							}
						}
					});

			$(".content-wrapper").click(
					function() {
						// Enable hide menu when clicking on the content-wrapper
						// on small screens
						if ($(window).width() <= (screenSizes.sm - 1)
								&& $("body").hasClass("sidebar-open")) {
							$("body").removeClass('sidebar-open');
						}
					});

			// Enable expand on hover for sidebar mini
			if ($.bb.options.sidebarExpandOnHover
					|| ($('body').hasClass('fixed') && $('body').hasClass(
							'sidebar-mini'))) {
				this.expandOnHover();
			}
		},
		expandOnHover : function() {
			var thiz = this;
			var screenWidth = $.bb.options.screenSizes.sm - 1;
			// Expand sidebar on hover
			$('.main-sidebar').hover(
					function() {
						if ($('body').hasClass('sidebar-mini')
								&& $("body").hasClass('sidebar-collapse')
								&& $(window).width() > screenWidth) {
							thiz.expand();
						}
					},
					function() {
						if ($('body').hasClass('sidebar-mini')
								&& $('body').hasClass(
										'sidebar-expanded-on-hover')
								&& $(window).width() > screenWidth) {
							thiz.collapse();
						}
					});
		},
		expand : function() {
			$("body").removeClass('sidebar-collapse').addClass(
					'sidebar-expanded-on-hover');
		},
		collapse : function() {
			if ($('body').hasClass('sidebar-expanded-on-hover')) {
				$('body').removeClass('sidebar-expanded-on-hover').addClass(
						'sidebar-collapse');
			}
		}
	};
	/*
	 * Tree() ====== Converts the sidebar into a multilevel tree view menu.
	 * 
	 * @type Function @Usage: $.bb.tree('.sidebar')
	 */
	$.bb.tree = function(menu) {
		var thiz = this;
		var animationSpeed = $.bb.options.animationSpeed;
		$(menu).on(
			'click',
			'li a',
			function(e) {
				// Get the clicked link and the next element
				var $this = $(this);
				var checkElement = $this.next();

				// Check if the next element is a menu and is visible
				if ((checkElement.is('.treeview-menu'))
						&& (checkElement.is(':visible'))
						&& (!$('body').hasClass('sidebar-collapse'))) {
					// Close the menu
					checkElement.slideUp(animationSpeed, function() {
						checkElement.removeClass('menu-open');
						// Fix the layout in case the sidebar stretches over
						// the height of the window
						// thiz.layout.fix();
					});
					checkElement.parent("li").removeClass("active");
				}
				// If the menu is not visible
				else if ((checkElement.is('.treeview-menu'))
						&& (!checkElement.is(':visible'))) {
					// Get the parent menu
					var parent = $this.parents('ul').first();
					// Close all open menus within the parent
					var ul = parent.find('ul:visible').slideUp(
							animationSpeed);
					// Remove the menu-open class from the parent
					ul.removeClass('menu-open');
					// Get the parent li
					var parent_li = $this.parent("li");

					// Open the target menu and add the menu-open class
					checkElement.slideDown(animationSpeed, function() {
						// Add the class active to the parent li
						checkElement.addClass('menu-open');
						parent.find('li.active').removeClass('active');
						parent_li.addClass('active');
						// Fix the layout in case the sidebar stretches over
						// the height of the window
						thiz.layout.fix();
					});
				}
				// if this isn't a link, prevent the page from being
				// redirected
				if (checkElement.is('.treeview-menu')) {
					e.preventDefault();
				}
			}
		);
	};

	/*
	 * ControlSidebar ============== Adds functionality to the right sidebar
	 * 
	 * @type Object @usage $.bb.controlSidebar.activate(options)
	 */
	$.bb.controlSidebar = {
		// instantiate the object
		activate : function() {
			// Get the object
			var thiz = this;
			// Update options
			var o = $.bb.options.controlSidebarOptions;
			// Get the sidebar
			var sidebar = $(o.selector);
			// The toggle button
			var btn = $(o.toggleBtnSelector);

			// Listen to the click event
			btn.on('click', function(e) {
				e.preventDefault();
				// If the sidebar is not open
				if (!sidebar.hasClass('control-sidebar-open')
						&& !$('body').hasClass('control-sidebar-open')) {
					// Open the sidebar
					thiz.open(sidebar, o.slide);
				} else {
					thiz.close(sidebar, o.slide);
				}
			});

			// If the body has a boxed layout, fix the sidebar bg position
			var bg = $(".control-sidebar-bg");
			thiz._fix(bg);

			// If the body has a fixed layout, make the control sidebar fixed
			if ($('body').hasClass('fixed')) {
				thiz._fixForFixed(sidebar);
			} else {
				// If the content height is less than the sidebar's height,
				// force max height
				if ($('.content-wrapper, .right-side').height() < sidebar.height()) {
					thiz._fixForContent(sidebar);
				}
			}
		},
		// Open the control sidebar
		open : function(sidebar, slide) {
			// Slide over content
			if (slide) {
				sidebar.addClass('control-sidebar-open');
			} else {
				// Push the content by adding the open class to the body instead
				// of the sidebar itself
				$('body').addClass('control-sidebar-open');
			}
		},
		// Close the control sidebar
		close : function(sidebar, slide) {
			if (slide) {
				sidebar.removeClass('control-sidebar-open');
			} else {
				$('body').removeClass('control-sidebar-open');
			}
		},
		_fix : function(sidebar) {
			var thiz = this;
			if ($("body").hasClass('layout-boxed')) {
				sidebar.css('position', 'absolute');
				sidebar.height($(".wrapper").height());
				$(window).resize(function() {
					thiz._fix(sidebar);
				});
			} else {
				sidebar.css({
					'position' : 'fixed',
					'height' : 'auto'
				});
			}
		},
		_fixForFixed : function(sidebar) {
			sidebar.css({
				'position' : 'fixed',
				'max-height' : '100%',
				'overflow' : 'auto',
				'padding-bottom' : '50px'
			});
		},
		_fixForContent : function(sidebar) {
			$(".content-wrapper, .right-side").css('min-height',sidebar.height());
			$(".content-wrapper .content").css('min-height',sidebar.height() - $(".content-wrapper .content-header").outerHeight() - $(".content-wrapper .content-footer").outerHeight());
		}
	};
	$.bb.initialize = function(){
		// Fix for IE page transitions
		$("body").removeClass("hold-transition");

		// Extend options if external options exist
		if (typeof ancloudOptions !== "undefined") {
			$.extend(true, $.bb.options, ancloudOptions);
		}

		// Easy access to options
		var o = $.bb.options;

		// Activate the layout maker
		$.bb.layout.activate();

		// Enable sidebar tree view controls
		$.bb.tree('.sidebar');

		// Enable control sidebar
		if (o.enableControlSidebar) {
			$.bb.controlSidebar.activate();
		}

		// Add slimscroll to navbar dropdown
		if (o.navbarMenuSlimscroll && typeof $.fn.slimscroll != 'undefined') {
			$(".navbar .menu").slimscroll({
				height : o.navbarMenuHeight,
				alwaysVisible : false,
				size : o.navbarMenuSlimscrollWidth
			}).css("width", "100%");
		}

		// Activate sidebar push menu
		if (o.sidebarPushMenu) {
			$.bb.pushMenu.activate(o.sidebarToggleSelector);
		}

		// Activate Bootstrap tooltip
		if (o.enableBSToppltip) {
			$('body').tooltip({
				selector : o.BSTooltipSelector
			});
		}

		// Activate fast click
		if (o.enableFastclick && typeof FastClick != 'undefined') {
			FastClick.attach(document.body);
		}
		
		
		if(typeof $().submenu != 'undefined'){
			$("[data-submenu]").submenu();
		}
		
		// modules init
		for(var propertyName in $.bb){
			if(typeof $.bb[propertyName] == 'object' && typeof $.bb[propertyName]['initialize'] == 'function'){
				$.bb[propertyName]['initialize']();
			}
		}
	};
})(jQuery);
