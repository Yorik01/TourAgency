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
					 <p>${comment.user.email}</p>${reservation.date}
				</div>
			</div>
			<div class="comment-text">${comment.text}</div>
			<div class="comment-mark">${comment.mark}</div>
			<div class="action">
				<button type="button" class="btn btn-success btn-xs btn-edit-comment" title="Edit">
					<i class="fa fa-pencil-alt"></i>
				</button>
				<button type="button" class="btn btn-danger btn-delete-comment btn-xs" title="Delete">
					<i class="fa fa-trash-alt"></i>
				</button>
				<input type="hidden" class="comment-id" value="${comment.id}" />
			</div>
		</div>
	</div>
</li>