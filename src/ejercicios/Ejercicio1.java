package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Ejercicio1 {

	public static void main(String[] args) {
		File f = new File("recursos" + File.separator + "ejercicio1.txt");
		String resultado = "";
		
		if(!f.exists()) {
			System.err.println("No se ha encontrado el fichero");
			System.exit(1);
		}
		
		try (FileReader fr = new FileReader(f)){
			for(int i = 0; i < f.length(); i++) {
				resultado = ((char) fr.read()) + resultado;
			}
			fr.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		System.out.println(resultado);
	}

}
