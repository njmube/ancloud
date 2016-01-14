<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="code" rtexprvalue="true" required="true" %>
<c:catch var="exception">
	<spring:message code="${code}"></spring:message>
</c:catch>
<c:if test = "${exception != null}">
	<c:out value="${code}"></c:out>
</c:if>

