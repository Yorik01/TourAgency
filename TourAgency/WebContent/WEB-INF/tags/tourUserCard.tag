<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<%@ attribute name="tour" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Tour"%>

<div class="card tour-card">
		<img class="card-img-top"
			src="/TourAgency/photo/${tour.photoLink}"
			alt="Card image cap">
		<div class="card-body">
			<h5 class="card-title">
				${tour.hotel.name}
				<c:if test="${tour.isFired() == 1}">
					<i class="fa fa-fire"></i>
				</c:if>
			</h5>
			<p class="card-text">${tour.hotel.info}</p>
			<form action="/TourAgency/controller">
				<input type="hidden" name="action" value="tourInfo"/> 
				<input type="hidden" name="id" value="${tour.id}" />
				<button class="btn btn-primary" type="submit"><fmt:message key="common.open" /></button>
			</form>
		</div>
</div>