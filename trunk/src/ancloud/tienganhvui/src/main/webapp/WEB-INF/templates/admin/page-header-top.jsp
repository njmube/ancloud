<%@include file="/WEB-INF/views/include.jsp" %>

<header class="main-header">
	<nav class="navbar navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="${basePath}"
					class="navbar-brand"> 
					<span class="logo-lg"><b>Med</b>TECH</span>
				</a>
				<button
					type="button"
					class="navbar-toggle collapsed"
					data-toggle="collapse"
					data-target="#navbar-collapse">
					<i class="fa fa-bars"></i>
				</button>
			</div>
			<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<sec:authorize access="hasRole('Administrator')">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
								<i class="svg-icon svg-admin"></i> 
								<span>Administration</span> 
								<span class="pull-right-container"> 
									<i class="fa fa-angle-down pull-right"></i>
								</span>
							</a>
							<ul class="dropdown-menu animated-dropdown-menu">
								<li>
									<a href="${basePath }/admin/medical-center/search">
										<i class="svg-icon svg-hospital"></i>
										<span class="icon-neighbor-info">Medical centers</span>
									</a>
								</li>
								<li>
									<a href="${basePath }/admin/account/search">
										<i class="svg-icon svg-login-user"></i>
										<span class="icon-neighbor-info">Users</span>
									</a>
								</li>
								<li>
									<a href="${basePath }/admin/license/search">
										<i class="svg-icon svg-license"></i>
										<span class="icon-neighbor-info">Licenses</span>
									</a>
								</li>
							</ul>
						</li>
					</sec:authorize>
		<%-- 		<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')">	 --%>
		<!-- 			<li class="dropdown"> -->
		<!-- 				<a href="#">  -->
		<!-- 					<i class="fa fa-circle-o"></i>  -->
		<!-- 					<span>M&D</span>  -->
		<!-- 					<span class="pull-right-container">  -->
		<!-- 						<i class="fa fa-angle-left pull-right"></i> -->
		<!-- 					</span> -->
		<!-- 				</a> -->
		<!-- 				<ul class="dropdown-menu animated-dropdown-menu" style="display: block;"> -->
		<%-- 					<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')"> --%>
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/patient/search"> --%>
		<!-- 							<i class="fa fa-wheelchair"></i> -->
		<!-- 							Patients -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<%-- 					</sec:authorize> --%>
		<%-- 					<sec:authorize access="hasAnyRole('Administrator,Doctor')"> --%>
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/nurse/search"> --%>
		<!-- 							<i class="fa icon-care-staff-area"></i> -->
		<!-- 							Nurses -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<%-- 					</sec:authorize> --%>
		<%-- 					<sec:authorize access="hasAnyRole('Administrator')"> --%>
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/doctor/search"> --%>
		<!-- 							<i class="fa fa-user-md"></i> -->
		<!-- 							Doctors -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<%-- 					</sec:authorize> --%>
		<%-- 					<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')"> --%>
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/heart/search"> --%>
		<!-- 							<i class="fa fa-heartbeat"></i> -->
		<!-- 							ECG information -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<%-- 					</sec:authorize> --%>
		<!-- 				</ul> -->
		<!-- 			</li> -->
		<%-- 		</sec:authorize> --%>
		<%-- 		<sec:authorize access="hasAnyRole('Patient')"> --%>
		<!-- 			<li class="treeview"> -->
		<!-- 				<a href="#">  -->
		<!-- 					<i class="fa fa-circle-o"></i>  -->
		<!-- 					<span>M&D</span>  -->
		<!-- 					<span class="pull-right-container">  -->
		<!-- 						<i class="fa fa-angle-left pull-right"></i> -->
		<!-- 					</span> -->
		<!-- 				</a> -->
		<!-- 				<ul class="dropdown-menu animated-dropdown-menu" style="display: block;"> -->
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
		<!-- 							<i class="fa fa-wheelchair"></i> -->
		<!-- 							Vital signs -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<!-- 				</ul> -->
		<!-- 			</li> -->
		<%-- 		</sec:authorize> --%>
		<!-- 			<li class="treeview"> -->
		<!-- 				<a href="#">  -->
		<!-- 					<i class="fa fa-circle-o"></i>  -->
		<!-- 					<span>Health analysis</span>  -->
		<!-- 					<span class="pull-right-container">  -->
		<!-- 						<i class="fa fa-angle-left pull-right"></i> -->
		<!-- 					</span> -->
		<!-- 				</a> -->
		<!-- 				<ul class="dropdown-menu animated-dropdown-menu" style="display: block;"> -->
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
		<!-- 							<i class="fa fa-wheelchair"></i> -->
		<!-- 							Tracking calendar -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
		<!-- 							<i class="fa fa-wheelchair"></i> -->
		<!-- 							Charting -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<%-- 						<a href="${basePath }/admin/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
		<!-- 							<i class="fa fa-wheelchair"></i> -->
		<!-- 							Reporting -->
		<!-- 						</a> -->
		<!-- 					</li> -->
		<!-- 				</ul> -->
		<!-- 			</li> -->
				</ul>
				<bb-ex:navigationLink
					items="${sessionScope.NAVIGATION_LINKS}"
					type="top"></bb-ex:navigationLink>
