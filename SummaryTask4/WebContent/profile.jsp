<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<html>
<head>
	<meta charset="UTF-8">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<link href="font/font-awesome/css/all.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<title><fmt:message key="profile_jsp.title" /></title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<div class="container-fluid">
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

</body>
</html>