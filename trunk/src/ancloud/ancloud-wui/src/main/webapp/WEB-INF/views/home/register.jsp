<%@include file="/WEB-INF/views/include.jsp" %>
<c:set var="resourceBasePath" scope="request">/resources</c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>MedTech</title>
<link rel="icon" href="${resourceBasePath}/img/favicon.ico?v=1" />
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
					<div class="signup-box">
					<bb:messages name="messages"></bb:messages>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<span>Registration</span>
						</div>
						<div class="panel-body">
							<form:form modelAttribute="accountForm" cssClass="form-horizontal bb-form" action="${basePath }/register" method="POST">
								<spring:bind path="accountForm.userName">
									<div class="form-group  ${status.error?'has-error':'' }">
										<label for="userName " class="col-md-4 col-sm-5">User name</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="userName" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-user"></i></span>
										</div>
										<form:errors path="userName"  cssClass="help-block col-md-12" />
									</div>
								</spring:bind>
								<spring:bind path="accountForm.password">
									<div class="form-group ${status.error?'has-error':'' }">
										<label for="password" class="col-md-4 col-sm-5">Password</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:password path="password" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-lock"></i></span>
										</div>
										<form:errors path="password"  cssClass="help-block col-md-12" />
									</div>
								</spring:bind>
								<spring:bind path="accountForm.reenterPassword">
									<div class="form-group ${status.error?'has-error':'' }">
										<label for="reenterPassword" class="col-md-4 col-sm-5">Confirm password</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:password path="reenterPassword" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-lock"></i></span>
										</div>
										<form:errors path="password"  cssClass="help-block col-md-12" />
									</div>
								</spring:bind>
								<div class="form-group">
									<label for="name" class="col-md-4 col-sm-5">Full name</label> 
									<div class="input-group col-md-8 col-sm-7">
										<form:input path="name" cssClass="form-control" />
										<span class="input-group-addon"><i class="fa fa-user"></i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="email" class="col-md-4 col-sm-5">Email address</label> 
									<div class="input-group col-md-8 col-sm-7">
										<form:input path="email" cssClass="form-control" />
										<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
									</div>
									<form:errors path="email" cssClass="help-block col-md-12" />
								</div>
								<div class="form-group">
									<label for="accountType" class="col-md-4 col-sm-5">Account type</label> 
									<div class="input-group col-md-8 col-sm-7">
										<form:select path="accountType"  cssClass="form-control" onchange="accountTypeSelectChanged(this);">
											<option value="1">Doctor</option>
											<option value="3" selected>Patient</option>
										</form:select>
									</div>
								</div>
								<div id="doctor_info" style="display:none">
									<div class="form-group">
										<label for="department" class="col-md-4 col-sm-5">Department</label>
										<div class="input-group col-md-8 col-sm-7"> 
											<form:input path="department" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="designation" class="col-md-4 col-sm-5">Designation</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="designation" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="allocation" class="col-md-4 col-sm-5">Allocation</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="allocation" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
								</div>
								<div id="nurse_info" style="display:none">
									<div class="form-group">
										<label for="wristbandId" class="col-md-4 col-sm-5">Wrist band id</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="wristbandId" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-hand-o-up"></i></span>
										</div>
									</div>
								</div>
								<div id="patient_info" style="display:none">
									<div class="form-group">
										<label for="ward" class="col-md-4 col-sm-5">Weight</label>
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="weight" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="ward" class="col-md-4 col-sm-5">Height</label>
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="height" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="pasId" class="col-md-4 col-sm-5">PAS id</label>
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="pasId" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="heartdoId" class="col-md-4 col-sm-5">Heartdo Id</label>
										<div class="input-group col-md-8 col-sm-7"> 
											<form:input path="heartdoId" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="bed" class="col-md-4 col-sm-5">Bed</label> 
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="bed" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="ward" class="col-md-4 col-sm-5">Ward</label>
										<div class="input-group col-md-8 col-sm-7">
											<form:input path="ward" cssClass="form-control" />
											<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
					<div class="form-group btn-group col-md-12 bb-action-group-footer">
						<div class="col-md-4 col-sm-5">
							<button type="submit" form="accountForm" class="btn btn-primary bb-button">Register</button>
						</div>
						<div class="col-md-4 col-sm-5"></div>
						<div class="col-md-4 col-sm-5"></div>
						<div class="col-md-4 col-sm-5"></div>
						<div class="col-md-4 col-sm-5"></div>
						<div class="col-md-4 col-sm-5"></div>
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
	<script type="text/javascript">
			function accountTypeChanged(accountType){
				$("#doctor_info").hide();
				$("#nurse_info").hide();
				$("#patient_info").hide();
				switch(accountType){
				case '0':
					break;
				case '1':
					$("#doctor_info").show();
					break;
				case '2':
					$("#nurse_info").show();
					break;
				case '3':
					$("#patient_info").show();
					break;
				}
			}
			function accountTypeSelectChanged(select){
				accountTypeChanged($(select).val());
			}
			$(function(){
				accountTypeChanged($("select[name=accountType]").val());
			});
		</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>