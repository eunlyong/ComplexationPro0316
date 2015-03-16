<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="dao.*" import="model.*"  import="servlet.*" import="java.io.*"
	import="java.net.*" import="java.sql.SQLException"
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
				<ul class="nav nav-tabs"  id="myTab">
					<li class="active"><a data-toggle="tab" href="#sectionA">Search</a></li>
					<li><a data-toggle="tab" href="#sectionB" onclick="link_to_insertComplexation('<%=basePath%>')">Insert</a></li>

				</ul>
				<div class="tab-content" >
					<div id="sectionA" class="tab-pane fade in active distance-re">
						<h3>Search</h3>
						<%
						if(null != request.getParameter("complexation")){
							String comp = request.getParameter("complexation");
						
			
			Complexation complexationDetail =  new Dao().getComplexationInfos(comp);
			Host hostDetail = complexationDetail.getHost();
			Guest guestDetail = complexationDetail.getGuest();
			%>
			
				<table class="table table-condensed">

					<tbody>
					<tr>
			<td colspan="4" height="42" class="info"><h3>Binding Result:</h3></td>
		</tr>
						<tr>
							<td class="warning">Host:</td>
							<td><%=complexationDetail.getHost().getHost()%></td>
							<td class="warning">Guest:</td>
							<td><%=complexationDetail.getGuest().getGuest()%></td>
						</tr>
						<tr>
							<td class="warning">Binding Constant:</td>
			<td><%=complexationDetail.getBindingConstant()%></td>

			<td class="warning">Stoichiometry Binding</td>
			<td><%=complexationDetail.getStoichiometry()%></td>
						</tr>
						<tr>
			<td colspan="4" height="42" class="info"><h3>Experiment Condition:</h3></td>
		</tr>
		<tr>
			<td class="warning">Temperature</td>
			<td><%=complexationDetail.getUsedTemperature()%></td>

			<td class="warning">Solvent:</td>
			<td><%=complexationDetail.getUsedSolvent()%></td>


		</tr>
		<tr>
			<td class="warning">PH:</td>
			<td><%=complexationDetail.getUsedPH()%></td>

			<td class="warning">Mixing Time:</td>
			<td><%=complexationDetail.getUsedTime()%></td>

		</tr>
		<tr>
			<td colspan="4" height="42" class="info"><h3>NMR evidence:</h3></td>
		</tr>
		<tr>
			<td class="warning">Host Concentration:</td>
			<td><%=complexationDetail.getHostConcentration()%></td>

			<td class="warning">Guest Concentration:</td>
			<td><%=complexationDetail.getGuestConcentration()%></td>

		</tr>
		<tr>
			<td class="warning">Buffer:</td>
			<td><%=complexationDetail.getBuffer()%></td>

			<td class="warning">Frequency:</td>
			<td><%=complexationDetail.getFrequency()%></td>

		</tr>
		<tr>
			<td class="warning">Temperature:</td>
			<td><%=complexationDetail.getNmrTemperature()%></td>

			<td class="warning">Mixing Time:</td>
			<td><%=complexationDetail.getNmrMixingTime()%></td>

		</tr>
		<tr>

			<td class="warning">PH:</td>
			<td><%=complexationDetail.getNmrPH()%></td>

		</tr>
		<tr>
			<td class="warning">Spectrum:</td><td></td><td></td><td></td>
		</tr>
		<tr id="head">
			<td height="42" colspan="4" class="info">
			<h3  style="display:inline">Host:</h3>
			<a href="#" style="float:right; margin-right:3px;padding-top:6px;" 
			onClick="gotoHostDetail('<%=basePath %>', '<%=hostDetail.getHost() %>')">
			Click here to see more information of host</a></td>
		</tr>
		<tr>
			<td class="warning">Host Name:</td>
			<td><%=hostDetail.getHost()%></td>

			<td class="warning">Molecular Formular:</td>
			<td><%=hostDetail.getMolecularFormular().toUpperCase() %></td>
		</tr>
		<tr>
			<td class="warning">Boiling Point:</td>
			<td><%=hostDetail.getBoilingPoint()%></td>

			<td class="warning">Melting Point:</td>
			<td><%=hostDetail.getMeltingPoint()%></td>
		</tr>
		<tr>
			<td class="warning">Solubility:</td>
			<td><%=hostDetail.getSolubility()%></td>

			<td class="warning">Toxicity</td>
			<td><%=hostDetail.getToxicity()%></td>
		</tr>
		<tr>
			<td>Structure Information:</td>
		</tr>
		<tr id="head" class="info">
			<td height="42" colspan="4"><h3 style="display:inline">
			Guest:</h3>
			<a href="#" style="float:right; margin-right:3px; padding-top:6px" 
			onClick="gotoGuestDetail('<%=basePath %>', '<%=guestDetail.getGuest()%>')">
			Click here to see more information of guest</a></td>
		</tr>
		<tr>
			<td class="warning">Guest Name</td>
			<td><%=guestDetail.getGuest()%></td>
			<%
				/*Guest g = new Guest();
				Dao dao = new Dao();
				if (!guest.getMolId().equals("")) {
					//g = dao.getGuestInfoByPubChemId(guest.getMolId());
				}*/
			%>
			<td class="warning">Molecular Formular:</td>
			<td><%=guestDetail.getMolecularFormular()%></td>
		</tr>
		<tr>
			<td colspan="4"><img
				src="<%=basePath %>GuestImage/<%=guestDetail.getMolId()%>.png"
				width=150 height=120 />
				</td>
		</tr>
		<tr>
			<td class="warning">Molecular Weight:</td>
			<td><%=guestDetail.getMolecularWeight()%></td>
			<td class="warning">Total Formal Charge:</td>
			<td><%=guestDetail.getTotalCharge() %></td>
		</tr>
		<tr>
			<td class="warning">Canonical SMILES:</td>
			<td colspan="3" ><%=guestDetail.getOpeneyeCanSmiles() %></td>
		</tr>
		
		<tr>
			<td  class="warning">Isomeric SMILES:</td>
			<td colspan="3"><%=guestDetail.openeyeIsoSmiles %></td>
		</tr>
		
		<tr>
			<td  class="warning">Preferred IUPAC Name:</td>
			<td colspan="3"><%=guestDetail.getIupacName() %></td>
		</tr>
		
		<tr>
			<td class="warning">IUPAC InChI</td>
			<td colspan="3"><%=guestDetail.getIupacInchi()%></td>
		</tr>
		
		



					</tbody>
				</table>
				<%
						}
						else{
							request.getRequestDispatcher("search_complexation.jsp").forward(request, response);
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