<%@include file="/WEB-INF/views/include.jsp" %>
<c:set var="adminResourcePath" scope="request">${resourceBasePath}/nimda</c:set>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Medtech</title>
<link rel="icon" href="${adminResourcePath}/img/favicon.ico?v=1" />
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<script type="text/javascript">var CONTEXT_PATH = '${basePath}';</script>
<link rel="stylesheet" href="${resourceBasePath}/plugin/font-awesome.css?v=1">
<link rel="stylesheet" href="${resourceBasePath}/plugin/wfmi-style.css?v=1">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bb-medical.css?v=1">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bootstrap.css?v=1">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bootstrap.submenu.css?v=1">
<link rel="stylesheet" href="${resourceBasePath}/plugin/flag/flag-icon.css?v=1">
<link rel="stylesheet" href="${resourceBasePath}/plugin/typeahead.css?v=1">
<link rel="stylesheet" href="${adminResourcePath}/css/core/bluebird.css?v=1">
<link rel="stylesheet" href="${adminResourcePath}/css/core/bluebird.blue-light.css?v=1">
<link rel="stylesheet" href="${adminResourcePath}/css/app/bootstrap.bluebird.css?v=1">
<link rel="stylesheet" href="${adminResourcePath}/css/app/bootstrap.override.css?v=1">
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
<c:set var="layout" value="sidebar-mini fixed"></c:set>
<body class="hold-transition skin-blue-light ${layout}">
	<div class="wrapper">
		<c:if test="${!fn:contains(layout,'layout-top-nav') }">
			<tiles:insertAttribute name="page-header" />
			<tiles:insertAttribute name="page-main-sidebar" />
			<div class="content-wrapper">
				<section class="content-header">
					<tiles:insertAttribute name="content-header" ignore="true"/>
				</section>
				<section class="content clearfix">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<bb:messages name="messages"></bb:messages>
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
	<script src="${resourceBasePath}/plugin/jquery.js?v=1"></script>
	<script src="${resourceBasePath}/plugin/bootstrap.js?v=1"></script>
	<script src="${resourceBasePath}/plugin/bootstrap.submenu.js?v=1"></script>
	<script src="${resourceBasePath}/plugin/jquery.slimscroll.js?v=1"></script>
	<script src="${resourceBasePath}/plugin/handlebars.js?v=1"></script>
	<script src="${resourceBasePath}/plugin/typeahead.bundle.js?v=1"></script>
	<script src="${resourceBasePath}/plugin/angular.js?v=1" type="text/javascript"></script>
	<script src="${resourceBasePath}/plugin/angular-router.js?v=1" type="text/javascript"></script>
	<script src="${adminResourcePath}/js/core/bluebird.js?v=1"></script>
	<script src="${adminResourcePath}/js/core/bluebird.common.js?v=1"></script>
	<script src="${adminResourcePath}/js/core/bluebird.ar.js?v=1"></script>
	
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