<!-- 				<form -->
<!-- 					class="navbar-form navbar-left" -->
<!-- 					role="search"> -->
<!-- 					<div class="form-group"> -->
<!-- 						<input -->
<!-- 							type="text" -->
<!-- 							class="form-control" -->
<!-- 							id="navbar-search-input" -->
<!-- 							placeholder="Search"> -->
<!-- 					</div> -->
<!-- 				</form> -->
			</div>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown messages-menu">
						<a
							href="#"
							class="dropdown-toggle"
							data-toggle="dropdown"> 
							<i class="fa fa-envelope-o"></i>
							<span class="label label-success">4</span>
						</a>
						<ul class="dropdown-menu animated-dropdown-menu">
							<li class="header">You have 4 messages</li>
							<li>
								<ul class="menu">
									<li>
										<a href="#">
											<div class="pull-left">
												<!-- User Image -->
												<img
													src="${imageResourcePath}/avatar.png"
													class="img-circle"
													alt="User Image">
											</div> <!-- Message title and timestamp -->
											<h4>
												Support Team <small><i class="fa fa-clock-o"></i> 5
													mins</small>
											</h4> <!-- The message -->
											<p>Why not buy a new awesome theme?</p>
										</a>
									</li>
								</ul>
							</li>
							<li class="footer"><a href="#">See All Messages</a></li>
						</ul>
					</li>
					<li class="dropdown notifications-menu">
						<a href="#"
							class="dropdown-toggle"
							data-toggle="dropdown">
							<i class="fa fa-bell-o"></i> 
							<span class="label label-warning">10</span>
						</a>
						<ul class="dropdown-menu animated-dropdown-menu">
							<li class="header">You have 10 notifications</li>
							<li>
								<ul class="menu">
									<li>
										<a href="#"> 
											<i class="fa fa-users text-aqua"></i> 
											5 new members joined today
										</a>
									</li>
								</ul>
							</li>
							<li class="footer"><a href="#">View all</a></li>
						</ul>
					</li>
					<li class="dropdown tasks-menu">
						<a href="#"
							class="dropdown-toggle"
							data-toggle="dropdown"> 
							<i class="fa fa-flag-o"></i>
							<span class="label label-danger">9</span>
						</a>
						<ul class="dropdown-menu animated-dropdown-menu">
							<li class="header">You have 9 tasks</li>
							<li>
								<ul class="menu">
									<li>
										<a href="#">
											<h3>
												Design some buttons <small class="pull-right">20%</small>
											</h3>
											<div class="progress xs">
												<div
													class="progress-bar progress-bar-aqua"
													style="width: 20%"
													role="progressbar"
													aria-valuenow="20"
													aria-valuemin="0"
													aria-valuemax="100">
													<span class="sr-only">20% Complete</span>
												</div>
											</div>
										</a>
									</li>
								</ul>
							</li>
							<li class="footer"><a href="#">View all tasks</a></li>
						</ul>
					</li>
					<li class="dropdown languages-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							<span class="flag-icon ${codelist['language'][currentLocale].resourceGroup }"></span>
						</a>
						<ul class="dropdown-menu animated-dropdown-menu">
							<c:forEach items="${codelist['language'].values()}" var="item">
								<li>
									<a href="?language=${item.key}">
										<span class="flag-icon ${item.resourceGroup }"></span>
										<span>${item.value }</span>
									</a>
								</li>
							</c:forEach>
						</ul>
					</li>
					<li class="dropdown user user-menu">
						<a href="#"
							class="dropdown-toggle"
							data-toggle="dropdown">
							<img src="${imageResourcePath}/avatar.png"
								class="user-image"
								alt="User Image">
							<span class="hidden-xs">${currentAccount.userName }</span>
						</a>
						<ul class="dropdown-menu animated-dropdown-menu">
							<li class="user-header"><img
								src="${imageResourcePath}/avatar.png"
								class="img-circle"
								alt="User Image">
								<p>
									${currentAccount.userName } - ${currentAccount.accountType } <small><spring:message code="sc.sys.00020"></spring:message> <bb-ex:formatDate jodaEnabled="true" jodaValue="${currentAccount.createdDate}" pattern="MMM. yyyy" /></small>
								</p>
							</li>
							<li class="user-body">
								<div class="row">
								</div>
							</li>
							<li class="user-footer">
								<div class="pull-left">
									<a href="#" class="btn btn-default btn-flat">
										<spring:message code="sc.account.00013"></spring:message>
									</a>
								</div>
								<div class="pull-right">
									<form:form action="${pageContext.request.contextPath}/logout" method="POST">
										<button type="submit" class="btn btn-default btn-flat">
											<spring:message code="sc.sys.00004"></spring:message>
										</button>
									</form:form>
								</div>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>
