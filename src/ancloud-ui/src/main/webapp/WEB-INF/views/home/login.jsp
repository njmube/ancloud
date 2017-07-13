<tiles:insertDefinition name="default">
	<tiles:putAttribute name="content-body">
		<div class="signin-box">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><spring:message code="sc.sys.00003" />
						<div class="lost-password">
							<a href="#"><spring:message code="sc.sys.00005" /></a><br>
						</div>
					</div>
					
				</div>
				<div class="panel-body">
					<bb:messages name="messages"></bb:messages>
					<form:form action="${basePath}/login" method="POST">
						<div class="input-group has-feedback">
							<span class="input-group-addon"><i class=" glyphicon glyphicon-user"></i></span>
							<input type="text" class="form-control" placeholder="Username" name="userName" value="${userName }">
						</div>
						<div class="input-group has-feedback">
							<span class="input-group-addon"><i class=" glyphicon glyphicon-lock"></i></span>
							<input type="password" class="form-control" placeholder="Password" name="password"  value="${password }">
						</div>
						<div class="input-group">
							<div class="checkbox">
								<label><input type="checkbox">&nbsp;<spring:message code="sc.sys.00008" /></label>
							</div>
						</div>
						<div class="input-group">
							<div>
								<button type="submit" class="btn btn-success"><spring:message code="sc.sys.00003" /></button>
								<button type="submit" class="btn btn-primary"><spring:message code="sc.sys.00009" /></button>
							</div>
						</div>
						<div class="input-group">
							<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
								<spring:message code="sc.sys.00007" /><a href="${basePath}/register" class="text-center"><spring:message code="sc.sys.00006" /></a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>