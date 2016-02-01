<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.account.00001"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<bb-ex:action-bar modulePath="account" basePath="${basePath}"></bb-ex:action-bar>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4><spring:message code="sc.common.00006"></spring:message>&nbsp;<span class="badge">${page.totalElements}</span></h4>
			</div>
			<div class="panel-body">
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
								<th><spring:message code="sc.common.00007" /></th>
								<th><spring:message code="sc.account.00006" /></th>
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
				<ul class="pagination pull-right">
					<li class="disabled"><a href="#"><span
							class="glyphicon glyphicon-chevron-left"></span></a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-chevron-right"></span></a></li>
				</ul>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
