<%@include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var timerStart = Date.now();
	console.log(timerStart);
</script>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<meta http-equiv="Content-Security-Policy" content="
        img-src 'self' data:;
        " />
<title>ancloud</title>
<link rel="icon" href="${imageResourcePath}/favicon.ico?v=1" />
<!-- Tell the browser to be responsive to screen width -->


<script type="text/javascript">var CONTEXT_PATH = '${basePath}';</script>
<link rel="stylesheet" href="${resourcePath}/admin.css?v=1">

<tiles:insertAttribute name="page-style-link" ignore="true" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<tiles:insertAttribute name="page-style" ignore="true" />
</head>
<%--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
--%>
<c:if test="${not empty sessionScope.pageLayout }">
	<c:set var="layout" value="${sessionScope.pageLayout }"></c:set>
</c:if>
<c:if test="${empty sessionScope.pageLayout }">
	<c:set var="layout" value="sidebar-mini"></c:set>
</c:if>
<c:set var="layout" value="sidebar-mini"></c:set>
<body class="hold-transition skin-blue-light ${layout}">
	<div class="wrapper">
		<c:if test="${!fn:contains(layout,'layout-top-nav') }">
			<tiles:insertAttribute name="page-header" />
			<tiles:insertAttribute name="page-main-sidebar" />
			<div class="content-wrapper">
				<section class="content-header">
					<tiles:insertAttribute name="content-header" ignore="true"/>
				</section>
				<section class="content">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<bb-ex:messages messages="${messages}"></bb-ex:messages>
					</div>
					<tiles:insertAttribute name="content-body" ignore="true"/>
				</section>
				<tiles:insertAttribute name="content-footer" />
			</div>
			<tiles:insertAttribute name="page-control-sidebar" />
		</c:if>
		<c:if test="${fn:contains(layout,'layout-top-nav') }">
			<tiles:insertAttribute name="page-header-top" />
			<div class="content-wrapper">
				<div class="container">
					<section class="content-header">
						<tiles:insertAttribute name="content-header" ignore="true"/>
					</section>
					<section class="content">
						<bb:messages name="messages"></bb:messages>
						<tiles:insertAttribute name="content-body" ignore="true"/>
					</section>
					<tiles:insertAttribute name="content-footer" />
				</div>
			</div>
		</c:if>
	</div>
	<div class="modal fade" id="confirmationBox">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Confirmation ?</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Confirm</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script src="${resourcePath}/admin.js?v=1"></script>
	<script src="${resourcePath}/jsMessageSource.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			console.log("Time until DOMready: ", Date.now() - timerStart);
		});
		$(window).load(function() {
			console.log("Time until everything loaded: ", Date.now() - timerStart);
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$.bb.initialize();
			$.bb.ar.setTemplateFunction(function(template,data){
				return Handlebars.compile(template)(data);
			});
			$(".btn-group").each(function(i){
				var $toggleButton = $(this).find(".dropdown-toggle");
				var $dropdownMenu = $(this).find("ul.dropdown-menu");
				if($dropdownMenu.length == 0 || $dropdownMenu.find("li").length==0){
					$toggleButton.prop("disabled",true);
				}
			});
		});
	</script>
	
	<tiles:insertAttribute name="page-template" ignore="true" />
	<tiles:insertAttribute name="page-script" ignore="true" />
	<%-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. --%>
</body>
</html>