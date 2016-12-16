<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.message.00001"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<bb-ex:action-bar modulePath="message" basePath="${basePath}"></bb-ex:action-bar>
		<div class="col-md-12">
			<bb-ex:iSearchPanel>
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
									Base name
								</label>
							</div>
							<div class="col-md-4 bb-form-group-control">
								<form:input path="basename" cssClass="form-control" />
							</div>
							<div class="col-md-2 bb-form-group-label">
								<label class="control-label" for="message">
									Language
								</label>
							</div>
							<div class="col-md-4 bb-form-group-control">
								<form:select path="language" class="form-control" items="${codelist['language'].values()}" itemLabel="value" itemValue="key">
								</form:select>
							</div>
						</div>
					</form:form>
			</bb-ex:iSearchPanel>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="messageSearchForm" class="btn btn-default bb-button"><spring:message code="sc.common.00004"></spring:message></button>
			</div>
			<div class="col-md-2">
				<button type="submit" form="messageSearchForm" class="btn btn-default bb-button" name="lucence" value="true"><spring:message code="sc.common.00004"></spring:message></button>
			</div>
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
												formName="messageSearchForm" 
												sortProperty="message" 
												label="sc.message.00001"/>
								</th>
								<th><spring:message code="sc.message.00003" /></th>
								<th><spring:message code="sc.message.00004" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="message" varStatus="status">
								<tr>
									<td data-title="<spring:message code='sc.common.00007' />">
										${page.number * page.size + status.index + 1}
									</td>
									<td>${message.message }</td>
									<td>${message.key }</td>
									<td>${message.language}-${message.country }</td>
									<td>${message.basename }</td>
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
