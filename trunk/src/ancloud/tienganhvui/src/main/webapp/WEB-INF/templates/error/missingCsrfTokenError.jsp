<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSRF Error!</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="error-template">
					<h1>CSRF Error! Missing CSRF Token!</h1>
					<div class="error-details">
						<div class="error">
							<c:if test="${!empty exceptionCode}">[<c:out escapeXml="true" value="${exceptionCode}" />]</c:if>
							<spring:message code="err.sys.0056" />
						</div>
					</div>
					<div class="error-actions">
						<a href="${pageContext.request.contextPath}" class="btn btn-primary btn-lg" onclick="takeMeHome();"><span class="glyphicon glyphicon-home"></span><spring:message code="sc.sys.js.0032" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>