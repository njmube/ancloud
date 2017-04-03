<%@include file="/WEB-INF/views/include.jsp" %>
<c:set var="resourceBasePath" scope="request">/resources</c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>MedTech</title>
<link rel="icon" href="${resourceBasePath}/nimda/img/favicon.ico?v=1" />
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${resourceBasePath}/plugin/font-awesome.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/bootstrap.css">
<link rel="stylesheet" href="${resourceBasePath}/plugin/icheck/all.css">
<link rel="stylesheet" href="${resourceBasePath}/nimda/css/core/bluebird.css">
<link rel="stylesheet" href="${resourceBasePath}/nimda/css/core/bluebird.blue-light.css">
<link rel="stylesheet" href="${resourceBasePath}/nimda/css/app/bootstrap.override.css">
<link rel="stylesheet" href="${resourceBasePath}/nimda/css/app/bootstrap.bluebird.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue-light layout-top-nav fixed">
	<div class="wrapper">
		<div class="content-wrapper">
			<div class="container">
				<section class="content">
					<div class="signin-box">
						<div class="panel panel-primary">
							<div class="panel-heading">
								Login
							</div>
							<div class="panel-body">
								<bb:messages name="messages"></bb:messages>
								<form:form action="${basePath}/login" method="POST">
									<div class="form-group">
										<label>User name</label>
										<div class="input-group has-feedback col-md-12 col-sm-12 col-xs-12">
											<input type="text" class="form-control" placeholder="Username" name="userName" value="${userName }">
											<span class="input-group-addon"><i class=" glyphicon glyphicon-user"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label>Password</label>
										<div class="input-group has-feedback col-md-12 col-sm-12  col-xs-12">
											<input type="password" class="form-control" placeholder="Password" name="password"  value="${password }">
											<span class="input-group-addon"><i class=" glyphicon glyphicon-lock"></i></span>
										</div>
									</div>
									<div class="form-group">
										<div class="input-group">
											<span>
												<button type="submit" class="btn btn-success">Login</button>
											</span>
											<span>
												<a href="${basePath }/register">Don't have an account? Create yours now.</a>
											</span>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</section>
				<tiles:insertAttribute name="content-footer" ignore="true"/>
			</div>
		</div>
	</div>
	<script src="${resourceBasePath}/plugin/jquery.js"></script>
	<script src="${resourceBasePath}/plugin/icheck/icheck.js"></script>
	<script src="${resourceBasePath}/plugin/jquery.slimscroll.js"></script>
	<script src="${resourceBasePath}/plugin/bootstrap.js"></script>
	<script src="${resourceBasePath}/nimda/js/core/bluebird.js"></script>
	<script src="${resourceBasePath}/nimda/js/core/bluebird.ar.js"></script>
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
