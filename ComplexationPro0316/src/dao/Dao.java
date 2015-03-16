package dao;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;

import model.*;

import org.apache.jena.atlas.lib.Sync;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.sparql.mgt.SystemInfo;
import com.hp.hpl.jena.tdb.*;
import com.hp.hpl.jena.update.*;

public class Dao {

	JenaDao jena = new JenaDao();
	AssistToolDao tool = new AssistToolDao();

	public ArrayList<Complexation> searchByHostName(String hostName) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();

		// Create a new query
		
		String querySubPart1 = " select DISTINCT ?complexation ?guest  ?bindingconstant ?setstoichiometry"
				+ " { ?complexation complexation:has_host  complexation:"
				+ hostName.toLowerCase().replace(" ", "-")
				+ ". "
				+ "?complexation complexation:has_guest ?guest.";

		String querySubPart2 = " OPTIONAL { ?complexation  complexation:binding_constant ?bindingconstant. } ";
		String querySubPart2_1 = "OPTIONAL { ?complexation  complexation:stoichiometry_binding ?setstoichiometry. }";
		String querySubPart3 = " OPTIONAL { ?complexation   complexation:used_condition _:condition . }"
				+ " OPTIONAL { _:condition1 complexation:used_temperature ?temperature .} "
				+ " OPTIONAL { _:condition2 complexation:used_solvent ?solvent. } "
				+ " OPTIONAL {_:condition3 complexation:used_ph ?ph. } "
				+ " OPTIONAL {_:condition4 complexation:used_time ?time . }} ";

		String sparql = Variables.prefix + querySubPart1 + querySubPart2
				+ querySubPart2_1 + querySubPart3;

		// String sparqlquery = Variables.prefix + queryPart1
		// + hostName.toLowerCase().replace(" ", "-") + ". }";

