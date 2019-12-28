package co.crisi.filefinder.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda cellphone: 321-937-3570 g-mail:
 *         harmaharcri.cs@gmail.com
 *
 */
public class Persistence {
	public static final String FILE_FINDER_PATH = "..\\dat\\FileFinder.dat";

	/**
	 * This method allows to serialize an object
	 * 
	 * @param fileName
	 * @param object
	 * @throws IOException
	 */
	public static void serialize(String fileName, Object object) throws IOException {
		ObjectOutputStream output = null;
		FileOutputStream fileOutPutStream = null;
		File file = new File(FILE_FINDER_PATH);
		String absolute = file.getCanonicalPath();
		fileOutPutStream = new FileOutputStream(absolute);
		output = new ObjectOutputStream(fileOutPutStream);
		output.writeObject(object);
		output.flush();
		output.close();
	}

	/**
	 * This method allows to read a file and return and object with the information
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readSerializeFile(String fileName) throws IOException, ClassNotFoundException {
		ObjectInputStream input;
		FileInputStream fileInputStream = null;
		Object object = null;
		File file = new File(FILE_FINDER_PATH);
		String absolute = file.getCanonicalPath();

		fileInputStream = new FileInputStream(absolute);
		input = new ObjectInputStream(fileInputStream);
		object = input.readObject();

		input.close();
		return object;
	}
}