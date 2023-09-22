package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import utils.Consola;

public class Ejercicio4c {

	public static void main(String[] args) {
		final File d = new File("datos");
		final File f = new File(d, "Marvel.dat");
		final int bytesRegistro = 110;

		if(! f.exists()) {
			System.err.println("No se ha encontrado el archivo de datos");
			System.exit(1);
		}
		
		try {
			int encontrados = 0;
			String mensaje = "";
			System.out.print("Introduce un tipo de personaje: ");
			String tipoBuscado = (Consola.readString()).trim();
			
			RandomAccessFile raf = new RandomAccessFile(f, "r");
			for(int posicion = 0; posicion < raf.length(); posicion += bytesRegistro) {
				char[] auxTipo = new char[10];
				String tipo;
				
				raf.seek(posicion);
				raf.skipBytes(82);
				
				for(int i = 0; i < auxTipo.length; i++) {
					auxTipo[i] = raf.readChar();
				}
				tipo = (new String(auxTipo)).trim();
				
				if(tipo.equalsIgnoreCase(tipoBuscado)) {
					encontrados++;
					mensaje += leerPersonaje(raf, posicion);
				}
			}
			
			raf.close();
			
			if(encontrados == 0) {
				System.out.printf("No existen %ss en el fichero.", tipoBuscado);
			} else {
				System.out.printf("Se han encontrado %d %ss%n", encontrados, tipoBuscado);
				System.out.println(mensaje);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private static String leerPersonaje(RandomAccessFile raf, int posicion) throws IOException {
		char[] auxDni = new char[9];
		char[] auxNom = new char[10];
		char[] auxIdentidad = new char[20];
		char[] auxTipo = new char[10];
		String dni, nom, identidad, tipo;
		int peso, altura;
		
		raf.seek(posicion);
		raf.skipBytes(4);
		
		for(int i = 0; i < auxDni.length; i++) {
			auxDni[i] = raf.readChar();
		}
		dni =new String(auxDni);
		
		for(int i = 0; i < auxNom.length; i++) {
			auxNom[i] = raf.readChar();
		}
		nom = new String(auxNom);
		
		for(int i = 0; i < auxIdentidad.length; i++) {
			auxIdentidad[i] = raf.readChar();
		}
		identidad = new String(auxIdentidad);
		
		for(int i = 0; i < auxTipo.length; i++) {
			auxTipo[i] = raf.readChar();
		}
		tipo = new String(auxTipo);
		
		peso = raf.readInt();
		
		altura = raf.readInt();

		return String.format("Personaje [dni=%s,nombre=%s,identidad=%s,tipo=%s,peso=%d,altura=%d]%n", dni, nom, identidad, tipo, peso, altura);
	}
}
