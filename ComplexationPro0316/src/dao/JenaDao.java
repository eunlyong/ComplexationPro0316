package dao;

import java.io.InputStream;
import java.util.ArrayList;

import model.*;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.*;
import com.hp.hpl.jena.util.FileManager;

public class JenaDao {
	public void insertDataIntoJena(String queryString) {
		System.out.println("Insert QUERY= " + queryString);

		Dataset dataset = TDBFactory.createDataset(Variables.directory);
		GraphStore graphStore = GraphStoreFactory.create(dataset);
		UpdateRequest request = UpdateFactory.create(queryString);
		UpdateProcessor qe = UpdateExecutionFactory.create(request, graphStore);
		qe.execute();
	}

	public void searchDataFromJena(ArrayList<Complexation> complexations,
			String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Complexation complexation = new Complexation();
			complexation.setComplexation(row.getResource("complexation")
					.toString());
			complexations.add(complexation);
		}
		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		qe.close();

	}
	public ArrayList<Complexation> searchAllComplexationFromJena2(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Complexation> complexations = new ArrayList<Complexation>();
		// model.enterCriticalSection(Lock.Write);
		
		System.out.println("searchAllComplexation: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Complexation complexation = new Complexation();
			Host host = new Host();
			Guest guest = new Guest();
			complexation.setComplexation(row.getResource("complexation")
					.toString());
			complexations.add(complexation);
			if(null != row
					.getResource("host")){
				host.setHost(row
						.getResource("host")
						.toString()
						.substring(
								row.getResource("host").toString().indexOf("#") + 1)
						.toString());
			}
			if(null != row
					.getResource("guest")){
				guest.setGuest(row
						.getResource("guest")
						.toString()
						.substring(
								row.getResource("guest").toString().indexOf("#") + 1)
						.toString());
			}
			
			complexation.setHost(host);
			complexation.setGuest(guest);
			if (row.getLiteral("bindingconstant") != null) {

				complexation.setBindingConstant(row.getLiteral(
						"bindingconstant").getDouble());

			} else {

			}

			if (row.getLiteral("setstoichiometry") != null) {

				complexation.setStoichiometry(row
						.getLiteral("setstoichiometry").getString());

			} else {
				complexation.setStoichiometry("");
			}
			if (row.getLiteral("nmrspec") != null) {

				complexation.setNmrSpectrum(row.getLiteral("nmrspec").getString());

			} else {
				complexation.setStoichiometry("");
			}
		}

		qe.close();

		return complexations;
	}
	public ArrayList<Complexation> searchAllComplexationFromJena() {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Complexation> complexations = new ArrayList<Complexation>();
		// model.enterCriticalSection(Lock.Write);
		String querySubPart1 = " select distinct ?complexation ?host ?guest ?bindingconstant ?setstoichiometry "
				+ " { ?complexation complexation:has_host ?host ."
				+ "?complexation complexation:has_guest ?guest.";

		String querySubPart2 = " OPTIONAL {?complexation  complexation:binding_constant ?bindingconstant. } ";
		String querySubPart2_1 = " OPTIONAL {?complexation  complexation:stoichiometry_binding ?setstoichiometry. } ";
		String querySubPart3 = " OPTIONAL { ?complexation   complexation:used_condition _:condition . }"
				+ " OPTIONAL { _:condition1 complexation:used_temperature ?temperature .} "
				+ " OPTIONAL { _:condition2 complexation:used_solvent ?solvent. } "
				+ " OPTIONAL {_:condition3 complexation:used_ph ?ph. } "
				+ " OPTIONAL {_:condition4 complexation:used_time ?time . } "
				+ " OPTIONAL {?complexation complexation:has_evidence _:nmr. } "
				+ " OPTIONAL {_:nmr1 complexation:nmr_spectrum ?nmrspec.} }";
		String sparql = Variables.prefix + querySubPart1 + querySubPart2
				+ querySubPart2_1 + querySubPart3;
		System.out.println("searchAllComplexation: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Complexation complexation = new Complexation();
			Host host = new Host();
			Guest guest = new Guest();
			
			
			complexation.setComplexation(row.getResource("complexation")
					.toString());
			complexations.add(complexation);

			host.setHost(row
					.getResource("host")
					.toString()
					.substring(
							row.getResource("host").toString().indexOf("#") + 1)
					.toString());
			guest.setGuest(row
					.getResource("guest")
					.toString()
					.substring(
							row.getResource("guest").toString().indexOf("#") + 1)
					.toString());
			complexation.setHost(host);
			complexation.setGuest(guest);
			if (row.getLiteral("bindingconstant") != null) {

				complexation.setBindingConstant(row.getLiteral(
						"bindingconstant").getDouble());

			} else {

			}

			if (row.getLiteral("setstoichiometry") != null) {

				complexation.setStoichiometry(row
						.getLiteral("setstoichiometry").getString());

			} else {
				complexation.setStoichiometry("");
			}
			if (row.getLiteral("nmrspec") != null) {

				complexation.setNmrSpectrum(row.getLiteral("nmrspec").getString());

			} else {
				complexation.setStoichiometry("");
			}
		}

		qe.close();

		return complexations;
	}

	
	public ArrayList<Host> searchAllHostFromJena(String hostName,String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Host> hosts = new ArrayList<Host>();
		// model.enterCriticalSection(Lock.Write);
		
		System.out.println("searchAllHost: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Host host = new Host();
			if(null != row
					.getResource("host")){
				host.setHost(row
						.getResource("host")
						.toString()
						.substring(
								row.getResource("host").toString().indexOf("#") + 1)
						.toString());
				
			}else if(!hostName.equals("")){
				host.setHost(hostName.split(":")[1]);
			}
			
			if (row.getLiteral("molecular_formular") != null) {

				host.setMolecularFormular(row.getLiteral("molecular_formular")
						.getString());

			} else {
				host.setMolecularFormular("");
			}

			if (row.getLiteral("boiling_point") != null) {

				host.setBoilingPoint(row.getLiteral("boiling_point")
						.getDouble());

			} else {

			}
			if (row.getLiteral("melting_point") != null) {

				host.setMeltingPoint(row.getLiteral("melting_point")
						.getDouble());

			} else {

			}

			if (row.getLiteral("solubility") != null) {

				host.setSolubility(row.getLiteral("solubility").getDouble());

			} else {

			}
			if (row.getLiteral("toxicity") != null) {

				host.setToxicity(row.getLiteral("toxicity").getInt());

			}
			hosts.add(host);

		}

		qe.close();

		return hosts;
	}
	public ArrayList<Guest> searchAllGuestFromJena(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Guest> guests = new ArrayList<Guest>();
		
		System.out.println("searchAllGuest: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Guest guest = new Guest();
			if (row.getResource("guest") != null) {
				guest.setGuest(row
						.getResource("guest")
						.toString()
						.substring(
								row.getResource("guest").toString()
										.indexOf("#") + 1).toString());
			} else {
				guest.setGuest("");
			}
			if (row.getLiteral("mol_id") != null) {
				guest.setMolId(row.getLiteral("mol_id").getString());
			} else {
				guest.setMolId("");
			}
			
			 if(null != row.getLiteral("smiles")){
				 guest.setOpeneyeCanSmiles(row.getLiteral("smiles").getString());
			 }else{
				 guest.setOpeneyeCanSmiles("");
			 }
			 if(null != row.getLiteral("formular")){
				 guest.setMolecularFormular(row.getLiteral("formular").getString());
			 }else{
				 guest.setMolecularFormular("");
			 }
			 if(null != row.getLiteral("weight")){
			 guest.setMolecularWeight(row.getLiteral("weight").getString());
			 }else{
			 guest.setMolecularWeight("");
			 }
			 if(null != row.getLiteral("iupac") ){
				 guest.setIupacName(row.getLiteral("iupac").getString());
			 }else{
				 guest.setIupacName("");
			 }

			guests.add(guest);

		}

		qe.close();

		return guests;
	}
	public ArrayList<Monosaccharide> searchAllMonosaccharideFromJena(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Monosaccharide> monos = new ArrayList<Monosaccharide>();
		// model.enterCriticalSection(Lock.Write);
		
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Monosaccharide mono = new Monosaccharide();
			if (row.getLiteral("alias") != null) {
				mono.setAliasName(row.getLiteral("alias").getString());
			} else {
				mono.setAliasName("");
			}
			if (row.getLiteral("notation") != null) {
				mono.setNotationScheme(row.getLiteral("notation").getString());
			} else {
				mono.setNotationScheme("");
			}
			if (row.getResource("anomer") != null) {
				mono.setAnomer(row.getResource("anomer").toString().substring(
						row.getResource("anomer").toString()
						.indexOf("#") + 1).toString());
			} else {
				mono.setAnomer("");
			}
			if (row.getResource("absolute") != null) {
				mono.setAbsoluteConfiguration(row.getResource("absolute").toString());
			} else {
				mono.setAbsoluteConfiguration("");
			}
			
			
			monos.add(mono);

		}

		qe.close();

		return monos;
	}
	public ArrayList<Monosaccharide> searchAllMonosaccharideFromJena() {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Monosaccharide> monos = new ArrayList<Monosaccharide>();
		// model.enterCriticalSection(Lock.Write);
		String querySubPart1 = " select * "
				+ " { OPTIONAL {?basetype rdf:type glycan:basetype. } OPTIONAL {?mono glycan:has_alias _:alias.}"
				+ " OPTIONAL { _:alias1 glycan:has_alias_name ?alias.} "
				+ "  OPTIONAL {_:alias2 glycan:has_monosaccharide_notation_scheme ?notation.}"
				+ " OPTIONAL{?basetype  glycan:has_anomer ?anomer. }"
				+ " OPTIONAL {?mono  glycan:has_basetype ?basetype. ?basetype glycan:has_configuration _:conf.  "
				+ " _:conf1 glycan:has_absolute_configuration ?absolute. "
				+ " _:conf2 glycan:has_relative_configuration ?relative. }"
				+ "OPTIONAL {?mono  glycan:has_substitution ?substituent. }} ";
				

	
		String sparql = Variables.prefix + querySubPart1;
		System.out.println("searchAllMono: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
	
		while (results.hasNext()) {
			
			QuerySolution row = results.next();
			Monosaccharide mono = new Monosaccharide();
			if (null != row.getResource("mono")) {
				mono.setMonosaccharide(row.getResource("mono").toString().substring(
						row.getResource("mono").toString()
						.indexOf("#") + 1).toString());
			} else {
				mono.setMonosaccharide("");
			}
			if (null != row.getLiteral("alias")) {
				mono.setAliasName(row.getLiteral("alias").getString());
			} else {
				mono.setAliasName("");
			}
			if (null != row.getLiteral("notation")) {
				mono.setNotationScheme(row.getLiteral("notation").getString());
			} else {
				mono.setNotationScheme("");
			}
			if (null != row.getResource("anomer")) {
				mono.setAnomer(row.getResource("anomer").toString().substring(
						row.getResource("anomer").toString()
						.indexOf("#") + 1).toString());
			} else {
				mono.setAnomer("");
			}
			if (null != row.getResource("absolute")) {
				mono.setAbsoluteConfiguration(row.getResource("absolute").toString());
			} else {
				mono.setAbsoluteConfiguration("");
			}
			if (null != row.getResource("basetype")) {
				Basetype base = new Basetype();
				base.setBasetype(row.getResource("basetype").toString());
				mono.setBasetype(base);
			} else {
				Basetype base = new Basetype();
				base.setBasetype("");
				mono.setBasetype(base);
			}
			
			
			monos.add(mono);

		}

		qe.close();

		return monos;
	}
	public ArrayList<Basetype> searchAllBasetypeFromJena() {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		ArrayList<Basetype> basetypes = new ArrayList<Basetype>();
		// model.enterCriticalSection(Lock.Write);
		String querySubPart1 = " select * "
				+ " { OPTIONAL {?basetype rdf:type glycan:basetype. } OPTIONAL {?mono glycan:has_alias _:alias.}"
				+ " OPTIONAL { _:alias1 glycan:has_alias_name ?alias.} "
				+ "  OPTIONAL {_:alias2 glycan:has_monosaccharide_notation_scheme ?notation.}"
				+ " OPTIONAL{?basetype  glycan:has_anomer ?anomer. }"
				+ " OPTIONAL {?mono  glycan:has_basetype ?basetype. ?basetype glycan:has_configuration _:conf.  "
				+ " _:conf1 glycan:has_absolute_configuration ?absolute. "
				+ " _:conf2 glycan:has_relative_configuration ?relative. }"
				+ "OPTIONAL {?mono  glycan:has_substitution ?substituent. }} ";
				

		
		String sparql = Variables.prefix + querySubPart1;
		System.out.println("searchAllMono: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {

			QuerySolution row = results.next();
			Basetype basetype = new Basetype();
			
			if (row.getResource("absolute") != null) {
				basetype.setAbsoluteConfiguration(row.getResource("absolute").toString());
			} else {
				basetype.setAbsoluteConfiguration("");
			}
			
			if (null != row.getResource("basetype")) {
				
				basetype.setBasetype(row.getResource("basetype").toString());
			} else {
				basetype.setBasetype("");
			}
			
			basetypes.add(basetype);

		}

		qe.close();

		return basetypes;
	}

	public Guest searchGuestInfoFromJena(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		System.out.println("searchGuestInfo: " + sparql);
		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		Guest guest = new Guest();
		while (results.hasNext()) {

			QuerySolution row = results.next();
			
			if (row.getLiteral("mol_id") != null) {
				guest.setMolId(row.getLiteral("mol_id").getString());
			} else {
				guest.setMolId("");
			}
			

			if (null != row.getLiteral("has_canonicalized")) {
				guest.setCanonicalized(row.getLiteral("has_canonicalized").getString());
			} else {
			}
			if (null != row.getLiteral("has_complexity")) {
				guest.setComplexity(row.getLiteral("has_complexity").getString());
			} else {
			}
			if (null != row.getLiteral("has_h_bond_acceptor")) {
				guest.setHbondAcceptor(row.getLiteral("has_h_bond_acceptor").getString());
			} else {
			}
			if (null != row.getLiteral("has_h_bond_donor")) {
				guest.setHbondDonor(row.getLiteral("has_h_bond_donor").getString());
			} else {
			}

			if (null != row.getLiteral("has_rotatable_bond")) {
				guest.setRotatableBond(row.getLiteral("has_rotatable_bond").getString());
			} else {
			}
			if (null != row.getLiteral("has_subskeys")) {
				guest.setSubskeys(row.getLiteral("has_subskeys").getString());
			} else {
			}
			if (null != row.getLiteral("has_iupac_openeye_name")) {
				guest.setIupacOpeneyeName(row.getLiteral("has_iupac_openeye_name").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_iupac_cas_name")) {
				guest.setIupacCasName(row.getLiteral("has_iupac_cas_name").getString());
			} else {
			}
			if (null != row.getLiteral("has_iupac_name")) {
				guest.setIupacName(row.getLiteral("has_iupac_name").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_systematic_name")) {
				guest.setSystematicName(row.getLiteral("has_systematic_name").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_iupac_traditional_name")) {
				guest.setIupacTraditionalName(row.getLiteral("has_iupac_traditional_name")
					.getString());
				
			} else {
			}
			if (null != row.getLiteral("has_iupac_inchi")) {
				guest.setIupacInchi(row.getLiteral("has_iupac_inchi").getString());
			
			} else {
			}
			if (null != row.getLiteral("has_iupac_inchikey")) {
				guest.setIupacInchiKey(row.getLiteral("has_iupac_inchikey").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_xlogp3_aa")) {
				guest.setXlogp3_AA(row.getLiteral("has_xlogp3_aa").getString());
				
			} else {
			}

			if (null != row.getLiteral("exact_mass")) {
				guest.setExactMass(row.getLiteral("exact_mass").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_molecular_formular")) {
				guest.setMolecularFormular(row.getLiteral("has_molecular_formular").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_molecular_weight")) {
				guest.setMolecularWeight(row.getLiteral("has_molecular_weight").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_openeye_can_smiles")) {
				guest.setOpeneyeCanSmiles(row.getLiteral("has_openeye_can_smiles").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_openeye_iso_smiles")) {
				guest.setOpeneyeIsoSmiles(row.getLiteral("has_openeye_iso_smiles").getString());
			} else {
			}
			if (null != row.getLiteral("has_tpas")) {
				guest.setTpas( row.getLiteral("has_tpas").getString());
			} else {
			}
			if (null != row.getLiteral("has_monoisotopic_weight")) {
				guest.setMonoisotopicWeight(row.getLiteral("has_monoisotopic_weight").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_total_charge")) {
				guest.setTotalCharge(row.getLiteral("has_total_charge").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_heavy_atom_count")) {
				guest.setHeavyAtomCount(row.getLiteral("has_heavy_atom_count").getString());
			
			} else {
			}

			if (null != row.getLiteral("has_atom_def_stereo_count")) {
				guest.setAtomDefStereoCount(row.getLiteral("has_atom_def_stereo_count").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_atom_udef_stereo_count")) {
				guest.setAtomUdefStereoCount(row.getLiteral("has_atom_udef_stereo_count")
					.getString());
				
			} else {
			}
			if (null != row.getLiteral("has_bond_def_stereo_count")) {
				guest.setBondDefStereoCount(row.getLiteral("has_bond_def_stereo_count").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_bond_udef_stereo_count")) {
				guest.setBondUdefStereoCount(row.getLiteral("has_bond_udef_stereo_count")
					.getString());
				
			} else {
			}
			if (null != row.getLiteral("has_isotopic_atom_count")) {
				guest.setIsotopicAtomCount(row.getLiteral("has_isotopic_atom_count").getString());
			
			} else {
			}
			if (null != row.getLiteral("has_component_count")) {
				guest.setComponentCount(row.getLiteral("has_component_count").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_tauto_count")) {
				guest.setTautoCount(row.getLiteral("has_tauto_count").getString());
				
			} else {
			}

			if (null != row.getLiteral("has_coordinate_type")) {
				guest.setCoordinateType(row.getLiteral("has_coordinate_type").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_bond_annotations")) {
				guest.setBondAnnotations(row.getLiteral("has_bond_annotations").getString());
				
			} else {
			}

			

		}

		qe.close();

		return guest;
	}
	public Host searchHostInfoFromJena(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		Host host = new Host();
		while (results.hasNext()) {

			QuerySolution row = results.next();
			
			if (row.getLiteral("molecular_formular") != null) {
				host.setMolecularFormular(row.getLiteral("molecular_formular").getString());
			} else {
				host.setMolecularFormular("");
				
			}
			if (row.getLiteral("boiling_point") != null) {
				host.setBoilingPoint(row.getLiteral("boiling_point").getInt());
			} else {
				
			}
			if (row.getLiteral("melting_point") != null) {
				host.setMeltingPoint(row.getLiteral("melting_point").getInt());
			} else {
				
			}
			if (row.getLiteral("solubility") != null) {
				host.setSolubility(row.getLiteral("solubility").getInt());
			} else {
				
			}
			if (row.getLiteral("toxicity") != null) {
				host.setToxicity(row.getLiteral("toxicity").getInt());
			} else {
				
			}
			Monosaccharide mono = new Monosaccharide();
			if (row.getResource("mono") != null) {
				
				mono.setMonosaccharide(row.getResource("mono").toString());
				host.setMono(mono);
			} else {
				mono.setMonosaccharide("");
				host.setMono(mono);
			}
			if (row.getLiteral("card") != null) {
				host.setNumberOfMono(row.getLiteral("card").getInt());
			} else {
				
			}
			
			
		}
		qe.close();

		return host;
	}
	public Monosaccharide searchMonosaccharideInfoFromJena(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		Monosaccharide mono = new Monosaccharide();
		while (results.hasNext()) {

			QuerySolution row = results.next();
			
			if (row.getLiteral("alias")!= null) {
				mono.setAliasName(row.getLiteral("alias").getString());
			} else {
				mono.setAliasName("");
			}
			if (row.getLiteral("notation") != null) {
				mono.setNotationScheme(row.getLiteral("notation").getString() );
			} else {
				mono.setNotationScheme("");
			}
			if (row.getResource("anomer") != null) {
				mono.setAnomer(row.getResource("anomer").toString().substring(
						row.getResource("anomer").toString().indexOf(
								"#") + 1));
			} else {
				mono.setAnomer("");
			}
			
			if (row.getLiteral("start") != null) {
				mono.setRingStart(row.getLiteral("start").getInt());
			} else {
				
			}
			if (row.getLiteral("end") != null) {
				mono.setRingEnd(row.getLiteral("end").getInt());
			} else {
				
			}
			if (row.getLiteral("size") != null) {
				mono.setSize(row.getLiteral("size").getInt());
			} else {
				
			}
			if (row.getResource("absolute") != null) {
				mono.setAbsoluteConfiguration(row.getResource("absolute").toString().substring(
						row.getResource("absolute").toString().indexOf(
								"#") + 1));
			} else {
				mono.setAbsoluteConfiguration("");
				
			}
			if (row.getResource("relative") != null) {
				mono.setRelativeConfiguration(row.getResource("relative").toString().substring(
						row.getResource("relative").toString().indexOf(
								"#") + 1));
			} else {
				mono.setRelativeConfiguration("");
				
			}
			if (row.getResource("substituent") != null) {
				mono.setSubstituent(row.getResource("substituent").toString().substring(
						row.getResource("substituent").toString().indexOf(
								"#") + 1));
			} else {
				mono.setSubstituent("");
			}
			if (row.getResource("subtype") != null) {
				mono.setSubstituentType(row.getResource("subtype").toString().substring(
						row.getResource("subtype").toString().indexOf(
								"#") + 1));
			} else {
				mono.setSubstituentType("");
			}
			if (row.getResource("linktype") != null) {
				mono.setLinkageType(row.getResource("linktype").toString().substring(
						row.getResource("linktype").toString().indexOf(
								"#") + 1));
			} else {
				mono.setLinkageType("");
			}
			
			
		}
		qe.close();

		return mono;
	}

	public void searchDataFromJena(Object obj, ArrayList<Guest> list,
			String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		System.out.println("MOlId & DiffSum sparql: " + sparql);
		
		Molstat molstat = new Molstat();
		Molcfp molcfp = new Molcfp();
		while (results.hasNext()) {

			QuerySolution row = results.next();
			Guest guest = new Guest();
			guest.setMolId(row.getLiteral("mol_id").getString());
			guest.setDiffsum(row.getLiteral("diffsum").getInt());
			// complexations.add(complexation);
			list.add(guest);
		}

		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		qe.close();

	}

	public void searchDataFromJena(Complexation complexation, String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		Query query = QueryFactory.create(sparql);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		Host host = new Host();
		Guest guest = new Guest();
		Molstat molstat = new Molstat();
		Molcfp molcfp = new Molcfp();
		if (results.hasNext()) // if(results1.hasNext())
		{
			QuerySolution row = results.next();

			// complexation.setComplexation(row.getResource("complexation").toString());
			host.setHost(row
					.getResource("host")
					.toString()
					.substring(
							row.getResource("host").toString().indexOf("#") + 1)
					.toString());
			guest.setGuest(row
					.getResource("guest")
					.toString()
					.substring(
							row.getResource("guest").toString().indexOf("#") + 1)
					.toString());

			if (row.getLiteral("bindingconstant") != null) {

				complexation.setBindingConstant(row.getLiteral(
						"bindingconstant").getDouble());

			} else {

			}

			if (row.getLiteral("setstoichiometry") != null) {

				complexation.setStoichiometry(row
						.getLiteral("setstoichiometry").getString());

			} else {
				complexation.setStoichiometry("");
			}

			if (row.getLiteral("temperature") != null) {

				complexation.setUsedTemperature(row.getLiteral("temperature")
						.getDouble());

			} else {

			}

			if (row.getLiteral("solvent") != null) {

				complexation.setUsedSolvent(row.getLiteral("solvent")
						.getString());

			} else {
				complexation.setUsedSolvent("");
			}

			if (row.getLiteral("ph") != null) {

				complexation.setUsedPH(row.getLiteral("ph").getDouble());

			} else {

			}

			if (row.getLiteral("time") != null) {

				complexation.setUsedTime(row.getLiteral("time").getInt());

			} else {

			}

			if (row.getLiteral("hostcon") != null) {

				complexation.setHostConcentration(row.getLiteral("hostcon")
						.getDouble());

			} else {

			}
			if (row.getLiteral("guestcon") != null) {

				complexation.setGuestConcentration(row.getLiteral("guestcon")
						.getDouble());

			} else {

			}

			if (row.getLiteral("buffer") != null) {

				complexation.setBuffer(row.getLiteral("buffer").getString());

			} else {
				complexation.setBuffer("");
			}
			if (row.getLiteral("freq") != null) {

				complexation.setFrequency(row.getLiteral("freq").getDouble());

			} else {

			}

			if (row.getLiteral("temp") != null) {

				complexation.setNmrTemperature(row.getLiteral("temp").getInt());

			} else {

			}

			if (row.getLiteral("mixtime") != null) {

				//complexation.setNmrMixingTime(row.getLiteral("mixtime").getInt());

			} else {

			}

			if (row.getLiteral("usedph") != null) {

				complexation.setNmrPH(row.getLiteral("usedph").getDouble());

			} else {

			}

			// if(row.getLiteral("freq") != null){
			//
			// complexation.set=row.getLiteral("freq").getDouble()
			//
			//
			// } else{
			//
			//
			// }
			if (row.getLiteral("molecular_formular") != null) {

				host.setMolecularFormular(row.getLiteral("molecular_formular")
						.getString());

			} else {
				host.setMolecularFormular("");
			}

			if (row.getLiteral("boiling_point") != null) {

				host.setBoilingPoint(row.getLiteral("boiling_point")
						.getDouble());

			} else {

			}
			if (row.getLiteral("melting_point") != null) {

				host.setMeltingPoint(row.getLiteral("melting_point")
						.getDouble());

			} else {

			}

			if (row.getLiteral("solubility") != null) {

				host.setSolubility(row.getLiteral("solubility").getDouble());

			} else {

			}
			if (row.getLiteral("toxicity") != null) {

				host.setToxicity(row.getLiteral("toxicity").getInt());

			}

			guest.setGuest(row
					.getResource("guest")
					.toString()
					.substring(
							row.getResource("guest").toString().indexOf("#") + 1)
					.toString());

			guest.setMolId(row.getLiteral("mol_id").getString());

			// values = getGuestInfoByPubChemId(guest.getMolId());
			if (null != row.getLiteral("has_canonicalized")) {
				guest.setCanonicalized(row.getLiteral("has_canonicalized").getString());
			} else {
			}
			if (null != row.getLiteral("has_complexity")) {
				guest.setComplexity(row.getLiteral("has_complexity").getString());
			} else {
			}
			if (null != row.getLiteral("has_h_bond_acceptor")) {
				guest.setHbondAcceptor(row.getLiteral("has_h_bond_acceptor").getString());
			} else {
			}
			if (null != row.getLiteral("has_h_bond_donor")) {
				guest.setHbondDonor(row.getLiteral("has_h_bond_donor").getString());
			} else {
			}

			if (null != row.getLiteral("has_rotatable_bond")) {
				guest.setRotatableBond(row.getLiteral("has_rotatable_bond").getString());
			} else {
			}
			if (null != row.getLiteral("has_subskeys")) {
				guest.setSubskeys(row.getLiteral("has_subskeys").getString());
			} else {
			}
			if (null != row.getLiteral("has_iupac_openeye_name")) {
				guest.setIupacOpeneyeName(row.getLiteral("has_iupac_openeye_name").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_iupac_cas_name")) {
				guest.setIupacCasName(row.getLiteral("has_iupac_cas_name").getString());
			} else {
			}
			if (null != row.getLiteral("has_iupac_name")) {
				guest.setIupacName(row.getLiteral("has_iupac_name").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_systematic_name")) {
				guest.setSystematicName(row.getLiteral("has_systematic_name").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_iupac_traditional_name")) {
				guest.setIupacTraditionalName(row.getLiteral("has_iupac_traditional_name")
					.getString());
				
			} else {
			}
			if (null != row.getLiteral("has_iupac_inchi")) {
				guest.setIupacInchi(row.getLiteral("has_iupac_inchi").getString());
			
			} else {
			}
			if (null != row.getLiteral("has_iupac_inchikey")) {
				guest.setIupacInchiKey(row.getLiteral("has_iupac_inchikey").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_xlogp3_aa")) {
				guest.setXlogp3_AA(row.getLiteral("has_xlogp3_aa").getString());
				
			} else {
			}

			if (null != row.getLiteral("exact_mass")) {
				guest.setExactMass(row.getLiteral("exact_mass").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_molecular_formular")) {
				guest.setMolecularFormular(row.getLiteral("has_molecular_formular").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_molecular_weight")) {
				guest.setMolecularWeight(row.getLiteral("has_molecular_weight").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_openeye_can_smiles")) {
				guest.setOpeneyeCanSmiles(row.getLiteral("has_openeye_can_smiles").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_openeye_iso_smiles")) {
				guest.setOpeneyeIsoSmiles(row.getLiteral("has_openeye_iso_smiles").getString());
			} else {
			}
			if (null != row.getLiteral("has_tpas")) {
				guest.setTpas( row.getLiteral("has_tpas").getString());
			} else {
			}
			if (null != row.getLiteral("has_monoisotopic_weight")) {
				guest.setMonoisotopicWeight(row.getLiteral("has_monoisotopic_weight").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_total_charge")) {
				guest.setTotalCharge(row.getLiteral("has_total_charge").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_heavy_atom_count")) {
				guest.setHeavyAtomCount(row.getLiteral("has_heavy_atom_count").getString());
			
			} else {
			}

			if (null != row.getLiteral("has_atom_def_stereo_count")) {
				guest.setAtomDefStereoCount(row.getLiteral("has_atom_def_stereo_count").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_atom_udef_stereo_count")) {
				guest.setAtomUdefStereoCount(row.getLiteral("has_atom_udef_stereo_count")
					.getString());
				
			} else {
			}
			if (null != row.getLiteral("has_bond_def_stereo_count")) {
				guest.setBondDefStereoCount(row.getLiteral("has_bond_def_stereo_count").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_bond_udef_stereo_count")) {
				guest.setBondUdefStereoCount(row.getLiteral("has_bond_udef_stereo_count")
					.getString());
				
			} else {
			}
			if (null != row.getLiteral("has_isotopic_atom_count")) {
				guest.setIsotopicAtomCount(row.getLiteral("has_isotopic_atom_count").getString());
			
			} else {
			}
			if (null != row.getLiteral("has_component_count")) {
				guest.setComponentCount(row.getLiteral("has_component_count").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_tauto_count")) {
				guest.setTautoCount(row.getLiteral("has_tauto_count").getString());
				
			} else {
			}

			if (null != row.getLiteral("has_coordinate_type")) {
				guest.setCoordinateType(row.getLiteral("has_coordinate_type").getString());
				
			} else {
			}
			if (null != row.getLiteral("has_bond_annotations")) {
				guest.setBondAnnotations(row.getLiteral("has_bond_annotations").getString());
				
			} else {
			}
			complexation.setHost(host);
			complexation.setGuest(guest);

		}

		qe.close();

	}

	public Complexation searchDataFromJena(String sparql) {
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// model.enterCriticalSection(Lock.Write);

		Query query = QueryFactory.create(sparql);
		System.out.println("Mol Hfp Sparql: "  + sparql);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		Complexation complexation = new Complexation();
		Guest guest = new Guest();
		Molfgb molfgb = new Molfgb();
		Molcfp molcfp = new Molcfp();
		if (results.hasNext()) {

			QuerySolution row = results.next();
			
			String guestResource = "";
			if(null != row.getResource("guest")){
				guestResource = row.getResource("guest").toString();
				
			}
			if(null != row.getResource("complexation")){
				complexation.setComplexation(row.getResource("complexation")
						.toString());
			}
		

			
			molcfp.setDfp01(row.getLiteral("dfp01").getString());
			String[] hfp = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
					"0", "0", "0", "0", "0", "0" };

			hfp[0] = row.getLiteral("hfp01").getString();
			hfp[1] = row.getLiteral("hfp02").getString();
			hfp[2] = row.getLiteral("hfp03").getString();
			hfp[3] = row.getLiteral("hfp04").getString();
			hfp[4] = row.getLiteral("hfp05").getString();
			hfp[5] = row.getLiteral("hfp06").getString();
			hfp[6] = row.getLiteral("hfp07").getString();
			hfp[7] = row.getLiteral("hfp08").getString();
			hfp[8] = row.getLiteral("hfp09").getString();
			hfp[9] = row.getLiteral("hfp10").getString();
			hfp[10] = row.getLiteral("hfp11").getString();
			hfp[11] = row.getLiteral("hfp12").getString();
			hfp[12] = row.getLiteral("hfp13").getString();
			hfp[13] = row.getLiteral("hfp14").getString();
			hfp[14] = row.getLiteral("hfp15").getString();
			hfp[15] = row.getLiteral("hfp16").getString();

			molcfp.setHfp16(hfp);
			molcfp.setN_h1bits(row.getLiteral("n_h1bits").getString());

			String[] fg = new String[8];
			fg[0] = row.getLiteral("fg01").getString();
			fg[1] = row.getLiteral("fg02").getString();
			fg[2] = row.getLiteral("fg03").getString();
			fg[3] = row.getLiteral("fg04").getString();
			fg[4] = row.getLiteral("fg05").getString();
			fg[5] = row.getLiteral("fg06").getString();
			fg[6] = row.getLiteral("fg07").getString();
			fg[7] = row.getLiteral("fg08").getString();

			molfgb.setFg(fg);

			guest.setMolcfp(molcfp);
			guest.setMolfgb(molfgb);
			guest.setGuest(guestResource);
			
			
			complexation.setGuest(guest);

		}

		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		qe.close();
		return complexation;

	}

	public void readFileToJena(String file) {
		// create an empty model
		Dataset dataset = TDBFactory.createDataset(Variables.directory);

		Model model = dataset.getDefaultModel();
		// Model model = ModelFactory.createDefaultModel();

		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(file);
		if (in == null) {
			throw new IllegalArgumentException("File: " + file + " not found");
		}

		// read the RDF/XML file
		// model.read(in, null);
		model.read(in, "TTL");

		// write it to standard out
		model.write(System.out);
	}
}
