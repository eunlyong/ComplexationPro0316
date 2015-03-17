package dao;

public class Variables {
	public static double MIN_SIMILARIRY = 0;
	public static String directory = "D:/jena-fuseki-0.2.7/Complexation";
	public static String GuestImagePath = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\GuestImage\\";
	public static String HostImagePath = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\HostImage\\";
	public static String ComplexationImagePath = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\ComplexationImage\\";
	public static String StructureFile = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\gueststructure.mol";
	
	public static String ToolLocationPath = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\Tool\\";
	public static String UsedFilePath = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\";
	static String fpchunk = "D:\\Git\\ComplexationDB\\ComplexationPro0316\\ComplexationPro0316\\fpchunk.sdf";
	
	public static String prefix = "prefix bibo:    <http://purl.org/ontology/bibo/> "
			+ "prefix owl:     <http://www.w3.org/2002/07/owl#> "
			+ "prefix xsd:     <http://www.w3.org/2001/XMLSchema#> "
			+ "prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
			+ "prefix glycan:  <http://purl.jp/bio/12/glyco/glycan#> "
			+ "prefix dcterms:  <http://purl.org/dc/terms/> "
			+ "prefix complexation: <http://www.semanticweb.org/eunlyong/ontologies/2014/5/untitled-ontology-1#> ";
	static String molstatTable = "complexation:n_atoms,complexation:n_bonds,complexation:n_rings,complexation:n_qa,"
			+ "complexation:n_qb,complexation:n_chg,complexation:n_c1,complexation:n_c2,complexation:n_c,"
			+ "complexation:n_chb1p,complexation:n_chb2p,complexation:n_chb3p,complexation:n_chb4,complexation:n_o2,"
			+ "complexation:n_o3,complexation:n_n1,complexation:n_n2,complexation:n_n3,complexation:n_s,"
			+ "complexation:n_sete,complexation:n_f,complexation:n_cl,complexation:n_br,complexation:n_i,"
			+ "complexation:n_p,complexation:n_b,complexation:n_met,complexation:n_x,complexation:n_b1,"
			+ "complexation:n_b2,complexation:n_b3,complexation:n_bar,complexation:n_c1o,complexation:n_c2o,"
			+ "complexation:n_cn,complexation:n_xy,complexation:n_r3,complexation:n_r4,complexation:n_r5,"
			+ "complexation:n_r6,complexation:n_r7,complexation:n_r8,complexation:n_r9,complexation:n_r10,"
			+ "complexation:n_r11,complexation:n_r12,complexation:n_r13p,complexation:n_rn,complexation:n_rn1,"
			+ "complexation:n_rn2,complexation:n_rn3p,complexation:n_ro,complexation:n_ro1,complexation:n_ro2p,"
			+ "complexation:n_rs,complexation:n_rx,complexation:n_rar";
	static String molfgbTable = "complexation:fg01,complexation:fg02,complexation:fg03,complexation:fg04,complexation:fg05,"
			+ "complexation:fg06,complexation:fg07,complexation:fg08,complexation:n_1bits";
	static String molcfpTable = "complexation:dfp01,complexation:hfp01,complexation:hfp02,complexation:hfp03,complexation:hfp04,"
			+ "complexation:hfp05,complexation:hfp06,complexation:hfp07,complexation:hfp08,"
			+ "complexation:hfp09,complexation:hfp10,complexation:hfp11,complexation:hfp12,"
			+ "complexation:hfp13,complexation:hfp14,complexation:hfp15,complexation:hfp16,"
			+ "complexation:n_h1bits";
	static String guestPropertyOntology = "complexation:has_canonicalized,complexation:has_complexity,complexation:has_h_bond_acceptor,"
			+ "complexation:has_h_bond_donor,complexation:has_rotatable_bond,complexation:has_subskeys,complexation:has_iupac_openeye_name,"
			+ "complexation:has_iupac_cas_name,complexation:has_iupac_name,complexation:has_systematic_name,complexation:has_iupac_traditional_name,"
			+ "complexation:has_iupac_inchi,complexation:has_iupac_inchikey,complexation:has_xlogp3_aa,complexation:has_exact_mass,"
			+ "complexation:has_molecular_formular,complexation:has_molecular_weight,complexation:has_openeye_can_smiles,complexation:has_openeye_iso_smiles,"
			+ "complexation:has_tpas,complexation:has_monoisotopic_weight,complexation:has_total_charge,complexation:has_heavy_atom_count,complexation:has_atom_def_stereo_count,"
			+ "complexation:has_atom_udef_stereo_count,complexation:has_bond_def_stereo_count,complexation:has_bond_udef_stereo_count,"
			+ "complexation:has_isotopic_atom_count,complexation:has_component_count,complexation:has_tauto_count,"
			+ "complexation:has_coordinate_type,complexation:has_bond_annotations";
	static String pubChemProperties = "PUBCHEM_COMPOUND_CANONICALIZED,PUBCHEM_CACTVS_COMPLEXITY,PUBCHEM_CACTVS_HBOND_ACCEPTOR,PUBCHEM_CACTVS_HBOND_DONOR,PUBCHEM_CACTVS_ROTATABLE_BOND,"
			+ "PUBCHEM_CACTVS_SUBSKEYS,PUBCHEM_IUPAC_OPENEYE_NAME,PUBCHEM_IUPAC_CAS_NAME,PUBCHEM_IUPAC_NAME,PUBCHEM_IUPAC_SYSTEMATIC_NAME,PUBCHEM_IUPAC_TRADITIONAL_NAME,PUBCHEM_IUPAC_INCHI,"
			+ "PUBCHEM_IUPAC_INCHIKEY,PUBCHEM_XLOGP3,PUBCHEM_EXACT_MASS,PUBCHEM_MOLECULAR_FORMULA,PUBCHEM_MOLECULAR_WEIGHT,PUBCHEM_OPENEYE_CAN_SMILES,PUBCHEM_OPENEYE_ISO_SMILES,PUBCHEM_CACTVS_TPSA,"
			+ "PUBCHEM_MONOISOTOPIC_WEIGHT,PUBCHEM_TOTAL_CHARGE,PUBCHEM_HEAVY_ATOM_COUNT,PUBCHEM_ATOM_DEF_STEREO_COUNT,PUBCHEM_ATOM_UDEF_STEREO_COUNT,PUBCHEM_BOND_DEF_STEREO_COUNT,"
			+ "PUBCHEM_BOND_UDEF_STEREO_COUNT,PUBCHEM_ISOTOPIC_ATOM_COUNT,PUBCHEM_COMPONENT_COUNT,PUBCHEM_CACTVS_TAUTO_COUNT";
	
	

