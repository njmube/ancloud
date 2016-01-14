<tiles:insertDefinition name="default">
	<tiles:putAttribute name="content-body">
		<div class="login-box">
			<div class="login-logo">
				<a href="${pageContext.request.contextPath}/"><b>Ejukate</b></a>
			</div>
			<div class="login-box-body">
				<div class="social-auth-links text-center">
					<form action="${pageContext.request.contextPath}/connect/facebook"
						method="POST">
						<input type="hidden" name="scope" value="public_profile " />
						<button type="submit"
							class="btn btn-block btn-social btn-facebook btn-flat">
							<i class="fa fa-facebook"></i> Sign in using Facebook
						</button>
					</form>
				</div>
				<p class=" text-center">- OR -</p>
				<form:form action="${pageContext.request.contextPath}/login" method="POST">
					<div class="form-group has-feedback">
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
						<input type="text" class="form-control" placeholder="Email" name="userName">
					</div>
					<div class="form-group has-feedback">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
						<input type="password" class="form-control" placeholder="Password" name="password">
						<div class="lost-password">
							<a href="#">Forgot password</a><br>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="checkbox icheck">
								<label><input type="checkbox">&nbsp;Remember Me</label>
							</div>
						</div>
						<div class="col-xs-12">
							<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
						</div>
					</div>
				</form:form>
				
				Bạn chưa có tài khoản? <a href="register.html" class="text-center">Đăng ký ngay</a>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>




