package model;

public class Monosaccharide {
	public String monosaccharide;
	public int cardinality;
	public String aliasName;
	public boolean primaryOrNot;
	public String notationScheme;
	public String anomer;
	public Basetype basetype;
	public int ringStart;
	public int ringEnd;
	public int size;
	public String absoluteConfiguration;
	public String relativeConfiguration;
	public String coreModificationType;
	public int modificationPosition;
	public String substituent;
	public String substituentType;
	public String linkageType;
	public int basetypeLinkagePosition;
	public Monosaccharide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Monosaccharide(String monosaccharide, int cardinality,
			String aliasName, boolean primaryOrNot, String notationScheme,
			String anomer, Basetype basetype, int ringStart, int ringEnd,
			int size, String absoluteConfiguration,
			String relativeConfiguration, String coreModificationType,
			int modificationPosition, String substituent,
			String substituentType, String linkageType,
			int basetypeLinkagePosition) {
		super();
		this.monosaccharide = monosaccharide;
		this.cardinality = cardinality;
		this.aliasName = aliasName;
		this.primaryOrNot = primaryOrNot;
		this.notationScheme = notationScheme;
		this.anomer = anomer;
		this.basetype = basetype;
		this.ringStart = ringStart;
		this.ringEnd = ringEnd;
		this.size = size;
		this.absoluteConfiguration = absoluteConfiguration;
		this.relativeConfiguration = relativeConfiguration;
		this.coreModificationType = coreModificationType;
		this.modificationPosition = modificationPosition;
		this.substituent = substituent;
		this.substituentType = substituentType;
		this.linkageType = linkageType;
		this.basetypeLinkagePosition = basetypeLinkagePosition;
	}
	public String getMonosaccharide() {
		return monosaccharide;
	}
	public void setMonosaccharide(String monosaccharide) {
		this.monosaccharide = monosaccharide;
	}
	public int getCardinality() {
		return cardinality;
	}
	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public boolean isPrimaryOrNot() {
		return primaryOrNot;
	}
	public void setPrimaryOrNot(boolean primaryOrNot) {
		this.primaryOrNot = primaryOrNot;
	}
	public String getNotationScheme() {
		return notationScheme;
	}
	public void setNotationScheme(String notationScheme) {
		this.notationScheme = notationScheme;
	}
	public String getAnomer() {
		return anomer;
	}
	public void setAnomer(String anomer) {
		this.anomer = anomer;
	}
	public Basetype getBasetype() {
		return basetype;
	}
	public void setBasetype(Basetype basetype) {
		this.basetype = basetype;
	}
	public int getRingStart() {
		return ringStart;
	}
	public void setRingStart(int ringStart) {
		this.ringStart = ringStart;
	}
	public int getRingEnd() {
		return ringEnd;
	}
	public void setRingEnd(int ringEnd) {
		this.ringEnd = ringEnd;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getAbsoluteConfiguration() {
		return absoluteConfiguration;
	}
	public void setAbsoluteConfiguration(String absoluteConfiguration) {
		this.absoluteConfiguration = absoluteConfiguration;
	}
	public String getRelativeConfiguration() {
		return relativeConfiguration;
	}
	public void setRelativeConfiguration(String relativeConfiguration) {
		this.relativeConfiguration = relativeConfiguration;
	}
	public String getCoreModificationType() {
		return coreModificationType;
	}
	public void setCoreModificationType(String coreModificationType) {
		this.coreModificationType = coreModificationType;
	}
	public int getModificationPosition() {
		return modificationPosition;
	}
	public void setModificationPosition(int modificationPosition) {
		this.modificationPosition = modificationPosition;
	}
	public String getSubstituent() {
		return substituent;
	}
	public void setSubstituent(String substituent) {
		this.substituent = substituent;
	}
	public String getSubstituentType() {
		return substituentType;
	}
	public void setSubstituentType(String substituentType) {
		this.substituentType = substituentType;
	}
	public String getLinkageType() {
		return linkageType;
	}
	public void setLinkageType(String linkageType) {
		this.linkageType = linkageType;
	}
	public int getBasetypeLinkagePosition() {
		return basetypeLinkagePosition;
	}
	public void setBasetypeLinkagePosition(int basetypeLinkagePosition) {
		this.basetypeLinkagePosition = basetypeLinkagePosition;
	}
	
}
