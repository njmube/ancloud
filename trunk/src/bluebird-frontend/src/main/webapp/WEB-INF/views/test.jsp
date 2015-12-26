<%@ page contentType="text/html; charset=UTF-16" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="additionalHeader">
		<style>
			body {
			}
		</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-body">
		<spring:message code="application.welcome" />
	</tiles:putAttribute>
</tiles:insertDefinition>
