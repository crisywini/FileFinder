package co.crisi.filefinder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class Applicant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String lastNames;
	private ArrayList<Defendant> defendants;

	public Applicant() {
		this("NoNames", "NoLastNames");
	}

	public Applicant(String names, String lastNames) {
		this.names = names;
		this.lastNames = lastNames;
		defendants = new ArrayList<Defendant>();
	}

	// Getters and setters methods
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public ArrayList<Defendant> getDefendant() {
		return defendants;
	}

	public void setDefendant(ArrayList<Defendant> defendant) {
		this.defendants = defendant;
	}

	public void addDefendant(Defendant defendant) {
		defendants.add(defendant);
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj == this)
			return true;
		if (obj instanceof Applicant) {
			Applicant applicant = (Applicant) obj;
			if (applicant.getNames().equalsIgnoreCase(names) && applicant.getLastNames().equalsIgnoreCase(lastNames))
				isEqual = true;
		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastNames, names, defendants);
	}

}
