<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="modal fade" id="comment-editor-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="admin_jsp.setting_max_discount" /></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<form class="form-group">
				<label for="select-tour"><fmt:message key="common.tour" /></label>
				<select class="form-control" id="select-tour">
				</select>
				<label for="input-comment-text"><fmt:message key="common.comment_text" /></label>
				<input class="form-control" id="input-comment-text" />
				<label for="input-comment-mark"><fmt:message key="common.comment_mark" /></label>
				<input class="form-control" id="input-comment-mark" type="number" min="1" max="10" />
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="common.close" /></button>
	        <button type="button" class="btn btn-primary" id="btn-save-comment"><fmt:message key="common.save" /></button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>