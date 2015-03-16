package model;

public class Basetype {
	
	public String basetype;
	public String basetypeId;
	public String ringStart;
	public String ringEnd;
	public String size;
	public String ringType;
	public String absoluteConfiguration;
	public String relativeConfiguration;
	public Basetype(String basetype, String basetypeId, String ringStart,
			String ringEnd, String size, String ringType,
			String absoluteConfiguration, String relativeConfiguration) {
		super();
		this.basetype = basetype;
		this.basetypeId = basetypeId;
		this.ringStart = ringStart;
		this.ringEnd = ringEnd;
		this.size = size;
		this.ringType = ringType;
		this.absoluteConfiguration = absoluteConfiguration;
		this.relativeConfiguration = relativeConfiguration;
	}
	public Basetype() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBasetype() {
		return basetype;
	}
	public void setBasetype(String basetype) {
		this.basetype = basetype;
	}
	public String getBasetypeId() {
		return basetypeId;
	}
	public void setBasetypeId(String basetypeId) {
		this.basetypeId = basetypeId;
	}
	public String getRingStart() {
		return ringStart;
	}
	public void setRingStart(String ringStart) {
		this.ringStart = ringStart;
	}
	public String getRingEnd() {
		return ringEnd;
	}
	public void setRingEnd(String ringEnd) {
		this.ringEnd = ringEnd;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRingType() {
		return ringType;
	}
	public void setRingType(String ringType) {
		this.ringType = ringType;
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
	
	
}
