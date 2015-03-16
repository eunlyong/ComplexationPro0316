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
h3{
  margin-top: 3px;
  margin-bottom: 3px;
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
				<h2>Monosaccharide DB</h2>
				<ul class="nav nav-tabs"  id="myTab">
					<li class="active"><a data-toggle="tab" href="#sectionA">Search</a></li>
					<li><a data-toggle="tab" href="#sectionB" onclick="link_to_insertMono('<%=basePath%>')">Insert</a></li>

				</ul>
				<div class="tab-content" >
					<div id="sectionA" class="tab-pane fade in active distance-re">
						<h3>Search</h3>
			<%
			String monoName = request.getParameter("mono");
			Dao dao = new Dao();
			Monosaccharide mono = dao.getMonosaccharideInfos(monoName);
			
			
					%>

		
			<div class="bs-example">
				<table class="table table-condensed">
				
					<tbody>
						<tr class="info">
							<td><h4 class="featurette-heading">Monosaccharide:</h4></td>
							<td><%=monoName %></td>
							<td><h4 class="featurette-heading">Anomer:</h4></td>
							<td><%=mono.getAnomer() %></td>

						</Tr>
						<tr>
							<td class="warning">Alias:</td>
							<td><%=mono.getAliasName() %></td>

							<td class="warning">Notation Scheme:</td>
							<td><%=mono.getNotationScheme() %></td>

						</tr>
						<tr>
							<td class="warning">Ring Start:</td>
							<td><%=mono.getRingStart() %></td>

							<td class="warning">Ring End:</td>
							<td><%=mono.getRingEnd() %></td>

						</tr>
						<tr>
							<td class="warning">Absolute Configuration:</td>
							<td><%=mono.getAbsoluteConfiguration() %></td>

							<td class="warning">Relative Configuration:</td>
							<td><%=mono.getRelativeConfiguration() %></td>
						</tr>
						<tr>
							<td class="warning">Ring Size:</td>
							<td><%=mono.getSize() %></td>

							<td class="warning">Basetype Id:</td>
							<td><%=mono.getBasetype()%></td>

						</tr>
						<tr>
							<td class="warning">Substituent:</td>
							<td><%=mono.getSubstituent() %></td>

							<td class="warning">Substituent Type:</td>
							<td><%=mono.getSubstituentType() %></td>

						</tr>
						<tr>
							<td class="warning">Linkage Type:</td>
							<td><%=mono.getLinkageType() %></td>

							<td class="warning">Basetype Linkage Position:</td>
							<td><%=mono.getBasetypeLinkagePosition() %></td>

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

	</div>

</body>
</html>