<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<div class="col-md-6"><h3>Account permission <small>Search</small></h3></div>
		<div class="btn-group col-md-6">
			<div class="col-md-6"></div>
			<div class="col-md-6" style="text-align: right">
				<a href="${basePath}/admin/account-permission/register"
					class="btn bb-fa-btn fa-plus">
					Register new
				</a>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-search"></i>
					<span><bb-ex:message code="sc.common.00008"></bb-ex:message></span>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="accountPermissionSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/admin/account-permission/search" method="POST">
						<div class="bb-form-group">
							<div class="col-md-2 bb-form-group-label">
								<label class="control-label" for="account.name">
									Account
								</label>
							</div>
							<div class="col-md-4 bb-form-group-control input-group">
								<input name="account.name" 
										value="${accountPermissionSearchForm.account.name}" 
										type="text" 
										class="bb-autocomplete form-control" 
										data-source-path="/autocomplete/account" 
										data-query-string-property="name"
										data-display-properties="name"
										data-submit-property="uid"></input>
								<span class="input-group-addon"><i class="fa fa-arrow-down"></i></span>
								<input name="account.id" value="${accountPermissionSearchForm.account.id}" type="hidden"></input>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-2 bb-form-group-label">
	<%-- 						<bb-ex:codelist-select codelistName="language" path="userName"></bb-ex:codelist-select> --%>
							</div>
							<div class="col-md-4 bb-form-group-control"></div>
							<div class="col-md-2 bb-form-group-label"></div>
							<div class="col-md-4 bb-form-group-control"></div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="accountPermissionSearchForm" class="btn btn-primary bb-button"><bb-ex:message code="sc.common.00004"></bb-ex:message></button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-list"></i>
					<span></span><bb-ex:message code="sc.common.00006"></bb-ex:message></span>
					&nbsp;<span class="badge">${page.totalElements}</span>
				</div>
				<div class="panel-body">
					<c:if test="${page.totalElements > 0}">
							<table class="table table-bordered table-condensed  bb-table-action">
								<colgroup>
									<col>
									<col>
									<col width="15%">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th><bb-ex:message code="sc.common.00007" /></th>
										<th>
											<bb-ex:sort	formName="accountPermissionSearchForm" 
														sortProperty="account.name" 
														label="Account name"/>
										</th>
										<th>Permission</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.content}" var="accountPermission"
										varStatus="status">
										<tr>
											<td data-title="<bb-ex:message code='sc.common.00007' />">
												${page.number * page.size + status.index + 1}</td>
											<td data-title="<bb-ex:message code='sc.accountPermission.00006' />">
												<a href="${basePath}/admin/accountPermission/show-info?id=${accountPermission.id}">
													${accountPermission.account.name }
												</a>
											</td>
											<td data-title="Code">
												${accountPermission.permission.code }
											</td>
											<td>
												<div class="btn-group">
													<a class="btn btn-success bb-fa-btn fa-edit" href="${basePath}/admin/account-permission/modify?id=${accountPermission.id}"></a>
													<button type="button"
														class="btn btn-success dropdown-toggle"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false">
														<span class="caret"></span> 
													</button>
													<ul class="dropdown-menu">
														<li>
															<a href="${basePath}/admin/account-permission/getQrCode?id=${accountPermission.id}">
																<i class="fa fa-download"></i>Download QR code
															</a>
														</li>
														<li class="divider"></li>
														<li>
															<a class="bb-confirm-delete" href="${basePath}/admin/account-permission/delete?id=${accountPermission.id}">
																<i class="fa fa-close"></i>Delete
															</a>
														</li>
													</ul>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						<bb-ex:pagination formName="accountPermissionSearchForm"></bb-ex:pagination>
					</c:if>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
</tiles:insertDefinition>
