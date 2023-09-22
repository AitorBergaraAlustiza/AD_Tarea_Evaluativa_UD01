package ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Ejercicio3 {

	public static void main(String[] args) {
		final byte[] cabeceraZip = {80, 75, 3, 4};
		byte[] cabeceraFichero = new byte[4];
		
		//File f = new File("recursos" + File.separator + "ejercicio3.zip");
		File f = new File("recursos" + File.separator + "ejercicio3_no.zip");
		
		if(! f.exists()) {
			System.err.println("No se ha encontrado el fichero");
			System.exit(1);
		}
		
		try (FileInputStream fis = new FileInputStream(f)){
			for(int i = 0; i < cabeceraFichero.length; i++) {
				cabeceraFichero[i] = (byte) fis.read();
			}
			fis.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		String es = (Arrays.equals(cabeceraZip, cabeceraFichero) ? "SI" : "NO");
		System.out.printf("El fichero %s %s es un fichero ZIP", f.getName(), es);
	}

}
