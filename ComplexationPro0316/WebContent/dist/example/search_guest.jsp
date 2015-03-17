<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="dao.*" import="model.*" import="servlet.*"
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
	
	String guestImage = request.getSession().getServletContext().getRealPath("/"+"GuestImage");
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
<script type="text/javascript" charset="utf-8" src="../../eunlyong_js/function.js"></script>
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

h4 {
	margin-top: 3px;
	margin-bottom: 3px;
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
				<h2>Guest DB</h2>
				<ul class="nav nav-tabs" id="myTab">
					
						<li class="active"><a data-toggle="tab" href="#sectionA">Search</a></li>
					<li><a data-toggle="tab" href="#sectionB" onclick="link_to_insertGuest('<%=basePath%>')">Insert</a></li>
					

				</ul>
				<div class="tab-content">
					
						<div id="sectionA" class="tab-pane fade in active distance-re">
					
						<h3>Search</h3>
						<div class="form-group">
							
							<div class="bs_example">
								<input type="text" placeholder="Search Guest"
									class="form-control-re" id="searchguest">
								<button type="submit" class="btn btn-primary"
									onClick="searchGuest('<%=basePath%>')">
									<span class="glyphicon glyphicon-search"></span> Search
								</button>
							</div>
						</div>
						<hr class="featurette-divider">

						<%
							System.out.println("MODE: " + request.getParameter("mode"));
							Dataset dataset = TDBFactory.createDataset(Variables.directory);
							//dataset.begin(ReadWrite.READ) ;

							Model model = dataset.getDefaultModel();
							//model.enterCriticalSection(Lock.Write);
							// Create a new query
							Dao dao = new Dao();
							ArrayList<Guest> guests = new ArrayList<Guest>();
							String mode = "";
							String getguest = ""; 
							if (null != request.getParameter("mode")) {
								mode = request.getParameter("mode");
								if (mode.equals("1")) {
									getguest = request.getParameter("guest");
									guests = dao.getGuestsByName(getguest);

								}else if(mode.equals("2") ){
									guests = dao.getAllGuest();
								}

								if (guests.size() != 0) {
									for (int i = 0; i < guests.size(); i++) {
										Guest guest = guests.get(i);
										if(mode.equals("2")){
											getguest = guest.getGuest();
										}
									
						%>

						<div class="row featurette"
							onClick="gotoGuestDetail('<%=basePath%>','<%=getguest%>')">
							<div class="col-md-2">

								<img
									src="<%=guestImage %>/<%=guest.getMolId()%>.png"
									width=150 height=120 alt="Generic placeholder image" />
							</div>
							<div class="col-md-6">

								<table class="table-condensed ">
									<tbody>
										<Tr>
											<td>Guest:</td>
											<td><%=getguest%></td>
											<td>PubChem CID:</td>
											<td><%=guest.getMolId()%></td>

										</Tr>
										<tr>
											<td>Molecular Weight:</td>
											<td><%=guest.getMolecularWeight()%></td>

											<td>Molecular Formular:</td>
											<td><%=guest.getMolecularFormular()%></td>

										</tr>
										<tr>
											<td>IUPAC:</td>
											<td colspan="3"><%=guest.getIupacName()%></td>



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
					
						
					</div>
					</div>


			</div>

		</div>

	</div>
		



</body>
</html>