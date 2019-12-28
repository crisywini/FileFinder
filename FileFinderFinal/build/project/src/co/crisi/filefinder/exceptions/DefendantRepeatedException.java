package co.crisi.filefinder.exceptions;

import java.io.Serializable;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class DefendantRepeatedException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefendantRepeatedException(String errorMessage) {
		super(errorMessage);
	}

}
