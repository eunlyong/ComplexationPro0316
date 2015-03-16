// JavaScript Document

function getAllHost() {
	alert();

}

$(document).ready(function() {

	$("#MyOrder").click(function() {
		window.location.href = "searchbyhost.jsp";
	});

	$("#MyInfo").click(function() {
		window.location.href = "searchbyguest.jsp";
	});
	$("#MyAddress").click(function() {
		window.location.href = "searchbycomplexation.jsp";
	});

});
$(document).ready(function() {

	// hide message_body after the first one
	$(".message_list .message_body:gt(0)").hide();

	// hide message li after the 5th
	$(".message_list li:gt(4)").hide();

	// toggle message_body
	$(".message_head").click(function() {
		$(this).next(".message_body").slideToggle(500)
		return false;
	});

	// collapse all messages
	$(".collpase_all_message").click(function() {
		$(".message_body").slideUp(500)
		return false;
	});

	// show all messages
	$(".show_all_message").click(function() {
		$(this).hide()
		$(".show_recent_only").show()
		$(".message_list li:gt(4)").slideDown()
		return false;
	});

	// show recent messages only
	$(".show_recent_only").click(function() {
		$(this).hide()
		$(".show_all_message").show()
		$(".message_list li:gt(4)").slideUp()
		return false;
	});

});

