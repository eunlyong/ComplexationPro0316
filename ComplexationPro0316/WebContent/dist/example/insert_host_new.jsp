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
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<link rel="shortcut icon" href="../../assets/ico/favicon.png">
<script type="text/javascript" charset="utf-8"
	src="../../eunlyong_js/function.js"></script>
<script type="text/javascript" language="javascript"
	src="../jsme/jsme.nocache.js"></script>
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
<style type="text/css">
.bs-example {
	margin-top: 3px;
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

#dg {
	width: 540px;
	height: 45px;
	border: 1px solid #ddd;
	padding-top: 3px;
}

#dg_title {
	width: 270px;
	height: 30px;
	float: left;
	padding-top: 6px;
	margin-left: 6px;
	font-size: 18px;
}

#dg_remove {
	width: 90px;
	height: 30px;
	float: left;
}

#dg_remove img {
	width: 36px;
	height: 36px;
	margin-top: 0px;
	margin-left: 15px;
}
</style>

<SCRIPT LANGUAGE="JavaScript">
	
var count = 0;
function monoSelect() {
	
	count++;
	var select= document.getElementById("monoselect");
    var monoselect = select.options[select.selectedIndex].text;
	
	var mononew_1 = "<div id='dg' class='selectMono"+count+"'><div id='dg_title' >";
	var mononew_2 = "<input type='hidden' id='selectedMono"+count+"' value='" + monoselect + "' /></div><div id='plus_less_module'>"
		+ "<input id='quantity' name='quantity" + count + "' type='text'  value='0' maxlength='6' "
		+"onkeyup=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\\\D/g,'')}\""
        +" onafterpaste=\"if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\\\D/g,'')}\""
		+ " style='ime-mode:Disabled'><div id='plus_less_button'><button id='plus'onclick=\"plusQuantity('selectMono"+count+"')\">гл</button>"
	    + "<button id='less' onclick=\"lessQuantity('selectMono"+count+"')\">гн</button></div></div><div id='dg_remove'><img src='<%=basePath%>"
		+ "image/delete.png' onClick=\"removeMonoList('selectMono"
			+ count + "')\"></div></div>";
	
	var monos = mononew_1 + $('#monoselect option:selected').text()
			+ mononew_2;
	$('#monolist').append(monos);
	document.getElementById("countValue").value = count;
	
	
}
function removeMonoList(monoCount) {
	$('.' + monoCount).remove();

}
	function transferToOtherFormat() {

		var mol = document.JME.molFile();
		document.form.mol_output.value = mol;
		var drawing = document.JME.smiles();
		document.form.smi.value = drawing;

	
	}

	function processJme() {
		document.JME.readMolecule(document.form.jme_output.value);
	}

	function useMol() {
		document.JME.readMolFile(document.form.mol_output.value);

	}

	function readMolecule() {
		var jme = "16 17 C 7.37 -8.99 C 7.37 -7.59 C 6.16 -6.89 C 4.95 -7.59 C 4.95 -8.99 C 6.16 -9.69 N 8.58 -6.89 C 8.58 -5.49 C 7.37 -4.79 O 6.16 -5.49 C 9.80 -7.59 O 9.80 -8.99 C 11.01 -6.89 Cl 12.22 -7.59 Cl 11.01 -5.49 C 9.80 -4.79 1 2 1 2 3 2 3 4 1 4 5 2 5 6 1 6 1 2 7 8 1 8 9 1 9 10 1 3 10 1 2 7 1 7 11 1 11 12 2 11 13 1 13 14 1 13 15 1 8 16 1";
		document.JME.readMolecule(jme);
	}

	function readMultipart() {
		var jme = "9 9 C 6.68 -7.15 C 5.47 -6.45 C 4.26 -7.15 C 4.26 -8.55 C 5.47 -9.25 C 6.68 -8.55 C 5.47 -5.05 O- 6.68 -4.35 O 4.26 -4.35 1 2 1 2 3 2 3 4 1 4 5 2 5 6 1 6 1 2 2 7 1 7 8 1 7 9 2|1 0 Na+ 12.21 -6.61";
		document.JME.readMolecule(jme)
	}

	function readReaction() {
		var jme = "3 2 C:1 1.41 -7.12 O:2 1.41 -5.72 Cl 2.63 -7.82 1 2 2 1 3 1|3 2 N:3 5.72 -6.78 C:4 7.12 -6.78 H:5 5.02 -7.99 1 2 1 1 3 1 >> 5 4 C:1 13.51 -6.40 O:2 13.51 -5.00 N:3 14.72 -7.10 C:4 15.94 -6.40 H:5 14.71 -8.50 1 2 2 1 3 1 3 4 1 3 5 1";
		document.JME.readMolecule(jme);
	}

	function openHelpWindow() {
		window
				.open(
						"http://www.molinspiration.com/jme/help/jme2008hints.html",
						"jmehelp",
						"toolbar=no,menubar=no,scrollbars=yes,resizable=yes,width=510,height=675,left=400,top=20");
	}
	jQuery.support.cors = true;

	//this function will be started after the JavaScriptApplet code has been loaded
	function jsmeOnLoad() {
		//Instantiate a new JSME:
		//arguments: HTML id, width, height (must be string not number!)

		jsmeApplet = new JSApplet.JSME("appletContainer", "640px", "480px", {
			//optional parameters
			"options" : "query,hydrogens,removehs"
		//removehs : remove explicit hydrogens when importing a structure - useful when importing from the NCI service
		});

		//jsmeApplet has the same API as the original Java applet
		//One can mimic the JME Java applet access to simplify the adaptation of HTML and JavaScript code:
		document.JME = jsmeApplet;

		jsmeApplet.setNotifyStructuralChangeJSfunction("showMoleculeName"); //set to null to reset

		//jsmeApplet.setPrePasteJSfunction("convertToMolfile");//set to null to reset

		//customize the paste label in the popup menu
		jsmeApplet.setPasteLabel("Paste any chemical text repr.");//set to null to reset

		function convertToMolfile2(jsmeInstance, molecularRepresentation) {
			if (molecularRepresentation
					&& molecularRepresentation.indexOf("M  END") == -1
					&& molecularRepresentation.slice(0, 4) != "$RXN") {
				$.ajax({
					url : "http://cactus.nci.nih.gov/chemical/structure",
					crossDomain : true,
					data : {
						"string" : molecularRepresentation,
						"representation" : "sdf"
					},
					success : function(data) {
						jsmeInstance.readMolFile(data);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("Ajax error: " + textStatus);
					}
				});

			} else {
				jsmeInstance.readMolFile(molecularRepresentation);
			}

		}

		jsmeApplet.setBeforePasteCallback(convertToMolfile2);

	}

	function fixedEncodeURIComponent(str) {
		return encodeURIComponent(str).replace(/[!'()*]/g, escape);
	}
	function showMoleculeName() {
		var smiles = document.JME.smiles();
		if (smiles) {

			$.ajax({
				url : "http://cactus.nci.nih.gov/chemical/structure",
				crossDomain : true,
				data : {
					"string" : smiles,
					"representation" : "iupac_name"
				},
				success : function(data) {
					document.JME.showInfo(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if (errorThrown.toUpperCase() != "NOT FOUND") {
						alert("Ajax error: " + errorThrown);
					} else {
						document.JME.showInfo("Name not found");
					}
				}

			});

		}
	}
	function convertToMolfile(molecularRepresentation) {
		if (molecularRepresentation
				&& molecularRepresentation.indexOf("M  END") == -1
				&& molecularRepresentation.slice(0, 4) != "$RXN") {
			$.ajax({
				url : "http://cactus.nci.nih.gov/chemical/structure",
				crossDomain : true,
				data : {
					"string" : molecularRepresentation,
					"representation" : "sdf"
				},
				success : function(data) {
					document.JME.readMolFile(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("Ajax error: " + errorThrown);
				}
			});

		} else {
			document.JME.readMolFile(molecularRepresentation);
		}

	}
</SCRIPT>

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

					<li ><a data-toggle="tab" href="#sectionA"
						id="dg_search" onclick="link_to_searchHost('<%=basePath%>')">Search</a></li>
					<li class="active"><a data-toggle="tab" href="#sectionB" id="dg_insert">Insert</a></li>

				</ul>
				<div class="tab-content">

					<div id="sectionA" class="tab-pane fade  distance-re">
					</div>

					<div id="sectionB" class="tab-pane fade in active distance-re">
					<h3 style="margin-top:0px; margin-bottom:3px;">Insert</h3>
						<table class="table table-condensed">
							<tbody>
							<tr>

									<td colspan="4"><h3>Monosaccharide Component</h3></td>


								</tr>
								<tr>
									<td colspan="2" style="height: 45px;">Choose from exist
										host: <span> <select id="monoselect"
											onChange="monoSelect()">
												<option selected value="">SELECT MONOSACCAHRIDE</option>
												<%
													ArrayList<Monosaccharide> monos = new ArrayList<Monosaccharide>();
													monos = new Dao().getAllMonosaccharide();

													for (int i = 0; i < monos.size(); i++) {
														Monosaccharide mono = monos.get(i);
														String monoName = mono.getMonosaccharide().substring(
																mono.getMonosaccharide().indexOf("#") + 1);
														String monoResource = mono.getMonosaccharide();
														String baseResource = mono.getBasetype().getBasetype();
														String baseName = mono.getBasetype().getBasetype().substring(
																mono.getBasetype().getBasetype().toString().indexOf(
																		"#") + 1);
												%>
												<option value="<%=monoResource%>"><%=monoName%></option>
												<%
													}
												%>
										</select>
									</span>
									</td>
									<td style="border-bottom: 1px solid #ddd; width: 540px"
										colspan="2">
										<div style="" id="monolist">
											
										</div>
										<input type="hidden" id="countValue" value="" />
									</td>
									<td style="border-bottom: 1px solid #ddd;"></td>
								</tr>





								<tr>

									<td colspan="4"><h3>Host Property</h3></td>


								</tr>
								<tr>
									<td>Host Name:</td>
									<td><span><input type="text"
											id="complexation:has_host_name" /></span></td>
									<td>Average Molecule Weight:</td>
									<td><span><input type="text"
											id="complexation:has_average_molecule_weight" /></span></td>

								</tr>
								<tr>
									<td>Molecular Formular:</td>
									<td><input type="text"
										id="complexation:has_molecular_formular" /></td>
									<td>Linkage Position:</td>
									<td><input type="text" id="" /></td>

								</tr>
								<tr>
									<td>Boiling Point:</td>
									<td><input type="text" id="complexation:has_boiling_point" /></td>

									<td>Melting Point:</td>
									<td><input type="text" id="complexation:has_melting_point" /></td>
								</tr>
								<tr>
									<td>Monoisotopic Molecule Weight:</td>
									<td><input type="text"
										id="glycan:has_monoisotopic_molecule_weight" /></td>
									<td>Msdb Id:</td>
									<td><input type="text" id="glycan:has_msdb_id" /></td>
								</tr>

								<tr>
									<td>Solubility:</td>
									<td><input type="text" id="complexation:has_solubility" /></td>
									<td>Toxicity:</td>
									<td><span><select id="complexation:has_toxicity">
												<option selected value="">SELECT</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
										</select> </span></td>
								</tr>

								<tr>
									<td colspan="4"><h3>NMR Property</h3></td>
								</tr>
								<tr>
									<td colspan="1">Host NMR Spectrum:</td>
									<td colspan="3"><input type="file" id="image2"
										name="myfile"></td>
								</tr>
								<tr>



									<td>Linkage:</td>
									<td><input type="text" /></td>
									<td>Residue:</td>
									<td><input type="text" /></td>
								</tr>

								<tr>
									<td>Chemical Shift:</td>
									<td><input type="text" /></td>
									<td>Chemical Shift Max:</td>
									<td><input type="text" /></td>
								</tr>
								<tr>
									<td>Chemical Shift Min:</td>
									<td><input type="text" /></td>
									<td>Coupling Constant:</td>
									<td><input type="text" /></td>
								</tr>
								<tr>
									<td>Multiplicity:</td>
									<td><input type="text" /></td>
									<td>Carbon Number:</td>
									<td><input type="text" /></td>
								</tr>
								
								
								
								<tr height="450">
									<td id="appletContainer" colspan="3"></td>
									<td style="padding-top: 60px" align="center" rowspan="3">
										<FORM METHOD="POST" NAME="form" ONSUBMIT="return false;">

											<CENTER>
												<p>
													<input type="button" class="btn btn-info" id="transfer"
														value="Transfer To Below Format"
														onClick="transferToOtherFormat()">
												</p>
												<p>
													<b>SMILES</b><BR> <TEXTAREA style="width: 300px"
														ROWS=5 COLS=40 NAME="smi"  id="has_smiles"></TEXTAREA><BR>
												</p>

												<p>
													<b>MOL File</b><BR>
													<TEXTAREA style="width: 300px" NAME="mol_output" ROWS=5
														COLS=40 id="has_mol"></TEXTAREA>
												</P>
											</CENTER>
										</FORM>
									</td>

								</tr>
								<tr>
									<td align="right" colspan="3"><a href="javascript:openHelpWindow();">JME
											Help</a></td>
											<td></td>
								</tr>
								<tr>
									<td colspan="4">
										<button type="button"
											style="margin-top: 9px; height: 45px; width: 210px"
											class="btn btn-primary"
											onClick="submitHostInfo('<%=basePath%>')">Submit
											Host</button>
									</td>
								</tr>
								
								
								
								
								
							</tbody>
						</table>
						<table class="table table-condensed">

							<tbody>
								
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
		</div>
</body>
</html>