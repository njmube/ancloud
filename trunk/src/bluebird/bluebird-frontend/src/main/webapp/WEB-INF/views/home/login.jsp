<tiles:insertDefinition name="default">
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="login-box">
			<div class="login-logo">
				<a href="${basePath}/"><b><spring:message code="sc.sys.00001" /></b></a>
			</div>
			<div class="login-box-body">
				<div class="social-auth-links text-center">
					<form action="${basePath}/connect/facebook" method="POST">
						<input type="hidden" name="scope" value="public_profile " />
						<button type="submit" class="btn btn-block btn-social btn-facebook btn-flat">
							<i class="fa fa-facebook"></i> <spring:message code="sc.sys.00009" />
						</button>
					</form>
				</div>
				<p class="text-center"><spring:message code="sc.sys.00011" /></p>
				<form:form action="${basePath}/login" method="POST" >
					<div class="form-group has-feedback">
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
						<input type="text" class="form-control" placeholder="Email" name="userName" value="${userName }">
					</div>
					<div class="form-group has-feedback">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
						<input type="password" class="form-control" placeholder="Password" name="password"  value="${password }">
						<div class="lost-password">
							<a href="#"><spring:message code="sc.sys.00005" /></a><br>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="checkbox icheck">
								<label><input type="checkbox">&nbsp;<spring:message code="sc.sys.00008" /></label>
							</div>
						</div>
						<div class="col-xs-12">
							<button type="submit" class="btn btn-primary btn-block btn-flat"><spring:message code="sc.sys.00003" /></button>
						</div>
					</div>
				</form:form>
				<spring:message code="sc.sys.00007" /><a href="#" class="text-center"><spring:message code="sc.sys.00006" /></a>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>




