<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><spring:message code="sc.sys.00001" /></title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<script type="text/javascript">
		var CONTEXT_PATH = "${pageContext.request.contextPath}";
		var CURRENT_PROJECT_ID = "${sessionScope.CURRENT_PROJECT.id}";
</script>
<link rel="stylesheet" href="${resourceBasePath}/plugin/font-awesome.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bootstrap.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bootstrap.submenu.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/flag/flag-icon.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/typeahead.css">
<link rel="stylesheet" href="${resourceBasePath}/css/core/ancloud.css">
<link rel="stylesheet" href="${resourceBasePath}/css/core/skin-red.css">
<link rel="stylesheet" href="${resourceBasePath}/css/core/skin-red-light.css">
<link rel="stylesheet" href="${resourceBasePath}/css/app/bootstrap.ancloud.css">
<link rel="stylesheet" href="${resourceBasePath}/css/app/bootstrap.override.css">
<tiles:insertAttribute name="page-style" ignore="true" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<tiles:insertAttribute name="page-stylelink" ignore="true" />
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
<%-- <c:set var="layout" value="layout-top-nav"></c:set> --%>
<body class="hold-transition skin-red fixed ${layout}">
	<div class="wrapper">
		<c:if test="${!fn:contains(layout,'layout-top-nav') }">
			<tiles:insertAttribute name="page-header" />
			<tiles:insertAttribute name="page-main-sidebar" />
			<div class="content-wrapper">
				<section class="content-header">
					<tiles:insertAttribute name="content-header" ignore="true"/>
				</section>
				<section class="content" style="height:">
					<bb:messages name="messages"></bb:messages>
					<tiles:insertAttribute name="content-body" ignore="true"/>
				</section>
				<tiles:insertAttribute name="content-footer" />
			</div>
<%-- 			<tiles:insertAttribute name="page-control-sidebar" /> --%>
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
					<tiles:insertAttribute name="content-footer" ignore="true"/>
				</div>
			</div>
		</c:if>
	</div>
	<script src="${resourceBasePath}/plugin/jquery.js"></script>
	<script src="${resourceBasePath}/plugin/bootstrap.js"></script>
	<script src="${resourceBasePath}/plugin/bootstrap.submenu.js"></script>
	<script src="${resourceBasePath}/plugin/jquery.slimscroll.js"></script>
	<script src="${resourceBasePath}/plugin/handlebars.js"></script>
	<script src="${resourceBasePath}/plugin/typeahead.bundle.js"></script>
	<script src="${resourceBasePath}/js/core/ancloud.js"></script>
	<script src="${resourceBasePath}/js/core/ancloud.common.js"></script>
	<script src="${resourceBasePath}/js/core/ancloud.ar.js"></script>
	<script src="${resourceBasePath}/plugin/angular.js" type="text/javascript"></script>
	<script src="${resourceBasePath}/plugin/angular-router.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(function() {
			$.bb.initialize();
			$.bb.ar.setTemplateFunction(function(template,data){
				return Handlebars.compile(template)(data);
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




