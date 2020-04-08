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
           <ul class="nav flex-column">
              <li class="nav-item mt-4">
                <a class="nav-link active" href="/TourAgency/controller?action=filterForm">
					<i class="fa fa-home"></i>
					Home page
                </a>
              </li>
           	</ul>
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
                <a class="nav-link" href="#" id="show-set-maxdiscount-modal" data-toggle="modal" data-target="#set-maxdiscount-modal">
					<i class="fa fa-edit"></i>
					Set max discount
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal" data-target="#set-discountstep-for-all-users-modal">
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
                		<a class="nav-link" href="/TourAgency/controller?action=allUsers">
		                <i class="fa fa-users"></i>
							Users
               			</a>
		             </li>
		              <li class="nav-item">
                		<a class="nav-link" href="#" data-toggle="modal" data-target="#create-manager-modal">
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
    
	<div class="modal fade" id="set-maxdiscount-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Setting max discount</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<form class="form-group">
				<label for="select-tour">Tour</label>
				<select class="form-control" id="select-tour">
				</select>
				<label for="set-maxdiscount">Max discount</label>
				<input class="form-control" id="set-maxdiscount" type="number" />
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="btn-set-maxdiscount">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="set-discountstep-for-all-users-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Setting Step of discount</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<form class="form-group">
				<label for="set-discountstep-for-all-users">Step of discount</label>
				<input class="form-control" type="number" id="set-discountstep-for-all-users"/>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="btn-set-discountstep-for-all-users">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="create-manager-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Creating of manager</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<form class="form-group">
				<label for="manager-email">Email</label>
				<input class="form-control" type="email" id="manager-email"/>
			</form>
			<form class="form-group">
				<label for="manager-password">Password</label>
				<input class="form-control" type="password" id="manager-password"/>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="btn-create-manager">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="info-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Info</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<span id="info-modal-message"></span>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script src="js/jquery-3.4.1.min.js" ></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/tour.js"></script>
	
	<script>
		$(document).ready(() => {
			const tours = JSON.parse('${requestScope.tours}');
			
			setTours(tours);
			
			$("#btn-set-discountstep-for-all-users").click(() => {
				let discountStepVal = $("#set-discountstep-for-all-users").val();
				
				$.post("/TourAgency/controller",
						{action: 'setDiscountStepForAllUsers', discountStep: discountStepVal})
					.done(() => {
						$("#info-modal-message").text("Max discount has been set successfully!");
					})
					.fail(() => {
						$("#info-modal-message").text("Error of setting max discount!");
					})
					.always(() => {
						$("#info-modal").modal("show");
					});
				$("#set-discountstep-for-all-users-modal").modal("hide");
			});
			
			$("#btn-set-maxdiscount").click(() => {
				let tourIdVal = $("#select-tour").val();
				let maxDiscountVal = $("#set-maxdiscount").val();
				
				$.post("/TourAgency/controller",
						{action: 'setMaxDiscount', tourId: tourIdVal, maxDiscount: maxDiscountVal})
					.done(() => {
						$("#info-modal-message").text("Max discount has been set successfully!");
					})
					.fail(() => {
						$("#info-modal-message").text("Error of setting max discount!");
					})
					.always(() => {
						$("#info-modal").modal("show");
					});
				$("#set-maxdiscount-modal").modal("hide");
			});
			
			$("#btn-create-manager").click(() => {
				let managerEmail = $("#manager-email").val();
				let managerPassword = $("#manager-password").val();
				
				$.post("/TourAgency/controller",
						{action: 'createManager', email: managerEmail, password: managerPassword})
					.done(() => {
						$("#info-modal-message").text("The manager has been created successfully!");
					})
					.fail(() => {
						$("#info-modal-message").text("Error of creating the manager!");
					})
					.always(() => {
						$("#info-modal").modal("show");
					});
				$("#create-manager-modal").modal("hide");
			});
		});
	</script>
</body>
</html>