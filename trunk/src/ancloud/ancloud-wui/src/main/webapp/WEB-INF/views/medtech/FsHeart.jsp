<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header" >
		<h3>Heartdo management</h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-script">
		<script src="${resourceBasePath}/plugin/moment.js"></script>
		<script src="${resourceBasePath}/plugin/chart.js"></script>
		<script type="text/javascript">
			$(function(){
				$(".bb-table-list>tbody>tr").each(function(i){
					var ctx = $(this).find("canvas[name^=ecg]");
					var data = $(this).find("input[name^=ecg]").val().split("#");
					var chart = new Chart(ctx,{
						type : 'line',
						data : 
						{
								labels : data,
								datasets : 
								[
									{
										data : data,
										fill: false,
							            lineTension: 0.1,
							            backgroundColor: "rgba(75,192,192,0.4)",
							            borderColor: "rgba(75,192,192,1)",
							            borderCapStyle: 'butt',
							            borderDash: [],
							            borderDashOffset: 0.0,
							            borderJoinStyle: 'miter',
							            pointBorderColor: "rgba(75,192,192,1)",
							            pointBackgroundColor: "#fff",
							            pointBorderWidth: 1,
							            pointHoverRadius: 5,
							            pointHoverBackgroundColor: "rgba(75,192,192,1)",
							            pointHoverBorderColor: "rgba(220,220,220,1)",
							            pointHoverBorderWidth: 2,
							            pointRadius: 1,
							            pointHitRadius: 10,
							            spanGaps: false
									}
								]
						},
						options : {
							scales : {
								xAxes : [ {
									display : false
								}]
							},
							legend: {
					            display: false,
					            labels: {
					                fontColor: 'rgb(255, 99, 132)'
					            }
					        },
					        responsive :true
						}
					});
				});
			});
		</script>
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
						<form:form modelAttribute="heartSearchForm" cssClass="form-horizontal bb-form" action="${basePath }/med/heart/search" method="POST">
							<div class="bb-form-group">
								<div class="col-md-2 bb-form-group-label">
									<label class="control-label">
										Patient name
									</label>
								</div>
								<div class="col-md-4 bb-form-group-control">
									<form:input path="patient.name" cssClass="form-control" />
								</div>
								<div class="col-md-2 bb-form-group-label"></div>
								<div class="col-md-4 bb-form-group-control"></div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group btn-group col-md-12 bb-action-group-footer">
					<div class="col-md-2">
						<button type="submit" form="heartSearchForm" class="btn btn-primary bb-button">Search</button>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
				</div>
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
									<col width="20%">
									<col width="20%">
									<col width="10%">
								</colgroup>
								<thead>
									<tr>
										<th>No.</th>
										<th><bb-ex:sort page="${page }" 
														formName="heartSearchForm" 
														sortProperty="recordedDate" 
														label="Recorded date"/></th>
										<th>Value</th>
										<th>ECG</th>
										<th><bb-ex:sort page="${page }" 
														formName="heartSearchForm" 
														sortProperty="patient.name" 
														label="Patient"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.content}" var="item" varStatus="status">
										<tr>
											<td>
												${page.number * page.size + status.index + 1}
											</td>
											<td><fmt:formatDate  value="${item.recordedDate }" pattern="dd/MM/yyyy HH:mm:ss"/></td>
											<td>
												<table class="small">
													<tr>
														<td>Heart rate : </td>
														<td>&nbsp;${item.heartRate } (bpm)</td>
													</tr>
													<tr>
														<td>Respiration rate : </td>
														<td>&nbsp;${item.respirationRate } (bpm)</td>
													</tr>
													<tr>
														<td>HRV : </td>
														<td>&nbsp;${item.hrv } (ms)</td>
													</tr>
													<tr>
														<td>Heart rate : </td>
														<td>&nbsp;${item.arrhythmia }</td>
													</tr>
													<tr>
														<td>Missed beat : </td>
														<td>&nbsp;${item.missedBeat }</td>
													</tr>
													<tr>
														<td>Premature beat : </td>
														<td>&nbsp;${item.prematureBeat }</td>
													</tr>
												</table>
											</td>
											<td>
												<input name="ecg${page.number * page.size + status.index + 1}" type="hidden" value="${item.ecg }" />
												<canvas name="ecg${page.number * page.size + status.index + 1}"></canvas> 											
											</td>
											<td>${item.patient.name }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<bb-ex:pagination formName="heartSearchForm" page="${page }"></bb-ex:pagination>
					</c:if>
					</div>
				</div>
			</div>
			
	</tiles:putAttribute>
</tiles:insertDefinition>


