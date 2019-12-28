package co.crisi.filefinder.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.crisi.filefinder.exceptions.ApplicantNullException;
import co.crisi.filefinder.exceptions.ApplicantRepeatedException;
import co.crisi.filefinder.exceptions.DefendantNullException;
import co.crisi.filefinder.exceptions.DefendantRepeatedException;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class CivilIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Defendant> defendants;
	private ArrayList<Applicant> applicants;

	/**
	 * Constructor method
	 */
	public CivilIndex() {
		defendants = new ArrayList<Defendant>();
		applicants = new ArrayList<Applicant>();
	}

	// Getters and setters methods
	public ArrayList<Defendant> getDefendants() {
		return defendants;
	}

	public void setDefendants(ArrayList<Defendant> defendants) {
		this.defendants = defendants;
	}

	public ArrayList<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(ArrayList<Applicant> applicants) {
		this.applicants = applicants;
	}

	/**
	 * This method allows to get an index of a defendant
	 * 
	 * @param lastNames defendant last names
	 * @param names     defendant names
	 * @return an integer value different to -1 if the defendant exists
	 */
	public int getPosDefendants(String lastNames, String names) {
		int pos = -1;
		boolean stopper = false;
		Defendant defendant;
		for (int i = 0; i < defendants.size() && !stopper; i++) {
			defendant = defendants.get(i);
			if (defendant.getLastNames().equalsIgnoreCase(lastNames) && defendant.getNames().equalsIgnoreCase(names)) {
				pos = i;
				stopper = true;
			}
		}
		return pos;
	}

	/**
	 * This method allows to add a defendant
	 * 
	 * @param names     defendant names
	 * @param lastNames defendant last names
	 * @throws DefendantRepeatedException if the defendant exists
	 */
	public void addDefendant(String names, String lastNames) throws DefendantRepeatedException {
		int pos = getPosDefendants(lastNames, names);
		if (pos != -1) {
			throw new DefendantRepeatedException(
					"El demandado: " + lastNames.toUpperCase() + " " + names.toUpperCase() + " ya está registrado");
		}
		defendants.add(new Defendant(names, lastNames));
	}

	/**
	 * This method allows to remove a defendant
	 * 
	 * @param names     defendant names
	 * @param lastNames defendant last names
	 * @return the {@link Defendant} removed
	 * @throws DefendantNullException if the defendant does not exist
	 */
	public Defendant removeDefendant(String names, String lastNames) throws DefendantNullException {
		int pos = getPosDefendants(lastNames, names);
		if (pos == -1) {
			throw new DefendantNullException("El demandado: " + lastNames.toUpperCase() + " " + names.toUpperCase()
					+ " no se encuentra registrado");
		}
		return defendants.remove(pos);
	}

	/**
	 * This method allows to get an index of an applicant
	 * 
	 * @param lastNames applicant last names
	 * @param names     applicant names
	 * @return an integer value different to -1 if the applicant exists
	 */
	public int getPosApplicant(String lastNames, String names) {
		int pos = -1;
		boolean stopper = false;
		Applicant applicant;
		for (int i = 0; i < applicants.size() && !stopper; i++) {
			applicant = applicants.get(i);
			if (applicant.getLastNames().equalsIgnoreCase(lastNames) && applicant.getNames().equalsIgnoreCase(names)) {
				pos = i;
				stopper = true;
			}
		}
		return pos;
	}

	/**
	 * This method allows to add an applicant if the applicant
	 * 
	 * @param names     applicant names
	 * @param lastNames applicant last names
	 * @throws ApplicantRepeatedException if the applicant exists
	 */
	public void addApplicant(String names, String lastNames) throws ApplicantRepeatedException {
		int pos = getPosApplicant(lastNames, names);
		if (pos != -1) {
			throw new ApplicantRepeatedException(
					"El demandante: " + lastNames.toUpperCase() + " " + names.toUpperCase() + " ya está registrado");
		}
		applicants.add(new Applicant(names, lastNames));
	}

	/**
	 * This method allows to remove an applicant
	 * 
	 * @param names     applicant names
	 * @param lastNames applicant last names
	 * @return the {@link Applicant} removed
	 * @throws ApplicantNullException if the applicant does not exist
	 */
	public Applicant removeApplicant(String names, String lastNames) throws ApplicantNullException {
		int pos = getPosApplicant(lastNames, names);
		if (pos == -1) {
			throw new ApplicantNullException("El demandado: " + lastNames.toUpperCase() + " " + names.toUpperCase()
					+ " no se encuentra registrado");
		}
		return applicants.remove(pos);
	}

}
