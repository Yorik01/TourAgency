<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<div class="container">
    <div class="row">
        <div class="panel panel-default widget w-100">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-comment"></span>
                <h3 class="panel-title">
                    <fmt:message key="common.comments" /></h3>
            </div>
            <div class="panel-body">
            	<c:choose>
            		<c:when test="${not empty comments}">
		                <ul class="list-group">
		                	<c:forEach items="${comments}" var="comment">
		                		<t:commentCard
		                			comment="${comment}"
		                		 />
		                	</c:forEach>
		                </ul>
	                </c:when>
	                <c:otherwise>
	                	<h4>
	                		<fmt:message key="common.no_comments" />
	                	</h4>
	                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>