	String gethostquery = "prefix bibo:    <http://purl.org/ontology/bibo/> "
			+ "prefix owl:     <http://www.w3.org/2002/07/owl#> "
			+ "prefix xsd:     <http://www.w3.org/2001/XMLSchema#> "
			+ "prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
			+ "prefix glycan:  <http://purl.jp/bio/12/glyco/glycan#> "
			+ "prefix dcterms:  <http://purl.org/dc/terms/> "
			+ "prefix complexation: <http://www.semanticweb.org/eunlyong/ontologies/2014/5/untitled-ontology-1#> "
			+ " select ?host {?complexation complexation:has_host ?host.}";
	
	static String calcGuestMolstatDiffsum_1 = "select distinct ?mol_id  (SUM( ";
	static String calcGuestMolstatDiffsum_2 = ") as  ?diffsum) where {"
			+ " ?guest complexation:has_mol_id ?mol_id. "
			+ " ?guest complexation:has_molstat _:molstat. "
			+ " _:molstat complexation:n_atoms ?n_atoms."
			+ " _:molstat complexation:n_bonds ?n_bonds."
			+ " _:molstat complexation:n_rings ?n_rings."
			+ " _:molstat complexation:n_qa ?n_qa."
			+ " _:molstat complexation:n_qb ?n_qb."
			+ " _:molstat complexation:n_chg ?n_chg."
			+ " _:molstat complexation:n_c1 ?n_c1."
			+ " _:molstat complexation:n_c2 ?n_c2."
			+ " _:molstat complexation:n_c ?n_c."
			+ " _:molstat complexation:n_chb1p ?n_chb1p."
			+ " _:molstat complexation:n_chb2p ?n_chb2p."
			+ " _:molstat complexation:n_chb3p ?n_chb3p."
			+ " _:molstat complexation:n_chb4 ?n_chb4."
			+ " _:molstat complexation:n_o2 ?n_o2."
			+ " _:molstat complexation:n_o3 ?n_o3."
			+ " _:molstat complexation:n_n1 ?n_n1."
			+ " _:molstat complexation:n_n2 ?n_n2."
			+ " _:molstat complexation:n_n3 ?n_n3."
			+ " _:molstat complexation:n_s ?n_s."
			+ " _:molstat complexation:n_sete ?n_sete."
			+ " _:molstat complexation:n_f ?n_f."
			+ " _:molstat complexation:n_cl ?n_cl."
			+ " _:molstat complexation:n_br ?n_br."
			+ " _:molstat complexation:n_i ?n_i."
			+ " _:molstat complexation:n_p ?n_p."
			+ " _:molstat complexation:n_b ?n_b."
			+ " _:molstat complexation:n_met ?n_met."
			+ " _:molstat complexation:n_x ?n_x."
			+ " _:molstat complexation:n_b1 ?n_b1."
			+ " _:molstat complexation:n_b2 ?n_b2."
			+ " _:molstat complexation:n_b3 ?n_b3."
			+ " _:molstat complexation:n_bar ?n_bar."
			+ " _:molstat complexation:n_c1o ?n_c1o."
			+ " _:molstat complexation:n_c2o ?n_c2o."
			+ " _:molstat complexation:n_cn ?n_cn."
			+ " _:molstat complexation:n_xy ?n_xy."
			+ " _:molstat complexation:n_r3 ?n_r3."
			+ " _:molstat complexation:n_r4 ?n_r4."
			+ " _:molstat complexation:n_r5 ?n_r5."
			+ " _:molstat complexation:n_r6 ?n_r6."
			+ " _:molstat complexation:n_r7 ?n_r7."
			+ " _:molstat complexation:n_r8 ?n_r8."
			+ " _:molstat complexation:n_r9 ?n_r9."
			+ " _:molstat complexation:n_r10 ?n_r10."
			+ " _:molstat complexation:n_r11 ?n_r11."
			+ " _:molstat complexation:n_r12 ?n_r12."
			+ " _:molstat complexation:n_r13p ?n_r13p."
			+ " _:molstat complexation:n_rn ?n_rn."
			+ " _:molstat complexation:n_rn1 ?n_rn1."
			+ " _:molstat complexation:n_rn2 ?n_rn2."
			+ " _:molstat complexation:n_rn3p ?n_rn3p."
			+ " _:molstat complexation:n_ro ?n_ro."
			+ " _:molstat complexation:n_ro1 ?n_ro1."
			+ " _:molstat complexation:n_ro2p ?n_ro2p."
			+ " _:molstat complexation:n_rs ?n_rs."
			+ " _:molstat complexation:n_rx ?n_rx."
			+ " _:molstat complexation:n_rar ?n_rar."
			+ "} GROUP BY ?mol_id ORDER BY (?diffsum) LIMIT 100  ";
	static String guestMolcfp_1 = " _:molcfp complexation:dfp01 ?dfp01. "
			+ " _:molcfp complexation:hfp01 ?hfp01. "
			+ " _:molcfp complexation:hfp02 ?hfp02. "
			+ " _:molcfp complexation:hfp03 ?hfp03. "
			+ " _:molcfp complexation:hfp04 ?hfp04. "
			+ " _:molcfp complexation:hfp05 ?hfp05. "
			+ " _:molcfp complexation:hfp06 ?hfp06. "
			+ " _:molcfp complexation:hfp07 ?hfp07. "
			+ " _:molcfp complexation:hfp08 ?hfp08. "
			+ " _:molcfp complexation:hfp09 ?hfp09. "
			+ " _:molcfp complexation:hfp10 ?hfp10. "
			+ " _:molcfp complexation:hfp11 ?hfp11. "
			+ " _:molcfp complexation:hfp12 ?hfp12. "
			+ " _:molcfp complexation:hfp13 ?hfp13. "
			+ " _:molcfp complexation:hfp14 ?hfp14. "
			+ " _:molcfp complexation:hfp15 ?hfp15. "
			+ " _:molcfp complexation:hfp16 ?hfp16. "
			+ " _:molcfp complexation:n_h1bits ?n_h1bits. ";
	static String guestMolfgb_1 = "  ?guest complexation:has_molfgb _:molfgb. ";

