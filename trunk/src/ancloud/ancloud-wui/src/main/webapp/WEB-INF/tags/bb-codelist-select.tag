<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="path" rtexprvalue="true" required="true" %>
<%@ attribute name="codelistName" rtexprvalue="true" required="true" %>
<%@ attribute name="cssClass" rtexprvalue="true" required="true" %>

<form:select path="${path}" cssClass="${cssClass }">
	<option></option>
	<c:forEach items="${codelist[codelistName].values() }" var="item">
		<form:option value="${item.resourceKey}">
			<c:if test="${not empty item.value}">
				<c:out value="${item.value}" escapeXml="true"/>
			</c:if>
			<c:if test="${empty item.value}">
				<spring:message code="${item.messageKey }" htmlEscape="true"/>
			</c:if>
		</form:option>
	</c:forEach>
</form:select>



