package model;

public class Complexation {
	public String complexation;
	public Host host;
	public Guest guest;
	public double bindingConstant;
	public String stoichiometry;
	public String phenomena;
	public double phenomenaValue;
	public double usedTemperature;
	public String usedSolvent;
	public double usedPH;
	public int usedTime;
	public double hostConcentration;
	public double guestConcentration;
	public String buffer;
	public double frequency;
	public double nmrPH;
	public double nmrTemperature;
	public double nmrMixingTime;
	public String article;
	public String journal;
	public String nmrSpectrum;
	
	
	public Complexation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Complexation(String complexation, Host host, Guest guest,
			double bindingConstant, String stoichiometry, String phenomena,
			double phenomenaValue, double usedTemperature, String usedSolvent,
			double usedPH, int usedTime, double hostConcentration,
			double guestConcentration, String buffer, double frequency,
			double nmrPH, double nmrTemperature, double nmrMixingTime,
			String article, String journal, String nmrSpectrum) {
		super();
		this.complexation = complexation;
		this.host = host;
		this.guest = guest;
		this.bindingConstant = bindingConstant;
		this.stoichiometry = stoichiometry;
		this.phenomena = phenomena;
		this.phenomenaValue = phenomenaValue;
		this.usedTemperature = usedTemperature;
		this.usedSolvent = usedSolvent;
		this.usedPH = usedPH;
		this.usedTime = usedTime;
		this.hostConcentration = hostConcentration;
		this.guestConcentration = guestConcentration;
		this.buffer = buffer;
		this.frequency = frequency;
		this.nmrPH = nmrPH;
		this.nmrTemperature = nmrTemperature;
		this.nmrMixingTime = nmrMixingTime;
		this.article = article;
		this.journal = journal;
		this.nmrSpectrum = nmrSpectrum;
	}
	public String getComplexation() {
		return complexation;
	}
	public void setComplexation(String complexation) {
		this.complexation = complexation;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public double getBindingConstant() {
		return bindingConstant;
	}
	public void setBindingConstant(double bindingConstant) {
		this.bindingConstant = bindingConstant;
	}
	public String getStoichiometry() {
		return stoichiometry;
	}
	public void setStoichiometry(String stoichiometry) {
		this.stoichiometry = stoichiometry;
	}
	public String getPhenomena() {
		return phenomena;
	}
	public void setPhenomena(String phenomena) {
		this.phenomena = phenomena;
	}
	public double getPhenomenaValue() {
		return phenomenaValue;
	}
	public void setPhenomenaValue(double phenomenaValue) {
		this.phenomenaValue = phenomenaValue;
	}
	public double getUsedTemperature() {
		return usedTemperature;
	}
	public void setUsedTemperature(double usedTemperature) {
		this.usedTemperature = usedTemperature;
	}
	public String getUsedSolvent() {
		return usedSolvent;
	}
	public void setUsedSolvent(String usedSolvent) {
		this.usedSolvent = usedSolvent;
	}
	public double getUsedPH() {
		return usedPH;
	}
	public void setUsedPH(double usedPH) {
		this.usedPH = usedPH;
	}
	public double getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}
	public double getHostConcentration() {
		return hostConcentration;
	}
	public void setHostConcentration(double hostConcentration) {
		this.hostConcentration = hostConcentration;
	}
	public double getGuestConcentration() {
		return guestConcentration;
	}
	public void setGuestConcentration(double guestConcentration) {
		this.guestConcentration = guestConcentration;
	}
	public String getBuffer() {
		return buffer;
	}
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	public double getNmrPH() {
		return nmrPH;
	}
	public void setNmrPH(double nmrPH) {
		this.nmrPH = nmrPH;
	}
	public double getNmrTemperature() {
		return nmrTemperature;
	}
	public void setNmrTemperature(double nmrTemperature) {
		this.nmrTemperature = nmrTemperature;
	}
	public double getNmrMixingTime() {
		return nmrMixingTime;
	}
	public void setNmrMixingTime(double nmrMixingTime) {
		this.nmrMixingTime = nmrMixingTime;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getNmrSpectrum() {
		return nmrSpectrum;
	}
	public void setNmrSpectrum(String nmrSpectrum) {
		this.nmrSpectrum = nmrSpectrum;
	}
	
	
}
