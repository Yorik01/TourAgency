<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ attribute name="title" required="true" type="java.lang.String"%>
<%@ attribute name="info" required="true" type="java.lang.String"%>
<%@ attribute name="photo" required="true" type="java.lang.String"%>
<%@ attribute name="fired" required="true" type="java.lang.Byte"%>
<%@ attribute name="id" required="true" type="java.lang.Integer"%>

<div class="tour-cards">
	<div class="card tour-card">
		<img class="card-img-top"
			src="${photo}"
			alt="Card image cap">
		<div class="card-body">
			<h5 class="card-title">
				${title}
				<c:if test="${fired == 1}">
					<i class="fa fa-fire"></i>
				</c:if>
			</h5>
			<p class="card-text">${info}</p>
			<form action="/SummaryTask4/controller">
				<input type="hidden" name="action" value="tourInfo"/> 
				<input type="hidden" name="id" value="${id}" />
				<button class="btn btn-primary" type="submit">Look</button>
			</form>
		</div>
	</div>
</div>