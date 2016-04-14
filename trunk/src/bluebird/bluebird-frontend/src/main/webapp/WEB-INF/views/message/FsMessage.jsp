<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.message.00001"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<bb-ex:action-bar modulePath="message" basePath="${basePath}"></bb-ex:action-bar>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="fa fa-search"></i>
				<span><spring:message code="sc.common.00008"></spring:message></span>
			</div>
			<div class="panel-body">
				<form:form modelAttribute="messageSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/message/search" method="POST">
					<div class="bb-form-group form-group">
						<div class="col-md-2 bb-form-group-label">
							<label class="control-label" for="message">
								<spring:message code="sc.message.00001" />
							</label>
						</div>
						<div class="col-md-4 bb-form-group-control">
							<form:input path="message" cssClass="form-control" />
						</div>
						<div class="col-md-2 bb-form-group-label">
							<label class="control-label" for="message">
								<spring:message code="sc.message.00003" />
							</label>
						</div>
						<div class="col-md-4 bb-form-group-control">
							<form:input path="key" cssClass="form-control" />
						</div>
					</div>
					<div class="bb-form-group form-group">
						<div class="col-md-2 bb-form-group-label">
							<label class="control-label" for="message">
								<spring:message code="sc.message.00001" />
							</label>
						</div>
						<div class="col-md-4 bb-form-group-control">
							<form:input path="message" cssClass="form-control" />
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
			</div>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="messageSearchForm" class="btn btn-primary bb-button"><spring:message code="sc.common.00004"></spring:message></button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
