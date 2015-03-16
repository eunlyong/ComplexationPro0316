<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="dao.*" import="model.Complexation" import="servlet.*"
	import="java.io.*" import="java.net.*" import="java.sql.SQLException"
	import="org.apache.jena.atlas.lib.Sync"
	import="com.hp.hpl.jena.query.*" import="com.hp.hpl.jena.rdf.model.*"
	import="com.hp.hpl.jena.shared.Lock" import="java.util.*"
	import="com.hp.hpl.jena.sparql.mgt.SystemInfo"
	import="com.hp.hpl.jena.tdb.*" import="com.hp.hpl.jena.update.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setCharacterEncoding("utf-8");
	Dataset dataset = TDBFactory.createDataset(Variables.directory);
	//dataset.begin(ReadWrite.READ) ;

	Model model = dataset.getDefaultModel();
	//model.enterCriticalSection(Lock.Write);
	// Create a new query
	Dao dao = new Dao();
%>
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
<script type="text/javascript" charset="utf-8"
	src="../../eunlyong_js/function.js"></script>
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
	margin-top: 10px;
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

.featurette-image {
	margin: 0px;
}

h4 {
	margin-top: 2px;
	margin-bottom: 2px;
}

table {
	padding: 2px;
}

.distance-re {
	margin: 5px;
}

.form-control {
	display: block;
	width: 40%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.428571429;
	color: #555555;
	vertical-align: middle;
	background-color: #ffffff;
	border: 1px solid #cccccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	-webkit-transition: border-color ease-in-out 0.15s, box-shadow
		ease-in-out 0.15s;
	transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
}

.form-control-re {
	width: 35%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.428571429;
	color: #555555;
	vertical-align: middle;
	background-color: #ffffff;
	border: 1px solid #cccccc;
}
</style>
<body>
	<%@include file="navigation.jsp"%>

	<div class="row row-offcanvas row-offcanvas-right" id="navbarCollapse"
		class="collapse navbar-collapse">

		<div class="container">
			<div class="bs_example">
				<div></div>
				<h2>DB</h2>
			</div>
			<div class="bs-example">
				<h2>Complexation DB</h2>
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a data-toggle="tab" href="#sectionA">Search</a></li>
					<li><a data-toggle="tab" href="#sectionB">Insert</a></li>

				</ul>
				<div class="tab-content">
					<div id="sectionA" class="tab-pane fade in active distance-re">
						<h3>Search</h3>

						<div class="form-group">
							<div class="alert alert-warning">
								<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Tip!</strong>
								There was a problem with your network connection.
							</div>
							<div class="bs_example">
								<input type="text" placeholder="Search Host"
									class="form-control-re" id="searchhost"> <input
									type="text" placeholder="Search Guest" class="form-control-re"
									id="searchguest">
								<button type="submit" class="btn btn-primary"
									onClick="searchComplexation('<%=basePath%>')">
									<span class="glyphicon glyphicon-search"></span> Search
								</button>
							</div>
						</div>
						<hr class="featurette-divider">

						<%
							System.out.println("MODE: " + request.getParameter("mode"));
							ArrayList<Complexation> complexations = new ArrayList<Complexation>();
							if (null != request.getParameter("mode")) {
								
								String mode = request.getParameter("mode");
								System.out.println("mode:" + mode);
								String gethost = "";
								String getguest = "";
								if (mode.equals("1")) {
									gethost = request.getParameter("host");
									complexations = new Dao().searchByHostName(gethost);
									
								} else if (mode.equals("2")) {
									getguest = request.getParameter("guest");
									complexations = new Dao().searchByGuestName(getguest);
								} else if (mode.equals("3")) {
									gethost = request.getParameter("host");
									getguest = request.getParameter("guest");
									complexations = new Dao()
											.searchByHostGuestName(gethost, getguest);
								}

								//complexations = dao.getAllComplexation();
								if (complexations.size() != 0) {
									for (int i = 0; i < complexations.size(); i++) {
										Complexation complexation = complexations.get(i);
										String comp = complexation.getComplexation()
												.substring(
														complexation.getComplexation().indexOf(
																"#") + 1);
										comp = "complexation:" + comp;
										//Resource complexation = results.next().getResource("complexation");
										System.out.println("complexation= " + comp);
										if(mode.equals("1")){
											getguest = complexation.getGuest().getGuest();
										}else if(mode.equals("2")){
											gethost = complexation.getHost().getHost();
										}
										//Complexation complexation =  new Dao().getComplexationInfos(comp);
						%>

						<div class="row featurette"
							onClick="gotoComplexationDetail('<%=comp%>')">
							<div class="col-md-2">
								<img class="featurette-image img-responsive"
									src="../../image/betacyclodextrin.PNG" width="100" height="100"
									alt="Generic placeholder image">
							</div>
							<div class="col-md-6">

								<table class="table-condensed ">
									<tbody>
										<Tr>
											<td><h4 class="featurette-heading">Host:</h4></td>
											<td><%=gethost %></td>
											<td><h4 class="featurette-heading">Guest:</h4></td>
											<td><%=getguest%></td>
										</Tr>
										<tr>
											<td>Binding Constant:</td>
											<td><%=complexation.getBindingConstant()%></td>
											<td>Stoichiometry Binding:</td>
											<td><%=complexation.getStoichiometry()%></td>
										</tr>
									</tbody>
								</table>


							</div>

						</div>
						<hr class="featurette-divider">
						<%
							}
								} else {
						%>
						<table class="table-condensed ">
							<tbody>
								<Tr>
									<td>Sorry. No search result.</td>
								</Tr>
							</tbody>
						</table>

						<%
							}

							}
						%>


					</div>
					<div id="sectionB" class="tab-pane fade distance-re">
						<h3>Insert</h3>
						<%@include file="insert_complexation.jsp"%>
					</div>
				</div>


			</div>

		</div>

	</div>




</body>
</html>