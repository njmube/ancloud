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
		class="btn bb-fa-btn fa-plus"><bb-ex:message code="sc.common.00001"></bb-ex:message></a>
	<a href="${basePath}/${modulePath}/modify?id=${entityId}"
		class="btn btn-default bb-fa-btn fa-pencil ${empty entityId?'disabled':'' }"><bb-ex:message code="sc.common.00002"></bb-ex:message></a>
	<a href="${basePath}/${modulePath}/delete?id=${entityId}"
		class="btn btn-default bb-fa-btn fa-times ${empty entityId?'disabled':'' }"><bb-ex:message code="sc.common.00003"></bb-ex:message></a>
	<a href="${basePath}/${modulePath}/search"
		class="btn btn-default bb-fa-btn fa-search"><bb-ex:message code="sc.common.00004"></bb-ex:message></a>
	<a href="${basePath}/${modulePath}/search?all"
		class="btn btn-default bb-fa-btn fa-list"><bb-ex:message code="sc.common.00005"></bb-ex:message></a>
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
