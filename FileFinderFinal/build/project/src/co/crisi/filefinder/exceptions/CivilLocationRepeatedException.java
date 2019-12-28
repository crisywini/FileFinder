package co.crisi.filefinder.exceptions;

import java.io.Serializable;
/**
 * 
 * @author Cristian Giovanny S�nchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class CivilLocationRepeatedException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CivilLocationRepeatedException(String erroMessage) {
		super(erroMessage);
	}

}
