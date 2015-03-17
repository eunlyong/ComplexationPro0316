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
<script type="text/javascript" language="javascript"
	src="../jsme/jsme.nocache.js"></script>
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
	margin-top: 30px;
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
</head>
<SCRIPT LANGUAGE="JavaScript">
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
	function checkChange1() {
		document.getElementById("pubchemname").value = "";
		document.getElementById("pubchemid").disabled = false;
		document.getElementById("pubchemname").disabled = true;
		document.getElementById("JME").disabled = true;

		document.getElementById("pubchemname").focus();
	}
	function checkChange2() {
		document.getElementById("pubchemid").value = "";
		document.getElementById("pubchemname").disabled = false;
		document.getElementById("pubchemid").disabled = true;
		document.getElementById("JME").disabled = true;
		document.getElementById("pubchemname").focus();
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

<body>
	<%@include file="navigation.jsp"%>

	<div class="row row-offcanvas row-offcanvas-right" id="navbarCollapse"
		class="collapse navbar-collapse">

		<div class="container">
			<div class="bs_example">
				<div></div>
				<h2>DB</h2>
			</div>
			<div class="bs_example">
			
    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">1. Search Similar Structure By JSME Applet</a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in">
                <div class="panel-body">
                <table class="table-condensed ">
					<tbody>

						<tr>
							<td id="appletContainer"></td>
						</tr>
						<tr>
							<td align="right"><a href="javascript:openHelpWindow();">JME
									Help</a></td>
						</tr>

					</tbody>
				</table>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">2. Search Similar Structure By Mol File</a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse">
                <div class="panel-body">
                <TABLE>
					<tr>
						<td>Mol File: 
						<TEXTAREA style="width: 300px" id="molfile" ROWS=5 COLS=40
									></TEXTAREA>	
						</td>
					</tr>
				</TABLE>
                </div>
            </div>
        </div>
        </div>
				
				
				<p>
				
							<button type="submit" class="btn btn-primary"
									onClick="searchSimilarGuest('<%=basePath%>')" >
									<span class="glyphicon glyphicon-search"></span> 
									Similar Structure Guest Search
							</button>	
								
				
				<br>
				<form id="hiddenform"  action="complex_hostrecommend.jsp"  METHOD="POST">
								<input type="hidden" id="hiddenmol" name="hiddenmol" value=""/>
								<input type="hidden" id="hiddenmode" name="hiddenmode" value="" />
							
							</form>
				<table>
					<tr>
						<td>
							<h4>Found similar compound, and the complexation
								information:</h4>
							
							<hr>
						</td>
					</tr>

				</table>

				<%
					String molstruc = "";

					String testfile = Variables.StructureFile;
					String struc = "";
					if (null != request.getParameter("hiddenmol")) {
						

							String getstruc = request.getParameter("hiddenmol");
							/*for(int i = 0; i < getstruc.split(",").length; i++){
								struc += getstruc.split(",")[i] + "\n";
								
							}*/
							struc = getstruc.replaceAll(",", "\n");
							//System.out.println(struc);
					}
					
					System.out.println("STRUC: " + struc);

					PrintWriter writer = new PrintWriter(testfile, "UTF-8");
					writer.println(struc);

					writer.close();
					//testfile = "D:\\Install\\moldb5r20\\safemol1.mol";

					//hash = dao1.getSimilarStructure(testfile);
					if (!struc.equals("")) {

						Dao dao1 = new Dao();

						Map<String, Double> hash = new HashMap<String, Double>();
						hash = dao1.getSimilarStructure(testfile);
						hash = dao1.hashSequence(hash);
						Iterator<String> iterator = hash.keySet().iterator();
						/*while(iterator.hasNext()){
							hash.put(iterator.next(), dao1.getSimilarStructure(testfile).get(iterator.next()));
						}*/
						
						if (iterator.hasNext()) {
				%>
				<div>

					<%
						//Iterator<Complexation> keySetIterator = hash.keySet().iterator();
								for (Map.Entry<String, Double> entry : hash.entrySet()) {

									String guestName = entry.getKey();

									guestName = guestName
													.substring(guestName.indexOf("#") + 1);
									//Resource complexation = results.next().getResource("complexation");

									System.out.println("complexation= " + guestName);
									//Complexation complexation =  new Dao().getComplexationInfos(comp);
									//System.out.println("Complexation Binding constant: " + complexation.getBindingConstant());
									// complexation = new Dao().searchByGuestPubChemId(iterator.next());
									Guest guest = dao.getGuestInfos(guestName);
					%>
					<h3>
						Tanimoto:
						<%=entry.getValue()%>%</h3>

					<div class="row featurette"
						onClick="searchRecommendation('<%=basePath%>','<%=guestName %>')">
						<div class="col-md-2">
							
							<img
								src="<%=basePath %>GuestImage/<%=guest.getMolId()%>.png"
								width=150 height=120 alt="Generic placeholder image" />
						</div>
						<div class="col-md-6">

							<table class="table-condensed ">
								<tbody>
									<Tr>
										<td>Guest:</td>
										<td><%=guestName%></td>
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
				%>
				</div>
				<%

						}

					}

					//qe.close();
				%>
			</div>
		</div>
	</div>


</body>

</html>