<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="entityId" rtexprvalue="true" %>
<%@ attribute name="modulePath" rtexprvalue="true" required="true" %>
<%@ attribute name="basePath" rtexprvalue="true" required="true" %>

<div class="bb-action-group-header btn-group">
	<a href="${basePath}/${modulePath}/register"
		class="btn bb-fa-btn fa-plus"><spring:message code="sc.common.00001"></spring:message></a>
	<a href="${basePath}/${modulePath}/modify?id=${entityId}"
		class="btn btn-default bb-fa-btn fa-pencil ${empty entityId?'disabled':'' }"><spring:message code="sc.common.00002"></spring:message></a>
	<a href="${basePath}/${modulePath}/delete?id=${entityId}"
		class="btn btn-default bb-fa-btn fa-times ${empty entityId?'disabled':'' }"><spring:message code="sc.common.00003"></spring:message></a>
	<a href="${basePath}/${modulePath}/search"
		class="btn btn-default bb-fa-btn fa-search"><spring:message code="sc.common.00004"></spring:message></a>
	<a href="${basePath}/${modulePath}/search?all"
		class="btn btn-default bb-fa-btn fa-list"><spring:message code="sc.common.00005"></spring:message></a>
	<div class="btn-group">
		<a  class="btn btn-default dropdown-toggle"
			data-toggle="dropdown">Action</a>
		<ul class="dropdown-menu"
			role="menu">
			<li><a href="#">Tablet</a></li>
			<li><a href="#">Smartphone</a></li>
		</ul>
	</div>
</div>
