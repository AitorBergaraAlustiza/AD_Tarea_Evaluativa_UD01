package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Ejercicio4a {

	public static void main(String[] args) {
		final int [] ids= {1, 2, 3, 4, 5, 6, 7};
		final String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
		final String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
		final String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
		final String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
		final int[] pesos = {76,84,66,136,78,102,70};
		final int[] alturas = {178,183,156,152,177,182,188};
		final int bytesRegistro = 110;
		
		final File d = new File("datos");
		final File f = new File(d, "Marvel.dat");
		
		int registro = 0;

		if(! d.exists()) d.mkdirs();
		if(f.exists() ) f.delete();
		
		try {
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			StringBuffer dniBuffer;
			StringBuffer nomBuffer;
			StringBuffer identidadBuffer;
			StringBuffer tipoBuffer;

			int posicion = 0;
			
			for(; registro < ids.length; registro++) {
				posicion = registro * bytesRegistro;
				raf.seek(posicion);
				
				raf.writeInt(ids[registro]);
				
				dniBuffer = new StringBuffer();
				dniBuffer.append(dnis[registro]);
				dniBuffer.setLength(9);
				raf.writeChars(dniBuffer.toString());
				
				nomBuffer = new StringBuffer();
				nomBuffer.append(noms[registro]);
				nomBuffer.setLength(10);
				raf.writeChars(nomBuffer.toString());
				
				identidadBuffer = new StringBuffer();
				identidadBuffer.append(identidades[registro]);
				identidadBuffer.setLength(20);
				raf.writeChars(identidadBuffer.toString());
				
				tipoBuffer = new StringBuffer();
				tipoBuffer.append(tipos[registro]);
				tipoBuffer.setLength(10);
				raf.writeChars(tipoBuffer.toString());
				
				raf.writeInt(pesos[registro]);
				raf.writeInt(alturas[registro]);
			}
			
			raf.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(registro == ids.length) {
				System.out.println("La carga de los personajes ha terminado correctamente.");
			} else {
				System.out.println("La carga de los personajes ha terminado con errores");
			}			
		}
	}
}