function submitComplexation(basepath) {
	// complexation related information
	var host = document.getElementById("has_host").value;
	var hostproperty = document.getElementById("has_host").getAttribute("id");
	var guest = document.getElementById("has_guest").value;
	var guestproperty = document.getElementById("has_guest").getAttribute("id");
	var bindingconstant = document.getElementById("binding_constant").value;
	var bindingconstantproperty = document.getElementById("binding_constant")
			.getAttribute("id");
	var stoichiometry = document.getElementById("stoichiometry_binding").value;
	var stoichiometryproperty = document
			.getElementById("stoichiometry_binding").getAttribute("id");
	var phenomenaProperty = document.getElementById("has_phenomena").value; // the
																			// selecton
	var phenomena = document.getElementById("phenomena_value").value;
	var temperature = document.getElementById("used_temperature").value;
	var temperatureproperty = document.getElementById("used_temperature")
			.getAttribute("id");
	var solvent = document.getElementById("used_solvent").value;
	var solventproperty = document.getElementById("used_solvent").getAttribute(
			"id");
	var ph = document.getElementById("used_ph").value;
	var phproperty = document.getElementById("used_ph").getAttribute("id");
	var reactiontime = document.getElementById("used_time").value;
	var reactiontimeproperty = document.getElementById("used_time")
			.getAttribute("id");
	var hostconcentration = document.getElementById("host_concentration").value;
	var hostconcentrationproperty = document.getElementById(
			"host_concentration").getAttribute("id");
	var guestconcentration = document.getElementById("guest_concentration").value;
	var guestconcentrationproperty = document.getElementById(
			"guest_concentration").getAttribute("id");
	var buffer = document.getElementById("buffer").value;
	var bufferproperty = document.getElementById("buffer").getAttribute("id");
	var frequency = document.getElementById("frequency").value;
	var frequencyproperty = document.getElementById("frequency").getAttribute(
			"id");
	var nmrph = document.getElementById("ph").value;
	var nmrphproperty = document.getElementById("ph").getAttribute("id");
	var nmrtemperature = document.getElementById("temperature").value;
	var nmrtemperatureproperty = document.getElementById("temperature")
			.getAttribute("id");
	var nmrmixingtime = document.getElementById("mixing_time").value;
	var nmrmixingtimeproperty = document.getElementById("mixing_time")
			.getAttribute("id");
	var article = document.getElementById("article").value;
	var articleproperty = document.getElementById("article").getAttribute("id");
	var journal = document.getElementById("journal").value;
	var journalproperty = document.getElementById("journal").getAttribute("id");

	var prefix = "complexation:";
	var compproperty = prefix + hostproperty + "," + prefix + guestproperty
			+ "," + prefix + bindingconstantproperty + "," + prefix
			+ stoichiometryproperty + "," + prefix + phenomenaProperty + ","
			+ prefix + temperatureproperty + "," + prefix + solventproperty
			+ "," + prefix + phproperty + "," + prefix + reactiontimeproperty
			+ "," + prefix + hostconcentrationproperty + "," + prefix
			+ guestconcentrationproperty + "," + prefix + bufferproperty + ","
			+ prefix + frequencyproperty + "," + prefix + nmrphproperty + ","
			+ prefix + nmrtemperatureproperty + "," + prefix
			+ nmrmixingtimeproperty + "," + prefix + articleproperty + ","
			+ prefix + journalproperty + ",";

	var compvalue = host + "," + guest + "," + bindingconstant + ","
			+ stoichiometry + "," + phenomena + "," + temperature + ","
			+ solvent + "," + ph + "," + reactiontime + "," + hostconcentration
			+ "," + guestconcentration + "," + buffer + "," + frequency + ","
			+ nmrph + "," + nmrtemperature + "," + nmrmixingtime + ","
			+ article + "," + journal + ",";
	var sparql_part = prefix + host + guest + ",";
	if(host == "" || guest == ""){
		alert("Please select Host or Guest~ ");
	}else{
		for (var i = 0; i < compvalue.split(",").length; i++) {

			if (compvalue.split(",")[i] != "") {
				if (i == 0) {
					sparql_part += compproperty.split(",")[i] + " " + prefix + host
							+ ",";
				} else if (i == 1) {
					sparql_part += compproperty.split(",")[i] + " " + prefix
							+ guest + ",";
				} else if (i == 2) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ bindingconstant + "\"^^xsd:double,";
				} else if (i == 3) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ stoichiometry + "\"^^xsd:string,";
				} else if (i == 4) {
					sparql_part += compproperty.split(",")[i] + " \"" + phenomena
							+ "\"^^xsd:double,";
				} else if (i == 5) {
					sparql_part += compproperty.split(",")[i] + " \"" + temperature
							+ "\"^^xsd:double,";
				} else if (i == 6) {
					sparql_part += compproperty.split(",")[i] + " \"" + solvent
							+ "\"^^xsd:string,";
				} else if (i == 7) {
					sparql_part += compproperty.split(",")[i] + " \"" + ph
							+ "\"^^xsd:double,";
				} else if (i == 8) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ reactiontime + "\"^^xsd:integer,";
				} else if (i == 9) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ hostconcentration + "\"^^xsd:double,";
				} else if (i == 10) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ guestconcentration + "\"^^xsd:double,";
				} else if (i == 11) {
					sparql_part += compproperty.split(",")[i] + " \"" + buffer
							+ "\"^^xsd:string,";
				} else if (i == 12) {
					sparql_part += compproperty.split(",")[i] + " \"" + frequency
							+ "\"^^xsd:double,";
				} else if (i == 13) {
					sparql_part += compproperty.split(",")[i] + " \"" + nmrph
							+ "\"^^xsd:double,";
				} else if (i == 14) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ nmrtemperature + "\"^^xsd:integer,";
				} else if (i == 15) {
					sparql_part += compproperty.split(",")[i] + " \""
							+ nmrmixingtime + "\"^^xsd:integer,";
				} else if (i == 16) {
					sparql_part += compproperty.split(",")[i] + " \"" + article
							+ "\"^^xsd:string,";
				} else if (i == 17) {
					sparql_part += compproperty.split(",")[i] + " \"" + journal
							+ "\"^^xsd:string";
				}
			}else{
				sparql_part += ",";
			}
		}
		var fileObj = document.getElementById("image1").files[0]; // js 获取文件对象
		var FileController = "SubmitComplexation"; // 接收上传文件的后台地址
		// FormData 对象

		var form = new FormData();
		form.append("image1", fileObj); // 文件对象
		// form.append("complexation", complexation); // 文件对象
		form.append("sparqlpart", sparql_part); // 文件对象
		// XMLHttpRequest 对象
		var xhr = new XMLHttpRequest();
		xhr.open("post",basepath +  FileController, true);

		xhr.onload = function() {

			alert("Upload Success!");

		};

		// xhr.upload.addEventListener("progress", image1ProgressFunction, false);
		xhr.send(form);
	}
	

	

}

