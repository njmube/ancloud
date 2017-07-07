<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="path" rtexprvalue="true" required="true" %>
<%@ attribute name="codelistName" rtexprvalue="true" required="true" %>
<%@ attribute name="cssClass" rtexprvalue="true" required="true" %>
<%@ attribute name="valueMember" rtexprvalue="true" %>
<%@ attribute name="displayMember" rtexprvalue="true" %>
<%@ attribute name="defaultValue" rtexprvalue="true" %>
<%@ attribute name="modelAttribute" rtexprvalue="true" %>
<c:set var="value">
	<spring:eval expression="${modelAttribute}.${path}"></spring:eval>
</c:set>
<c:if test="${empty value}">
	<c:set var="value" value="${defaultValue }"></c:set>
</c:if>
<select name="${path}" class="${cssClass }">
	<c:forEach items="${codelist[codelistName].values() }" var="item">
		<c:set var="optionValue" value="${empty valueMember?item.resourceKey:item[valueMember]}" />
		<option value="${optionValue }" ${value eq optionValue?"selected":""}>
			<c:if test="${empty valueMember and not empty item.value}">
				<c:out value="${item.value}" escapeXml="true"/>
			</c:if>
			<c:if test="${empty valueMember and empty item.value}">
				<bb-ex:message code="${item.messageKey }" htmlEscape="true"/>
			</c:if>
			<c:if test="${not empty valueMember}">
				<c:out value="${item[displayMember]}" escapeXml="true"/>
			</c:if>
		</option>
	</c:forEach>
</select>



