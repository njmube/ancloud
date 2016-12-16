<%@ tag language="java" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="formName" rtexprvalue="true" required="true"%>
<%@ attribute name="page" rtexprvalue="true" required="true" type="org.springframework.data.domain.Page" %>
<c:if test="${page.totalPages>1 }">
<div>
	<ul class="pagination">
		<li>
			<span>Page ${page.number+1} / ${page.totalPages}</span>
		</li>
		<li>
			<span><input  type="text" style="padding:0;border:0;width:48px;max-width:200px"/></span>
		</li>
		<li>
			<a href="#" onclick="var $input=$(this).parents('.pagination').find('input');var number=(parseInt($input.val()));if(number>0&number!=NaN){window.location.href='?sort=${fn:replace(fn:replace(page.sort,' ',''),':',',')}&page='+(number>${page.totalPages}?${page.totalPages}-1:(number<1?0:number-1));}else{$input.focus();return false;}">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</li>
		
	</ul>
	<ul class="pagination">
		<li class="${page.number le 0?'disabled':'' }">
			<a href="?page=${page.number-1 }&sort=${fn:replace(fn:replace(page.sort,' ',''),':',',')}" style="${page.number <=0 ?'pointer-events: none;':'' }">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
		</li>
		<c:forEach begin="${(page.number-2) lt 0?0:page.number-2}" end="${(page.number+2) lt page.totalPages?page.number+2:page.totalPages-1 }" varStatus="status">
			<li class="${status.index eq page.number?'active':'' }">
				<a href="?page=${status.index }&sort=${fn:replace(fn:replace(page.sort,' ',''),':',',')}">${status.index + 1 }</a>
			</li>
		</c:forEach>
		<li class="${page.number ge page.totalPages-1?'disabled':'' }">
			<a href="?page=${page.number+1 }&sort=${fn:replace(fn:replace(page.sort,' ',''),':',',')}" style="${page.number ge page.totalPages-1?'pointer-events: none;':'' }">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</li>
	</ul>
</div>
<!-- <div class="input-group pull-right" style="width:100px;margin-right:10px;"> -->
<%-- 	<span class="input-group-addon">Page ${page.number+1} of ${page.totalPages}</span> --%>
<!-- 	<input type="text" class="form-control" style="width:48px"/> -->
<%-- 	<a class="input-group-addon" href="#" onclick="var number=(parseInt($(this).prev('input').val()));window.location.href='?sort=${fn:replace(fn:replace(page.sort,' ',''),':',',')}&page='+(number>${page.totalPages}?${page.totalPages}-1:(number<1?0:number-1));"> --%>
<!-- 			Go -->
<!-- 	</a> -->
<!-- </div> -->
</c:if>