
function clickHostDB() {
	window.location.href = "showcompound.jsp";
}
var count = 0;
function monoSelect() {
	count++;
	var monos_1 = "<div id=''><a href='#' class='list-group-item'>";
	var monos_2 = " "
			+ "<div id='plus_less_module'><input id='quantity' type='text' name='quantity' value='0' "
			+ "maxlength='6' onKeypress='return (/[\d.]/.test(String.fromCharCode(event.keyCode)))' "
			+ "style='ime-mode:Disabled'><div id='plus_less_button'><button id='plus'>＋</button> "
		    + "<button id='less'>－</button></div></div>"
			+ "<span class='glyphicon glyphicon-remove-circle' onClick='removeMonoList()'></span></a></div>";
	var mononew_1 = "<div id='dg' class='selectMono"+count+"'><div id='dg_title' >";
	var mononew_2 = "</div><div id='plus_less_module'>"
		+ "<input id='quantity' class='quantity" + count + "' type='text' name='quantity' value='0' maxlength='6' "
		+ "onKeypress='return (/[\d.]/.test(String.fromCharCode(event.keyCode)))' "
		+ "style='ime-mode:Disabled'><div id='plus_less_button'><button id='plus'onclick=\"plusQuantity('selectMono"+count+"')\">＋</button>"
	    + "<button id='less' onclick=\"lessQuantity('selectMono"+count+"')\">－</button></div></div><div id='dg_remove'><img src='<%=basePath%>image/delete.png' onClick=\"removeMonoList('selectMono"+count+"')\"></div></div>";
	var monos = mononew_1 + $('#monoselect option:selected').text() + mononew_2;
	$('#monolist').append(monos);
	

}
function removeMonoList(monoCount){
	$('.'+ monoCount).remove();
	
	
}
function transferToOtherFormat(){
	var drawing = document.JME.smiles();
	  document.form.smi.value = drawing;
	  var jme = document.JME.jmeFile();
	  document.form.jme_output.value = jme;
	  var mol = document.JME.molFile();
	  document.form.mol_output.value = mol;
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
	  window.open("http://www.molinspiration.com/jme/help/jme2008hints.html","jmehelp","toolbar=no,menubar=no,scrollbars=yes,resizable=yes,width=510,height=675,left=400,top=20");
	}
	jQuery.support.cors = true;

	//this function will be started after the JavaScriptApplet code has been loaded
	function jsmeOnLoad() {
		//Instantiate a new JSME:
		//arguments: HTML id, width, height (must be string not number!)
		alert();
		
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