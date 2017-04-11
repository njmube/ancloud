<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="formName" rtexprvalue="true" required="true"%>
<%@ attribute name="page" rtexprvalue="true" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="sortProperty" rtexprvalue="true" required="true"%>
<%@ attribute name="label" rtexprvalue="true" %>

<c:set var="direction">${page.sort.getOrderFor(sortProperty).direction}</c:set>
<a href="?page=${page.number }&sort=${empty direction?sortProperty.concat(',asc'):direction eq 'DESC'?'': sortProperty.concat(',desc')}">
	<span>
		<c:catch var="exception">
			<spring:message code="${label}"></spring:message>
		</c:catch>
		<c:if test = "${exception != null}">
			<c:out value="${label}"></c:out>
		</c:if>
	</span>
	<span class= "fa ${empty direction?'fa-sort':direction eq 'DESC'?'fa-sort-alpha-desc': 'fa-sort-alpha-asc'}"></span>
</a>