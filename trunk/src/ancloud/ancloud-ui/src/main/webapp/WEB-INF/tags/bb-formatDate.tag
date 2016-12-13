<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="value" rtexprvalue="true" required="true" type="java.sql.Timestamp"%>

<fmt:formatDate value="${value }" pattern="${sessionScope.CURRENT_ACCOUNT_PROFILE.dateFormat }" />
