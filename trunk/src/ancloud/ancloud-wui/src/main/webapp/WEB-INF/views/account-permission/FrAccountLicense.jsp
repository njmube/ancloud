<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<div class="col-md-6">
			<h3>License <small>Registration</small></h3>
		</div>
		<div class="form-group btn-group col-md-6 bb-action-group-footer">
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-6" style="text-align: right">
				<a href="${basePath}/admin/account-license/search"
					class="btn bb-fa-btn fa-search">
					Search
				</a>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-info-circle"></i>
					<span>General information</span>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="accountLicenseRForm" cssClass="form-horizontal bb-form" action="${basePath }/admin/account-license/register" method="POST">
						<div class="form-group ${status.error?'has-error':'' }">
							<label for="account.id" class="col-md-2 col-xs-12">Account</label> 
							<div class="input-group col-md-10 col-xs-12">
								<input name="account.name" 
										value="${accountLicenseRForm.account.name}" 
										type="text" 
										class="bb-autocomplete form-control" 
										data-source-path="${basePath }/autocomplete/account" 
										data-query-string-property="name"
										data-display-properties="name"
										data-submit-property="uid"
										data-parameter='{}' />
								<input name="account.id" value="${accountLicenseRForm.account.id}" type="hidden"></input>
							</div>
							<form:errors path="account.id"  cssClass="text-red col-md-12" />
						</div>
						<div class="form-group ${status.error?'has-error':'' }">
                            <label for="fromDate" class="col-md-2">License period</label> 
                            <div class="input-group col-md-10">
                                <form:input path="fromDate"  class="bb-datepicker form-control"/>
                                <span class="input-group-addon">to</span>
                                <form:input path="toDate"  class="bb-datepicker form-control"/>
                            </div>
                            <form:errors path="fromDate"  cssClass="text-red error col-md-12" />
                            <form:errors path="toDate"  cssClass="text-red col-md-12" />
                        </div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="form-group btn-group col-md-12 bb-action-group-footer">
			<div class="col-md-2">
				<button type="submit" form="accountLicenseRForm" class="btn btn-primary bb-button">Save</button>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-script">
		<script type="text/javascript">
			
// 			$("#periodDate").daterangepicker({
// 				autoApply: true,
// 				autoUpdateInput: true,
// 				minDate:moment(),
// 				endDate : moment(),
// 				locale : {
// 					format : 'DD/MM/YYYY'
// 				},
// 				showCustomRangeLabel : false,
// 				alwaysShowCalendars : true
// 			},function(start, end, label) {
// 				$("#fromDate").val(start.format('DD/MM/YYYY'));
// 				$("#toDate").val(end.format('DD/MM/YYYY'));
// 				$("#periodDate").val(start.format('DD/MM/YYYY') + " - " + end.format('DD/MM/YYYY'));
// 			});
// 			if($("#fromDate").val()){
// 				$('#periodDate').data('daterangepicker').setStartDate($("#fromDate").val());
// 			}
// 			if($("#toDate").val()){
// 				$('#periodDate').data('daterangepicker').setEndDate($("#toDate").val());
// 			}
			
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>
