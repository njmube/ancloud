<%@include file="/WEB-INF/views/include.jsp" %>

<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-info-circle"></i>
					<span>Account information</span>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="accountForm" cssClass="form-horizontal bb-form" action="${basePath }/admin/account/register" method="POST">
						<spring:bind path="accountForm.userName">
							<div class="form-group  ${status.error?'has-error':'' }">
								<label for="userName " class="col-md-2">User name</label> 
								<div class="input-group col-md-10">
									<form:input path="userName" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-user"></i></span>
								</div>
								<form:errors path="userName"  cssClass="help-block col-md-12" />
							</div>
						</spring:bind>
						<spring:bind path="accountForm.password">
							<div class="form-group ${status.error?'has-error':'' }">
								<label for="password" class="col-md-2">Password</label> 
								<div class="input-group col-md-10">
									<form:password path="password" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								</div>
								<form:errors path="password"  cssClass="help-block col-md-12" />
							</div>
						</spring:bind>
						<spring:bind path="accountForm.reenterPassword">
							<div class="form-group ${status.error?'has-error':'' }">
								<label for="password " class="col-md-2">Re-enter password</label> 
								<div class="input-group col-md-10">
									<form:password path="reenterPassword" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								</div>
								<form:errors  path="reenterPassword" cssClass="help-block col-md-12" />
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
							<label for="enabled" class="col-md-2">Enabled</label> 
							<div class="input-group col-md-10">
								<form:checkbox path="enabled" cssClass="checkbox" />
							</div>
							<form:errors path="enabled" cssClass="help-block col-md-12" />
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
							<div class="form-group">
								<label for="role" class="col-md-2">Role</label> 
								<div class="input-group col-md-10">
									<form:input path="role" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="received" class="col-md-2">Recieved</label> 
								<div class="input-group col-md-10">
									<form:input path="received" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="lookAfter" class="col-md-2">Look after</label>
								<div class="input-group col-md-10">
									<form:input path="lookAfter" cssClass="form-control" />
									<span class="input-group-addon"><i class="fa fa-circle-thin"></i></span>
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
									<form:input path="received" cssClass="form-control" />
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
					</form:form>
				</div>
			</div>