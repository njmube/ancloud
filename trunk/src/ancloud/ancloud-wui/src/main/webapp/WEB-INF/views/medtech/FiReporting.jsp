<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header">
		<h3>Patient dashboard</h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-script">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/4.4.0/d3.min.js"></script>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-style">
		<style type="text/css">
			.vital-sign{
				padding:5px;
			}
			.health-analysis .info-link {
				border-radius : 50%;
				display : block;
				height: 110px;
			    width: 110px;
			    font-size: 48px;
			    line-height: 110px;
			    text-align: center;
			}
		</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="box box-primary">
				<div class="box-body">
					<div class="col-md-4 col-sm-12 col-xs-12">
						<ul class="list-group list-group-unbordered">
							<li class="list-group-item"><b>Age</b>
								<a class="pull-right">${patient.age}</a>
							</li>
							<li class="list-group-item"><b>Sex</b>
								<a class="pull-right">${patient.sex eq '1'?"Male":"Female" }</a>
							</li>
							<li class="list-group-item"><b>Ward</b>
								<a class="pull-right">${patient.ward }</a>
							</li>
							<li class="list-group-item"><b>Bed</b>
								<a class="pull-right">${patient.bed }</a>
							</li>
							
						</ul>
					</div>
					<div class="col-md-4 col-sm-12 col-xs-12">
						<ul class="list-group list-group-unbordered">
							<li class="list-group-item"><b>Occupation</b> <a
								class="pull-right"></a></li>
							<li class="list-group-item"><b>Address</b> <a
								class="pull-right"></a></li>
							<li class="list-group-item"><b>Contact number</b> <a
								class="pull-right"></a></li>
						</ul>
					</div>
					<div class="col-md-4 col-sm-12 col-xs-12">
						
						<img class="profile-user-img img-responsive img-circle"
							src="${resourceBasePath }/img/avatar.png">
						<h3 class="profile-username text-center">${patient.name }</h3>
						<p class="text-muted text-center">${patient.email }</p>
						<p class="text-muted text-center">Patient</p>
					</div>
<%-- 					<a href="${basePath }/med/patient/show-info?patientId=${patient.id }" class="btn btn-info btn-block"><b>More info ...</b></a> --%>
				</div>
			</div>
		</div>
		<h3>Vital sign</h3>
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="col-md-4 col-sm-6 col-xs-12 vital-sign">
				<div class="info-box">
					<span class="info-box-icon bg-aqua"><i
						class="bb bb-foot"></i></span>
					<div class="info-box-content">
						<span class="info-box-text">PAS alarm</span> <span
							class="info-box-number">Normal</span>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-12 vital-sign">
				<div class="info-box">
					<span class="info-box-icon bg-red"><i
						class="bb bb-diaper"></i></span>
					<div class="info-box-content">
						<span class="info-box-text">Diaper</span> <span
							class="info-box-number">Normal</span>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-12 vital-sign">
				<div class="info-box">
					<span class="info-box-icon bg-green"><i
						class="bb bb-heart"></i></span>
					<div class="info-box-content">
						<table class="small">
							<tr>
								<td>Heart rate : </td>
								<td>&nbsp;${heart.heartRate } (bpm)</td>
							</tr>
							<tr>
								<td>Respiration rate : </td>
								<td>&nbsp;${heart.respirationRate } (bpm)</td>
							</tr>
							<tr>
								<td>HRV : </td>
								<td>&nbsp;${heart.hrv } (ms)</td>
							</tr>
							<tr>
								<td>Heart rate : </td>
								<td>&nbsp;${heart.arrhythmia }</td>
							</tr>
							<tr>
								<td>Missed beat : </td>
								<td>&nbsp;${heart.missedBeat }</td>
							</tr>
							<tr>
								<td>Premature beat : </td>
								<td>&nbsp;${heart.prematureBeat }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<h3>Health analysis</h3>
		<div class="col-md-12 col-sm-12 col-xs-12 health-analysis">
			<div class="col-md-4 col-sm-6 col-xs-12">
				<a class="info-link bg-aqua center-block" title="Tracking calendar" href="#"><i
					class="fa fa-calendar"></i>
				</a>
				<p class="text-muted text-center">Tracking calendar</p>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-12">
				<a class="info-link bg-green center-block" title="Charting" href="#"><i
					class="fa fa-bar-chart"></i>
				</a>
				<p class="text-muted text-center">Charting</p>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-12">
				<a class="info-link bg-red center-block" title="Reporting" href="#">
					<i class="bb bb-report"></i>
				</a>
				<p class="text-muted text-center">Reporting</p>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


