<tiles:insertDefinition name="default">
	<tiles:putAttribute name="content-body">
		<div class="register-box">
			<div class="register-logo">
				<a href="${basePath}/"><b><spring:message code="sc.sys.00001" /></b></a>
			</div>
			<div class="register-box-body">
				<div class="social-auth-links text-center">
					<form action="${basePath}/connect/facebook"
						method="POST">
						<input type="hidden" name="scope" value="public_profile " />
						<button type="submit"
							class="btn btn-block btn-social btn-facebook btn-flat">
							<i class="fa fa-facebook"></i>&nbsp;<spring:message code="sc.sys.00010" />
						</button>
					</form>
				</div>
				<p class=" text-center"><spring:message code="sc.sys.00011" /></p>
				<form action="${basePath }/register" method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" placeholder="<spring:message code="sc.sys.00012" />">
						<span class="glyphicon glyphicon-user form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="email" class="form-control" placeholder="<spring:message code="sc.sys.00013" />">
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" placeholder="<spring:message code="sc.sys.00014" />">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" placeholder="<spring:message code="sc.sys.00015" />"> 
						<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-xs-8">
							<div class="checkbox icheck">
								<label> 
									<input type="checkbox"> <spring:message code="sc.sys.00016" /> 
									<a href="#"><spring:message code="sc.sys.00017" /></a>
								</label>
							</div>
						</div>
						<div class="col-xs-4">
							<button type="submit" class="btn btn-primary btn-block btn-flat">
								<spring:message code="sc.sys.00018" />
							</button>
						</div>
					</div>
				</form>
				<a href="${basePath }/login" class="text-center"><spring:message code="sc.sys.00019" /></a>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


