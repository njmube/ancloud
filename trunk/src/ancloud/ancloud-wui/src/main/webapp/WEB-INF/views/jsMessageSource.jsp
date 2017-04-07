<%@ page contentType="text/javascript; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
window.messageSource ={<c:forEach items="${messages.keySet()}" var="locale">"${locale}":{<c:forEach items="${messages[locale].keySet() }" var="messageKey">"${messageKey}":"${messages[locale][messageKey]}",</c:forEach>},</c:forEach>};
