<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ejukate</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${resourceBasePath}/plugin/font-awesome.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bootstrap.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/icheck/all.css">
<link rel="stylesheet" href="${resourceBasePath}/css/core/bluebird.css">
<link rel="stylesheet" href="${resourceBasePath}/css/core/bluebird.purple-light.css">
<link rel="stylesheet" href="${resourceBasePath}/css/app/bootstrap.override.css">
<link rel="stylesheet" href="${resourceBasePath}/css/app/bootstrap.bluebird.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition">
	<div class="wrapper none-sidebar">
		<div class="content-wrapper">
			<section class="content">
				<tiles:insertAttribute name="content-body" />
			</section>
			<tiles:insertAttribute name="content-footer" />
		</div>
	</div>
	<script src="${resourceBasePath}/plugin/jquery.js"></script>
	<script src="${resourceBasePath}/plugin/icheck/icheck.js"></script>
	<script src="${resourceBasePath}/plugin/bootstrap.js"></script>
	<script src="${resourceBasePath}/js/core/bluebird.js"></script>
	<script src="${resourceBasePath}/js/core/bluebird.ar.js"></script>
	<script>
		$(function() {
			$.bb.initialize();
			$('.checkbox input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
	<tiles:insertAttribute name="page-script" ignore="true" />
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>




