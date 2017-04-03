<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<h3>Patient management</h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
			<bb:messages name="messages"></bb:messages>
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<i class="fa fa-search"></i>
						<span>Search information</span>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="patientSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/med/patient/search" method="POST">
							<div class="bb-form-group">
								<div class="col-md-2 bb-form-group-label">
									<label class="control-label" for="userName">
										Patient name
									</label>
								</div>
								<div class="col-md-4 bb-form-group-control">
									<form:input path="name" cssClass="form-control" />
								</div>
								<div class="col-md-2 bb-form-group-label"></div>
								<div class="col-md-4 bb-form-group-control"></div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="form-group btn-group col-md-12 bb-action-group-footer">
				<div class="col-md-2">
					<button type="submit" form="patientSearchForm" class="btn btn-primary bb-button">Search</button>
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
						<span>Search results</span>
						&nbsp;<span class="badge">${page.totalElements}</span>
					</div>
					<div class="panel-body">
					<c:if test="${page.totalElements > 0}">
						<div class="table-responsive">
							<table class="table table-bordered table-condensed  bb-table-list ">
								<colgroup>
									<col >
									<col >
									<col width="10%">
									<col width="17%">
									<col width="18%">
									<col width="18%">
									<col width="10%">
									<col width="auto">
								</colgroup>
								<thead>
									<tr>
										<th>No.</th>
										<th><bb-ex:sort page="${page }" 
														formName="patientSearchForm" 
														sortProperty="name" 
														label="Patient name"/></th>
										<th><bb-ex:sort page="${page }" 
														formName="patientSearchForm" 
														sortProperty="sex" 
														label="Sex"/></th>
										<th>Ward</th>
										<th>Bed</th>
										<th><bb-ex:sort page="${page }" 
														formName="patientSearchForm" 
														sortProperty="birthday" 
														label="Birthday"/></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.content}" var="item" varStatus="status">
										<tr>
											<td>
												${page.number * page.size + status.index + 1}
											</td>
											<td>${item.name }</td>
											<td>${item.sex}</td>
											<td>${item.ward }</td>
											<td>${item.bed }</td>
											<td><bb-ex:formatDate jodaEnabled="true" jodaValue="${item.birthday }" /></td>
											<td>
												<a class="btn btn-success bb-fa-btn fa-heartbeat" href="${basePath}/patient/show-vital/${item.id}"></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<bb-ex:pagination formName="patientSearchForm" page="${page }"></bb-ex:pagination>
						</c:if>
					</div>
				</div>
			</div>
			
	</tiles:putAttribute>
</tiles:insertDefinition>


