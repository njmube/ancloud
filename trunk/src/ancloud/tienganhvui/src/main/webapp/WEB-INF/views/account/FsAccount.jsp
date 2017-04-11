<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<div class="col-md-6"><h3>Account <small>Search</small></h3></div>
		<div class="btn-group col-md-6">
			<div class="col-md-6"></div>
			<div class="col-md-6" style="text-align: right">
				<a href="${basePath}/admin/account/register"
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
					<span><spring:message code="sc.common.00008"></spring:message></span>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="accountSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/admin/account/search" method="POST">
						<div class="bb-form-group">
							<div class="col-md-2 bb-form-group-label">
								<label class="control-label" for="name">
									Name
								</label>
							</div>
							<div class="col-md-4 bb-form-group-control">
								<form:input path="name" cssClass="form-control" />
							</div>
							<div class="col-md-2 bb-form-group-label">
								<label class="control-label" for="status">
									Status
								</label>
							</div>
							<div class="col-md-4 bb-form-group-control">
								<form:select path="accountStatus" cssClass="form-control">
									<form:option value="">All</form:option>
									<form:option value="Enabled">Enabled</form:option>
									<form:option value="Disabled">Disabled</form:option>
									<form:option value="Pending">Pending</form:option>
								</form:select>
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
				<button type="submit" form="accountSearchForm" class="btn btn-primary bb-button"><spring:message code="sc.common.00004"></spring:message></button>
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
					<span></span><spring:message code="sc.common.00006"></spring:message></span>
					&nbsp;<span class="badge">${page.totalElements}</span>
				</div>
				<div class="panel-body">
					<c:if test="${page.totalElements > 0}">
						<div class="">
							<table class="table table-bordered table-condensed table-hover  bb-table-action">
								<colgroup>
									<col>
									<col>
									<col width="12%">
									<col width="12%">
									<col width="16%">
									<col width="10%">
									<col width="10%">
									<col>
								</colgroup>
								<thead class="thead-inverse">
									<tr>
										<th><spring:message code="sc.common.00007" /></th>
										<th>
											<bb-ex:sort page="${page }" 
														formName="accountSearchForm" 
														sortProperty="name" 
														label="Name"/>
										</th>
										<th>
											<bb-ex:sort page="${page }" 
														formName="accountSearchForm" 
														sortProperty="accountType" 
														label="Account type"/>
										</th>
										<th>
											<bb-ex:sort page="${page }" 
														formName="accountSearchForm" 
														sortProperty="createdDate" 
														label="Created date"/>
										</th>
										<th>
											<bb-ex:sort page="${page }" 
														formName="accountSearchForm" 
														sortProperty="lastUpdatedDate" 
														label="Last updated date"/>
										</th>
										<th><spring:message code="sc.account.00007" /></th>
										<th>
											<bb-ex:sort page="${page }" 
														formName="accountSearchForm" 
														sortProperty="accountStatus" 
														label="Status" />
										</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.content}" var="account"
										varStatus="status">
										<tr>
											<td data-title="<spring:message code='sc.common.00007' />">
												${page.number * page.size + status.index + 1}</td>
											<td data-title="Name">
												<a href="${basePath}/admin/account/show-info?id=${account.id}&accountType=${account.accountType}">
													${account.name }
												</a>
											</td>
											<td data-title="Account type">
												${account.accountType}
											</td>
											<td data-title="Created date"><bb-ex:formatDate jodaValue="${account.createdDate }" jodaEnabled="true" /></td>
											<td data-title="Last updated date"><bb-ex:formatDate jodaValue="${account.lastUpdatedDate }" jodaEnabled="true" /></td>
											<td data-title="<spring:message code='sc.account.00007' />">******</td>
											<td data-title="<spring:message code='sc.account.00011' />">
												${account.accountStatus}
												
											</td>
											<td>
												<div class="btn-group">
													<a class="btn btn-success bb-fa-btn fa-edit" href="${basePath}/admin/account/modify?id=${account.id}&accountType=${account.accountType}"></a>
													<button type="button"
														class="btn btn-success dropdown-toggle"
														data-toggle="dropdown">
														<span class="caret"></span> 
													</button>
													<ul class="dropdown-menu">
														<c:if test="${account.accountStatus eq 'Pending' }">
															<li>
																<a title="Approve"
																	class="bb-confirm-approve"
																	href="${basePath}/admin/account/approve?isApproval=1&id=${account.id}&version=${account.version}">
																	<i class="fa fa-check-circle"></i>Approve
																</a>
															</li>
															<li>
																<a title="Reject"
																	class="bb-confirm-reject"
																	href="${basePath}/admin/account/approve?isApproval=0&id=${account.id}&version=${account.version}">
																	<i class="fa fa-ban"></i>Reject
																</a>
															</li>
															<li class="divider"></li>
														</c:if>
														<li>
															<a href="${basePath}/admin/account-license/search?account.id=${account.id}&account.name=${bb:escape(account.name)}&fromDate=&toDate=">
																Show licenses
															</a>
														</li>
														<li>
															<a href="${basePath}/admin/account-license/register?account.id=${account.id}&account.name=${bb:escape(account.name)}">
																Add license
															</a>
														</li>
														<li class="divider"></li>
														<li>
															<a class="bb-confirm-delete" href="${basePath}/admin/account/delete?id=${account.id}&accountType=${account.accountType}">
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
						</div>
						<bb-ex:pagination formName="accountSearchForm" page="${page }"></bb-ex:pagination>
					</c:if>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
