<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="../../assets/ico/favicon.png">

<title>Off Canvas Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="offcanvas.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<style type="text/css">
.bs-example {
	margin-top: 60px;
}
.lead-re {
  margin-bottom: 10px;
  font-size: 13px;
  font-weight: 200;
  line-height: 1;
}
.h2 {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-weight: 500;
  line-height: 1.1;
}
h3{
  margin-top: 3px;
  margin-bottom: 3px;
}

</style>
<body>
	<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Complexation Database</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.navbar -->


	<div class="row row-offcanvas row-offcanvas-right" id="navbarCollapse"
		class="collapse navbar-collapse">

		<div class="container">

		

			
				<div class="bs-example">
					<form role="search" class="navbar-form ">
						<ul class="breadcrumb">
							<li><a href="#">Host Search</a></li>
							<li><a href="#">Insert</a></li>
							<li>
								<div class="form-group">
									<input type="text" placeholder="Search" class="form-control">
									<button type="submit" class="btn btn-primary">
										<span class="glyphicon glyphicon-search"></span> Search
									</button>
								</div>
							</li>
						</ul>
					</form>
				</div>

			
			<hr class="featurette-divider">

			<div class="row featurette">
				<div class="col-md-2">
					<img class="featurette-image img-responsive"
						src="../../image/betacyclodextrin.PNG"
						width="150" height="150" alt="Generic placeholder image">
				</div>
				<div class="col-md-6">
					<h3 >
						Cyclodextrin <span class="text-muted"></span>
					</h3>
					<p class="lead-re">beta-cyclodextrin</p>
				</div>

			</div>
			<hr class="featurette-divider">

			<div class="row featurette">
				<div class="col-md-2">
					<img class="featurette-image img-responsive"
						src="../../image/betacyclodextrin.PNG"
						width="150" height="150" alt="Generic placeholder image">
				</div>
				<div class="col-md-6">
					<h3 class="featurette-heading">
						Cyclodextrin <span class="text-muted"></span>
					</h3>
					<p class="lead-re">molecular weight:</p>
					<p class="lead-re">formular:</p>
					<p class="lead-re">SMILES:</p>
					
				</div>

			</div>
			<hr class="featurette-divider">
			<div class="row featurette">
				<div class="col-md-2">
				<img class="featurette-image img-responsive"
					src="../../image/betacyclodextrin.PNG" width="150" height="150"
					 alt="Generic placeholder image">
				</div>
				<div class="col-md-6">
				<h3 class="featurette-heading">
					Cyclodextrin <span class="text-muted"></span>
				</h3>
				<p class="lead-re">beta-cyclodextrin</p>
				</div>

			</div>
			<hr class="featurette-divider">
		</div>

		
	</div>

	

</body>
</html>