		return jena.searchAllComplexationFromJena2(sparql);

	}

	public ArrayList<Host> searchByHostComponent(String monosaccharide,
			int numOfMono) {
		ArrayList<Host> list = new ArrayList<Host>();

		// Create a new query

		monosaccharide = "complexation:" + monosaccharide;
		String queryPart1 = " select DISTINCT  ?host ?molecular_formular ?boiling_point ?melting_point ?solubility"
				+ " ?toxicity { ?complexation complexation:has_host ?host. "
				+ " ?host glycan:has_component _:component. "
				+ " _:component1 glycan:has_monosaccharide ";

		String queryPart2 = " _:component2 glycan:has_cardinality ";

		String queryPart3 = " OPTIONAL {?host complexation:has_molecular_formular ?molecular_formular.} "
				+ " OPTIONAL {?host complexation:has_boiling_point ?boiling_point.}"
				+ " OPTIONAL {?host complexation:has_melting_point ?melting_point.} "
				+ " OPTIONAL {?host complexation:has_solubility ?solubility. }"
				+ " OPTIONAL {?host complexation:has_toxicity ?toxicity.}} ";

		String sparqlquery = Variables.prefix + queryPart1
				+ monosaccharide.toLowerCase() + ". " + queryPart2 + "\""
				+ numOfMono + "\"^^xsd:integer" + ". " + queryPart3;

		return jena.searchAllHostFromJena("", sparqlquery);

	}

	public ArrayList<Complexation> searchByHostConfiguration(String anomer,
			String ring, String absolute, String relative) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();

		String queryPart1 = " select  ?complexation  "
				+ " { ?complexation complexation:has_host ?host. "
				+ " ?host complexation:has_component _:component. "
				+ " _:component  glycan:has_monosaccharide ?monosaccharide. "
				+ "?monosaccharide glycan:has_basetype _:basetype . "
				+ "_:basetype glycan:has_anomer  ";
		String queryPart2 = " _:basetype glycan:has_configuration _:configuration. "
				+ "_:configuration glycan:has_absolute_configuration  ";
		String queryPart3 = " _:configuration glycan:has_relative_configuration  ";
		String queryPart4 = " _:configuration glycan:ring_type ";

		String sparqlquery = Variables.prefix + queryPart1 + "\"" + anomer
				+ "\"^^xsd:string. " + queryPart2 + "\"" + absolute
				+ "\"^^xsd:string. " + queryPart3 + " \"" + relative
				+ "\"^^xsd:string. " + queryPart4 + "\"" + ring
				+ "\"^^xsd:string. }";

		jena.searchDataFromJena(list, sparqlquery);

		return list;
	}

	public ArrayList<Complexation> searchByGuestName(String guestName) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();

		String queryPart1 = " select DISTINCT ?complexation ?host ?bindingconstant ?setstoichiometry "
				+ " { ?complexation complexation:has_guest complexation:";
		String queryPart2 = "  complexation:has_mol_id ?mol_id. ?complexation complexation:has_host ?host. ";
		String querySubPart3 = " OPTIONAL { ?complexation  complexation:binding_constant ?bindingconstant. } ";
		String querySubPart3_1 = "OPTIONAL { ?complexation  complexation:stoichiometry_binding ?setstoichiometry. }";
		String querySubPart4 = " OPTIONAL { ?complexation   complexation:used_condition _:condition . }"
				+ " OPTIONAL { _:condition1 complexation:used_temperature ?temperature .} "
				+ " OPTIONAL { _:condition2 complexation:used_solvent ?solvent. } "
				+ " OPTIONAL {_:condition3 complexation:used_ph ?ph. } "
				+ " OPTIONAL {_:condition4 complexation:used_time ?time . } }";

		String sparql = Variables.prefix + queryPart1 + guestName + ". "
				+ "complexation:" + guestName + queryPart2 + querySubPart3
				+ querySubPart3_1 + querySubPart4;

		return jena.searchAllComplexationFromJena2(sparql);
	}

	public ArrayList<Complexation> searchByHostGuestName(String hostName,
			String guestName) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();
		String querySubPart1 = " select DISTINCT ?complexation ?bindingconstant ?setstoichiometry "
				+ " { ?complexation complexation:has_host  complexation:"
				+ hostName.toLowerCase().replace(" ", "-") + ". ";

		String queryPart1 = " ?complexation complexation:has_guest complexation:";
		String queryPart2 = "  complexation:has_mol_id ?mol_id. ";
		String querySubPart3 = " OPTIONAL { ?complexation  complexation:binding_constant ?bindingconstant. } ";
		String querySubPart3_1 = "OPTIONAL { ?complexation  complexation:stoichiometry_binding ?setstoichiometry. }";
		String querySubPart4 = " OPTIONAL { ?complexation   complexation:used_condition _:condition . }"
				+ " OPTIONAL { _:condition1 complexation:used_temperature ?temperature .} "
				+ " OPTIONAL { _:condition2 complexation:used_solvent ?solvent. } "
				+ " OPTIONAL {_:condition3 complexation:used_ph ?ph. } "
				+ " OPTIONAL {_:condition4 complexation:used_time ?time . } "
				+ " OPTIONAL {?complexation complexation:has_evidence _:nmr. } "
				+ " OPTIONAL {_:nmr1 complexation:nmr_spectrum ?nmrspec.} }";

		String sparql = Variables.prefix + querySubPart1 + queryPart1
				+ guestName + ". " + "complexation:" + guestName + queryPart2
				+ querySubPart3 + querySubPart3_1 + querySubPart4;

		return jena.searchAllComplexationFromJena2(sparql);
	}

	public ArrayList<Complexation> searchByGuestPubChemId(String guestPubChemId) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();

		String queryPart1 = " select DISTINCT ?complexation  "
				+ " { ?complexation complexation:has_guest ?guest. "
				+ " ?guest complexation:has_mol_id  ";

		String sparqlquery = Variables.prefix + queryPart1 + "\""
				+ guestPubChemId + "\"^^xsd:string " + ". }";

		jena.searchDataFromJena(list, sparqlquery);

		return list;
	}

	public ArrayList<Complexation> searchByComplexationResult(String option,
			String value) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();

		String queryPart1 = " select  ?complexation { ?complexation complexation:binding_constant ?value. ";

		String sparqlquery = Variables.prefix + queryPart1 + " FILTER (?value "
				+ option + "  \"" + value + "\"^^xsd:double). }";

		jena.searchDataFromJena(list, sparqlquery);

		return list;
	}

	public ArrayList<Complexation> searchByComplexationPhenomena(
			String phenomena, String option, String value) {
		ArrayList<Complexation> list = new ArrayList<Complexation>();

		String queryPart1 = " select  ?complexation   ?value where "
				+ " { ?complexation complexation:has_phenomena _:phenomena. "
				+ " _:phenomena ";
		String queryPart2 = " ?value . " + "FILTER (?value ";

		String sparqlquery = Variables.prefix + queryPart1 + "complexation:"
				+ phenomena + queryPart2 + " " + option + " \"" + value
				+ "\"^^xsd:double ). }";
		jena.searchDataFromJena(list, sparqlquery);

		return list;
	}


	public ArrayList<Complexation> getAllComplexation() {

		return jena.searchAllComplexationFromJena();
	}

	public ArrayList<Guest> getAllGuest() {

		String querySubPart1 = " select * "
				+ " {  ?guest complexation:has_mol_id ?mol_id. "
				+ "  OPTIONAL {?guest complexation:has_iupac_name ?iupac.}"
				+ " OPTIONAL{ ?guest complexation:exact_mass ?mass. }"
				+ " OPTIONAL {?guest complexation:has_molecular_formular ?formular. }"
				+ "OPTIONAL {?guest complexation:has_molecular_weight ?weight. } "
				+ "OPTIONAL {?guest complexation:has_openeye_iso_smiles ?smiles.  }} ";

		String sparql = Variables.prefix + querySubPart1;
		System.out.println("searchAllGuest: " + sparql);
		return jena.searchAllGuestFromJena(sparql);
	}

	public ArrayList<Guest> getGuestsByName(String guestName) {
		guestName = "complexation:" + guestName;
		String querySubPart1 = " select * " + " {  " + guestName
				+ " complexation:has_mol_id ?mol_id. " + "  OPTIONAL {"
				+ guestName + " complexation:has_iupac_name ?iupac.}"
				+ " OPTIONAL{ " + guestName
				+ " complexation:exact_mass ?mass. }" + " OPTIONAL {"
				+ guestName
				+ " complexation:has_molecular_formular ?formular. }"
				+ "OPTIONAL {" + guestName
				+ " complexation:has_molecular_weight ?weight. } "
				+ "OPTIONAL {" + guestName
				+ " complexation:has_openeye_iso_smiles ?smiles.  }} ";

		String sparql = Variables.prefix + querySubPart1;
		System.out.println("searchAllGuest: " + sparql);
		return jena.searchAllGuestFromJena(sparql);
	}

	public ArrayList<Host> getAllHost() {
		String querySubPart1 = " select * { ?host rdf:type complexation:host. ";

		String querySubPart2 = " OPTIONAL {?host complexation:has_molecular_formular ?molecular_formular.} "
				+ " OPTIONAL {?host complexation:has_boiling_point ?boiling_point.}"
				+ " OPTIONAL {?host complexation:has_melting_point ?melting_point.} "
				+ " OPTIONAL {?host complexation:has_solubility ?solubility. }"
				+ " OPTIONAL {?host complexation:has_toxicity ?toxicity.}} ";
		String sparql = Variables.prefix + querySubPart1 + querySubPart2;
		System.out.println("searchAllGuest: " + sparql);
		return jena.searchAllHostFromJena("", sparql);
	}

	public ArrayList<Host> getHostsByName(String hostName) {
		hostName = "complexation:" + hostName;
		String querySubPart1 = " select * { ";

		String querySubPart2 = " OPTIONAL {" + hostName
				+ " complexation:has_molecular_formular ?molecular_formular.} "
				+ " OPTIONAL {" + hostName
				+ " complexation:has_boiling_point ?boiling_point.}"
				+ " OPTIONAL {" + hostName
				+ " complexation:has_melting_point ?melting_point.} "
				+ " OPTIONAL {" + hostName
				+ " complexation:has_solubility ?solubility. }" + " OPTIONAL {"
				+ hostName + " complexation:has_toxicity ?toxicity.}} ";
		String sparql = Variables.prefix + querySubPart1 + querySubPart2;
		System.out.println("searchAllGuest: " + sparql);
		return jena.searchAllHostFromJena(hostName, sparql);
	}

	public ArrayList<Monosaccharide> getMonosaccharidesByName(String monoName) {
		monoName = "complexation:" + monoName;
		String querySubPart1 = " select * "
				+ " { OPTIONAL {"
				+ monoName
				+ " glycan:has_alias _:alias. _:alias1 glycan:has_alias_name ?alias.} "

				+ "  OPTIONAL {_:alias2 glycan:has_monosaccharide_notation_scheme ?notation.}"
				+ " OPTIONAL{ " + monoName + "  glycan:has_anomer ?anomer. }"
				+ " OPTIONAL {" + monoName
				+ "  glycan:has_basetype _:basetype. "
				+ "_:basetype1 glycan:has_absolute_configuration ?absolute. "
				+ "_:basetype2 glycan:has_relative_configuration ?relative. }"
				+ "OPTIONAL {" + monoName
				+ "  glycan:has_substitution ?substituent. }} ";

		String sparql = Variables.prefix + querySubPart1;
		System.out.println("searchAllMono: " + sparql);
		return jena.searchAllMonosaccharideFromJena(sparql);
	}

	public ArrayList<Monosaccharide> getAllMonosaccharide() {

		return jena.searchAllMonosaccharideFromJena();
	}

	public ArrayList<Basetype> getAllBasetype() {

		return jena.searchAllBasetypeFromJena();
	}

	public Guest getGuestInfos(String guestName) {
		Guest guest = new Guest();

		Dataset dataset = TDBFactory.createDataset(Variables.directory);
		Model model = dataset.getDefaultModel();

		guestName = "complexation:" + guestName;

		String querySubPart1 = " select * " + " {  " + guestName
				+ "    complexation:has_mol_id ?mol_id . ";

		String querySubPart2 = "";
		for (int i = 0; i < Variables.guestPropertyOntology.split(",").length; i++) {
			querySubPart2 += " OPTIONAL { "
					+ guestName
					+ " "
					+ Variables.guestPropertyOntology.split(",")[i]
					+ " ?"
					+ Variables.guestPropertyOntology.split(",")[i].split(":")[1]
					+ ". } ";
		}
		String query = Variables.prefix + querySubPart1 + querySubPart2 + "}";
		System.out.println("SearchGuestInfo: " + query);
		return jena.searchGuestInfoFromJena(query);

	}

	public Host getHostInfos(String hostName) {

		hostName = "complexation:" + hostName;

		String querySubPart1 = "select * {" + " OPTIONAL {" + hostName
				+ " complexation:has_molecular_formular ?molecular_formular.} "
				+ " OPTIONAL {" + hostName
				+ " complexation:has_boiling_point ?boiling_point.}"
				+ " OPTIONAL {" + hostName
				+ " complexation:has_melting_point ?melting_point.} "
				+ " OPTIONAL {" + hostName
				+ " complexation:has_solubility ?solubility. }" + " OPTIONAL {"
				+ hostName + " complexation:has_toxicity ?toxicity.} "
				+ " OPTIONAL {" + hostName
				+ " glycan:has_component _:component.}"
				+ " OPTIONAL { _:component1 glycan:has_monosaccharide ?mono.} "
				+ " OPTIONAL { _:component2 glycan:has_cardinality ?card.}";

		String query = Variables.prefix + querySubPart1 + "}";
		System.out.println("SearchHostInfo: " + query);
		return jena.searchHostInfoFromJena(query);

	}

	public Monosaccharide getMonosaccharideInfos(String monoName) {
		Monosaccharide mono = new Monosaccharide();

		Dataset dataset = TDBFactory.createDataset(Variables.directory);
		Model model = dataset.getDefaultModel();

		monoName = "complexation:" + monoName;

		String querySubPart1 = "select * { OPTIONAL {"
				+ monoName
				+ " glycan:has_alias _:alias.} "
				+ " OPTIONAL { _:alias1 glycan:has_alias_name ?alias.} "
				+ " OPTIONAL { _:alias2 glycan:has_monosaccharide_notation_scheme ?notation. } "
				+ " OPTIONAL { "
				+ monoName
				+ " glycan:has_anomer ?anomer. } "
				+ " OPTIONAL {"
				+ monoName
				+ " glycan:has_basetype _:basetype. }"
				+ " OPTIONAL { _:basetype1 glycan:has_basetype_id ?id. } "
				+ " OPTIONAL { _:basetype2 glycan:has_ring_start ?start. }"
				+ " OPTIONAL { _:basetype3 glycan:has_ring_end ?end. } "
				+ " OPTIONAL { _:basetype4 glycan:has_size ?size. } "
				+ " OPTIONAL { _:basetype5 glycan:has_configuration _:config. } "

				+ " OPTIONAL { _:config1 glycan:has_absolute_configuration ?absolute. } "
				+ " OPTIONAL { _:config2 glycan:has_relative_configuration ?relative. } "
				+ " OPTIONAL { _:config3 glycan:has_core_modification ?modif. } "
				+ " OPTIONAL { "
				+ monoName
				+ " glycan:has_substituent ?substituent. } "
				+ " OPTIONAL { ?substituent glycan:substituent_type ?subtype. } "
				+ " OPTIONAL { ?substituent glycan:has_linkage_type ?linktype. } "
				+ " OPTIONAL { " + monoName
				+ " glycan:has_basetype_linkage_position ?baseposi. } ";

		String query = Variables.prefix + querySubPart1 + "}";
		System.out.println("SearchMonoInfo: " + query);
		return jena.searchMonosaccharideInfoFromJena(query);

	}

	public Complexation getComplexationInfos(String comp) {

		Dataset dataset = TDBFactory.createDataset(Variables.directory);
		Model model = dataset.getDefaultModel();

		String querySubPart1 = " select * " + " {  ";
		String querySubPart2 = "    complexation:has_host ?host . "
				+ " OPTIONAL {?host complexation:has_molecular_formular ?molecular_formular.} "
				+ " OPTIONAL {?host complexation:has_boiling_point ?boiling_point.}"
				+ " OPTIONAL {?host complexation:has_melting_point ?melting_point.} "
				+ " OPTIONAL {?host complexation:has_solubility ?solubility. }"
				+ " OPTIONAL {?host complexation:has_toxicity ?toxicity.} ";
		// complexation = " ?complexation" + "";

		String optional = " OPTIONAL { ";
		String querySubPart3 = " complexation:has_guest ?guest. ?guest complexation:has_mol_id ?mol_id. ";
		String querySubPart4 = "   complexation:binding_constant ?bindingconstant. }";
		String querySubPart4_1 = " complexation:stoichiometry_binding ?setstoichiometry. } ";
		String querySubPart4_2 = " complexation:article ?article. } ";
		String querySubPart4_3 = " complexation:journal ?journal. } ";
		String querySubPart5 = "   complexation:used_condition _:condition . }"
				+ " OPTIONAL { _:condition1 complexation:used_temperature ?temperature .} "
				+ " OPTIONAL { _:condition2 complexation:used_solvent ?solvent. } "
				+ " OPTIONAL {_:condition3 complexation:used_ph ?ph. } "
				+ " OPTIONAL {_:condition4 complexation:used_time ?time . }";

		String querySubPart6 = "  complexation:has_phenomena _:phenomena. }"
				+ " OPTIONAL { _:phenomena1 complexation:phenomena_solubility ?phenomena_solubility. } "
				+ " OPTIONAL { _:phenomena2 complexation:phenomena_stability ?phenomena_stability. } "
				+ " OPTIONAL { _:phenomena3 complexation:phenomena_fluorescence ?phenomena_fluorescence.} "
				+ " OPTIONAL { _:phenomena4 complexation:phenomena_enzyme_activity ?phenomena_enzyme_activity.} "
				+ " OPTIONAL { _:phenomena5 complexation:phenomena_bioavailability ?phenomena_bioavailability. } ";

		String querySubPart7 = " complexation:has_evidence _:nmr. }"
				+ " OPTIONAL {_:nmr1 complexation:host_concentration ?hostcon.} "
				+ "	OPTIONAL { _:nmr2 complexation:guest_concentration ?guestcon.} "
				+ "	OPTIONAL {_:nmr3 complexation:buffer ?buffer. }	"
				+ " OPTIONAL {_:nmr4 complexation:frequency ?freq.} "
				+ " OPTIONAL {	_:nmr5 complexation:ph ?usedph. } "
				+ " OPTIONAL {_:nmr6 complexation:temperature ?temp. } "
				+ " OPTIONAL {_:nmr7 complexation:mixing_time ?mixtime.} "
				+ " OPTIONAL {_:nmr8 complexation:spectrum ?spectrum. } ";

		String sparqlSubquery = Variables.prefix + querySubPart1 + comp
				+ querySubPart2 + comp + querySubPart3 + optional + comp
				+ querySubPart4 + optional + comp + querySubPart4_1 + optional
				+ comp + querySubPart4_2 + optional + comp + querySubPart4_3
				+ optional + comp + querySubPart5 + optional + comp
				+ querySubPart6 + optional + comp + querySubPart7;
		for (int i = 0; i < Variables.guestPropertyOntology.split(",").length; i++) {
			sparqlSubquery += " OPTIONAL { ?guest "
					+ Variables.guestPropertyOntology.split(",")[i]
					+ " ?"
					+ Variables.guestPropertyOntology.split(",")[i].split(":")[1]
					+ ". } ";
		}
		sparqlSubquery += "}";
		Complexation complexation = new Complexation();

		jena.searchDataFromJena(complexation, sparqlSubquery);

		return complexation;
	}

	public boolean checkGuestMolIdDuplicate(String mol_id) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);
		Model model = dataset.getDefaultModel();
		String guest_id = "\"" + mol_id + "\"^^xsd:string ";

		String queryPart1 = " select DISTINCT ?guest  "
				+ " { ?complexation complexation:has_guest ?guest. "
				+ " ?guest complexation:has_mol_id  ";

		String sparqlquery = Variables.prefix + queryPart1 + guest_id + ". }";

		boolean result = true;
		Query query = QueryFactory.create(sparqlquery);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		if (!results.hasNext()) {
			result = false;

		}
		return result;
	}

	public void guestInfoIntoJena(String guestFile, String guestName)
			throws IOException, InterruptedException {

		BufferedReader file = new BufferedReader(new FileReader(guestFile));
		String readfile = "";
		String guestinfo = "";
		String guestFile1 = guestFile.substring(0, guestFile.lastIndexOf("."))
				+ "-1.sdf";
		String guestFile2 = guestFile.substring(0, guestFile.lastIndexOf("."))
				+ "-2.sdf";

		while ((readfile = file.readLine()) != null) {

			guestinfo += readfile + "\n";
		}
		String gueststructure = guestinfo.split(">")[0];
		String guestid = guestinfo.split(">")[2].trim();

		guestName = guestName.toLowerCase();

		PrintWriter writer = new PrintWriter(guestFile, "UTF-8");
		writer.println(gueststructure);
		writer.close();

		PrintWriter writer1 = new PrintWriter(guestFile1, "UTF-8");
		writer1.println(gueststructure);
		writer1.close();

		PrintWriter writer2 = new PrintWriter(guestFile2, "UTF-8");
		writer2.println(gueststructure);
		writer2.close();

		String structure = "";
		String readstruc = "";
		BufferedReader struc = new BufferedReader(new FileReader(guestFile));
		while ((readstruc = struc.readLine()) != null) {

			structure += readstruc + ",";
		}

		// insert structure into molstruc
		// -m: wirte MDL molfile atoms/bonds

		String readline = "";
		readline = tool.checkmolChangeSdfToMDLmolfile(guestFile1);

		String guest_structurefile = Variables.UsedFilePath + "gueststructure.mol";
		PrintWriter writer4 = new PrintWriter(guest_structurefile, "UTF-8");
		writer4.println(readline);
		writer4.close();

		createGuestStructureInPNG(guest_structurefile, guestid);

		// insert structure info into molfgb & molstat & molcfp

		String readline1 = "";

		// checkmol -aXHB get each group information with comma
		readline1 = tool.checkmolRepresentEachElementWithComma(guestFile1);

		String[] molarray = new String[3];
		molarray = readline1.split("\n");
		String molstat = molarray[0];
		String molfgb = molarray[1];
		String molhfp = molarray[2];

		// insert struc info to molstruc
		String mol_id = "0";
		String molid_sparql = " complexation:" + guestName
				+ " complexation:has_mol_id \"" + guestid + "\"^^xsd:string. ";
		String molstruc_sparql = " complexation:" + guestName
				+ " complexation:has_struc \"" + structure + "\"^^xsd:string. ";

		// insert stat info to molstat

		String molstat_sparql = " complexation:" + guestName
				+ " complexation:has_molstat _:molstat. ";
		for (int i = 0; i < molstat.split(",").length; i++) {
			molstat_sparql += " _:molstat "
					+ Variables.molstatTable.split(",")[i] + " \""
					+ molstat.split(",")[i] + "\"^^xsd:integer. ";
		}

		// insert fgb info to molfgb

		String molfgb_sparql = " complexation:" + guestName
				+ " complexation:has_molfgb _:molfgb. ";
		molfgb.replaceAll(";", ",");
		System.out.println(molfgb);
		for (int i = 0; i < molfgb.split(";")[0].split(",").length; i++) {
			molfgb_sparql += " _:molfgb " + Variables.molfgbTable.split(",")[i]
					+ " \"" + molfgb.split(";")[0].split(",")[i]
					+ "\"^^xsd:string. ";
		}
		molfgb_sparql += " _:molfgb " + Variables.molfgbTable.split(",")[8]
				+ " \"" + molfgb.split(";")[1] + "\"^^xsd:integer. ";

		// insert cfp info molcfp
		// -F: fingerprint mode with decimal output

		String readline2 = "";
		readline2 = tool.matchmolCompareFingerPrintDecimalOutput(guestFile2);

		// value should add " " then insert
		String moldfp = " " + readline2;
		String molcfp_sparql = " complexation:" + guestName
				+ " complexation:has_molcfp _:molcfp. ";
		String molcfp = moldfp + "," + molhfp;
		for (int i = 0; i < molcfp.split(";")[0].split(",").length; i++) {
			molcfp_sparql += " _:molcfp " + Variables.molcfpTable.split(",")[i]
					+ " \""
					+ molcfp.split(";")[0].split(",")[i].replace(" ", "")
					+ "\"^^xsd:string. ";
		}
		molcfp_sparql += " _:molcfp " + Variables.molcfpTable.split(",")[17]
				+ " \"" + molcfp.split(";")[1] + "\"^^xsd:string. ";

		String insert_sparql = Variables.prefix + " INSERT DATA{ "
				+ molid_sparql + molstruc_sparql + molstat_sparql
				+ molfgb_sparql + molcfp_sparql + " }";

		jena.insertDataIntoJena(insert_sparql);
	}
	public String guestPropertiesFromPubChem(String guestName, String guestinfo){
		// Used to matching PubChem sdf file property with the whole property,
		// because sometimes the file doesn't contain all the properties.
		
		String propertyValue = "";
		String[] guestinfo_arr = guestinfo.split(">");
		System.out.println("Array length: " + guestinfo_arr.length);
		for (int i = 4; i <= guestinfo_arr.length; i += 2) {
			for(int j= 0 ; j < Variables.pubChemProperties.split(",").length; j++){
				//String pubchem_property = "";
				String pubchem_property = guestinfo.split(">")[i-1].substring(2);
				System.out.println("PUBCHEM: " + pubchem_property);
				
				if(pubchem_property.equals(Variables.pubChemProperties.split(",")[j])){
					propertyValue += " complexation:" + guestName + " "
							+ Variables.guestPropertyOntology.split(",")[j] + " \""
							+ guestinfo.split(">")[i].trim() + "\"^^xsd:string. ";
					continue;
				}else if(pubchem_property.equals("PUBCHEM_COORDINATE_TYPE")){
					propertyValue += " complexation:" + guestName + " "
							+ Variables.guestPropertyOntology.split(",")[j] + " \"" +  
							guestinfo.split(">")[i].trim().replace("\n",
							 " ")+ "\"^^xsd:string. ";
					continue;
				}else if(pubchem_property.equals("PUBCHEM_BONDANNOTATIONS")){
					propertyValue += " complexation:" + guestName + " "
							+ Variables.guestPropertyOntology.split(",")[j] + " \"" + 
							 guestinfo.substring(guestinfo.lastIndexOf(">")+1).trim().replace("\n",
							 " ")+ "\"^^xsd:string. ";
					continue;
				}
		
			}
			
		}
		return propertyValue;
	}

	public void guestFullFileInfoIntoJena(String guestFile, String guestName)
			throws IOException, InterruptedException {
		System.out.println("Insert all the pubchem information into Jena");

		// Insert all the pubchem information into Jena

		BufferedReader file = new BufferedReader(new FileReader(guestFile));
		String readfile = "";
		String guestinfo = "";
		String guestFile1 = guestFile.substring(0, guestFile.lastIndexOf("."))
				+ "-1.sdf";
		String guestFile2 = guestFile.substring(0, guestFile.lastIndexOf("."))
				+ "-2.sdf";

		while ((readfile = file.readLine()) != null) {

			guestinfo += readfile + "\n";
		}

		// Every Property in File
		String gueststructure = guestinfo.split(">")[0];
		String guestid = guestinfo.split(">")[2].trim();

		
		guestName = guestName.toLowerCase();

		PrintWriter writer = new PrintWriter(guestFile, "UTF-8");
		writer.println(gueststructure);
		writer.close();

		PrintWriter writer1 = new PrintWriter(guestFile1, "UTF-8");
		writer1.println(gueststructure);
		writer1.close();

		PrintWriter writer2 = new PrintWriter(guestFile2, "UTF-8");
		writer2.println(gueststructure);
		writer2.close();

		String structure = "";
		String readstruc = "";
		BufferedReader struc = new BufferedReader(new FileReader(guestFile));
		while ((readstruc = struc.readLine()) != null) {

			structure += readstruc + ",";
		}

		// insert structure into molstruc
		// -m: wirte MDL molfile atoms/bonds

		String readline = "";
		readline = tool.checkmolChangeSdfToMDLmolfile(guestFile1);

		String guest_structurefile = Variables.UsedFilePath + "gueststructure.mol";
		PrintWriter writer4 = new PrintWriter(guest_structurefile, "UTF-8");
		writer4.println(readline);
		writer4.close();

		createGuestStructureInPNG(guest_structurefile, guestid);

		// insert structure info into molfgb & molstat & molcfp

		String readline1 = "";

		// checkmol -aXHB get each group information with comma
		readline1 = tool.checkmolRepresentEachElementWithComma(guestFile1);

		String[] molarray = new String[3];
		molarray = readline1.split("\n");
		String molstat = molarray[0];
		String molfgb = molarray[1];
		String molhfp = molarray[2];

		// insert struc info to molstruc
		String mol_id = "0";
		String molid_sparql = " complexation:" + guestName
				+ " complexation:has_mol_id \"" + guestid + "\"^^xsd:string. ";
		String molstruc_sparql = " complexation:" + guestName
				+ " complexation:has_struc \"" + structure + "\"^^xsd:string. ";

		// insert stat info to molstat

		String molstat_sparql = " complexation:" + guestName
				+ " complexation:has_molstat _:molstat. ";
		for (int i = 0; i < molstat.split(",").length; i++) {
			molstat_sparql += " _:molstat "
					+ Variables.molstatTable.split(",")[i] + " \""
					+ molstat.split(",")[i] + "\"^^xsd:integer. ";
		}

		// insert fgb info to molfgb

		String molfgb_sparql = " complexation:" + guestName
				+ " complexation:has_molfgb _:molfgb. ";
		molfgb.replaceAll(";", ",");
		System.out.println(molfgb);
		for (int i = 0; i < molfgb.split(";")[0].split(",").length; i++) {
			molfgb_sparql += " _:molfgb " + Variables.molfgbTable.split(",")[i]
					+ " \"" + molfgb.split(";")[0].split(",")[i]
					+ "\"^^xsd:string. ";
		}
		molfgb_sparql += " _:molfgb " + Variables.molfgbTable.split(",")[8]
				+ " \"" + molfgb.split(";")[1] + "\"^^xsd:integer. ";

		// insert cfp info molcfp
		// -F: fingerprint mode with decimal output

		String readline2 = "";
		readline2 = tool.matchmolCompareFingerPrintDecimalOutput(guestFile2);

		// value should add " " then insert
		String moldfp = " " + readline2;
		String molcfp_sparql = " complexation:" + guestName
				+ " complexation:has_molcfp _:molcfp. ";
		String molcfp = moldfp + "," + molhfp;
		for (int i = 0; i < molcfp.split(";")[0].split(",").length; i++) {
			molcfp_sparql += " _:molcfp " + Variables.molcfpTable.split(",")[i]
					+ " \""
					+ molcfp.split(";")[0].split(",")[i].replace(" ", "")
					+ "\"^^xsd:string. ";
		}
		molcfp_sparql += " _:molcfp " + Variables.molcfpTable.split(",")[17]
				+ " \"" + molcfp.split(";")[1] + "\"^^xsd:string. ";

		// Value for Properties
		String propertyValue =  guestPropertiesFromPubChem( guestName,guestinfo);
		System.out.println("Properties: " + propertyValue);
		
		
		propertyValue += " complexation:" + guestName
				+ " rdf:type complexation:guest. ";

		String insert_sparql = Variables.prefix + " INSERT DATA{ "
				+ molid_sparql + molstruc_sparql + molstat_sparql
				+ molfgb_sparql + molcfp_sparql + propertyValue + " }";
		System.out.println("GuestIntoJena: " + insert_sparql);
		jena.insertDataIntoJena(insert_sparql);
	}

	public void createGuestStructureInPNG(String guestfile, String molid)
			throws IOException, InterruptedException {
		String createimagefile = Variables.UsedFilePath + "createimage.mol";

		String readline = "";
		readline = tool.mol2psChangeMdlfileToPostscript(guestfile);

		PrintWriter writer = new PrintWriter(createimagefile, "UTF-8");
		writer.println(readline);
		writer.close();

		tool.phostscriptChangeToImage(molid, createimagefile);

	}

	public HashMap<String, Double> getSimilarStructure(String testfile)
			throws IOException, InterruptedException {
		System.out.println("Guest Similar Test File: " + testfile);

		HashMap<String, Double> hash = new HashMap<String, Double>();

		String readline = "";
		readline = tool.checkmolRepresentEachGroupInBitString(testfile);

		String[] myres = readline.split("\n");
		String chkresult1a = "";
		String chkresult1b = "";
		String chkresult2 = "";
		String chkresult3 = "";
		String chkresult4 = "";

		String[] hfparr = new String[16];

		if (readline.split("\n").length != 3) {
		} else {
			chkresult1a = myres[0];
			chkresult1b = myres[1];
			chkresult2 = myres[2];
			chkresult3 = chkresult2.split(";")[0];
			chkresult4 = chkresult2.split(";")[1];
			hfparr = (chkresult3 + ",").split(",");
		}
		int max_hits = 250;
		int maxcand = 5000;
		int max_diffsum = 300;
		double ssim_wt = 0.5, fsim_wt = 0.5;

		String bfpnum = tool.matchmolCompareFingerPrintDecimalOutput(testfile);

		// int a_sum_dfp = count1bits_mysql(bfpnum);

		int a_sum_dfp = Long.bitCount(Long.parseLong(bfpnum));
		System.out.println("A Sum dfp: " + a_sum_dfp);
		String querypart_1 = "";
		for (int j = 0; j < chkresult1a.split(";").length; j++) {
			querypart_1 += "(?"
					+ chkresult1a.split(";")[j].replace(":", " - ")
							.toLowerCase()
					+ ") * (?"
					+ chkresult1a.split(";")[j].replace(":", " - ")
							.toLowerCase() + ") +";
		}
		querypart_1 = querypart_1.substring(0, querypart_1.lastIndexOf("+"));

		String querypart = Variables.prefix
				+ Variables.calcGuestMolstatDiffsum_1 + querypart_1
				+ Variables.calcGuestMolstatDiffsum_2;

		ArrayList<Guest> guestlist = new ArrayList<Guest>();
		Molstat molstat = new Molstat();
		jena.searchDataFromJena(molstat, guestlist, querypart);

		String c_qstr1 = "select *  {  "
				+ "  ?guest complexation:has_molcfp _:molcfp. "
				+ " ?guest complexation:has_mol_id ";

		// Get all guest in complexation (tanimoto score > minimum score [now it
		// is 0.1]),
		// then order that by the tanimoto score

		for (int i = 0; i < guestlist.size(); i++) {
			String mol_id = guestlist.get(i).getMolId();
			int diffsum = guestlist.get(i).getDiffsum();
			System.out.println("Mol Id & Diffsum: " + mol_id + " & " + diffsum);
			String c_qstr = Variables.prefix + c_qstr1 + " \"" + mol_id
					+ "\"^^xsd:string. " + Variables.guestMolcfp_1
					+ Variables.guestMolfgb_1 + Variables.guestMolfgb_2;
			Molcfp molcfp = new Molcfp();
			String dfp01 = "";

			Complexation complexation = jena.searchDataFromJena(c_qstr);
			String guestName = complexation.getGuest().getGuest();
			dfp01 = complexation.getGuest().getMolcfp().getDfp01();

			long n1 = 0, n2 = 0, n3 = 0;
			int a = 0, b = 0, c = 0;
			int a_sum = 0, b_sum = 0, c_sum = 0;
			int b_sum_fg = 0, c_sum_fg = 0;
			String[] hfp = new String[16];
			hfp = complexation.getGuest().getMolcfp().getHfp16();
			for (int k = 0; k < 16; k++) {
				n1 = Long.parseLong(hfparr[k].replace(" ", ""));
				n2 = Long.parseLong(hfp[k].replace(" ", "") + "");
				n3 = n1 & n2;

				a = count1bits(n1);
				b = count1bits(n2);
				c = count1bits(n3);

				a_sum += a;
				b_sum += b;
				c_sum += c;

			}

			a_sum += a_sum_dfp;

			int c_dfp = Long.bitCount(bitwiseAnd(Long.parseLong(dfp01.trim()),
					Long.parseLong(bfpnum.trim())));
			int b_dfp = Long.bitCount(Long.parseLong(dfp01.trim()));

			c_sum += c_dfp;
			b_sum += b_dfp;

			String[] fg = chkresult1b.split(";")[0].split(",");
			int a_sum_fg = Integer.parseInt(chkresult1b.split(";")[1]);

			String[] fgarr = new String[8];
			fgarr = complexation.getGuest().getMolfgb().getFg();

			int c_fg = 0, b_fg = 0;

			for (int n = 0; n < fg.length; n++) {
				c_fg += Long.bitCount(bitwiseAnd(Long.parseLong(fgarr[n]),
						Long.parseLong(fg[n])));
				b_fg += Long.bitCount(Long.parseLong(fgarr[n]));
			}

			c_sum_fg += c_fg;
			b_sum_fg += b_fg;
			double tanimoto_s = 0;
			if (a_sum + b_sum - c_sum > 0) {
				tanimoto_s = (double) c_sum / (a_sum + b_sum - c_sum);
			}
			double tanimoto_f = 0;
			if (a_sum_fg + b_sum_fg - c_sum_fg > 0) {
				tanimoto_f = (double) c_sum_fg
						/ (a_sum_fg + b_sum_fg - c_sum_fg);

			}
			double tanimoto = ssim_wt * (double) tanimoto_s + fsim_wt
					* (double) tanimoto_f;
			// double molstat_coefficient = (double) (max_diffsum - diffsum)
			// / (double) max_diffsum;
			// tanimoto = tanimoto * molstat_coefficient;
			System.out.println("Tanimoto: " + tanimoto);
			if (tanimoto > Variables.MIN_SIMILARIRY) { // Min Similarity =

				String tanimoto4 = String.format("%.1f", tanimoto * 100);
				hash.put(guestName, Double.parseDouble(tanimoto4));
			}

		}
		return hash;
	}

	public int bitwiseAnd(int a, int b) {
		int bitwise = a & b;
		return bitwise;
	}

	public long bitwiseAnd(long a, long b) {
		long bitwise = a & b;
		return bitwise;
	}

	public long bitwiseAnd(int a, long b) {
		long bitwise = a & b;
		return bitwise;
	}

	public int count1bits(long num) {
		long testnum = 0;
		int count = 0;
		for (int m = 0; m < 32; m++) {
			testnum = num >> m;
			if (testnum % 2 == 1) {
				count++;
			}

		}
		return count;
	}

	public Map<String, Double> hashSequence(Map<String, Double> hash) {
		TreeMap<String, Double> sortedMap = SortByValue(hash);
		System.out.println(sortedMap);
		return sortedMap;
	}

	public TreeMap<String, Double> SortByValue(Map<String, Double> map) {
		ValueComparator vc = new ValueComparator(map);
		TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(vc);
		sortedMap.putAll(map);
		return sortedMap;

	}

}

class ValueComparator implements Comparator<String> {

	Map<String, Double> map;

	public ValueComparator(Map<String, Double> base) {
		this.map = base;
	}

	public int compare(String a, String b) {
		if (map.get(a) >= map.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}
