<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="login" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<form id="form-login" class="form-center" action="/TourAgency/controller" method="post">
		<h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login_jsp.greeting" /></h1>
		<label for="inputEmail" class="sr-only"><fmt:message key="login_jsp.email" /></label> 
		<input type="email" id="input-email" class="form-control mb-3"
			placeholder="<fmt:message key="common.email" />" name="email" required autofocus />
			 <label for="inputPassword" class="sr-only"><fmt:message key="common.password" /></label>
		<input type="password" id="input-password" class="form-control"
				placeholder="<fmt:message key="common.password" />" name="password" required />
		<button id="btn-login" class="btn btn-primary btn-block mt-3" type="submit">
			<fmt:message key="login_jsp.login" />
		</button>
		<span id="reg-offering"><fmt:message key="login_jsp.account_question" />
			<a href="/TourAgency/signup.jsp"><fmt:message key="common.signup" /></a>
		</span>
		<input type="hidden" name="action" value="login" />
	</form>
	
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>