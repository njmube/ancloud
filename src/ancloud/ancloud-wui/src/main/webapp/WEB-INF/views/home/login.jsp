<%@include file="/WEB-INF/views/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>MedTech</title>
<link rel="icon" href="${resourcePath}/nimda/img/favicon.ico?v=1" />
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${resourcePath}/plugin/font-awesome.css">
<link rel="stylesheet" href="${resourcePath}/plugin/bootstrap.css">
<link rel="stylesheet" href="${resourcePath}/nimda/css/core/bluebird.css">
<link rel="stylesheet" href="${resourcePath}/nimda/css/skin/blue.css">
<link rel="stylesheet" href="${resourcePath}/nimda/css/app/bootstrap.bluebird.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
    <div class="container">
        <section class="content">
            <div class="signin-box">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4><b>Welcome to Admin dashboard</b></h4>
                    </div>
                    <div class="panel-body">
                        <bb-ex:messages messages="${messages}"></bb-ex:messages>
                        <form:form action="${basePath}/login" method="POST">
                            <div class="form-group">
                                <div class="input-group has-feedback col-md-12 col-sm-12 col-xs-12">
                                    <input type="text" class="form-control" placeholder="User name" name="userName" value="${userName }">
                                    <span class="input-group-addon"><i class=" glyphicon glyphicon-user"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group has-feedback col-md-12 col-sm-12  col-xs-12">
                                    <input type="password" class="form-control" placeholder="Password" name="password"  value="${password }">
                                    <span class="input-group-addon"><i class=" glyphicon glyphicon-lock"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group pull-right">
                                    <span>
                                        <button type="submit" class="btn btn-success">Login</button>
                                    </span>
                                    <span>
<%--                                                <a href="${basePath }/register">Don't have an account? Create one now.</a> --%>
                                    </span>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script src="${resourcePath}/plugin/jquery.js"></script>
    <script src="${resourcePath}/plugin/jquery.slimscroll.js"></script>
    <script src="${resourcePath}/plugin/bootstrap.js"></script>
    <script src="${resourcePath}/nimda/js/core/bluebird.js"></script>
</body>
</html>
