<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="profile" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<div class="container-fluid" id="profile-main">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-white h-100 sidebar position-fixed">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="/TourAgency/controller?action=userEditForm&id=${user.id}">
					<i class="fa fa-user"></i>
					<fmt:message key="profile_jsp.profile_info" />
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=userReservations&id=${user.id}">
				<i class="fa fa-list-alt"></i>
					<fmt:message key="profile_jsp.my_reservations" />
                </a>
              </li>
               <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=userComments&id=${user.id}">
				<i class="fa fa-comment-dots"></i>
					<fmt:message key="profile_jsp.my_comments" />
                </a>
              </li>
            </ul>
            
          </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	        <c:if test="${requestScope.form != null}">
	        	<jsp:include page="${requestScope.form}" />
	        </c:if>
        </main>
      </div>
      	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
    
    <c:set var="userComment" value="" />
	<c:forEach items="${comments}" var="comment">
		<c:if test="${comment.user.id eq sessionScope.user.id}">
			<c:set var="userComment" value="${comment.id}" />
		</c:if>
	</c:forEach>
	
	<c:choose>
		<c:when test="${not empty userComment}">
			<input type="hidden" id="comment-id" value="${userComment}" />
		</c:when>
		<c:otherwise>
			<input type="hidden" id="comment-id" value="" />
		</c:otherwise>
	</c:choose>	
    
    
    <%@ include file="/WEB-INF/jspf/modals/commentModal.jspf" %>
		
	<%@ include file="/WEB-INF/jspf/modals/infoModal.jspf" %>	
	
	<%@ include file="/WEB-INF/jspf/modals/confirmDeleteModal.jspf" %>
		
	<script src="js/tour.js"></script>
	<script src="js/user.js"></script>
	<script>
		$(document).ready(() => {
		    const tours = JSON.parse('${requestScope.toursJSON}');
			
			setTours(tours);
			
			setUserId('${sessionScope.user.id}');
			setErrMessage('<fmt:message key="common.error_save_comment" />');

		    setPasswordEqualityEvent('<fmt:message key="common.passwords_equal" />');
		});
	</script>
</body>
</html>