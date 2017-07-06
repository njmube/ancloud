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
<%@ attribute name="action" rtexprvalue="true" required="true"%>

<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<i class="fa fa-search"></i>
			<span><bb-ex:message code="sc.common.00008"></bb-ex:message></span>
		</div>
		<div class="panel-body">	
			<form:form modelAttribute="${modelAttribute }" action="${action}" cssClass="form-horizontal bb-form"  method="POST">
				<jsp:doBody />
			</form:form>
		</div>
	</div>
</div>

