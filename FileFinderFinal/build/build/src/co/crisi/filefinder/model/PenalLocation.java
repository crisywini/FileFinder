package co.crisi.filefinder.model;

import java.io.Serializable;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class PenalLocation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long radication;
	private long folio;
	private long volume;
	private TypeFunction function;
	private String crime;
	private Imputed imputed;

	/**
	 * Default constructor method
	 */
	public PenalLocation() {
		this(0, 0, 0, TypeFunction.CONOCIMIENTO, "NoCrime", new Imputed());
	}

	/**
	 * Constructor method
	 * 
	 * @param radication from Penal Location
	 * @param folio      from Penal Location
	 * @param volume     from Penal Location
	 * @param function   from Penal Location
	 * @param crime      from Penal Location
	 * @param imputed    from Penal Location
	 */
	public PenalLocation(long radication, long folio, long volume, TypeFunction function, String crime,
			Imputed imputed) {
		this.radication = radication;
		this.folio = folio;
		this.volume = volume;
		this.function = function;
		this.crime = crime;
		this.imputed = imputed;
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

	public long getVolume() {
		return volume;
	}

	public StringProperty volumeProperty() {
		return new SimpleStringProperty(volume + "");
	}

	public void setVolume(long volume) {
		this.volume = volume;
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

	public TypeFunction getFunction() {
		return function;
	}

	public StringProperty functionProperty() {
		return new SimpleStringProperty(function.toString());
	}

	public void setFunction(TypeFunction function) {
		this.function = function;
	}

	public String getCrime() {
		return crime;
	}

	public StringProperty crimeProperty() {
		return new SimpleStringProperty(crime);
	}

	public void setCrime(String crime) {
		this.crime = crime;
	}

	public Imputed getImputed() {
		return imputed;
	}

	public void setImputed(Imputed imputed) {
		this.imputed = imputed;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj == this)
			return true;
		if (obj instanceof PenalLocation) {
			PenalLocation penalLocation = (PenalLocation) obj;
			if (penalLocation.getCrime().equalsIgnoreCase(crime) && penalLocation.getFolio() == folio
					&& penalLocation.getRadication() == radication && penalLocation.getVolume() == volume
					&& penalLocation.getFunction() == function && penalLocation.getImputed().equals(imputed))
				isEqual = true;
		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(radication, folio, volume, function, crime, imputed);
	}

}
