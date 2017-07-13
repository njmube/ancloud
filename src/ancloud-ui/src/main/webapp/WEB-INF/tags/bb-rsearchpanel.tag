<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<i class="fa fa-list"></i>
		<span><spring:message code="sc.common.00006"></spring:message>&nbsp;<span class="badge">${page.totalElements}</span></span>
	</div>
	<div class="panel-body">
		<c:if test="${page.totalElements > 0}">
			<jsp:doBody></jsp:doBody>
		</c:if>
	</div>
</div>