<c:set var="resourceBasePath" scope="request">${pageContext.request.contextPath}/resources/default</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ejukate</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="${resourceBasePath}/bootstrap.css">
<link rel="stylesheet" href="${resourceBasePath}/AdminLTE/css/AdminLTE.css">
<link rel="stylesheet" href="${resourceBasePath}/AdminLTE/css/skins/skin-blue.min.css">
<link rel="stylesheet" href="${resourceBasePath}/AdminLTE/css/skins/skin-red.min.css">
<link rel="stylesheet" href="${resourceBasePath}/font-awesome.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<tiles:insertAttribute name="additionalHeader" ignore="true" />
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
<body class="hold-transition skin-red sidebar-mini">
	<div class="wrapper">
		<tiles:insertAttribute name="page-header" />
		<tiles:insertAttribute name="page-main-sidebar" />

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Page Header<small>Optional description</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>
			<section class="content">
				<tiles:insertAttribute name="page-body" />
			</section>
		</div>
		<tiles:insertAttribute name="page-footer" />
		<tiles:insertAttribute name="page-control-sidebar" />
	</div>
	<script src="${pageContext.request.contextPath}/resources/default/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/default/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/default/AdminLTE/js/app.js"></script>
	<script type="text/javascript">
	    $(function() {
	      $(window).scroll(function() {
	
// 	        if ($(this).scrollTop() > 0) {
// 	          $('.main-footer').hide();
// 	        } else {
// 	          $('.main-footer').show();
// 	        }
	      });
	    });
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>




