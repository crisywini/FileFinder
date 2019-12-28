package co.crisi.filefinder.model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class CivilLocation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long radication;
	private long folio;
	private long volume;
	private String process;
	private Defendant defendant;
	private Applicant applicant;

	/**
	 * Default constructor method
	 */
	public CivilLocation() {
		this(000, 000, 000, "NoProcess", new Defendant(), new Applicant());
	}

	/**
	 * Constructor method
	 * 
	 * @param radication
	 * @param folio
	 * @param volume
	 * @param process
	 * @param defendant
	 * @param applicant
	 */
	public CivilLocation(long radication, long folio, long volume, String process, Defendant defendant,
			Applicant applicant) {
		this.radication = radication;
		this.folio = folio;
		this.volume = volume;
		this.process = process;
		this.defendant = defendant;
		this.applicant = applicant;
	}

	// Getters and setters
	public long getRadication() {
		return radication;
	}

	public StringProperty radicationProperty() {
		return new SimpleStringProperty(radication + "");
	}

	public void setRadication(long radication) {
		this.radication = radication;
	}

	public long getFolio() {
		return folio;
	}

	public StringProperty folioProperty() {
		return new SimpleStringProperty(folio + "");
	}

	public void setFolio(long folio) {
		this.folio = folio;
	}

	public long getVolume() {
		return volume;
	}

	public StringProperty volumeProperty() {
		return new SimpleStringProperty(volume + "");
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public String getProcess() {
		return process;
	}

	public StringProperty processProperty() {
		return new SimpleStringProperty(process);
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Defendant getDefendant() {
		return defendant;
	}

	public void setDefendant(Defendant defendant) {
		this.defendant = defendant;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public StringProperty applicantProperty() {
		return new SimpleStringProperty(applicant.getLastNames() + " " + applicant.getNames());
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

}
