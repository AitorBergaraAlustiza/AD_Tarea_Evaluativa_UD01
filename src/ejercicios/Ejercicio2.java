package ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {

	public static void main(String[] args) {
		File f = new File("recursos" + File.separator + "ejercicio2.txt");
		List<String> palindromos = new ArrayList<>();
		
		if(!f.exists()) {
			System.err.println("No se ha encontrado el fichero");
			System.exit(1);
		}
		
		try (BufferedReader bf = new BufferedReader(new FileReader(f))){
			String linea;
			
			while((linea = bf.readLine()) != null) {
				String palabras[] = linea.split("\\s+"); // NOTA 1
				
				for(int i = 0; i < palabras.length; i++) {
					String invertida = new StringBuffer(palabras[i]).reverse().toString(); // NOTA 2
					
					if(palabras[i].equalsIgnoreCase(invertida)) {
						palindromos.add(palabras[i]);
					}
				}
			}
			bf.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		f = new File(f.getParent() + File.separator + "palindromos.txt");
		
		if(f.exists()) {
			f.delete();
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
			for (String palindromo : palindromos) {
				bw.write(palindromo);
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}

// NOTAS
// 1    https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
// 2    https://parzibyte.me/blog/2019/02/22/palindromo-java-saber-si-cadena-palindroma/