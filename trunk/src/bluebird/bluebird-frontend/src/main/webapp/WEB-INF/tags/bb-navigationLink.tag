<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ attribute name="items" rtexprvalue="true" required="true" type="java.lang.Iterable"%>
<%@ attribute name="type" rtexprvalue="true" required="true" type="String"%>
<%@ attribute name="deep" rtexprvalue="true"%>
<c:if test="${empty deep }">
	<c:set var="deep">0</c:set>
</c:if>

<c:if test="${type eq 'sidebar'}">
	<ul class="${deep gt 0?'treeview-menu':'sidebar-menu'}">
		<c:if test="${not empty items}">
			<c:forEach items="${items}" var="navigationLink">
				<li class="${not empty navigationLink.children?'treeview ':''} ${fn:startsWith(requestScope['javax.servlet.forward.request_uri'],basePath.concat(navigationLink.path))?'active':''}">
					<c:if test="${empty navigationLink.children}">
						<a href="${basePath }${navigationLink.path}" >
							<i class="${navigationLink.icon}"></i>
							<span><spring:message code="${navigationLink.messageKey}"></spring:message></span>
						</a>
					</c:if>
					<c:if test="${not empty navigationLink.children}">
						<a href="javascript:void();">
							<i class="${navigationLink.icon}"></i>
							<span><spring:message code="${navigationLink.messageKey}"></spring:message></span>
						</a>
						<bb-ex:navigationLink items="${navigationLink.children}" deep="${deep+1}" type="sidebar"/>
					</c:if>
				</li>
			</c:forEach>
		</c:if>
	</ul>
</c:if>
<c:if test="${type eq 'top'}">
	<ul class="nav navbar-nav">
		<c:if test="${not empty items}">
			<c:forEach items="${items}" var="navigationLink">
				<li class="${not empty navigationLink.children?(deep gt 0?'dropdown-submenu':'dropdown'):''}">
					<c:if test="${empty navigationLink.children}">
						<a href="${basePath }${navigationLink.path}">
							<i class="${navigationLink.icon}"></i>
							<span><spring:message code="${navigationLink.messageKey}"></spring:message></span>
						</a>
					</c:if>
					<c:if test="${not empty navigationLink.children}">
						<a href="javascript:void()">
							<i class="${navigationLink.icon}"></i>
							<span><spring:message code="${navigationLink.messageKey}"></spring:message></span>
						</a>
						<ul class="dropdown-menu">
							<bb-ex:navigationLink items="${navigationLink.children}" deep="${deep+1}" type="top"/>
						</ul>
					</c:if>
				</li>
			</c:forEach>
		</c:if>
	</ul>
</c:if>