function submitHostInfo(basePath) {
	var hostname = document.getElementById("complexation:has_host_name").value;
	var averagemoleculeweight = document
			.getElementById("complexation:has_average_molecule_weight").value;
	var molecularformular = document
			.getElementById("complexation:has_molecular_formular").value;
	var boilingpoint = document
			.getElementById("complexation:has_boiling_point").value;
	var meltingpoint = document
			.getElementById("complexation:has_melting_point").value;
	var monoisotopicweight = document
			.getElementById("glycan:has_monoisotopic_molecule_weight").value;
	var msdbid = document.getElementById("glycan:has_msdb_id").value;
	var solubility = document.getElementById("complexation:has_solubility").value;
	var toxicity = document.getElementById("complexation:has_toxicity").value;
	var has_smiles = document.getElementById("has_smiles").value;
	var has_mol = document.getElementById("has_mol").value;
	
	hostname = hostname.replace(" ","-");
	var hostpropertyvalues = hostname + "," + averagemoleculeweight + ","
			+ molecularformular + "," + boilingpoint + "," + meltingpoint + ","
			+ monoisotopicweight + "," + msdbid + "," + solubility + ","
			+ toxicity + "," + has_smiles + "," ;

	var hostbasicinfovalues = "\"" + hostname + "\"^^xsd:string,\""
			+ averagemoleculeweight + "\"^^xsd:integer,\"" + molecularformular
			+ "\"^^xsd:string,\"" + boilingpoint + "\"^^xsd:integer,\""
			+ meltingpoint + "\"^^xsd:integer,\"" + monoisotopicweight
			+ "\"^^xsd:double,\"" + msdbid + "\"^^xsd:string,\"" + solubility
			+ "\"^^xsd:integer,\"" + toxicity + "\"^^xsd:integer,\""
			+ has_smiles + "\"^^xsd:string,";
	var hostbasicinfopropertys = "complexation:has_host_name,complexation:has_average_molecule_weight,"
			+ "complexation:has_molecular_formular,complexation:has_boiling_point,complexation:has_melting_point,"
			+ "glycan:has_monoisotopic_molecule_weight,glycan:has_msdb_id,complexation:has_solubility,"
			+ "complexation:has_toxicity,complexation:has_smiles,";
	var hostbasicinfovalue = "";
	var hostbasicinfoproperty = "";

	
	if(hostpropertyvalues.split(",")[0] == ""){
		alert("Please Input The Host Name~");
	}else{
		for (var i = 0; i < hostpropertyvalues.split(",").length; i++) {
			
			if (hostpropertyvalues.split(",")[i] != "") {
				if (i == 0) {
					
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";

				} else if (i == 1) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 2) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 3) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 4) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 5) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 6) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 7) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 8) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} else if (i == 9) {
					hostbasicinfovalue += hostbasicinfovalues.split(",")[i] + ",";
					hostbasicinfoproperty += hostbasicinfopropertys.split(",")[i]
							+ ",";
				} 

			}

		}
		var hostmonoinfo = "";
		var NUMBEROFMONO = document.getElementById("countValue").value;
		for (var i = 1; i <= NUMBEROFMONO; i++) {

			if (document.getElementById("selectedMono" + i) != null) {
				var selectedMono = document.getElementById("selectedMono" + i).value;
				var has_cardinality = document.getElementsByName("quantity" + i)[0].value;
				hostmonoinfo += " complexation:" + hostname
						+ " glycan:has_component _:component."
						+ " _:component glycan:has_monosaccharide complexation:"
						+ selectedMono + ". _:component glycan:has_cardinality \""
						+ has_cardinality + "\"^^xsd:integer. ";
			}
		}
		var fileObj = document.getElementById("image2").files[0]; // js 获取文件对象
		var FileController = "SubmitHost"; // 接收上传文件的后台地址
		// FormData 对象
		var form = new FormData();

		form.append("image", fileObj); // 文件对象
		form.append("hostproperty", hostbasicinfoproperty); // 文件对象
		form.append("hostvalue", hostbasicinfovalue); // 文件对象
		form.append("hostmonoinfo", hostmonoinfo); // 文件对象
		form.append("hostmolfile", has_mol); // 文件对象

		// 文件对象 */
		form.append("host", hostname); // 文件对象
		// XMLHttpRequest 对象
		var xhr = new XMLHttpRequest();
		xhr.open("post", basePath + FileController, true);

		xhr.onload = function() {

			alert("Upload Success!");

		};

		// xhr.upload.addEventListener("progress", image1ProgressFunction, false);
		xhr.send(form);
	}
	
	

}
function submitGuestInfo(basepath){
	
	
	var has_guest_name = document.getElementById("has_guest_name").value;
	var has_canonicalized = document.getElementById("has_canonicalized").value;
	var has_complexity = document.getElementById("has_complexity").value;
	var has_h_bond_acceptor = document.getElementById("has_h_bond_acceptor").value;
	var has_h_bond_donor = document.getElementById("has_h_bond_donor").value;
	var has_rotatable_bond = document.getElementById("has_rotatable_bond").value;
	var has_subskeys = document.getElementById("has_subskeys").value;
	var has_iupac_openeye_name = document.getElementById("has_iupac_openeye_name").value;
	var has_iupac_cas_name = document.getElementById("has_iupac_cas_name").value;
	var has_iupac_name = document.getElementById("has_iupac_name").value;
	var has_systematic_name = document.getElementById("has_systematic_name").value;
	var has_iupac_traditional_name = document.getElementById("has_iupac_traditional_name").value;
	var has_iupac_inchi = document.getElementById("has_iupac_inchi").value;
	var has_iupac_inchikey = document.getElementById("has_iupac_inchikey").value;
	var has_xlogp3_aa = document.getElementById("has_xlogp3_aa").value;
	var has_exact_mass = document.getElementById("has_exact_mass").value;
	var has_molecular_formular = document.getElementById("has_molecular_formular").value;
	var has_molecular_weight = document.getElementById("has_molecular_weight").value;
	var has_openeye_can_smiles = document.getElementById("has_openeye_can_smiles").value;
	var has_openeye_iso_smiles = document.getElementById("has_openeye_iso_smiles").value;
	var has_tpas = document.getElementById("has_tpas").value;
	var has_monoisotopic_weight = document.getElementById("has_monoisotopic_weight").value;
	var has_total_charge = document.getElementById("has_total_charge").value;
	var has_heavy_atom_count = document.getElementById("has_heavy_atom_count").value;
	var has_atom_def_stereo_count = document.getElementById("has_atom_def_stereo_count").value;
	var has_atom_udef_stereo_count = document.getElementById("has_atom_udef_stereo_count").value;
	var has_bond_def_stereo_count = document.getElementById("has_bond_def_stereo_count").value;
	var has_bond_udef_stereo_count = document.getElementById("has_bond_udef_stereo_count").value;
	var has_isotopic_atom_count = document.getElementById("has_isotopic_atom_count").value;
	var has_component_count = document.getElementById("has_component_count").value;
	var has_tauto_count = document.getElementById("has_tauto_count").value;
	var has_coordinate_type = document.getElementById("has_coordinate_type").value;
	var has_bond_annotations = document.getElementById("has_bond_annotations").value;
	
	var guestValues = has_guest_name + "," + has_canonicalized + "," + has_complexity + "," 
				+ has_h_bond_acceptor + "," + has_h_bond_donor + "," + has_rotatable_bond + ","
				+ has_subskeys + "," + has_iupac_openeye_name + "," + has_iupac_cas_name + ","
				+ has_iupac_name + "," + has_systematic_name + "," + has_iupac_traditional_name + ","
				+ has_iupac_inchi + "," + has_iupac_inchikey + "," + has_xlogp3_aa + ","
				+ has_exact_mass + "," + has_molecular_formular + "," + has_molecular_weight + ","
				+ has_openeye_can_smiles + "," + has_openeye_iso_smiles + "," + has_tpas + ","
				+ has_monoisotopic_weight + "," + has_total_charge + "," + has_heavy_atom_count + ","
				+ has_atom_def_stereo_count + "," + has_atom_udef_stereo_count + "," + has_bond_def_stereo_count + ","
				+ has_bond_udef_stereo_count + "," + has_isotopic_atom_count + "," + has_component_count + ","
				+ has_tauto_count +"," + has_coordinate_type + "," + has_bond_annotations;
	
	var guestProperties = "has_guest_name,has_canonicalized,has_complexity," 
	+ "has_h_bond_acceptor,has_h_bond_donor,has_rotatable_bond,"
	+ "has_subskeys,has_iupac_openeye_name,has_iupac_cas_name,"
	+ "has_iupac_name,has_systematic_name,has_iupac_traditional_name,"
	+ "has_iupac_inchi,has_iupac_inchikey,has_xlogp3_aa,"
	+ "has_exact_mass,has_molecular_formular,has_molecular_weight,"
	+ "has_openeye_can_smiles,has_openeye_iso_smiles,has_tpas,"
	+ "has_monoisotopic_weight,has_total_charge,has_heavy_atom_count,"
	+ "has_atom_def_stereo_count,has_atom_udef_stereo_count,has_bond_def_stereo_count,"
	+ "has_bond_udef_stereo_count,has_isotopic_atom_count,has_component_count,"
	+ "has_tauto_count,has_coordinate_type,has_bond_annotations";
	
	var guestPropertyValues = "";
	if(guestValues.split(",")[0] == ""){
		alert("Please input the guest name~");
	}else{
		guestName = "complexation:" + guestValues.split(",")[0].replace(" ","-").toLowerCase();
		for(var i = 0; i < guestValues.split(",").length; i++){
			if(guestValues.split(",")[i] != ""){
				guestPropertyValues += guestName + " " + guestProperties.split(",")[i] + " \"" +
				guestValues.split(",")[i] + "\"^^xsd:string. ";
			}
		}
		
		guestPropertyValues += guestName + " rdf:type complexation:guest. ";
		
		var FileController = "SubmitGuestInfos"; // 接收上传文件的后台地址
		// FormData 对象

		var form = new FormData();
		
		form.append("guestvalues", guestPropertyValues); // 文件对象
		// XMLHttpRequest 对象
		var xhr = new XMLHttpRequest();
		xhr.open("post",basepath +  FileController, true);

		xhr.onload = function() {

			alert("Upload Success!");

		};

		// xhr.upload.addEventListener("progress", image1ProgressFunction, false);
		xhr.send(form);
	}
	
	
	
}
function submitMonosaccharideInfo(basepath) {
	var monosaccharideinfo = "";
	var monosaccharideProperty = "";

	var monosaccharide = document.getElementById("monosaccharide").value;
	var has_alias = document.getElementById("has_alias_name").value;
	var primaryornot = document.getElementById("checkId").value;
	var notation = document
			.getElementById("has_monosaccharide_notation_scheme").value;
	
	var has_anomer = document.getElementById("has_anomer").value;
	var has_basetype_id = document.getElementById("has_basetype_id").value;
	var has_ring_start = document.getElementById("has_ring_start").value;
	var has_ring_end = document.getElementById("has_ring_end").value;
	var has_size = document.getElementById("has_size").value;
	var has_ring_type = document.getElementById("has_ring_type").value;
	var has_absolute_configuration = document
			.getElementById("has_absolute_configuration").value;
	var has_relative_configuration = document
			.getElementById("has_relative_configuration").value;
	var has_core_modification_type = document
			.getElementById("has_core_modification_type").value;
	var has_modification_position = document
			.getElementById("has_modification_position").value;
	var has_substituent = document.getElementById("has_substituent").value;
	var substituent_type = document.getElementById("substituent_type").value;
	var has_linkage_type = document.getElementById("has_linkage_type").value;
	var has_basetype_linkage_position = document
			.getElementById("has_basetype_linkage_position").value;
	var has_average_molecular_weight = document
			.getElementById("has_average_molecular_weight").value;
	var has_msdb_id = document.getElementById("has_msdb_id").value;
	var basetype_name = document.getElementById("basetype_name").value;

	monosaccharide = monosaccharide.replace(" ","-");
	basetype_name = basetype_name.replace(" ","-");

	var monosaccharideinfos = "";
	var monosaccharidePropertys = "";
	monosaccharideinfos = has_alias + ",\"true\"^^xsd:boolean," + notation
			+ "," + has_anomer + "," + has_basetype_id + "," + has_ring_start
			+ "," + has_ring_end + "," + has_size + "," + has_ring_type + ","
			+ has_absolute_configuration + "," + has_relative_configuration
			+ "," + has_core_modification_type + ","
			+ has_modification_position + "," + has_substituent + ","
			+ substituent_type + "," + has_linkage_type + "," + ","
			+ has_basetype_linkage_position + ","
			+ has_average_molecular_weight + "," + has_msdb_id;
	monosaccharidePropertys += "glycan:has_alias_name,glycan:"
			+ primaryornot
			+ ",glycan:has_monosaccharide_notation_scheme,glycan:has_anomer,glycan:has_basetype_id,glycan:has_ring_start,"
			+ "glycan:has_ring_end,glycan:has_size,glycan:has_ring_type,glycan:has_absolute_configuration,"
			+ "glycan:has_relative_configuration,glycan:has_core_modification_type,glycan:has_modification_position,"
			+ "glycan:has_substituent,glycan:substituent_type,glycan:has_linkage_type,glycan:has_basetype_linkage_position"
			+ ",glycan:has_average_molecular_weight,glycan:has_msdb_id";
	
	if(monosaccharide == ""){
		alert("Please Input Monosaccharide Name~");
	}else{
		for (var i = 0; i < monosaccharideinfos.split(",").length; i++) {
			if (monosaccharideinfos.split(",")[i] != "") {
				if (i == 0) {

					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:string,";
					monosaccharideProperty += "glycan:has_alias_name,";
					monosaccharideinfo += monosaccharideinfos.split(",")[1] + ",";
					monosaccharideProperty += "glycan:" + primaryornot + ",";
					monosaccharideinfo += monosaccharideinfos.split(",")[2] + ",";
					monosaccharideProperty += "glycan:has_monosaccharide_notation_scheme,";
				} else if (i == 3) {
					monosaccharideinfo += monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_anomer,";
				} else if (i == 4) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:string,";
					monosaccharideProperty += "glycan:has_basetype_id,";

				} else if (i == 5) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:integer,";
					monosaccharideProperty += "glycan:has_ring_start,";
				} else if (i == 6) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:integer,";
					monosaccharideProperty += "glycan:has_ring_end,";
				} else if (i == 7) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:integer,";
					monosaccharideProperty += "glycan:has_size,";
				} else if (i == 8) {
					monosaccharideinfo += monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_ring_type,";
				} else if (i == 9) {
					monosaccharideinfo += "glycan:"
							+ monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_absolute_configuration,";
				} else if (i == 10) {
					monosaccharideinfo += monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_relative_configuration,";
				} else if (i == 11) {
					monosaccharideinfo += monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_core_modification_type,";
				} else if (i == 12) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:integer,";
					monosaccharideProperty += "glycan:has_modification_position,";
				} else if (i == 13) {
					monosaccharideinfo += "complexation:"
							+ monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_substitution,";
				} else if (i == 14) {
					monosaccharideinfo += "glycan:"
							+ monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:substituent_type,";
				} else if (i == 15) {
					monosaccharideinfo += "glycan:"
							+ monosaccharideinfos.split(",")[i] + ",";
					monosaccharideProperty += "glycan:has_linkage_type,";
				} else if (i == 16) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:integer,";
					monosaccharideProperty += "glycan:has_basetype_linkage_position,";
				} else if (i == 17) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:double,";
					monosaccharideProperty += "glycan:has_average_molecular_weight,";

				} else if (i == 18) {
					monosaccharideinfo += "\"" + monosaccharideinfos.split(",")[i]
							+ "\"^^xsd:integer";
					monosaccharideProperty += "glycan:has_msdb_id";
				}
			} else {

				monosaccharideinfo += ",";
				monosaccharideProperty += ",";

			}
		}

		var FileController = "SubmitMonosaccharide"; // 接收上传文件的后台地址
		// FormData 对象
		var form = new FormData();

		form.append("monosaccharide", monosaccharide); // 文件对象
		form.append("monosaccharideproperty", monosaccharideProperty); // 文件对象
		form.append("monosaccharidevalue", monosaccharideinfo); // 文件对象
		form.append("basetype", basetype_name); // 文件对象
		// XMLHttpRequest 对象
		var xhr = new XMLHttpRequest();
		xhr.open("post", basepath + FileController, true);

		xhr.onload = function() {

			alert("Upload Success!");

		};

		// xhr.upload.addEventListener("progress", image1ProgressFunction, false);
		xhr.send(form);
	}
	

}
function searchComplexation(basepath) {
	var searchhost = document.getElementById("searchhost").value;
	var searchguest = document.getElementById("searchguest").value;
	searchhost = searchhost.replace(" ","-");
	searchhost = searchhost.toLowerCase();
	
	searchguest = searchguest.replace(" ", "-");
	searchguest = searchguest.toLowerCase();
	if (searchhost != "" && searchguest == "") {
		// Search By Host
		window.location.href = basepath
				+ "dist/example/search_complexation.jsp?mode=1&host="
				+ searchhost;
	} else if (searchhost == "" && searchguest != "") {
		// Search By Guest
		window.location.href = basepath
				+ "dist/example/search_complexation.jsp?mode=2&guest="
				+ searchguest;
	} else if (searchhost != "" && searchguest != "") {
		// Search By Host & Guest
		window.location.href = basepath
				+ "dist/example/search_complexation.jsp?mode=3&host="
				+ searchhost + "&guest=" + searchguest;
	} else {
		alert("Sorry. No input.");
		window.location.href = basepath
		+ "dist/example/search_complexation.jsp?mode=4";
	}
}
function searchGuest(basepath) {
	var searchguest = document.getElementById("searchguest").value;
	searchguest = searchguest.replace(" ", "-");
	searchguest = searchguest.toLowerCase();
	if (searchguest != "") {
		// Search By Host
		window.location.href = basepath
				+ "dist/example/search_guest.jsp?mode=1&guest=" + searchguest;
	} else {
		alert("Sorry. No input.");
		window.location.href = basepath
		+ "dist/example/search_guest.jsp?mode=2";
	}
}
function searchHost(basepath) {
	//var host = document.getElementsByName("hosts")[0].value;
	var host = $('input[name="hosts"]:checked').val();
	var searchhost = document.getElementById("searchhost").value;
	var mononame = document.getElementById("mononame").value;
	if(searchhost != "" || mononame != ""){
		if(host == "1"){
			
			searchhost = searchhost.replace(" ","-");
			searchhost = searchhost.toLowerCase();
			if (searchhost != "") {
				// Search By Host
				window.location.href = basepath
						+ "dist/example/search_host.jsp?mode=1&host=" + searchhost;
			} else {
				alert("Sorry. No input.");
			}
		}else if(host == "2"){
			
			mononame = mononame.replace(" ","-");
			mononame = mononame.toLowerCase();
			var numofmono = document.getElementsByName("numofmono")[0].value;
			if(mononame != ""){
				window.location.href = basepath
				+ "dist/example/search_host.jsp?mode=2&mono=" + mononame + "&numofmono=" + numofmono;
			}else{
				alert("Sorry. Please Input Monosaccharide To Search.");
			}
		}
	}else{
		window.location.href = basepath
		+ "dist/example/search_host.jsp?mode=3";
	}
	
	
}
function searchMonosaccharide(basepath) {
	var searchmono = document.getElementById("searchmono").value;
	searchmono = searchmono.replace(" ", "-");
	searchmono = searchmono.toLowerCase();
	if (searchmono != "") {
		window.location.href = basepath
				+ "dist/example/search_monosaccharide.jsp?mode=1&mono="
				+ searchmono;

	} else {
		alert("Sorry. No input.");
		window.location.href = basepath
		+ "dist/example/search_monosaccharide.jsp?mode=2";
	}

}
function searchRecommendation(basepath, guestName){
	window.location.href = basepath + "dist/example/search_complexation.jsp?mode=2&guest="
	+ guestName;
}
function searchByGuest(basepath) {
	var select = document.getElementsByName("guestselect");
	// alert(select);
	if (select[0].checked == true) {
		var guestName = document.getElementById("guestname").value;
		window.location.href = "searchbyguest.jsp?guest=" + guestName
				+ "&mode=" + "1";
	} else {
		var pubChemId = document.getElementById("pubchemid").value;
		window.location.href = "searchbyguest.jsp?guest=" + pubChemId
				+ "&mode=" + "2";
	}

	document.getElementById("yl").style.display = "block";
}
function searchByComplexation() {
	var select = document.getElementsByName("comp");
	// alert(select);

	if (select[0].checked == true) {
		var option = document.getElementById("compare").value;
		var bindresult = document.getElementById("bindresult").value;
		alert(bindresult);
		window.location.href = "searchbycomplexation.jsp?property=" + ""
				+ "&option=" + option + "&value=" + bindresult + "&mode=" + "1";
	} else {
		var phenovalue = document.getElementById("phenovalue").value;
		var phenomena = document.getElementById("phenomena").value;
		var option1 = document.getElementById("compare").value;
		window.location.href = "searchbycomplexation.jsp?property=" + phenomena
				+ "&value=" + phenovalue + "&option=" + option1 + "&mode="
				+ "2";
	}

	// document.getElementById("yl").style.dispplay = "block";
}
function searchByHost() {
	var select = document.getElementsByName("host");
	if (select[0].checked == true) {
		var hostname = document.getElementById("hostname").value;
		window.location.href = "searchbyhost.jsp?host=" + hostname + "&mode="
				+ "1";
	} else if (select[1].checked == true) {
		var mononame = document.getElementById("mononame").value;
		var numofmono = document.getElementById("numofmono").value;
		window.location.href = "searchbyhost.jsp?host=" + mononame
				+ "&numofmono=" + numofmono + "&mode=" + "2";
	} else {
		var anomer = document.getElementById("anomer").value;
		var ring = document.getElementById("ring").value;
		var absolute = document.getElementById("absolute").value;
		var relative = document.getElementById("relative").value;
		window.location.href = "searchbyhost.jsp?host=" + anomer + "&ring="
				+ ring + "&absolute=" + absolute + "&relative=" + relative
				+ "&mode=" + "3";
	}
}
function sendGuestName(basepath) {
	var fileObj = document.getElementById("upfile").files[0]; // js 获取文件对象
	var guest = document.getElementById("has_guest").value;
	var FileController = "SubmitGuest"; // 接收上传文件的后台地址
	// FormData 对象
	guest = guest.replace(" ", "-");
	if(guest == ""){
		alert("Please Input The Guest Name~");
	}else{
		var form = new FormData();
		// form.append("author", "hooyes"); // 可以增加表单数据
		form.append("image", fileObj); // 文件对象
		form.append("guestname", guest); // 文件对象

		// XMLHttpRequest 对象
		var xhr = new XMLHttpRequest();
		xhr.open("post", basepath + FileController, true);

		xhr.onload = function() {

			alert("Upload Success!");

		};

		xhr.send(form);
		
	}
	
	
}
function sendPubChemFile() {
	var fileObj = document.getElementById("pubchemfile").files[0]; // js 获取文件对象
	var FileController = "SubmitPubChemFile"; // 接收上传文件的后台地址
	// FormData 对象
	var form = new FormData();
	form.append("pubchem", fileObj); // 文件对象
	// XMLHttpRequest 对象
	var xhr = new XMLHttpRequest();
	xhr.open("post", FileController, true);
	alert();
	xhr.onload = function() {

		alert("Upload Success!");

	};
	xhr.upload.addEventListener("progress", fileProgressFunction, false);
	xhr.send(form);

}
function fileProgressFunction(evt) {

	var progressBar = document.getElementById("file_progressBar");

	var percentageDiv = document.getElementById("file_percentage");

	if (evt.lengthComputable) {

		progressBar.max = evt.total;

		progressBar.value = evt.loaded;

		percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100)
				+ "%";

	}

}
function searchSimilarGuest(basepath) {
	
	var molfile = document.getElementById("molfile").value;
	var mol = document.JME.molFile();
	// document.form.mol_output.value = mol;
	// alert("MOL:" + mol);
	var sendmol = "";
	for (var i = 0; i < mol.split("\n").length; i++) {
		sendmol += mol.split("\n")[i] + ",";
	}
	
	var mode = "";
	if(mol == "" && molfile == ""){
		alert("No Structure For Search~");
	}else{
		if (mol != "") {
			mode = "1";
			document.getElementById("hiddenmol").value = sendmol;	
		} else if (mol == "" && molfile != "") {
			mode = "2";
			document.getElementById("hiddenmol").value = molfile;	
		}
		
		document.getElementById("hiddenmode").value = mode;
		document.getElementById("hiddenform").submit();
	}
	


}

function lessQuantity(selectMonoCount) {
	var value = parseFloat($("." + selectMonoCount + " #quantity").val()) - 1;
	if (parseFloat(value) < 0)
		value = 0;
	$("." + selectMonoCount + " #quantity").val(value);
}
function plusQuantity(selectMonoCount) {
	var value = parseFloat($("." + selectMonoCount + " #quantity").val()) + 1;
	$("." + selectMonoCount + " #quantity").val(value);
}
