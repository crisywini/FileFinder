package co.crisi.filefinder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import co.crisi.filefinder.exceptions.CivilLocationNullException;
import co.crisi.filefinder.exceptions.CivilLocationRepeatedException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class Defendant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String lastNames;
	private ArrayList<CivilLocation> civilLocations;

	/**
	 * Default constructor method
	 */
	public Defendant() {
		this("NoNames", "NoLastNames");
	}

	/**
	 * Constructor method
	 * 
	 * @param names
	 * @param lastNames
	 */
	public Defendant(String names, String lastNames) {
		this.names = names;
		this.lastNames = lastNames;
		civilLocations = new ArrayList<CivilLocation>();
	}

	// Getters and setters
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

	public ArrayList<CivilLocation> getCivilLocations() {
		return civilLocations;
	}

	public void setCivilLocations(ArrayList<CivilLocation> civilLocations) {
		this.civilLocations = civilLocations;
	}

	/**
	 * This method allows to find an index of a {@link CivilLocation} object
	 * 
	 * @param radication
	 * @param folio
	 * @param volume
	 * @param process
	 * @return
	 */
	public int getPosCivilLocation(long radication, long folio, long volume, String process) {
		int pos = -1;
		boolean stopper = false;
		CivilLocation civilLocation;
		for (int i = 0; i < civilLocations.size() && !stopper; i++) {
			civilLocation = civilLocations.get(i);
			if (civilLocation.getFolio() == folio && civilLocation.getRadication() == radication
					&& civilLocation.getVolume() == volume && civilLocation.getProcess().equalsIgnoreCase(process)) {
				pos = i;
				stopper = true;
			}
		}
		return pos;
	}

	/**
	 * This method allows to add a {@link CivilLocation}
	 * 
	 * @param radication from civil location
	 * @param folio      from civil location
	 * @param volume     from civil location
	 * @param applicant  from civil location
	 * @param process    from civil location
	 * @throws CivilLocationRepeatedException if the civil location if repeated
	 */
	public void addCivilLocation(long radication, long folio, long volume, Applicant applicant, String process)
			throws CivilLocationRepeatedException {
		if (getPosCivilLocation(radication, folio, volume, process) == -1) {
			civilLocations.add(new CivilLocation(radication, folio, volume, process, this, applicant));
		} else {
			throw new CivilLocationRepeatedException("EL lugar: radicación: " + radication + " folio: " + folio
					+ " tomo: " + volume + " proceso: " + process + " \nYa se encuentra registrada");
		}
	}

	/**
	 * This method allows to remove a civil location
	 * 
	 * @param radication from civil location
	 * @param folio      from civil location
	 * @param volume     from civil location
	 * @param process    from civil location
	 * @return the civil location with the exact information
	 * @throws CivilLocationNullException if the civil location is not set
	 */
	public CivilLocation removeCivilLocation(long radication, long folio, long volume, String process)
			throws CivilLocationNullException {
		int pos = getPosCivilLocation(radication, folio, volume, process);
		if (pos == -1) {
			throw new CivilLocationNullException("EL lugar: radicación: " + radication + " folio: " + folio + " tomo: "
					+ volume + " proceso: " + process + " \nNo se encuentra registrado");
		}
		return civilLocations.remove(pos);
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		Defendant defendant;
		if (obj == this)
			return true;
		if (obj instanceof Defendant) {
			defendant = (Defendant) obj;
			if (defendant.getNames().equalsIgnoreCase(names) && defendant.getLastNames().equalsIgnoreCase(lastNames))
				isEqual = true;
		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastNames, names, civilLocations);
	}

}
