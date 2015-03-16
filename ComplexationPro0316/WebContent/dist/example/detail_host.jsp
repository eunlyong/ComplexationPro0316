<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="dao.*" import="model.*" import="model.Complexation"
	import="servlet.*" import="java.io.*" import="java.net.*"
	import="java.sql.SQLException" import="org.apache.jena.atlas.lib.Sync"
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
%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
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

<script type="text/javascript">
	function clickHostDB() {
		window.location.href = "showcompound.jsp";
	}
</script>
</head>

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
				
						<li class="active"><a data-toggle="tab" href="#sectionA" id="dg_search">Search</a></li>
					    <li><a data-toggle="tab" href="#sectionB" id="dg_insert" onclick="link_to_insertHost('<%=basePath%>')" >Insert</a></li>
				</ul>
				<div class="tab-content">
				
						<div id="sectionA" class="tab-pane fade in active distance-re">
			<%
				String hostName = request.getParameter("host");
				Dao dao = new Dao();
				Host host = dao.getHostInfos(hostName);
				
				String mono = host.getMono().getMonosaccharide().substring(
						host.getMono().getMonosaccharide().indexOf(
								"#") + 1);
				
			%>
			<div class="bs-example">
				<table class="table table-condensed">

					<tbody>


						<tr class="info">
							<td colspan="4"><h4>Host Property</h4></td>
						</tr>
						<tr>
							<td class="warning">host name:</td>
							<td><%=hostName %></td>
							<td class="warning">average molecule weight:</td>
							<td><%=host.getMonoisotopicMolecularWeight() %></td>

						</tr>
						<tr>
							<td class="warning">molecular formular:</td>
							<td><%=host.getMolecularFormular().toUpperCase() %></td>
							<td class="warning">linkage position:</td>
							<td></td>

						</tr>
						<tr>
							<td class="warning">boiling point:</td>
							<td><%=host.getBoilingPoint() %></td>

							<td class="warning">melting point:</td>
							<td><%=host.getMeltingPoint() %></td>
						</tr>
						<tr>
							<td class="warning">monoisotopic molecule weight:</td>
							<td><%=host.getMonoisotopicMolecularWeight() %></td>
							<td class="warning">msdb id:</td>
							<td></td>
						</tr>

						<tr>
							<td class="warning">solubility:</td>
							<td><%=host.getSolubility() %></td>
							<td class="warning">toxicity:</td>
							<td><%=host.getToxicity() %></td>
						</tr>

						<tr class="info">
							<td colspan="4"><h4>NMR Property</h4></td>
						</tr>
						<tr>
							<td colspan="4">
							<img 
						src="<%=basePath %>HostImage/<%=hostName.replace("-", "") %>.PNG" width="100" height="100"
						alt="Generic placeholder image">
							</td>
						</tr>
						<tr>
							<td class="warning">linkage:</td>
							<td></td>
							<td class="warning">residue:</td>
							<td></td>
						</tr>

						<tr>
							<td class="warning">chemical shift:</td>
							<td></td>
							<td class="warning">chemical shift max:</td>
							<td></td>
						</tr>
						<tr>
							<td class="warning">chemical shift min:</td>
							<td></td>
							<td class="warning">coupling constant:</td>
							<td></td>
						</tr>
						<tr>
							<td class="warning">multiplicity:</td>
							<td></td>
							<td class="warning">carbon number:</td>
							<td></td>
						</tr>
						<tr class="info">
							<td colspan="4"><h4>Monosaccharide Component: </h4>
							</td>
						</tr>
						<tr>
							<td>
							<%=mono %></td>
							<Td>Number Of This Monosaccharide: </Td>
							<td>
							<%=host.getNumberOfMono() %></td>
							
							<td>
							<a href="#" style="float:right; margin-right:3px; padding-top:6px" 
			onClick="gotoMonoDetail('<%=basePath %>', '<%=mono %>')">
			Click here to see more information of Monosaccharide</a>
							</td>
						</tr>

					</tbody>


				</table>
			

					</div>
					<div id="sectionB" class="tab-pane fade distance-re">
					
					</div>
				</div>
			</div>

		</div>

	</div>
</body>
</html>