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
            </ul>
            
          </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	        <c:if test="${requestScope.form != null}">
	        	<jsp:include page="${requestScope.form}" />
	        </c:if>
        </main>
      </div>
    </div>
	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>