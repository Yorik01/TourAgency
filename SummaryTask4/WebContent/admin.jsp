<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<link href="font/font-awesome/css/all.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<title>Admin</title>
</head>
<body>
	<div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-white h-100 sidebar position-fixed">
          <div class="sidebar-sticky">
           <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            	<span>Main info</span>
				<i class="fa fa-info-circle"></i>
        	</h6>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="/TourAgency/controller?action=allTours">
					<i class="fa fa-globe-europe"></i>
					Tours
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allRoutes">
                <i class="fa fa-route"></i>
					Routes
                </a>
              </li>
               <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allHotels">
                <i class="fa fa-hotel"></i>
					Hotels
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allTransports">
                <i class="fa fa-plane"></i>
					Transports
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allReservations">
                <i class="fa fa-file-alt"></i>
					Reservations
                </a>
              </li>
            </ul>
            
            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Discounts</span>
				<i class="fa fa-tags"></i>
            </h6>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="#">
					<i class="fa fa-edit"></i>
					Set max discount
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                <i class="fa fa-edit"></i>
					Set step of discount
                </a>
              </li>
            </ul>
			
			<c:if test="${sessionScope.user.role eq 'ADMIN'}">
       		  	<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            		<span>Users</span>
				 	<i class="fa fa-users"></i>
    	        </h6>
	            <ul class="nav flex-column mb-2">
            		 <li class="nav-item">
                		<a class="nav-link" href="#">
		                <i class="fa fa-users"></i>
							Users
               			</a>
		             </li>
		              <li class="nav-item">
                		<a class="nav-link" href="#">
		                <i class="fa fa-plus"></i>
							Create manager
               			</a>
		             </li>
            	</ul>
            </c:if>
          </div>
        </nav>

        <main class="col-md-9 ml-sm-auto col-lg-10 px-4 admin-content">
	        <c:if test="${requestScope.form != null}">
	        	<jsp:include page="${requestScope.form}" />
	        </c:if>
        </main>
      </div>
    </div>

	<script src="js/jquery-3.4.1.min.js" ></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>