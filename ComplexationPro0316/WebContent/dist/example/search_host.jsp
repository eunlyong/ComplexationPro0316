<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="dao.*" import="model.*" import="servlet.*"
	import="java.io.*" import="java.net.*" import="java.sql.SQLException"
	import="org.apache.jena.atlas.lib.Sync"
	import="com.hp.hpl.jena.query.*" import="com.hp.hpl.jena.rdf.model.*"
	import="com.hp.hpl.jena.shared.Lock" import="java.util.*"
	import="com.hp.hpl.jena.sparql.mgt.SystemInfo"
	import="com.hp.hpl.jena.tdb.*" import="com.hp.hpl.jena.update.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	response.setCharacterEncoding("utf-8");
	
	String hostImage = request.getSession().getServletContext().getRealPath("/"+"HostImage");
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
<script type="text/javascript" charset="utf-8"
	src="../../eunlyong_js/navigation.js"></script>
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

#plus_less_module {
	width: 90px;
	height: 30px;
	float: left;
}

#plus_less_module #quantity {
	height: 36px;
	width: 60px;
	font-size: 18px;
	float: left;
}

#plus_less_button {
	width: 30px;
	height: 30px;
	float: right;
	padding-right: 1px;
	line-height: normal;
}

#plus_less_module #plus {
	position: relative;
	float: right;
	height: 18px;
	width: 30px;
}

#plus_less_module #less {
	position: relative;
	float: right;
	height: 18px;
	width: 30px;
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

h3 {
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
<script type="text/javascript">
	function checkChange1() {
		document.getElementById("mononame").value = "";
		document.getElementsByName("numofmono")[0].value = "";
		document.getElementById("mononame").disabled = true;
		document.getElementsByName("numofmono")[0].disabled = true;
		document.getElementById("searchhost").disabled = false;
		document.getElementById("searchhost").focus();

	}
	function checkChange2() {
		document.getElementById("searchhost").value = "";
		document.getElementById("searchhost").disabled = true;

		document.getElementById("mononame").disabled = false;
		document.getElementsByName("numofmono")[0].disabled = false;
		document.getElementById("mononame").focus();
	}
</script>
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
				<h2>Host DB</h2>
				<ul class="nav nav-tabs" id="myTab">

					<li class="active"><a data-toggle="tab" href="#sectionA"
						id="dg_search">Search</a></li>
					<li><a data-toggle="tab" href="#sectionB" id="dg_insert"
						onclick="link_to_insertHost('<%=basePath%>')">Insert</a></li>
				</ul>
				<div class="tab-content">

					<div id="sectionA" class="tab-pane fade in active distance-re">



						<h3 style="margin-top: 0px; margin-bottom: 3px;">Search</h3>
						<div class="form-group">
							<div class="alert alert-warning">
								<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Tip!</strong>
								Host could be search either by host name or monosaccharide component .
							</div>
							<div class="bs_example">
								<table class="table-condensed ">
									<tbody>
										<tr>
											<td><input type="radio" name="hosts" id="host" value="1"
												checked onclick="checkChange1()" /> Host Name: <input
												type="text" placeholder="Search By Host Name"
												class="form-control-re" id="searchhost"></td>
											<td rowspan="2" align="center">
											    <button style="margin-left:30px; height:90px;" type="submit"
													class="btn btn-primary"
													onClick="searchHost('<%=basePath%>')">
													<span class="glyphicon glyphicon-search"></span> Search
												</button></td>
										</tr>
										<Tr>
											<td>
												<div style="margin-top:3px;">
													<div style="float:left">
														<input type="radio" name="hosts" id="host" value="2"
														onclick="checkChange2()" /> Monosaccharide Name: <input
														type="text" placeholder="Search By Monosaccharide"
														class="form-control-re" id="mononame" name="mononame" disabled>
													</div>	
													<div style="float:left;">
														<div style="float:left; margin-top:9px;">Number of This Monosaccharide:</div>
														<div style="float:left; margin-left:6px;" id="plus_less_module">
															<input id="quantity" name="numofmono" disabled type="text" value="0" maxlength="6"
																onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\\D/g,'')}"
																onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\\\D/g,'')}"
																style="ime-mode: Disabled">
															<div id="plus_less_button">
																<button id="plus" onclick="plusQuantity('selectMono')">гл</button>
																<button id="less" onclick="lessQuantity('selectMono')">гн</button>
															</div>
														</div>
													</div>
												</div>	
											</td>
										</Tr>
									</tbody>
								</table>
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
							ArrayList<Host> hosts = new ArrayList<Host>();
							String mode = "";
							String gethost = "";
							if (null != request.getParameter("mode")) {
								mode = request.getParameter("mode");
								if (mode.equals("1")) {
									gethost = request.getParameter("host");
									hosts = dao.getHostsByName(gethost);
								}else if(mode.equals("2") ){
									String monosaccharide = request.getParameter("mono");
									String numofmono = request.getParameter("numofmono");
									hosts = new Dao().searchByHostComponent(monosaccharide, 
											Integer.parseInt(numofmono));
								}else if(mode.equals("3") ){
									hosts = new Dao().getAllHost();
								}

								if (hosts.size() != 0) {

									for (int i = 0; i < hosts.size(); i++) {
										Host host = hosts.get(i);
										String hostName = host.getHost();
						%>

						<div class="row featurette"
							onClick="gotoHostDetail('<%=basePath%>', '<%=hostName%>')">
							<div class="col-md-2">
								<img 
									src="<%=hostImage %>/<%=host.getHost().replace("-", "") %>.PNG" width="100" height="100"
									alt="Generic placeholder image">
							</div>
							<div class="col-md-6">


								<table class="table-condensed ">
									<tbody>
										<Tr>
											<td>Host:</td>
											<td><%=hostName%></td>
											<td>Molecular Formular:</td>
											<td><%=host.getMolecularFormular().toUpperCase() %></td>

										</Tr>
										<tr>
											<td>Molecular Weight:</td>
											<td><%=host.getAverageMolecularWeight()%></td>

											<td>Solubility:</td>
											<td><%=host.getSolubility()%></td>

										</tr>
										<tr>
											<td>Toxicity:</td>
											<td><%=host.getToxicity()%></td>

											<td>Boiling Point:</td>
											<td></td>

										</tr>
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

					<div id="sectionB" class="tab-pane fade distance-re"></div>
				</div>
			</div>

		</div>

	</div>


</body>
</html>