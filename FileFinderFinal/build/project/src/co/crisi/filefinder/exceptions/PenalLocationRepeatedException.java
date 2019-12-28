package co.crisi.filefinder.exceptions;
/**
 * 
 * @author Cristian Giovanny S�nchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class PenalLocationRepeatedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PenalLocationRepeatedException(String errorMessage) {
		super(errorMessage);
	}

}