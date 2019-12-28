package co.crisi.filefinder.model;

import java.io.Serializable;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class FileFinder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CivilIndex civilIndex;
	private PenalIndex penalIndex;

	/**
	 * Default constructor method
	 */
	public FileFinder() {
		this(new CivilIndex(), new PenalIndex());
	}

	/**
	 * Constructor method
	 * 
	 * @param civilIndex
	 * @param penalIndex
	 */
	public FileFinder(CivilIndex civilIndex, PenalIndex penalIndex) {
		this.civilIndex = civilIndex;
		this.penalIndex = penalIndex;
	}

	// Getters and setters methods
	public CivilIndex getCivilIndex() {
		return civilIndex;
	}

	public void setCivilIndex(CivilIndex civilIndex) {
		this.civilIndex = civilIndex;
	}

	public PenalIndex getPenalIndex() {
		return penalIndex;
	}

	public void setPenalIndex(PenalIndex penalIndex) {
		this.penalIndex = penalIndex;
	}

}
