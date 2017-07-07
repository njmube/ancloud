<%@include file="/WEB-INF/views/include.jsp" %>

<header class="main-header">
	<a href="${basePath}/" class="logo"> 
<%-- 		<span class="logo-mini"><img src="${imageResourcePath}/trek-mini-logo.png"/></span>  --%>
<%-- 		<span class="logo-lg"><b><img src="${imageResourcePath}/trek-lg-logo.png"/></b></span> --%>
		<span class="logo-mini">AnCD</span> 
		<span class="logo-lg"><b>An</b>Cloud</span>
	</a>
	<nav class="navbar navbar-static-top"
		role="navigation">
		<a href="#"
			class="fa fa-align-justify sidebar-toggle"
			data-toggle="offcanvas"
			role="button"> 
			<span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
<!-- 				<li class="dropdown messages-menu"> -->
<!-- 					<a href="#"  -->
<!-- 						class="dropdown-toggle"  -->
<!-- 						data-toggle="dropdown"> -->
<!-- 						<i class="fa fa-envelope-o"></i>  -->
<!-- 						<span class="label label-success">4</span> -->
<!-- 					</a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li class="header">You have 4 messages</li> -->
<!-- 						<li> -->
<!-- 							<ul class="menu"> -->
<!-- 								<li> -->
<!-- 									<a href="#"> -->
<!-- 										<div class="pull-left"> -->
<%-- 											<img src="${imageResourcePath}/avatar.png" --%>
<!-- 												class="img-circle" -->
<!-- 												alt="User Image"> -->
<!-- 										</div> -->
<!-- 										<h4> -->
<!-- 											Support Team  <small><i class="fa fa-clock-o"></i> 5 mins</small> -->
<!-- 										</h4> -->
<!-- 										<p>Why not buy a new awesome theme?</p> -->
<!-- 								</a> -->
<!-- 								</li> -->
<!-- 								end message -->
<!-- 							</ul> /.menu -->
<!-- 						</li> -->
<!-- 						<li class="footer"><a href="#">See All Messages</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 				<li class="dropdown notifications-menu"> -->
<!-- 					<a -->
<!-- 						href="#" -->
<!-- 						class="dropdown-toggle" -->
<!-- 						data-toggle="dropdown"> -->
<!-- 						<i class="fa fa-bell-o"></i>  -->
<!-- 						<span class="label label-warning">10</span> -->
<!-- 					</a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li class="header">You have 10 notifications</li> -->
<!-- 						<li> -->
<!-- 							<ul class="menu"> -->
<!-- 								<li> -->
<!-- 									<a href="#"> -->
<!-- 										<i class="fa fa-users text-aqua"></i>  -->
<!-- 										5 new members joined today -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</li> -->
<!-- 						<li class="footer"><a href="#">View all</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 				<li class="dropdown tasks-menu"> -->
<!-- 					<a href="#" class="dropdown-toggle" data-toggle="dropdown">  -->
<!-- 						<i class="fa fa-flag-o"></i>  -->
<!-- 						<span class="label label-danger">9</span> -->
<!-- 					</a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li class="header">You have 9 tasks</li> -->
<!-- 						<li> -->
<!-- 							<ul class="menu"> -->
<!-- 								<li> -->
<!-- 									<a href="#"> -->
<!-- 										<h3> -->
<!-- 											Design some buttons <small class="pull-right">20%</small> -->
<!-- 										</h3> -->
<!-- 										<div class="progress xs"> -->
<!-- 											<div -->
<!-- 												class="progress-bar progress-bar-aqua" -->
<!-- 												style="width: 20%" -->
<!-- 												role="progressbar" -->
<!-- 												aria-valuenow="20" -->
<!-- 												aria-valuemin="0" -->
<!-- 												aria-valuemax="100"> -->
<!-- 												<span class="sr-only">20% Complete</span> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</li> -->
<!-- 						<li class="footer"><a href="#">View all tasks</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
				<li class="dropdown languages-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<span class="flag-icon ${codelist['language'][currentLocale].resourceGroup }"></span>
					</a>
					<ul class="dropdown-menu">
						<c:forEach items="${codelist['language'].values()}" var="item">
							<li>
								<a href="?language=${item.resourceKey}">
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
					<ul class="dropdown-menu">
						<li class="user-header"><img
							src="${imageResourcePath}/avatar.png"
							class="img-circle"
							alt="User Image">
							<p>
								${currentAccount.userName } - 
								${currentAccount.accountType }
								<small><spring:message code="sc.sys.00020"></spring:message> <bb-ex:formatDate jodaEnabled="true" jodaValue="${currentAccount.createdDate}" pattern="MMM. yyyy" /></small>
							</p>
						</li>
						<li class="user-body">
							<div class="row">
							</div>
						</li>
						<li class="user-footer">
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat" disabled>
									<spring:message code="sc.account.00013"></spring:message>
								</a>
							</div>
							<div class="pull-right">
								<form:form action="${basePath}/logout" method="POST">
									<button type="submit" class="btn btn-default btn-flat">
										<spring:message code="sc.sys.00004"></spring:message>
									</button>
								</form:form>
							</div>
						</li>
					</ul>
				</li>
<!-- 				<li> -->
<!-- 					<a href="#" data-toggle="control-sidebar"> -->
<!-- 						<i class="fa fa-gears"></i> -->
<!-- 					</a> -->
<!-- 				</li> -->
			</ul>
		</div>
	</nav>
</header>