<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ attribute name="name" rtexprvalue="true" required="true"%>
<%@ attribute name="queryStringName" rtexprvalue="true" required="true"%>
<%@ attribute name="displayText" rtexprvalue="true" required="false"%>
<%@ attribute name="value" rtexprvalue="true" required="false"%>
<%@ attribute name="cssClass" rtexprvalue="true" required="false"%>
<%@ attribute name="sourcePath" rtexprvalue="true" required="true"%>
<%@ attribute name="displayProperties" rtexprvalue="true" required="true"%>
<%@ attribute name="sourceType" rtexprvalue="true"%>
<%@ attribute name="parameters" rtexprvalue="true"%>

<input name="${name }Autocomplete" 
		value="${displayText}" 
		type="text" 
		class="bb-autocomplete ${cssClass }" 
		data-source-path="${sourcePath }" 
		data-source-type="${not empty sourcePath? sourcePath:'0'  }" 
		data-parameter='${parameters }'
		data-query-string-name="${queryStringName }"
		data-display-properties="${displayProperties }" ></input>
<input name="${name }" value="${value}" type="hidden"></input>
