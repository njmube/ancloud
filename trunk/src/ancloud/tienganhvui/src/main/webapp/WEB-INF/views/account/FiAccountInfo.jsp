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
				$("select[name=accountType]").prop("disabled",true);
				accountTypeChanged($("[name=accountType]").val());
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3>Account management</h3>
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
						<div class="form-group  ${status.error?'has-error':'' }">
							<label for="userName " class="col-md-2">User name:</label> 
							<div class="input-group col-md-10">
								&nbsp;${account.userName }
							</div>
						</div>
						<div class="form-group ${status.error?'has-error':'' }">
							<label for="password" class="col-md-2">Password:</label> 
							<div class="input-group col-md-10">
								******
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-md-2">Full name</label> 
							<div class="input-group col-md-10">
									&nbsp;${account.name }
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-md-2">Email address:</label> 
							<div class="input-group col-md-10">
									&nbsp;${account.email }
							</div>
						</div>
						<div class="form-group">
							<label for="accountStatus" class="col-md-2">Status</label> 
							<div class="input-group col-md-10">
									&nbsp;${account.accountStatus }
							</div>
						</div>
						<div class="form-group">
							<label for="accountType" class="col-md-2">Account type</label> 
							<input type="hidden" name="accountType" value="${account.accountType }"/>
							<div class="input-group col-md-10">
								&nbsp;${account.accountType}
							</div>
						</div>
						<div id="doctor_info" style="display:none">
							<div class="form-group">
								<label for="department" class="col-md-2">Department</label>
								<div class="input-group col-md-10">
									&nbsp;${account.department }
								</div>
							</div>
							<div class="form-group">
								<label for="designation" class="col-md-2">Designation</label> 
								<div class="input-group col-md-10">
									&nbsp;${account.designation }
							</div>
							</div>
							<div class="form-group">
								<label for="allocation" class="col-md-2">Allocation:</label> 
								<div class="input-group col-md-10">
									&nbsp;${account.allocation }
								</div>
							</div>
						</div>
						<div id="nurse_info" style="display:none">
							<div class="form-group">
								<label for="wristbandId" class="col-md-2">Wrist band id</label> 
								<div class="input-group col-md-10">
									&nbsp;${account.wristbandId }
								</div>
							</div>
						</div>
						<div id="patient_info" style="display:none">
							<div class="form-group">
								<label for="ward" class="col-md-2">Weight</label>
								<div class="input-group col-md-10">
									&nbsp;${account.weight }
								</div>
							</div>
							<div class="form-group">
								<label for="ward" class="col-md-2">Height</label>
								<div class="input-group col-md-10">
									&nbsp;${account.height }
								</div>
							</div>
							<div class="form-group">
								<label for="pasId" class="col-md-2">PAS id</label>
								<div class="input-group col-md-10">
									&nbsp;${account.pasId }
								</div>
							</div>
							<div class="form-group">
								<label for="heartdoId" class="col-md-2">Heartdo Id</label>
								<div class="input-group col-md-10">
									&nbsp;${account.heartdoId }
								</div>
							</div>
							<div class="form-group">
								<label for="bed" class="col-md-2">Bed</label> 
								<div class="input-group col-md-10">
									&nbsp;${account.bed }
								</div>
							</div>
							<div class="form-group">
								<label for="ward" class="col-md-2">Ward</label>
								<div class="input-group col-md-10">
									&nbsp;${account.ward }
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
