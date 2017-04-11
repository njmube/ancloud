<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
 
<%@ attribute name="jodaEnabled" rtexprvalue="true" required="true"  type="java.lang.Boolean"%>
<%@ attribute name="value" rtexprvalue="true" type="java.sql.Timestamp"%>
<%@ attribute name="pattern" %>
<%@ attribute name="jodaValue" rtexprvalue="true" type="org.joda.time.DateTime"%>

<c:if test="${!jodaEnabled }">
	<fmt:formatDate value="${value }" pattern="${not empty pattern? pattern: sessionScope.CURRENT_ACCOUNT_PROFILE.dateFormat }" />
</c:if>
<c:if test="${jodaEnabled }">
	<joda:format value="${jodaValue }" pattern="${not empty pattern? pattern: sessionScope.CURRENT_ACCOUNT_PROFILE.dateFormat }" dateTimeZone="+0700"/> 
</c:if>
