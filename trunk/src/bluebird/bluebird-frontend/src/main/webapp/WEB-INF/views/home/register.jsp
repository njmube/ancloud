<tiles:insertDefinition name="default">
	<tiles:putAttribute name="content-body">
		<div class="signup-box">
			<bb:messages name="messages"></bb:messages>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><spring:message code="sc.sys.00018" />
						<div class="lost-password">
							<a href="#"><spring:message code="sc.sys.00003" /></a><br>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="register-box-body">
						<form action="${basePath }/register" method="post">
							<div class="input-group has-feedback">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-user"></i>
								</span>
								<input type="text" class="form-control" placeholder="<spring:message code="sc.sys.00012" />">
							</div>
							<div class="input-group has-feedback">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-envelope"></i>
								</span>
								<input type="email" class="form-control" placeholder="<spring:message code="sc.sys.00013" />">
							</div>
							<div class="input-group has-feedback">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-lock"></i>
								</span>
								<input type="password" class="form-control" placeholder="<spring:message code="sc.sys.00014" />">
							</div>
							<div class="input-group has-feedback">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-log-in"></i>
								</span>
								<input type="password" class="form-control" placeholder="<spring:message code="sc.sys.00015" />"> 
							</div>
							<div class="input-group has-feedback">
								<div class="checkbox icheck">
									<label> 
										<input type="checkbox"> <spring:message code="sc.sys.00016" /> 
										<a href="#"><spring:message code="sc.sys.00017" /></a>
									</label>
								</div>
							</div>
							<div class="input-group has-feedback">
								<div>
									<button type="submit" class="btn btn-info btn-block">
										<spring:message code="sc.sys.00018" />
									</button>
								</div>
								<form action="${basePath}/connect/facebook" method="POST">
									<input type="hidden" name="scope" value="public_profile " />
									<button type="submit"
										class="btn btn-primary">
										<i class="fa fa-facebook"></i>&nbsp;<spring:message code="sc.sys.00010" />
									</button>
								</form>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


