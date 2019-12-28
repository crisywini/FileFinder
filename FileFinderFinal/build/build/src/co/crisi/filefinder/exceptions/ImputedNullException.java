package co.crisi.filefinder.exceptions;

import java.io.Serializable;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class ImputedNullException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImputedNullException(String errorMessage) {
		super(errorMessage);
	}

}
