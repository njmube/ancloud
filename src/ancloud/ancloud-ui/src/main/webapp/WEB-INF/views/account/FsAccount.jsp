<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.account.00002"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="col-md-12">
			<bb-ex:action-bar modulePath="account" basePath="${basePath}"></bb-ex:action-bar>
		</div>
		<div class="col-md-12">
			<bb-ex:iSearchPanel>
				<form:form modelAttribute="accountSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/account/search" method="POST">
					<div class="bb-form-group">
						<div class="col-md-2 bb-form-group-label">
							<label class="control-label" for="userName">
								<spring:message code="sc.account.00006" />
							</label>
						</div>
						<div class="col-md-4 bb-form-group-control">
							<form:input path="userName" cssClass="form-control" />
						</div>
						<div class="col-md-2 bb-form-group-label"></div>
						<div class="col-md-4 bb-form-group-control"></div>
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
			</bb-ex:iSearchPanel>
		</div>
		<div class="col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="accountSearchForm" class="btn btn-default bb-button"><spring:message code="sc.common.00004"></spring:message></button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
		<div class="col-md-12">
			<bb-ex:rSearchPanel page="${page }">
				<div class="table-responsive">
					<table class="table table-bordered table-condensed table-hover bb-table-list">
						<colgroup>
							<col >
							<col >
							<col width="10%">
							<col width="17%">
							<col width="18%">
							<col width="18%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th><spring:message code="sc.common.00007" /></th>
								<th>
									<bb-ex:sort page="${page }" 
												formName="accountSearchForm" 
												sortProperty="userName" 
												label="sc.account.00006"/>
								</th>
								<th><spring:message code="sc.account.00007" /></th>
								<th><spring:message code="sc.account.00008" /></th>
								<th><spring:message code="sc.account.00009" /></th>
								<th><spring:message code="sc.account.00010" /></th>
								<th><spring:message code="sc.account.00011" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="account" varStatus="status">
								<tr>
									<td data-title="<spring:message code='sc.common.00007' />">
										${page.number * page.size + status.index + 1}
									</td>
									<td data-title="<spring:message code='sc.account.00006' />">${account.userName }</td>
									<td data-title="<spring:message code='sc.account.00007' />">******</td>
									<td data-title="<spring:message code='sc.account.00008' />">${account.accountNonExpired }</td>
									<td data-title="<spring:message code='sc.account.00009' />">${account.accountNonLocked }</td>
									<td data-title="<spring:message code='sc.account.00010' />">${account.credentialsNonExpired }</td>
									<td data-title="<spring:message code='sc.account.00011' />">${account.enabled }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pull-right">
					<bb-ex:pagination formName="accountSearchForm" page="${page }"></bb-ex:pagination>
				</div>
			</bb-ex:rSearchPanel>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
