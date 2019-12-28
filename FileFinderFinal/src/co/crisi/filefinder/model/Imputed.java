package co.crisi.filefinder.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.crisi.filefinder.exceptions.PenalLocationNullException;
import co.crisi.filefinder.exceptions.PenalLocationRepeatedException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class Imputed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String lastNames;
	private ArrayList<PenalLocation> penalLocations;

	/**
	 * Default constructor method
	 */
	public Imputed() {
		this("NoNames", "NoLastNames");
	}

	/**
	 * Constructor method
	 * 
	 * @param names
	 * @param lastNames
	 */
	public Imputed(String names, String lastNames) {
		this.names = names;
		this.lastNames = lastNames;
		penalLocations = new ArrayList<PenalLocation>();
	}

	// Getters and setters methods
	public String getNames() {
		return names;
	}

	public StringProperty namesProperty() {
		return new SimpleStringProperty(names);
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public StringProperty lastNamesProperty() {
		return new SimpleStringProperty(lastNames);
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public ArrayList<PenalLocation> getPenalLocations() {
		return penalLocations;
	}

	public void setPenalLocations(ArrayList<PenalLocation> penalLocations) {
		this.penalLocations = penalLocations;
	}

	/**
	 * This method allows to get the index of the penal location
	 * 
	 * @param radication penal location radication
	 * @param folio      penal location folio
	 * @param volume     penal location volume
	 * @param function   penal location function
	 * @param crime      penal location crime
	 * @return an integer value different to -1 if the penal location exists
	 */
	public int getPosPenalLocation(long radication, long folio, long volume, TypeFunction function, String crime) {
		int pos = -1;
		boolean stopper = false;
		PenalLocation penalLocation;
		for (int i = 0; i < penalLocations.size() && !stopper; i++) {
			penalLocation = penalLocations.get(i);
			if (penalLocation.getCrime().equalsIgnoreCase(crime) && penalLocation.getFolio() == folio
					&& penalLocation.getRadication() == radication && penalLocation.getVolume() == volume
					&& penalLocation.getFunction() == function) {
				pos = i;
				stopper = true;
			}
		}
		return pos;
	}

	/**
	 * This method allows to add a penal location
	 * 
	 * @param radication penal location radication
	 * @param folio      penal location folio
	 * @param volume     penal location volume
	 * @param function   penal location function
	 * @param crime      penal location crime
	 * @throws PenalLocationRepeatedException if the penal location exists
	 */
	public void addPenalLocation(long radication, long folio, long volume, TypeFunction function, String crime)
			throws PenalLocationRepeatedException {
		if (getPosPenalLocation(radication, folio, volume, function, crime) != -1) {
			throw new PenalLocationRepeatedException("El lugar: radicación: " + radication + " folio: " + folio
					+ " volume: " + volume + " ya se encuentra registrado.");
		}
		penalLocations.add(new PenalLocation(radication, folio, volume, function, crime, this));
	}

	/**
	 * This method allows to remove a penal location
	 * 
	 * @param radication penal location radication
	 * @param folio      penal location folio
	 * @param volume     penal location volume
	 * @param function   penal location function
	 * @param crime      penal location crime
	 * @return the {@link PenalLocation} removed
	 * @throws PenalLocationNullException if the penal location does not exist
	 */
	public PenalLocation removePenalLocation(long radication, long folio, long volume, TypeFunction function,
			String crime) throws PenalLocationNullException {
		if (getPosPenalLocation(radication, folio, volume, function, crime) == -1) {
			throw new PenalLocationNullException("El lugar: radicación: " + radication + " folio: " + folio
					+ " volume: " + volume + " no se encuentra registrado.");
		}
		return penalLocations.remove(getPosPenalLocation(radication, folio, volume, function, crime));
	}

}
