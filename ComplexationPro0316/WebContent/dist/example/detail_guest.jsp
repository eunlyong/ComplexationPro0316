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
	
	String guestImage = request.getSession().getServletContext().getRealPath("/"+"GuestImage");
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
				<h2>Guest DB</h2>
				<ul class="nav nav-tabs"  id="myTab">
					<li class="active"><a data-toggle="tab" href="#sectionA">Search</a></li>
					<li><a data-toggle="tab" href="#sectionB" onclick="link_to_insertGuest('<%=basePath%>')">Insert</a></li>

				</ul>
				<div class="tab-content" >
					<div id="sectionA" class="tab-pane fade in active distance-re">
						<h3>Search</h3>
			<%
				String guestName = request.getParameter("guest");
				Dao dao = new Dao();
				Guest guest = dao.getGuestInfos(guestName);
			%>
			<div class="bs-example">
				<table class="table table-condensed">

					<tbody>


						<tr class="info">
							<td><h4>Guest:</h4></td>
							<td><h4><%=guestName%></h4></td>
							<td></td>
							<Td></Td>
							
						</tr>
						<tr>
							<td class="warning">PubChem CID:</td>
							<td><%=guest.getMolId() %></td>
							
							<td colspan="2" rowspan="3"><img
								src="<%=guestImage %>/<%=guest.getMolId()%>.png"
								width=150 height=120 /></td>
						</tr>
						<tr>
							<td class="warning">Molecular Formular:</td>
							<td><%=guest.getMolecularFormular() %></td>
							
						</tr>
						<tr>
							<td class="warning">Molecular Weight:</td>
							<td><%=guest.getMolecularWeight() %></td>
						</tr>
						<tr>
							<td class="warning">IUPAC Name:</td>
							<td colspan="3"><%=guest.getIupacName() %></td>
						</tr>

						<tr>
							<td  class="warning">Canonical SMILES:</td>
							<td colspan="3"><%=guest.getOpeneyeCanSmiles() %></td>
						</tr>
						<tr>
						<td  class="warning">Canonicalized:</td>
						<td><%=guest.getCanonicalized() %></td>
						<td  class="warning">Complexity:</td>
						<td><%=guest.getComplexity() %></td>

					</tr>
					<tr>
						<td  class="warning">H Bond Acceptor:</td>
						<td><%=guest.getHbondAcceptor() %></td>

						<td  class="warning">H Bond Donor:</td>
						<td><%=guest.getHbondDonor() %></td>
					</tr>
					<tr>
						<td  class="warning">Rotatable Bond:</td>
						<td><%=guest.getRotatableBond() %></td>
						<td  class="warning">Subskeys:</td>
						<td><%=guest.getSubskeys() %></td>
					</tr>
					<tr>
						<td  class="warning">IUPAC Openeye Name:</td>
						<td><%=guest.getIupacOpeneyeName() %></td>
						<td  class="warning">IUPAC Cas Name:</td>
						<td><%=guest.getIupacCasName() %></td>
					</tr>
					<tr>
						<td  class="warning">IUPAC Name:</td>
						<td><%=guest.getIupacName() %></td>
						<td  class="warning">Systematic Name:</td>
						<td><%=guest.getSystematicName() %></td>
					</tr>
					<tr>
						<td  class="warning">IUPAC Traditional Name:</td>
						<td><%=guest.getIupacTraditionalName() %></td>
						<td  class="warning">IUPAC InChI:</td>
						<td><%=guest.getIupacInchi() %></td>
					</tr>
					<tr>
						<td  class="warning">IUPAC InChiKey:</td>
						<td><%=guest.getIupacInchiKey() %></td>
						<td  class="warning">Xlogp3 AA:</td>
						<td><%=guest.getXlogp3_AA() %></td>
					</tr>
					<tr>
						<td  class="warning">Exact Mass:</td>
						<td><%=guest.getExactMass() %></td>
						<td  class="warning">Openeye Can Smiles:</td>
						<td><%=guest.getOpeneyeCanSmiles() %></td>
					</tr>
					
					<tr>
						<td  class="warning">ISO Smiles:</td>
						<td><%=guest.getOpeneyeIsoSmiles() %></td>
						<td  class="warning">TPAS:</td>
						<td><%=guest.getTpas() %></td>
					</tr>
					<tr>
						<td  class="warning">Monoisotopic Weight:</td>
						<td><%=guest.getMonoisotopicWeight() %></td>
						<td  class="warning">Total Charge:</td>
						<td><%=guest.getTotalCharge() %></td>
					</tr>
					<tr>
						<td  class="warning">Heavy Atom Count:</td>
						<td><%=guest.getHeavyAtomCount() %></td>
						<td  class="warning">Atom Def Stereo Count:</td>
						<td><%=guest.getAtomDefStereoCount() %></td>
					</tr>
					<tr>
						<td  class="warning">Atom Udef Stereo Count:</td>
						<td><%=guest.getAtomUdefStereoCount() %></td>
						<td  class="warning">Bond Def Stereo Count:</td>
						<td><%=guest.getBondDefStereoCount() %></td>
					</tr>
					<tr>
						<td  class="warning">Bond Udef Stereo Count:</td>
						<td><%=guest.getBondUdefStereoCount() %></td>
						<td  class="warning">Isotopic Atom Count:</td>
						<td><%=guest.getIsotopicAtomCount() %></td>
					</tr>
					<tr>
						<td  class="warning">Component Count:</td>
						<td><%=guest.getComponentCount() %></td>
						<td  class="warning">Tauto Count:</td>
						<td><%=guest.getTautoCount() %></td>
					</tr>
					<tr>
						<td  class="warning">Coordinate Type:</td>
						<td><%=guest.getCoordinateType() %></td>
						<td  class="warning">Bond Annotations:</td>
						<td><%=guest.getBondAnnotations() %></td>
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