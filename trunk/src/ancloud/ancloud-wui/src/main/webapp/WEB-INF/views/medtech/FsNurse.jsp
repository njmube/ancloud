<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<h3>Nurse management</h3>
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
						<form:form modelAttribute="nurseSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/med/nurse/search" method="POST">
							<div class="bb-form-group">
								<div class="col-md-2 bb-form-group-label">
									<label class="control-label">
										User name
									</label>
								</div>
								<div class="col-md-4 bb-form-group-control">
									<form:input path="userName" cssClass="form-control" />
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
					<button type="submit" form="nurseSearchForm" class="btn btn-primary bb-button">Search</button>
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
							<table class="table table-bordered table-condensed  bb-table-list">
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
										<th>No.</th>
										<th><bb-ex:sort page="${page }" 
														formName="nurseSearchForm" 
														sortProperty="userName" 
														label="User name"/></th>
										<th>Name</th>
										<th>Email</th>
										<th>Role</th>
										<th>Look after</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.content}" var="item" varStatus="status">
										<tr>
											<td>
												${page.number * page.size + status.index + 1}
											</td>
											<td>${item.userName }</td>
											<td>${item.name }</td>
											<td>${item.email }</td>
											<td>${item.role }</td>
											<td>${item.lookAfter }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<bb-ex:pagination formName="nurseSearchForm" page="${page }"></bb-ex:pagination>
						</c:if>
					</div>
				</div>
			</div>
			
	</tiles:putAttribute>
</tiles:insertDefinition>


