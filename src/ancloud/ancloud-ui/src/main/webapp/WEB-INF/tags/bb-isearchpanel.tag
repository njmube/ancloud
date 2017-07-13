<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<i class="fa fa-search"></i>
		<span><spring:message code="sc.common.00008"></spring:message></span>
	</div>
	<div class="panel-body">
		<jsp:doBody></jsp:doBody>
	</div>
</div>