<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ taglib uri="http://bluebird.org/tags/core" prefix="bb"%>
<%@ attribute name="modelAttribute" rtexprvalue="true" required="true"%>

<div class="col-md-12">
	<div class="panel panel-primary panel-search-result">
		<div class="panel-heading">
			<i class="fa fa-list"></i>
			<span><bb-ex:message code="sc.common.00006"></bb-ex:message></span>
			&nbsp;<span class="badge">${page.totalElements}</span>
		</div>
		<div class="panel-body">
			<c:if test="${page.totalElements > 0}">
				<jsp:doBody />
				<bb-ex:pagination formName="${modelAttribute }"></bb-ex:pagination>
			</c:if>
		</div>
	</div>
</div>
