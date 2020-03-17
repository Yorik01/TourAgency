<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="font/font-awesome/css/all.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<title>Admin</title>
</head>
<body>
	<div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-white h-100 sidebar">
          <div class="sidebar-sticky">
           <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Tours</span>
				<i class="fa fa-plane"></i>
            </h6>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="#">
					<i class="fa fa-search"></i>
					Search
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                <i class="fa fa-plus"></i>
					Add
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
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
                <a class="nav-link active" href="#">
					<i class="fa fa-cog"></i>
					Set max discount
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                <i class="fa fa-cog"></i>
					Set step of discount
                </a>
              </li>
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Users</span>
			  <i class="fa fa-user"></i>
            </h6>
            <ul class="nav flex-column mb-2">
             <li class="nav-item">
                <a class="nav-link" href="#">
                <i class="fa fa-search"></i>
					Search
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                <i class="fa fa-plus"></i>
					Create manager
                </a>
              </li>
              <li class="nav-item">
				<a class="nav-link" href="#">
                <i class="fa fa-ban"></i>
					Banned
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        	<%@include file="/WEB-INF/jsp/addHotel.jsp"%>
        </main>
      </div>
    </div>

	<script src="js/jquery-3.4.1.min.js" ></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>