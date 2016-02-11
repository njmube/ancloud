<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ attribute name="name" rtexprvalue="true" required="true"%>
<%@ attribute name="value" rtexprvalue="true" required="false"%>
<%@ attribute name="cssClass" rtexprvalue="true" required="false"%>


<input name="${name }" value="${value}" type="text" class="bb-autocomplete ${cssClass }"></input>