package model;

public class Guest {
	public String guest;
	public String molId;
	public String structure;
	public String molcfp;
	public String molfgb;
	public String molstat;
	public int diffsum;
	
	public String canonicalized;
	public String complexity;
	public String hbondAcceptor;
	public String hbondDonor;
	public String rotatableBond;
	public String subskeys;
	public String iupacOpeneyeName;
	public String iupacCasName;
	public String iupacName;
	public String systematicName;
	public String iupacTraditionalName;
	public String iupacInchi;
	public String iupacInchiKey;
	public String xlogp3_AA;
	public String exactMass;
	public String molecularFormular;
	public String molecularWeight;
	public String openeyeCanSmiles;
	public String openeyeIsoSmiles;
	public String tpas;
	public String monoisotopicWeight;
	public String totalCharge;
	public String heavyAtomCount;
	public String atomDefStereoCount;
	public String atomUdefStereoCount;
	public String bondDefStereoCount;
	public String bondUdefStereoCount;
	public String isotopicAtomCount;
	public String componentCount;
	public String tautoCount;
	public String coordinateType;
	public String bondAnnotations;
	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Guest(String guest, String molId, String structure,
			String molcfp, String molfgb, String molstat, int diffsum,
			String canonicalized, String complexity, String hbondAcceptor,
			String hbondDonor, String rotatableBond, String subskeys,
			String iupacOpeneyeName, String iupacCasName, String iupacName,
			String systematicName, String iupacTraditionalName,
			String iupacInchi, String iupacInchiKey, String xlogp3_AA,
			String exactMass, String molecularFormular, String molecularWeight,
			String openeyeCanSmiles, String openeyeIsoSmiles, String tpas,
			String monoisotopicWeight, String totalCharge,
			String heavyAtomCount, String atomDefStereoCount,
			String atomUdefStereoCount, String bondDefStereoCount,
			String bondUdefStereoCount, String isotopicAtomCount,
			String componentCount, String tautoCount, String coordinateType,
			String bondAnnotations) {
		super();
		this.guest = guest;
		this.molId = molId;
		this.structure = structure;
		this.molcfp = molcfp;
		this.molfgb = molfgb;
		this.molstat = molstat;
		this.diffsum = diffsum;
		this.canonicalized = canonicalized;
		this.complexity = complexity;
		this.hbondAcceptor = hbondAcceptor;
		this.hbondDonor = hbondDonor;
		this.rotatableBond = rotatableBond;
		this.subskeys = subskeys;
		this.iupacOpeneyeName = iupacOpeneyeName;
		this.iupacCasName = iupacCasName;
		this.iupacName = iupacName;
		this.systematicName = systematicName;
		this.iupacTraditionalName = iupacTraditionalName;
		this.iupacInchi = iupacInchi;
		this.iupacInchiKey = iupacInchiKey;
		this.xlogp3_AA = xlogp3_AA;
		this.exactMass = exactMass;
		this.molecularFormular = molecularFormular;
		this.molecularWeight = molecularWeight;
		this.openeyeCanSmiles = openeyeCanSmiles;
		this.openeyeIsoSmiles = openeyeIsoSmiles;
		this.tpas = tpas;
		this.monoisotopicWeight = monoisotopicWeight;
		this.totalCharge = totalCharge;
		this.heavyAtomCount = heavyAtomCount;
		this.atomDefStereoCount = atomDefStereoCount;
		this.atomUdefStereoCount = atomUdefStereoCount;
		this.bondDefStereoCount = bondDefStereoCount;
		this.bondUdefStereoCount = bondUdefStereoCount;
		this.isotopicAtomCount = isotopicAtomCount;
		this.componentCount = componentCount;
		this.tautoCount = tautoCount;
		this.coordinateType = coordinateType;
		this.bondAnnotations = bondAnnotations;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getMolId() {
		return molId;
	}
	public void setMolId(String molId) {
		this.molId = molId;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getMolcfp() {
		return molcfp;
	}
	public void setMolcfp(String molcfp) {
		this.molcfp = molcfp;
	}
	public String getMolfgb() {
		return molfgb;
	}
	public void setMolfgb(String molfgb) {
		this.molfgb = molfgb;
	}
	public String getMolstat() {
		return molstat;
	}
	public void setMolstat(String molstat) {
		this.molstat = molstat;
	}
	public int getDiffsum() {
		return diffsum;
	}
	public void setDiffsum(int diffsum) {
		this.diffsum = diffsum;
	}
	public String getCanonicalized() {
		return canonicalized;
	}
	public void setCanonicalized(String canonicalized) {
		this.canonicalized = canonicalized;
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	public String getHbondAcceptor() {
		return hbondAcceptor;
	}
	public void setHbondAcceptor(String hbondAcceptor) {
		this.hbondAcceptor = hbondAcceptor;
	}
	public String getHbondDonor() {
		return hbondDonor;
	}
	public void setHbondDonor(String hbondDonor) {
		this.hbondDonor = hbondDonor;
	}
	public String getRotatableBond() {
		return rotatableBond;
	}
	public void setRotatableBond(String rotatableBond) {
		this.rotatableBond = rotatableBond;
	}
	public String getSubskeys() {
		return subskeys;
	}
	public void setSubskeys(String subskeys) {
		this.subskeys = subskeys;
	}
	public String getIupacOpeneyeName() {
		return iupacOpeneyeName;
	}
	public void setIupacOpeneyeName(String iupacOpeneyeName) {
		this.iupacOpeneyeName = iupacOpeneyeName;
	}
	public String getIupacCasName() {
		return iupacCasName;
	}
	public void setIupacCasName(String iupacCasName) {
		this.iupacCasName = iupacCasName;
	}
	public String getIupacName() {
		return iupacName;
	}
	public void setIupacName(String iupacName) {
		this.iupacName = iupacName;
	}
	public String getSystematicName() {
		return systematicName;
	}
	public void setSystematicName(String systematicName) {
		this.systematicName = systematicName;
	}
	public String getIupacTraditionalName() {
		return iupacTraditionalName;
	}
	public void setIupacTraditionalName(String iupacTraditionalName) {
		this.iupacTraditionalName = iupacTraditionalName;
	}
	public String getIupacInchi() {
		return iupacInchi;
	}
	public void setIupacInchi(String iupacInchi) {
		this.iupacInchi = iupacInchi;
	}
	public String getIupacInchiKey() {
		return iupacInchiKey;
	}
	public void setIupacInchiKey(String iupacInchiKey) {
		this.iupacInchiKey = iupacInchiKey;
	}
	public String getXlogp3_AA() {
		return xlogp3_AA;
	}
	public void setXlogp3_AA(String xlogp3_AA) {
		this.xlogp3_AA = xlogp3_AA;
	}
	public String getExactMass() {
		return exactMass;
	}
	public void setExactMass(String exactMass) {
		this.exactMass = exactMass;
	}
	public String getMolecularFormular() {
		return molecularFormular;
	}
	public void setMolecularFormular(String molecularFormular) {
		this.molecularFormular = molecularFormular;
	}
	public String getMolecularWeight() {
		return molecularWeight;
	}
	public void setMolecularWeight(String molecularWeight) {
		this.molecularWeight = molecularWeight;
	}
	public String getOpeneyeCanSmiles() {
		return openeyeCanSmiles;
	}
	public void setOpeneyeCanSmiles(String openeyeCanSmiles) {
		this.openeyeCanSmiles = openeyeCanSmiles;
	}
	public String getOpeneyeIsoSmiles() {
		return openeyeIsoSmiles;
	}
	public void setOpeneyeIsoSmiles(String openeyeIsoSmiles) {
		this.openeyeIsoSmiles = openeyeIsoSmiles;
	}
	public String getTpas() {
		return tpas;
	}
	public void setTpas(String tpas) {
		this.tpas = tpas;
	}
	public String getMonoisotopicWeight() {
		return monoisotopicWeight;
	}
	public void setMonoisotopicWeight(String monoisotopicWeight) {
		this.monoisotopicWeight = monoisotopicWeight;
	}
	public String getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}
	public String getHeavyAtomCount() {
		return heavyAtomCount;
	}
	public void setHeavyAtomCount(String heavyAtomCount) {
		this.heavyAtomCount = heavyAtomCount;
	}
	public String getAtomDefStereoCount() {
		return atomDefStereoCount;
	}
	public void setAtomDefStereoCount(String atomDefStereoCount) {
		this.atomDefStereoCount = atomDefStereoCount;
	}
	public String getAtomUdefStereoCount() {
		return atomUdefStereoCount;
	}
	public void setAtomUdefStereoCount(String atomUdefStereoCount) {
		this.atomUdefStereoCount = atomUdefStereoCount;
	}
	public String getBondDefStereoCount() {
		return bondDefStereoCount;
	}
	public void setBondDefStereoCount(String bondDefStereoCount) {
		this.bondDefStereoCount = bondDefStereoCount;
	}
	public String getBondUdefStereoCount() {
		return bondUdefStereoCount;
	}
	public void setBondUdefStereoCount(String bondUdefStereoCount) {
		this.bondUdefStereoCount = bondUdefStereoCount;
	}
	public String getIsotopicAtomCount() {
		return isotopicAtomCount;
	}
	public void setIsotopicAtomCount(String isotopicAtomCount) {
		this.isotopicAtomCount = isotopicAtomCount;
	}
	public String getComponentCount() {
		return componentCount;
	}
	public void setComponentCount(String componentCount) {
		this.componentCount = componentCount;
	}
	public String getTautoCount() {
		return tautoCount;
	}
	public void setTautoCount(String tautoCount) {
		this.tautoCount = tautoCount;
	}
	public String getCoordinateType() {
		return coordinateType;
	}
	public void setCoordinateType(String coordinateType) {
		this.coordinateType = coordinateType;
	}
	public String getBondAnnotations() {
		return bondAnnotations;
	}
	public void setBondAnnotations(String bondAnnotations) {
		this.bondAnnotations = bondAnnotations;
	}
	
	
	
	
	
}
