<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="page-script">
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
				$("select[name=accountType]").prop("readonly",true);
				accountTypeChanged($("select[name=accountType]").val());
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3>Modify account</h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
					<div class="col-md-6" style="text-align: right">
						<a href="${basePath}/admin/account/search"
							class="btn bb-fa-btn fa-search">
							Search account
						</a>
					</div>
					
				
		</div>
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-info-circle"></i>
					<span>Account information</span>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="accountMForm" cssClass="form-horizontal bb-form" action="${basePath }/admin/account/modify" method="POST">
						<spring:bind path="accountMForm.userName">
							<div class="form-group  ${status.error?'has-error':'' }">
								<label for="userName " class="col-md-2">User name</label> 
								<div class="input-group col-md-10">
									<form:hidden path="accountType"/>
									<form:hidden path="id"/>
									<form:input path="userName" cssClass="form-control"/>
									<span class="input-group-addon"><i class="fa fa-user"></i></span>
								</div>
								<form:errors path="userName"  cssClass="help-block col-md-12" />
							</div>
						</spring:bind>
						<spring:bind path="accountMForm.password">
							<div class="form-group ${status.error?'has-error':'' }">
								<label for="password" class="col-md-2">Password</label> 
								<div class="input-group col-md-10">
									<form:password path="password" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								</div>
								<form:errors path="password"  cssClass="help-block col-md-12" />
							</div>
						</spring:bind>
						<spring:bind path="accountMForm.reenterPassword">
							<div class="form-group ${status.error?'has-error':'' }">
								<label for="reenterPassword" class="col-md-2">Confirm password</label> 
								<div class="input-group col-md-10">
									<form:password path="reenterPassword" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								</div>
								<form:errors path="password"  cssClass="help-block col-md-12" />
							</div>
						</spring:bind>
						<div class="form-group">
							<label for="name" class="col-md-2">Full name</label> 
							<div class="input-group col-md-10">
								<form:input path="name" cssClass="form-control" />
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-md-2">Email address</label> 
							<div class="input-group col-md-10">
								<form:input path="email" cssClass="form-control" />
								<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
							</div>
							<form:errors path="email" cssClass="help-block col-md-12" />
						</div>
						<div class="form-group">
							<label for="accountStatus" class="col-md-2">Status</label> 
							<div class="input-group col-md-10">
								<form:select path="accountStatus" cssClass="form-control">
									<form:option value="Enabled">Enabled</form:option>
									<form:option value="Disabled">Disabled</form:option>
								</form:select>
							</div>
							<form:errors path="accountStatus" cssClass="help-block col-md-12" />
						</div>
						<div class="form-group">
							<label for="accountType" class="col-md-2">Account type</label> 
							<div class="input-group col-md-10">
								<form:select path="accountType" cssClass="form-control" onchange="accountTypeSelectChanged(this);">
									<form:option value="0">Administrator</form:option>
									<form:option value="1">Doctor</form:option>
									<form:option value="2">Nurse</form:option>
									<form:option value="3">Patient</form:option>
								</form:select>
							</div>
						</div>
						<div id="doctor_info" style="display:none">
							<div class="form-group">
								<label for="department" class="col-md-2">Department</label>
								<div class="input-group col-md-10"> 
									<form:input path="department" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="designation" class="col-md-2">Designation</label> 
								<div class="input-group col-md-10">
									<form:input path="designation" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="allocation" class="col-md-2">Allocation</label> 
								<div class="input-group col-md-10">
									<form:input path="allocation" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
						</div>
						<div id="nurse_info" style="display:none">
							<div class="form-group">
								<label for="wristbandId" class="col-md-2">Wrist band id</label> 
								<div class="input-group col-md-10">
									<form:input path="wristbandId" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-hand-o-up"></i></span>
								</div>
							</div>
						</div>
						<div id="patient_info" style="display:none">
							<div class="form-group">
								<label for="ward" class="col-md-2">Weight</label>
								<div class="input-group col-md-10">
									<form:input path="weight" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="ward" class="col-md-2">Height</label>
								<div class="input-group col-md-10">
									<form:input path="height" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="pasId" class="col-md-2">PAS id</label>
								<div class="input-group col-md-10">
									<form:input path="wristbandId" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="heartdoId" class="col-md-2">Heartdo Id</label>
								<div class="input-group col-md-10"> 
									<form:input path="heartdoId" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="bed" class="col-md-2">Bed</label> 
								<div class="input-group col-md-10">
									<form:input path="bed" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="ward" class="col-md-2">Ward</label>
								<div class="input-group col-md-10">
									<form:input path="ward" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
						</div>
						<form:hidden path="version"/>
					</form:form>
				</div>
			</div>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="accountMForm" class="btn btn-primary bb-button">Save</button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
