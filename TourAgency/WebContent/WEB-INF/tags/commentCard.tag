<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="comment" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Comment"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<li class="list-group-item">
	<div class="row">
		<div class="col-xs-10 col-md-11">
			<div>
				<div class="mic-info">
					<fmt:message key="common.sender" />:
					 <p>${comment.user.email}</p>
					 <p>${comment.date}</p>
					 <p class="comment-tour-info">${comment.tour.hotel.name} (${comment.tour.startDate} - ${comment.tour.endDate})</p>
				</div>
			</div>
			<div class="comment-text">${comment.text}</div>
			<div class="comment-mark">${comment.mark}</div>
			<div class="action">
				<c:if test="${user.id eq comment.user.id}">
					<button type="button" class="btn btn-success btn-xs btn-edit-comment" data-toggle="modal" data-target="#comment-editor-modal">
						<i class="fa fa-pencil-alt"></i>
					</button>
				</c:if>
				<c:if test="${(user.id eq comment.user.id) or (user.role eq 'ADMIN')}">
					<button type="button" class="btn btn-danger btn-delete-comment btn-xs" data-toggle="modal" data-target="#confirm-delete-modal">
						<i class="fa fa-trash-alt"></i>
					</button>
				</c:if>
				<input type="hidden" class="comment-tour-id" value="${comment.tour.id}" />
				<input type="hidden" class="comment-user-id" value="${comment.user.id}" />
				<input type="hidden" class="comment-id" value="${comment.id}" />
			</div>
		</div>
	</div>
</li>