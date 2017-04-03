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
		<sec:authorize access="hasRole('Administrator')">
			<li class="treeview active">
				<a href="#"> 
					<i class="fa fa-circle-o"></i> 
					<span>Administration</span> 
					<span class="pull-right-container"> 
						<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu menu-open" style="display: block;">
					<li>
						<a href="${basePath }/account/search">
							<i class="fa fa-user"></i>
							Account
						</a>
					</li>
				</ul>
			</li>
		</sec:authorize>
<%-- 		<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')">	 --%>
<!-- 			<li class="treeview active"> -->
<!-- 				<a href="#">  -->
<!-- 					<i class="fa fa-circle-o"></i>  -->
<!-- 					<span>M&D</span>  -->
<!-- 					<span class="pull-right-container">  -->
<!-- 						<i class="fa fa-angle-left pull-right"></i> -->
<!-- 					</span> -->
<!-- 				</a> -->
<!-- 				<ul class="treeview-menu menu-open" style="display: block;"> -->
<%-- 					<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')"> --%>
<!-- 					<li> -->
<%-- 						<a href="${basePath }/patient/search"> --%>
<!-- 							<i class="fa fa-wheelchair"></i> -->
<!-- 							Patients -->
<!-- 						</a> -->
<!-- 					</li> -->
<%-- 					</sec:authorize> --%>
<%-- 					<sec:authorize access="hasAnyRole('Administrator,Doctor')"> --%>
<!-- 					<li> -->
<%-- 						<a href="${basePath }/nurse/search"> --%>
<!-- 							<i class="fa icon-care-staff-area"></i> -->
<!-- 							Nurses -->
<!-- 						</a> -->
<!-- 					</li> -->
<%-- 					</sec:authorize> --%>
<%-- 					<sec:authorize access="hasAnyRole('Administrator')"> --%>
<!-- 					<li> -->
<%-- 						<a href="${basePath }/doctor/search"> --%>
<!-- 							<i class="fa fa-user-md"></i> -->
<!-- 							Doctors -->
<!-- 						</a> -->
<!-- 					</li> -->
<%-- 					</sec:authorize> --%>
<%-- 					<sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')"> --%>
<!-- 					<li> -->
<%-- 						<a href="${basePath }/heart/search"> --%>
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
<!-- 				<ul class="treeview-menu menu-open" style="display: block;"> -->
<!-- 					<li> -->
<%-- 						<a href="${basePath }/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
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
<!-- 				<ul class="treeview-menu menu-open" style="display: block;"> -->
<!-- 					<li> -->
<%-- 						<a href="${basePath }/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
<!-- 							<i class="fa fa-wheelchair"></i> -->
<!-- 							Tracking calendar -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 					<li> -->
<%-- 						<a href="${basePath }/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
<!-- 							<i class="fa fa-wheelchair"></i> -->
<!-- 							Charting -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 					<li> -->
<%-- 						<a href="${basePath }/patient/show-vital/${CURRENT_ACCOUNT.id}"> --%>
<!-- 							<i class="fa fa-wheelchair"></i> -->
<!-- 							Reporting -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</li> -->
<!-- 		</ul> -->
		<bb-ex:navigationLink items="${sessionScope.NAVIGATION_LINKS}" type="sidebar"></bb-ex:navigationLink>
	</section>
</aside>