package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import utils.Consola;

public class Ejercicio4b {

	public static void main(String[] args) {
		final File d = new File("datos");
		final File f = new File(d, "Marvel.dat");
		final int bytesRegistro = 110;
		
		String dniBuscado;
		int pesoActual;

		if(! f.exists()) {
			System.err.println("No se ha encontrado el archivo de datos");
			System.exit(1);
		}
		
		try {
			System.out.print("Introduzca el DNI (con letra) del personaje para control de peso: ");
			dniBuscado = (Consola.readString()).trim();
			
			System.out.print("Introduzca su peso actual: ");
			pesoActual = Consola.readInt();
			
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			int posicion = -1;
			
			for(int i = 0; i < f.length() && posicion == -1; i += bytesRegistro) {
				char[] auxDni = new char[9];
				String tmpDni;
				
				raf.seek(i);
				raf.skipBytes(4);
				
				for(int j = 0; j < auxDni.length; j++) {
					auxDni[j] = raf.readChar();
				}
				tmpDni = new String(auxDni).trim();
				
				if(tmpDni.equalsIgnoreCase(dniBuscado)) {
					posicion = i;
				}
			}
			
			if(posicion == -1) {
				System.out.printf("No se ha encontrado a nadie con el DNI %s%n", dniBuscado);
			} else {
				char[] auxNombre = new char[10];
				String nombre;
				int pesoAnterior;
				
				raf.seek(posicion);
				raf.skipBytes(22);
				
				for(int i = 0; i < auxNombre.length; i++) {
					auxNombre[i] = raf.readChar();
				}
				nombre = (new String(auxNombre)).trim();
				
				raf.skipBytes(60);
				
				pesoAnterior = raf.readInt();
				
				if(pesoAnterior < pesoActual) {
					System.out.printf("%s ha engordado %d kilos.", nombre, pesoActual - pesoAnterior);
				} else if(pesoAnterior > pesoActual) {
					System.out.printf("%s ha adelgazado %d kilos.", nombre, pesoAnterior - pesoActual);
				} else {
					System.out.printf("%s se mantiene en su peso anterior.", nombre);
				}
			}

			raf.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

}
