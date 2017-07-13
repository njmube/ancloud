<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<div class="col-md-6">
			<h3>Account permission <small>Registration</small></h3>
		</div>
		<div class="form-group btn-group col-md-6 bb-action-group-footer">
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-6" style="text-align: right">
				<a href="${basePath}/admin/account-permission/search"
					class="btn bb-fa-btn fa-search">
					Search
				</a>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-info-circle"></i>
					<span>General information</span>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="accountPermissionForm" cssClass="form-horizontal bb-form" action="${basePath }/admin/account-permission/register" method="POST">
						<div class="form-group ${status.error?'has-error':'' }">
							<label for="account.id" class="col-md-2 col-xs-12">Account</label> 
							<div class="input-group col-md-10 col-xs-12">
								<input name="account.name" 
										value="${accountPermissionForm.account.name}" 
										type="text" 
										class="bb-autocomplete form-control" 
										data-source-path="/autocomplete/account" 
										data-query-string-property="name"
										data-display-properties="name"
										data-submit-property="uid"
										data-parameter='{}' />
								<span class="input-group-addon"><i class="fa fa-arrow-down"></i></span>
								<input name="account.id" value="${accountPermissionForm.account.id}" type="hidden"></input>
							</div>
							<form:errors path="account.id"  cssClass="text-red col-md-12" />
						</div>
						<div class="form-group ${status.error?'has-error':'' }">
							<label for="permission.id" class="col-md-2 col-xs-12">Permission</label> 
							<div class="input-group col-md-10 col-xs-12">
								<input name="permission.code" 
										value="${accountPermissionForm.permission.code}" 
										type="text" 
										class="bb-autocomplete form-control" 
										data-source-path="/autocomplete/permission" 
										data-query-string-property="code"
										data-display-properties="code"
										data-submit-property="uid"
										data-parameter='{}' />
								<span class="input-group-addon"><i class="fa fa-arrow-down"></i></span>
								<input name="permission.id" value="${accountPermissionForm.permission.id}" type="hidden"></input>
							</div>
							<form:errors path="permission.id"  cssClass="text-red col-md-12" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="accountPermissionForm" class="btn btn-primary bb-button">Save</button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
