<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ taglib uri="http://bluebird.org/tags/core" prefix="bb"%>

<c:set var="basePath" scope="application"></c:set>
<c:set var="adminPath" scope="application">${basePath}/admin</c:set>
<c:set var="resourcePath" scope="application">${basePath}/resources</c:set>
<c:set var="imageResourcePath" scope="application">${resourcePath}/img</c:set>
<c:set var="adminResourcePath" scope="request">${resourcePath}/nimda</c:set>
<c:set var="frontResourcePath" scope="request">${resourcePath}/front</c:set>
<%-- ${pageContext.request.contextPath} --%>
