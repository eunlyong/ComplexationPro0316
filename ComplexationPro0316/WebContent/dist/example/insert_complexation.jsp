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
<script type="text/javascript" charset="utf-8" src="../../eunlyong_js/navigation.js"></script>
<script type="text/javascript" charset="utf-8" src="../../eunlyong_js/function.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="../../assets/ico/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
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
				<h2>Complexation DB</h2>
				<ul class="nav nav-tabs" id="myTab">
					<li><a data-toggle="tab" href="#sectionA" onclick="link_to_searchComplexation('<%=basePath%>')">Search</a></li>
					<li class="active"><a data-toggle="tab" href="#sectionB">Insert</a></li>

				</ul>
				<div class="tab-content">

					<div id="sectionA" class="tab-pane fade  distance-re">
					</div>

					<div id="sectionB" class="tab-pane fade in active distance-re">
					<h3 style="margin-top:0px; margin-bottom:3px;">Insert</h3>
	<table class="table table-condensed">

		<tbody>
			<tr>
				<td colspan="4"><h4>Complexation Result:</h4></td>
			</tr>
			<tr>
				<td>Host:</td>
				<td><span> <select id="has_host"
						onChange="hostSelect()">
							<option selected value="">SELECT</option>
							<%
								/*	Dataset dataset = TDBFactory.createDataset(Variables.directory);
									//dataset.begin(ReadWrite.READ) ;

									Model model = dataset.getDefaultModel();
									//model.enterCriticalSection(Lock.Write);
									// Create a new query
									Dao dao1 = new Dao();  */
								ArrayList<Host> hosts = new ArrayList<Host>();
								hosts = new Dao().getAllHost();

								for (int i = 0; i < hosts.size(); i++) {
									Host host = hosts.get(i);
									String hostName = host.getHost().substring(
											host.getHost().indexOf("#") + 1);
							%>
							<option value="<%=hostName%>"><%=hostName%></option>
							<%
								}
							%>
					</select>
				</span> <input type="button" class="btn btn-info" id="inserNewHostButton"
					value="Insert New Host" onClick="gotoInsertHostNavi()"></td>
				<td>Guest:</td>
				<td><span> <select id="has_guest"
						onChange="guestSelect()">
						<option selected value="">SELECT</option>
							<%
								ArrayList<Guest> guests = new ArrayList<Guest>();
								guests = new Dao().getAllGuest();

								for (int i = 0; i < guests.size(); i++) {
									Guest guest = guests.get(i);
									String guestName = guest.getGuest().substring(
											guest.getGuest().indexOf("#") + 1);
							%>
							<option value="<%=guestName%>"><%=guestName%></option>
							<%
								}
							%>
					</select>
				</span> <input type="button" class="btn btn-info" id="inserNewGuestButton"
					value="Insert New Guest" onClick="gotoInsertGuestNavi()"></td>
			</tr>
			<tr>
				<td>Binding Constant value:</td>
				<td><input type="text" id="binding_constant" /></td>
				<td>Stoichiometry Binding:</td>
				<td><input type="text" id="stoichiometry_binding" /></td>
			</tr>
			<tr>
				<td>Article title:</td>
				<td><input type="text" id="article" /></td>
				<td>Journal:</td>
				<td><input type="text" id="journal" /></td>
			</tr>
			<tr>
				<td>Phenomena Value: <span><select id="has_phenomena">
							<option selected value="phenomena_solubility">Solubility</option>
							<option value="phenomena_stability">Stability</option>
							<option value="phenomena_fluorescence">Fluorescence</option>
							<option value="phenomena_enzyme_activity">EnzymeActivity</option>
							<option value="phenomena_bioavailability">Bioavailability</option>
					</select> </span>
				</td>
				<td><input type="text" id="phenomena_value" /></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="4"><h4>Experimental Condition:</h4></td>
			</tr>
			<tr>
				<td>Temperature:</td>
				<td><input type="text" id="used_temperature" />C</td>
				<td>Solvent:</td>
				<td><input type="text" id="used_solvent" /></td>
			</tr>

			<tr>
				<td>PH:</td>
				<td><input type="text" id="used_ph" /></td>
				<td>Reaction Time:</td>
				<td><input type="text" id="used_time" />H</td>
			</tr>
			<tr>
				<td colspan="4"><h4>NMR Property:</h4></td>
			</tr>
			
			<tr>
				<td>Host Concentration:</td>
				<td><input type="text" id="host_concentration" />mM</td>
				<td>Guest Concentration:</td>
				<td><input type="text" id="guest_concentration" />mM</td>
			</tr>

			<tr>
				<td>Buffer:</td>
				<td><input type="text" id="buffer" /></td>
				<td>Frequency:</td>
				<td><input type="text" id="frequency" /></td>
			</tr>
			<tr>
				<td>PH:</td>
				<td><input type="text" id="ph" /></td>
				<td>Temperature:</td>
				<td><input type="text" id="temperature" />C</td>
			</tr>
			<tr>
				<td>Mixing Time:</td>
				<td><input type="text" id="mixing_time" />ms</td>
				<td>NMR Spectrum:</td>
				<td><input type="file" id="image1" name="myfile"></td>
			</tr>
			<tr>
				<td colspan="4">
					<button type="button"
						style="margin-top: 9px; height: 45px; width: 210px"
						class="btn btn-primary" onClick="submitComplexation('<%=basePath%>')">Submit
						Complexation</button>
				</td>
			</tr>


		</tbody>
	</table>
	</div>
				</div>
			</div>
		</div>
		</div>

</body>
</html>