<tiles:insertDefinition name="ejukate">
	<tiles:putAttribute name="page-script">
	</tiles:putAttribute>
	<tiles:putAttribute name="content-header" >
		<h3><spring:message code="sc.navigationlink.00001"></spring:message></h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<i class="fa fa-pencil"></i>
				<span><spring:message code="sc.common.00010"></spring:message></span>
			</div>
			<div class="panel-body">
				<form:form modelAttribute="fmNavigationLink" cssClass="form-horizontal bb-form" action="${basePath }/navigation-link/modify" method="POST">
					<table class="table table-bordered table-condensed bb-table bb-table-list bb-table-action">
						<colgroup>
							<col >
							<col >
							<col width="20%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th><spring:message code="sc.common.00007" /></th>
								<th><spring:message code="sc.message.00003" /></th>
								<th><spring:message code="sc.navigationlink.00002" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
								</td>
								<td>
									<div class="input-group">
										<span class="input-group-btn">
											<button class="btn btn-default bb-fa-btn fa-angle-double-down" type="button"></button>
										</span>
										<bb-ex:autocomplete name="navigationLinks[0].messageCode" cssClass="form-control"/>
										<span class="input-group-btn">
											<button class="btn btn-default bb-fa-btn fa-angle-double-right" type="button" onclick="$.bb.ar.addItem({container:$(this).closest('tbody')});"></button>
										</span>
									</div>
								</td>
								<td>
									<form:input path="navigationLinks[0].path" class="form-control"/>
								</td>
								<td>
									<button class="btn btn-default bb-fa-btn fa-minus-square"></button>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="bb-table-action-plus">
						<button class="btn btn-default bb-fa-btn fa-plus-square"></button>
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