	static String guestMolfgb_2 = " _:molfgb complexation:fg01 ?fg01. "
			+ " _:molfgb complexation:fg02 ?fg02. "
			+ " _:molfgb complexation:fg03 ?fg03. "
			+ " _:molfgb complexation:fg04 ?fg04. "
			+ " _:molfgb complexation:fg05 ?fg05. "
			+ " _:molfgb complexation:fg06 ?fg06. "
			+ " _:molfgb complexation:fg07 ?fg07. "
			+ " _:molfgb complexation:fg08 ?fg08. "
			+ " _:molfgb complexation:n_1bits ?n_1bits. } ";
	// 1~2: monosaccharide, 3~4: basetype, 5~ configuration, 6~ modification, 7~
	// alias, 8~ substituent, 9~ substituent linkage
	// # NMR not add
	String monosaccharide_insert_sparql = "glycan:has_alias,glycan:has_average_molecular_weight,glycan:has_basetype,"
			+ "glycan:has_linkage_position,glycan:has_linkage_position,glycan:has_msdb_id,glycan:has_substituent,"
			+ "glycan:has_anomer,glycan:has_configuration,glycan:has_extended_stereocode,glycan:has_first_configuration,"
			+ "glycan:has_ring_end,glycan:has_ring_start,glycan:has_ring_type,glycan:has_size,glycan:configuration,"
			+ "glycan:has_absolute_configuration,glycan:has_relative_configuration,glycan:has_core_modification,"
			+ "glycan:has_core_modification_type,glycan:has_modification_position,"
			+ "glycan:has_alias_name,"
			+ "glycan:substituent_linkage,glycan:substituent_name,glycan:is_linkable,glycan:substituent_linkage,"
			+ "glycan:has_basetype_linkage_position,glycan:has_linakge_type";
	String host_insert_sparql = "glycan:has_cardinality,glycan:has_monosaccharide";

	public static String complexation_insert_sparql = "complexation:has_host,complexation:has_guest,complexation:binding_constant,"
			+ "complexation:stoichiometry_binding,complexation:used_condition,complexation:has_evidence,complexation:has_phenomena,"
			+ "complexation:used_temperature,complexation:used_solvent,complexation:used_ph,complexation:used_time,"
			+ "complexation:host_concentration,complexation:guest_concentration,complexation:buffer,complexation:frequency,"
			+ "complexation:ph,complexation:temperature,complexation:mixing_time,complexation:spectrum,"
			+ "complexation:phenomena_solubility,complexation:phenomena_stability,complexation:phenomena_fluorescence,"
			+ "complexation:phenomena_enzyme_activity,complexation:phenomena_bioavailability";
	
}
