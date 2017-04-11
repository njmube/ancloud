<%@include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-body">
		<div class="error-page">
<%-- 			<c:set var="statusCode" value="${pageContext.response.getStatus()}"></c:set> --%>
			<h2 class="headline ${statusCode ge 500?'text-red':'text-yellow'}">${statusCode}</h2>
			<div class="error-content">
				<h3>
					<i class="fa fa-warning ${statusCode ge 500?'text-red':'text-yellow'}"></i>
					<c:if test="${statusCode ge 500 }">
						Oops! Something went wrong.
					</c:if>
					<c:if test="${statusCode lt 500 }">
						Oops! Resource not found.
					</c:if>
				</h3>
				<p>
					<c:if test="${statusCode ge 500 }">
						We will work on fixing that right away. Meanwhile, you may <a href="${basePath }/admin/account/search">return to dashboard</a>.
					</c:if>
					<c:if test="${statusCode lt 500 }">
						We could not find the page you were looking for or it's inaccessible. Meanwhile, you may <a href="${basePath }/admin/account/search">return to dashboard</a>.
					</c:if>
				</p>
<!-- 				<form class="search-form"> -->
<!-- 					<div class="input-group"> -->
<!-- 						<input type="text" name="search" class="form-control" -->
<!-- 							placeholder="Search"> -->
<!-- 						<div class="input-group-btn"> -->
<!-- 							<button type="submit" name="submit" -->
<!-- 								class="btn btn-danger btn-flat"> -->
<!-- 								<i class="fa fa-search"></i> -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</form> -->
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
