<%@include file="/WEB-INF/views/include.jsp" %>

<aside class="main-sidebar">
	<section class="sidebar">
<!-- 		<form -->
<!-- 			action="#" -->
<!-- 			method="get" -->
<!-- 			class="sidebar-form"> -->
<!-- 			<div class="input-group"> -->
<!-- 				<input -->
<!-- 					type="text" -->
<!-- 					name="q" -->
<!-- 					class="form-control" -->
<!-- 					placeholder="Search...">  -->
<!-- 				<span class="input-group-btn"> -->
<!-- 					<button -->
<!-- 						type="submit" -->
<!-- 						name="search" -->
<!-- 						id="search-btn" -->
<!-- 						class="btn btn-flat"> -->
<!-- 						<i class="fa fa-search"></i> -->
<!-- 					</button> -->
<!-- 				</span> -->
<!-- 			</div> -->
<!-- 		</form> -->
		<ul class="sidebar-menu animated-dropdown-menu">
			<sec:authorize access="isAuthenticated()">
				<li class="treeview">
					<a href="#"> 
						<i class="svg-icon svg-hospital"></i> 
						<span>ancloud Management</span> 
					</a>
					<ul class="treeview-menu menu-open" style="display: block;">
						<li>
							<a href="${basePath }/admin/medical-center/search">
								<span class="icon-neighbor-info">Medical center</span>
							</a>
						</li>
					</ul>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('Administrator')">
				<li class="treeview">
					<a href="#"> 
						<i class="svg-icon svg-admin"></i> 
						<span>Account</span> 
					</a>
					<ul class="treeview-menu menu-open" style="display: block;">
						<sec:authorize access="hasAuthority('account_search')">
							<li>
								<a href="${basePath }/admin/account/search">
									<span class="icon-neighbor-info">Account</span>
								</a>
							</li>
						</sec:authorize>
						<li>
							<a href="${basePath }/admin/account-license/search">
								<span class="icon-neighbor-info">Account License</span>
							</a>
						</li>
						<sec:authorize access="hasAuthority('account_search')">
							<li>
								<a href="${basePath }/admin/account-permission/search">
									<span class="icon-neighbor-info">Permission</span>
								</a>
							</li>
						</sec:authorize>
					</ul>
				</li>
			</sec:authorize>
<%-- 		<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')">	 --%>
<!-- 			<li class="treeview"> -->
<!-- 				<a href="#">  -->
<!-- 					<i class="fa fa-circle-o"></i>  -->
<!-- 					<span>M&D<i class="fa fa-angle-down"></i> </span>  -->
<!-- 					<span class="pull-right-container">  -->
<!-- 						-->
<!-- 					</span> -->
<!-- 				</a> -->
<!-- 				<ul class="treeview-menu menu-open" style="display: block;"> -->
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
<!-- 					<span>M&D<i class="fa fa-angle-down"></i></span>  -->
<!-- 					<span class="pull-right-container">  -->
<!-- 					</span> -->
<!-- 				</a> -->
<!-- 				<ul class="treeview-menu menu-open" style="display: block;"> -->
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
<!-- 					<span>Health analysis<i class="fa fa-angle-down"></i></span>  -->
<!-- 					<span class="pull-right-container">  -->
<!-- 					</span> -->
<!-- 				</a> -->
<!-- 				<ul class="treeview-menu menu-open" style="display: block;"> -->
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
		<bb-ex:navigationLink items="${sessionScope.NAVIGATION_LINKS}" type="sidebar"></bb-ex:navigationLink>
	</section>
</aside>