<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ taglib uri="http://bluebird.org/tags/core" prefix="bb"%>

<%@ attribute name="messages" rtexprvalue="true" required="true" type="org.medtech.fw.presentation.message.ResultMessages"%>

<c:if test="${not empty messages}">
	<c:choose>
		<c:when test="${messages.type eq 'ERROR' }">
			<div class="alert alert-error">
				<strong>Validation error</strong><small>&nbsp;Please fix errors below and try again.</small>
				<ul>
				<c:forEach  items="${messages.getList() }" var="message">
					<li><bb-ex:message code="${message.code }"/></li>
				</c:forEach>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-success">
				<ul>
				<c:forEach  items="${messages.getList() }" var="message">
					<li><bb-ex:message code="${message.code }"/></li>
				</c:forEach>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</c:if>
