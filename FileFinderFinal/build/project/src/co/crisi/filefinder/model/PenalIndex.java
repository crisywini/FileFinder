package co.crisi.filefinder.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.crisi.filefinder.exceptions.ImputedNullException;
import co.crisi.filefinder.exceptions.ImputedRepeatedException;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class PenalIndex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Imputed> imputedPeople;

	public PenalIndex() {
		imputedPeople = new ArrayList<Imputed>();
	}

	// Getters and setters methods

	public ArrayList<Imputed> getImputedPeople() {
		return imputedPeople;
	}

	public void setImputedPeople(ArrayList<Imputed> imputedPeople) {
		this.imputedPeople = imputedPeople;
	}

	/**
	 * This method allows to get an index of the imputed
	 * 
	 * @param lastNames imputed last names
	 * @param names     imputed names
	 * @return an integer value different to -1 if the imputed exists
	 */
	public int getPosImputed(String lastNames, String names) {
		int pos = -1;
		boolean stopper = false;
		Imputed imputed;
		for (int i = 0; i < imputedPeople.size() && !stopper; i++) {
			imputed = imputedPeople.get(i);
			if (imputed.getLastNames().equalsIgnoreCase(lastNames) && imputed.getNames().equalsIgnoreCase(names)) {
				pos = i;
				stopper = true;
			}
		}
		return pos;
	}

	/**
	 * This method allows to add an imputed
	 * 
	 * @param lastNames imputed last names
	 * @param names     imputed names
	 * @throws ImputedRepeatedException if the imputed exists
	 */
	public void addImputed(String lastNames, String names) throws ImputedRepeatedException {
		int pos = getPosImputed(lastNames, names);
		if (pos != -1) {
			throw new ImputedRepeatedException("El indexado o imputado: " + lastNames.toUpperCase() + " "
					+ names.toUpperCase() + " ya se encuentra registrado.");
		}
		imputedPeople.add(new Imputed(names, lastNames));
	}

	/**
	 * This method allows to remove an imputed
	 * 
	 * @param lastNames imputed last names
	 * @param names     imputed names
	 * @return the {@link Imputed} removes
	 * @throws ImputedNullException if the imputed does not exist
	 */
	public Imputed removeImputed(String lastNames, String names) throws ImputedNullException {
		int pos = getPosImputed(lastNames, names);
		if (pos == -1) {
			throw new ImputedNullException("El indexado o imputado: " + lastNames.toUpperCase() + " "
					+ names.toUpperCase() + " no se encuentra registrado.");
		}
		return imputedPeople.remove(pos);
	}

}
