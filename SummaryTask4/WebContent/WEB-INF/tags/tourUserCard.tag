<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ attribute name="title" required="true" type="java.lang.String"%>
<%@ attribute name="info" required="true" type="java.lang.String"%>
<%@ attribute name="photo" required="true" type="java.lang.String"%>
<%@ attribute name="fired" required="true" type="java.lang.Byte"%>

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
			<a href="#" class="btn btn-primary">Look</a>
		</div>
	</div>
</div>