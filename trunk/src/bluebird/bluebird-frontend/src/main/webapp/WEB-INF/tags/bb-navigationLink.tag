<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://bluebird.org/tags/ex" prefix="bb-ex"%>
<%@ attribute name="items" rtexprvalue="true" required="true" type="java.lang.Iterable"%>
<%@ attribute name="type" rtexprvalue="true" required="true" type="String"%>
<%@ attribute name="deep" rtexprvalue="true"%>
<c:if test="${empty deep }">
	<c:set var="deep">0</c:set>
</c:if>
<c:if test="${type eq 'sidebar'}">
	<ul class="sidebar-menu">
		<c:if test="${not empty items}">
			<c:forEach items="${items}" var="navigationLink">
				<li class="${not empty navigationLink.children?'treeview':'active'}">
					<c:if test="${empty navigationLink.children}">
						<a href="${basePath }${navigationLink.path}">
							<i class="${navigationLink.icon}"></i>
							<span><spring:message code="${navigationLink.messageKey}"></spring:message></span>
						</a>
					</c:if>
					<c:if test="${not empty navigationLink.children}">
						<a href="javascript:void();">
							<i class="${navigationLink.icon}"></i>
							<span><spring:message code="${navigationLink.messageKey}"></spring:message></span>
						</a>
						<ul class="treeview-menu">
							<bb-ex:navigationLink items="${navigationLink.children}" type="sidebar"/>
						</ul>
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
					<a href="${basePath }${navigationLink.path}">
						<i class="${navigationLink.icon}"></i>
						<span><spring:message code="${navigationLink.displaymessageKey}"></spring:message></span>
					</a>
					<c:if test="${not empty navigationLink.children}">
						<ul class="dropdown-menu">
							<bb-ex:navigationLink items="${navigationLink.children}" deep="${deep+1}" type="top"/>
						</ul>
					</c:if>
				</li>
			</c:forEach>
		</c:if>
	</ul>
</c:if>

