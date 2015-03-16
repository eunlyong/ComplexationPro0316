package model;

public class Host {
	public String host;
	public double averageMolecularWeight;
	public String molecularFormular;
	public double meltingPoint;
	public double boilingPoint;
	public double monoisotopicMolecularWeight;
	public String msdbId;
	public double solubility;
	public int toxicity;
	public int numberOfMono;
	public Monosaccharide mono;
    
	public Host() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Host(String host,  double averageMolecularWeight,
			String molecularFormular, double meltingPoint, double boilingPoint,
			double monoisotopicMolecularWeight, String msdbId,
			double solubility, int toxicity, int numberOfMono,
			Monosaccharide mono) {
		super();
		this.host = host;
		
		this.averageMolecularWeight = averageMolecularWeight;
		this.molecularFormular = molecularFormular;
		this.meltingPoint = meltingPoint;
		this.boilingPoint = boilingPoint;
		this.monoisotopicMolecularWeight = monoisotopicMolecularWeight;
		this.msdbId = msdbId;
		this.solubility = solubility;
		this.toxicity = toxicity;
		this.numberOfMono = numberOfMono;
		this.mono = mono;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	

	public double getAverageMolecularWeight() {
		return averageMolecularWeight;
	}

	public void setAverageMolecularWeight(double averageMolecularWeight) {
		this.averageMolecularWeight = averageMolecularWeight;
	}

	public String getMolecularFormular() {
		return molecularFormular;
	}

	public void setMolecularFormular(String molecularFormular) {
		this.molecularFormular = molecularFormular;
	}

	public double getMeltingPoint() {
		return meltingPoint;
	}

	public void setMeltingPoint(double meltingPoint) {
		this.meltingPoint = meltingPoint;
	}

	public double getBoilingPoint() {
		return boilingPoint;
	}

	public void setBoilingPoint(double boilingPoint) {
		this.boilingPoint = boilingPoint;
	}

	public double getMonoisotopicMolecularWeight() {
		return monoisotopicMolecularWeight;
	}

	public void setMonoisotopicMolecularWeight(double monoisotopicMolecularWeight) {
		this.monoisotopicMolecularWeight = monoisotopicMolecularWeight;
	}

	public String getMsdbId() {
		return msdbId;
	}

	public void setMsdbId(String msdbId) {
		this.msdbId = msdbId;
	}

	public double getSolubility() {
		return solubility;
	}

	public void setSolubility(double solubility) {
		this.solubility = solubility;
	}

	public int getToxicity() {
		return toxicity;
	}

	public void setToxicity(int toxicity) {
		this.toxicity = toxicity;
	}

	public int getNumberOfMono() {
		return numberOfMono;
	}

	public void setNumberOfMono(int numberOfMono) {
		this.numberOfMono = numberOfMono;
	}

	public Monosaccharide getMono() {
		return mono;
	}

	public void setMono(Monosaccharide mono) {
		this.mono = mono;
	}
	
}
