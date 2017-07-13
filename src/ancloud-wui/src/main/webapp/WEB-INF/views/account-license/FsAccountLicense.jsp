<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header">
		<div class="col-md-6 col-xs-12"><h3>Account license <small>Search</small></h3></div>
		<div class="btn-group col-md-6 col-xs-12">
			<div class="col-md-6"></div>
			<div class="col-md-6" style="text-align: right">
				<a href="${basePath}/admin/account-license/register"
					class="btn bb-fa-btn fa-plus">
					Register new
				</a>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<bb-ex:searchInfo modelAttribute="accountLicenseSearchForm" action="${basePath }/admin/account-license/search">
			<div class="bb-form-group">
				<div class="col-md-2 bb-form-group-label">
					<label class="control-label" for="account.name">
						Account
					</label>
				</div>
				<div class="col-md-4 bb-form-group-control">
					<input name="account.name" 
							value="${accountLicenseSearchForm.account.name}" 
							type="text" 
							class="bb-autocomplete form-control" 
							data-source-path="/autocomplete/account" 
							data-query-string-property="userName"
							data-display-properties="userName"
							data-submit-property="uid"></input>
					<input name="account.id" value="${accountLicenseSearchForm.account.id}" type="hidden"></input>
				</div>
				<div class="col-md-2 bb-form-group-label">
					<label class="control-label" for="accountLicenseKey">
						License key
					</label>
				</div>
				<div class="col-md-4 bb-form-group-control">
					<form:input path="code" cssClass="form-control" />
				</div>
				<div class="col-md-2 bb-form-group-label">
					<label class="control-label" for="fromDate">
						From
					</label>
				</div>
				<div class="col-md-4 bb-form-group-control">
					<form:input path="fromDate" cssClass="bb-datepicker form-control" />
				</div>
				<div class="col-md-2 bb-form-group-label">
					<label class="control-label" for="toDate">
						To
					</label>
				</div>
				<div class="col-md-4 bb-form-group-control">
					<form:input path="toDate" cssClass="bb-datepicker form-control" />
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
		</bb-ex:searchInfo>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="accountLicenseSearchForm" class="btn btn-primary bb-button"><bb-ex:message code="sc.common.00004"></bb-ex:message></button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
		<bb-ex:searchResults modelAttribute="accountLicenseSearchForm">
			<table class="table table-bordered table-condensed bb-table-action">
				<colgroup>
					<col>
					<col>
					<col width="15%">
					<col width="15%">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th><bb-ex:message code="sc.common.00007" /></th>
						<th>
							<bb-ex:sort formName="accountLicenseSearchForm" 
										sortProperty="account.name" 
										label="Account name"/>
						</th>
						<th>
							<bb-ex:sort formName="accountLicenseSearchForm" 
										sortProperty="fromDate" 
										label="From"/>
						</th>
						<th>
							<bb-ex:sort formName="accountLicenseSearchForm" 
										sortProperty="toDate" 
										label="To"/>
						</th>
						<th>License code</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="accountLicense" varStatus="status">
						<tr>
							<td data-title="<bb-ex:message code='sc.common.00007' />">
								${page.number * page.size + status.index + 1}</td>
							<td data-title="<bb-ex:message code='sc.accountLicense.00006' />">
								<a href="${basePath}/admin/accountLicense/show-info?id=${accountLicense.id}">
									${accountLicense.account.name }
								</a>
							</td>
							<td data-title="From date">
								<bb-ex:formatDate jodaEnabled="true" jodaValue="${accountLicense.fromDate}"></bb-ex:formatDate>
							</td>
							<td data-title="To date">
								<bb-ex:formatDate jodaEnabled="true" jodaValue="${accountLicense.toDate}"></bb-ex:formatDate>
							</td>
							<td data-title="Code">
								${accountLicense.code }
							</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-success bb-fa-btn fa-edit" href="${basePath}/admin/account-license/modify?id=${accountLicense.id}"></a>
									<button type="button"
										class="btn btn-success dropdown-toggle"
										data-toggle="dropdown">
										<span class="caret"></span> 
									</button>
									<ul class="dropdown-menu">
										<li>
											<a href="${basePath}/admin/account-license/getQrCode?id=${accountLicense.id}">
												<i class="fa fa-download"></i>Get QR code
											</a>
										</li>
										<li class="divider"></li>
										<li>
											<a class="bb-confirm-delete" href="${basePath}/admin/account-license/delete?id=${accountLicense.id}">
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
		</bb-ex:searchResults>
	</tiles:putAttribute>
</tiles:insertDefinition>
