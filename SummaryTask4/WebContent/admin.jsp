<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="admin" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<div class="container-fluid" id="admin-content">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-white h-100 sidebar position-fixed">
          <div class="sidebar-sticky">
           <ul class="nav flex-column">
              <li class="nav-item mt-4">
                <a class="nav-link active" href="/TourAgency/controller?action=filterForm">
					<i class="fa fa-home"></i>
					<fmt:message key="index_jsp.title" />
                </a>
              </li>
           	</ul>
           <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            	<span><fmt:message key="admin_jsp.main_info" /></span>
				<i class="fa fa-info-circle"></i>
        	</h6>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="/TourAgency/controller?action=allTours">
					<i class="fa fa-globe-europe"></i>
					<fmt:message key="common.tours" />
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allRoutes">
                <i class="fa fa-route"></i>
					<fmt:message key="common.routes" />
                </a>
              </li>
               <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allHotels">
                <i class="fa fa-hotel"></i>
					<fmt:message key="common.hotels" />
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allTransports">
                <i class="fa fa-plane"></i>
					<fmt:message key="common.transports" />
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/TourAgency/controller?action=allReservations">
                <i class="fa fa-file-alt"></i>
					<fmt:message key="common.reservations" />
                </a>
              </li>
            </ul>
            
            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span><fmt:message key="admin_jsp.discounts" /></span>
				<i class="fa fa-tags"></i>
            </h6>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="#" id="show-set-maxdiscount-modal" data-toggle="modal" data-target="#set-maxdiscount-modal">
					<i class="fa fa-edit"></i>
					<fmt:message key="admin_jsp.set_max_discount" />
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal" data-target="#set-discountstep-for-all-users-modal">
                <i class="fa fa-edit"></i>
					<fmt:message key="admin_jsp.set_step_discount" />
                </a>
              </li>
            </ul>
			
			<c:if test="${sessionScope.user.role eq 'ADMIN'}">
       		  	<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            		<span><fmt:message key="admin_jsp.users" /></span>
				 	<i class="fa fa-users"></i>
    	        </h6>
	            <ul class="nav flex-column mb-2">
            		 <li class="nav-item">
                		<a class="nav-link" href="/TourAgency/controller?action=allUsers">
		                <i class="fa fa-users"></i>
							<fmt:message key="admin_jsp.users" />
               			</a>
		             </li>
		              <li class="nav-item">
                		<a class="nav-link" href="#" data-toggle="modal" data-target="#create-manager-modal">
		                <i class="fa fa-plus"></i>
							<fmt:message key="admin_jsp.create_manager" />
               			</a>
		             </li>
            	</ul>
            </c:if>
          </div>
        </nav>

        <main class="col-md-9 ml-sm-auto col-lg-10 px-4" id="admin-content">
	        <c:choose>
	    	    <c:when test="${requestScope.form != null}">
		        	<jsp:include page="${requestScope.form}" />
		        </c:when>
		        <c:otherwise>
		        	<div id="admin-no-content">
			        	<span><fmt:message key="admin_jsp.choose_option" />	</span>
		        	</div>
		        </c:otherwise>
	        </c:choose>
        </main>
      </div>
    </div>
    
	<div class="modal fade" id="set-maxdiscount-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
				<label for="set-maxdiscount"><fmt:message key="common.max_discount" /></label>
				<input class="form-control" id="set-maxdiscount" type="number" />
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="common.close" /></button>
	        <button type="button" class="btn btn-primary" id="btn-set-maxdiscount"><fmt:message key="common.save" /></button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="set-discountstep-for-all-users-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="admin_jsp.setting_step_discount" /></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<form class="form-group">
				<label for="set-discountstep-for-all-users"><fmt:message key="admin_jsp.step_discount" /></label>
				<input class="form-control" type="number" id="set-discountstep-for-all-users"/>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="common.close" /></button>
	        <button type="button" class="btn btn-primary" id="btn-set-discountstep-for-all-users"><fmt:message key="common.save" /></button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="create-manager-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="admin_jsp.creating_manager" /></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			<form class="form-group">
				<label for="manager-email"><fmt:message key="common.email" /></label>
				<input class="form-control" type="email" id="manager-email"/>
			</form>
			<form class="form-group">
				<label for="manager-password"><fmt:message key="common.password" /></label>
				<input class="form-control" type="password" id="manager-password"/>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="common.close" /></button>
	        <button type="button" class="btn btn-primary" id="btn-create-manager"><fmt:message key="common.save" /></button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/infoModal.jsp" %>
	
	<%@ include file="/WEB-INF/jsp/confirmDeleteModal.jsp" %>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>

	<script src="js/tour.js"></script>
	
	<script>
		$(document).ready(() => {
			const tours = JSON.parse('${requestScope.toursJSON}');
			
			setTours(tours);
	
			let submitForm = null;
			
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
			$(".btn-delete").click(function(e) {
				e.preventDefault();
				$("#confirm-delete-modal").modal("show");
				
				submitForm = $(this).parent("form");
			});
			
			$("#btn-confirm-delete").click(() => {
				submitForm.submit();
			});
		});
	</script>
</body>
</html>