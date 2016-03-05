<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.navigationlink.00001"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-template">
		<jsp:include page="templateNavigationLink.jsp"></jsp:include>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-style">
		<link rel="stylesheet" href="${resourceBasePath}/css/app/navigationLink.css">
	</tiles:putAttribute>
	<tiles:putAttribute name="page-script">
		<script src="${resourceBasePath}/js/app/navigationLink.js"></script>
		<script type="text/javascript" >
			var navigationLinks = [
			<c:if test="${not empty fmNavigationLink.navigationLinks}">
			<c:forEach items="${fmNavigationLink.navigationLinks}"  var="navigationLink">
				{
					messageKey: '${navigationLink.messageKey}',
					icon: '${navigationLink.icon}',
					path: '${navigationLink.path}',
					groupId: '${navigationLink.groupId}',
					groupIndex: '${navigationLink.groupIndex}',
				},
			</c:forEach>
			</c:if>
			];
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="fa fa-pencil"></i>
				<span><spring:message code="sc.common.00010"></spring:message></span>
			</div>
			<div class="panel-body">
				<form:form modelAttribute="fmNavigationLink" cssClass="form-horizontal bb-form" action="${basePath }/navigation-link/modify" method="POST">
					<form:errors path="*" cssClass="alert alert-error" delimiter="<br/>" element="div" cssStyle="" />
					<table class="table table-bordered table-condensed bb-table bb-table-action" id="navigationLinkTable">
						<thead>
							<tr>
								<th><spring:message code="sc.common.00007" /></th>
								<th><spring:message code="sc.message.00003" /></th>
								<th><spring:message code="sc.navigationlink.00002" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody class="ar-dataContainer">
							<c:forEach items="${fmNavigationLink.navigationLinks}"  var="navigationLink" varStatus="status">
								<tr class="ar-item ar-dataItem" data-ar-groupid="${navigationLink.groupId }" data-ar-groupindex="${navigationLink.groupIndex }">
									<td class="ar-groupIndex">
										${navigationLink.groupIndex }
									</td>
									<td>
										<form:hidden path="navigationLinks[${status.index }].id"/>
										<form:hidden path="navigationLinks[${status.index }].groupId" cssClass="ar-groupId"/>
										<form:hidden path="navigationLinks[${status.index }].groupIndex" cssClass="ar-groupIndex"/>
										<form:hidden path="navigationLinks[${status.index }].itemIndex" cssClass="ar-itemIndex"/>
										<div class="input-group">
											<span class="input-group-btn">
												<button class="btn btn-default bb-fa-btn fa-angle-double-down bb-navigationLink-addLinkButton" type="button"></button>
											</span>
											<bb-ex:autocomplete
												name="navigationLinks[${status.index }].messageKey"
												cssClass="form-control"
												value="${navigationLink.messageKey }"
												parameters="{\"baseName\":\"sc\"}"
												queryStringProperty="message"
												sourcePath="/message/ajaxGetAllMessage"
												displayProperties="message,key,language"
												submitProperty="key"
												displayText="${navigationLink.message }" />
											<span class="input-group-btn">
												<button class="btn btn-default bb-fa-btn fa-angle-double-right bb-navigationLink-addNestedLinkButton" type="button"></button>
											</span>
										</div>
									</td>
									<td>
										<form:input path="navigationLinks[${status.index }].path" class="form-control"/>
									</td>
									<td>
										<button class="btn btn-default bb-fa-btn fa-minus-square bb-navigationLink-removeLinkButton" type="button"></button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="bb-table-action-plus">
						<button class="btn btn-default bb-fa-btn fa-plus-square bb-navigationLink-addLinkButton" type="button"></button>
					</div>
				</form:form>
			</div>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="fmNavigationLink" class="btn btn-primary bb-button"><spring:message code="sc.common.00009"></spring:message></button>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>