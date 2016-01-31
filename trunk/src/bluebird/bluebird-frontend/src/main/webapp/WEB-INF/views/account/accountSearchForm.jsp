<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.account.00001"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="panel panel-primary col-md-12">
			<div class="panel-body">
				<bb-ex:action-bar modulePath="account" basePath="${basePath}"></bb-ex:action-bar>
				<h3><spring:message code="sc.account.00002"></spring:message></h3>
				<form:form cssClass="form-horizontal" modelAttribute="accountSearchForm" action="${basePath }/account/search" method="POST">
					<div class="form-group">
							<div class="col-md-2">
								<label
									class="control-label"
									for="userName"><spring:message
										code="sc.account.00006" /></label>
							</div>
							<div class="col-md-4">
								<form:input
									path="userName"
									cssClass="form-control" />
							</div>
							<div class="col-md-2">
							</div>
							<div class="col-md-4">
							</div>
					</div>
					<div class="form-group">
						<div class="col-md-2">
<%-- 								<bb-ex:codelist-select codelistName="language" path="userName"></bb-ex:codelist-select> --%>
						</div>
					</div>
					<div class="bb-action-group-footer btn-group">
						<button type="submit" class="btn btn-primary bb-button"><spring:message code="sc.common.00004"></spring:message></button>
					</div>
				</form:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
