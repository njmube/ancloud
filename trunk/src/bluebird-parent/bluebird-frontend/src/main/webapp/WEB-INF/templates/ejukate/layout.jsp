<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><spring:message code="sc.sys.00001" /></title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="${resourceBasePath}/bootstrap.css">
<link rel="stylesheet" href="${resourceBasePath}/AdminLTE/css/AdminLTE.css">
<link rel="stylesheet" href="${resourceBasePath}/AdminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${resourceBasePath}/font-awesome.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<tiles:insertAttribute name="page-stylelink" ignore="true" />
</head>
<!--
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
-->
<c:if test="${not empty sessionScope.pageLayout }">
	<c:set var="layout" value="${sessionScope.pageLayout }"></c:set>
</c:if>
<c:if test="${empty sessionScope.pageLayout }">
	<c:set var="layout" value="layout-top-nav"></c:set>
</c:if>
<body class="hold-transition skin-purple-light ${layout}">
	<div class="wrapper">
		<tiles:insertAttribute name="page-header" />
		<tiles:insertAttribute name="page-main-sidebar" />

		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<tiles:insertAttribute name="content-header"/>
				</section>
				<section class="content">
					<bb:messages name="messages"></bb:messages>
					<tiles:insertAttribute name="content-body" />
				</section>
			</div>
		</div>
		<tiles:insertAttribute name="page-footer" />
		<tiles:insertAttribute name="page-control-sidebar" />
	</div>
	<script src="${resourceBasePath}/jquery.js"></script>
	<script src="${resourceBasePath}/bootstrap.js"></script>
	<script src="${resourceBasePath}/jquery.slimscroll.js"></script>
	<script src="${resourceBasePath}/AdminLTE/js/app.js"></script>
	
	<script type="text/javascript">
	    $(function() {
	    });
	</script>
	<tiles:insertAttribute name="page-script" ignore="true" />
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>




