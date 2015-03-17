<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="dao.*" import="model.*" import="servlet.*"
	import="java.io.*" import="java.net.*" import="java.sql.SQLException"
	import="org.apache.jena.atlas.lib.Sync"
	import="com.hp.hpl.jena.query.*" import="com.hp.hpl.jena.rdf.model.*"
	import="com.hp.hpl.jena.shared.Lock" import="java.util.*"
	import="com.hp.hpl.jena.sparql.mgt.SystemInfo"
	import="com.hp.hpl.jena.tdb.*" import="com.hp.hpl.jena.update.*"%>
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
<script type="text/javascript" charset="utf-8"
	src="../../eunlyong_js/function.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function setGuestName(){
		
		document.getElementById("guestname").value = document.getElementById("has_guest").value;
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
				<h2>Guest DB</h2>
				<ul class="nav nav-tabs" id="myTab">
					
						<li><a data-toggle="tab" href="#sectionA" onclick="link_to_searchGuest('<%=basePath%>')">Search</a></li>
					<li class="active"><a data-toggle="tab" href="#sectionB" >Insert</a></li>
					

				</ul>
				<div class="tab-content">

					<div id="sectionA" class="tab-pane fade  distance-re">
					</div>

					<div id="sectionB" class="tab-pane fade in active distance-re">
					<h3 style="margin-top:0px; margin-bottom:3px;">Insert</h3>
 <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Insert Guest Information Through File Uploading From PubChem</a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in">
                <div class="panel-body">
                    <table class="table table-condensed">

					
					<tbody>
					
					<tr>
							<td colspan="4">
							<table>
								<tr>
									<td style="border:0px; padding-top:15px;">Guest Name: 
									<input style="margin-top:-3px;" type="text" id="has_guest"
							name="has_guest" onkeyup="setGuestName()" /></td>
									<td style="border:0px; padding-top:15px; padding-left:15px;">
									   <input style="width: 180px;" type="file" id="upfile" name="myfile"></td>
									<td style="border:0px; "><button type="button"
									style="margin-top: 0px; height: 40px; width: 300px"
									class="btn btn-primary"
									onClick="sendGuestName('<%=basePath%>')">Submit SDF File</button>
									</td>
								</tr>
							
					<%
						if (request.getAttribute("guestFile") != null) {
							
							String sendFile = request.getAttribute("guestFile").toString();
							System.out.println("senffile: " + sendFile);
							String mol_id = request.getAttribute("molId").toString();

							
							boolean result = new Dao().checkGuestMolIdDuplicate(mol_id);
							if (result == false) {
								if (request.getAttribute("guestName") != null) {
									new Dao().guestFullFileInfoIntoJena(sendFile,
											request.getAttribute("guestName").toString());
								}
							} else {
					%>
					<tr>
						<td colspan="4">
							<h3>
								Sorry, The Guest
								<%=request.getAttribute("guestName")%>
								is already exist! If you sure, please use it!
							</h3>
						</td>
					</tr>
					<%
							}

						}
					%>
					</table>
							</td>
						</tr>
					</tbody>
					</table>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">If No File, Please Input Each Information</a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse">
                <div class="panel-body">
                
				<table class="table table-condensed">

					
					<tbody>
					<tr>
						<td>Guest Name:</td>
						<Td><input type="text" id="has_guest_name" /></Td>
						<Td></Td>
						<td></td>
					</tr>
					<tr>
						<td>Canonicalized:</td>
						<td><input type="text" id="has_canonicalized" /></td>
						<td>Complexity:</td>
						<td><input type="text" id="has_complexity" /></td>

					</tr>
					<tr>
						<td>H Bond Acceptor:</td>
						<td><input type="text" id="has_h_bond_acceptor" /></td>

						<td>H Bond Donor:</td>
						<td><input type="text" id="has_h_bond_donor" /></td>
					</tr>
					<tr>
						<td>Rotatable Bond:</td>
						<td><input type="text" id="has_rotatable_bond" /></td>
						<td>Subskeys:</td>
						<td><input type="text" id="has_subskeys" /></td>
					</tr>
					<tr>
						<td>IUPAC Openeye Name:</td>
						<td><input type="text" id="has_iupac_openeye_name" /></td>
						<td>IUPAC Cas Name:</td>
						<td><input type="text" id="has_iupac_cas_name" /></td>
					</tr>
					<tr>
						<td>IUPAC Name:</td>
						<td><input type="text" id="has_iupac_name" /></td>
						<td>Systematic Name:</td>
						<td><input type="text" id="has_systematic_name" /></td>
					</tr>
					<tr>
						<td>IUPAC Traditional Name:</td>
						<td><input type="text" id="has_iupac_traditional_name" /></td>
						<td>IUPAC InChI:</td>
						<td><input type="text" id="has_iupac_inchi" /></td>
					</tr>
					<tr>
						<td>IUPAC InChiKey:</td>
						<td><input type="text" id="has_iupac_inchikey" /></td>
						<td>Xlogp3 AA:</td>
						<td><input type="text" id="has_xlogp3_aa" /></td>
					</tr>
					<tr>
						<td>Exact Mass:</td>
						<td><input type="text" id="has_exact_mass" /></td>
						<td>Molecular Formular:</td>
						<td><input type="text" id="has_molecular_formular" /></td>
					</tr>
					<tr>
						<td>Molecular Weight:</td>
						<td><input type="text" id="has_molecular_weight" /></td>
						<td>Openeye Can Smiles:</td>
						<td><input type="text" id="has_openeye_can_smiles" /></td>
					</tr>
					<tr>
						<td>ISO Smiles:</td>
						<td><input type="text" id="has_openeye_iso_smiles" /></td>
						<td>TPAS:</td>
						<td><input type="text" id="has_tpas" /></td>
					</tr>
					<tr>
						<td>Monoisotopic Weight:</td>
						<td><input type="text" id="has_monoisotopic_weight" /></td>
						<td>Total Charge:</td>
						<td><input type="text" id="has_total_charge" /></td>
					</tr>
					<tr>
						<td>Heavy Atom Count:</td>
						<td><input type="text" id="has_heavy_atom_count" /></td>
						<td>Atom Def Stereo Count:</td>
						<td><input type="text" id="has_atom_def_stereo_count" /></td>
					</tr>
					<tr>
						<td>Atom Udef Stereo Count:</td>
						<td><input type="text" id="has_atom_udef_stereo_count" /></td>
						<td>Bond Def Stereo Count:</td>
						<td><input type="text" id="has_bond_def_stereo_count" /></td>
					</tr>
					<tr>
						<td>Bond Udef Stereo Count:</td>
						<td><input type="text" id="has_bond_udef_stereo_count" /></td>
						<td>Isotopic Atom Count:</td>
						<td><input type="text" id="has_isotopic_atom_count" /></td>
					</tr>
					<tr>
						<td>Component Count:</td>
						<td><input type="text" id="has_component_count" /></td>
						<td>Tauto Count:</td>
						<td><input type="text" id="has_tauto_count" /></td>
					</tr>
					<tr>
						<td>Coordinate Type:</td>
						<td><input type="text" id="has_coordinate_type" /></td>
						<td>Bond Annotations:</td>
						<td><input type="text" id="has_bond_annotations" /></td>
					</tr>
						<tr>
						<td colspan="4">
						<button type="button"
									style="margin-top: 0px; height: 40px; width: 300px"
									class="btn btn-primary" onClick="submitGuestInfo('<%=basePath %>')"
									>Submit Guest Information</button>
						</td>
					</tr>
					</tbody>
					</table>
                </div>
            </div>
        </div>
       
    </div>
    </div>
    </div>
    </div>
    </div>
    </div>
    
	
				
				
					
					
				
					
</body>
</html>