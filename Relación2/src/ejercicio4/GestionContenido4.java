package ejercicio4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido4 extends DefaultHandler{

	private static final int MAXIMO_SUSPENSOS = 2;
	
	private String etiquetaActual;
	private int numeroSuspensos;
	private boolean suspenso = false;
	StringBuilder datos = new StringBuilder();
	private String nombreAlumno;
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);
		contenido = contenido.trim();
		
		switch (contenido) {
		case "dni":
			datos.append(contenido);
		break;
		case "nombre":
			datos.append(contenido);
			contenido = nombreAlumno;

		default:
			break;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("nombre")) {
			
			if (suspenso) {
				escribirSuspenso();
			} else {
				crearDirectorioAprobado();
			}
		}
	}

	@Override
	public void startDocument() throws SAXException {
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.etiquetaActual = qName;
		
		if (etiquetaActual.equals("alumno")) {
			numeroSuspensos = Integer.parseInt(attributes.getValue("pendientees"));
			if (numeroSuspensos >= MAXIMO_SUSPENSOS) {
				suspenso = true;
			}
		}
		
	}
	
	private void crearDirectorioAprobado() {
		String nombreCarpeta = datos.toString();
		File carpeta = new File(nombreCarpeta);
		boolean creado = false;
		
		if ( carpeta.exists() && carpeta.isDirectory())
			System.out.println("Error. Ya existe un directorio con ese nombre");
		else
		{
			creado=carpeta.mkdir();
			if ( creado)
				System.out.println("Directorio creado correctamete");
			else
				System.out.println("No se pudo crear el directorio " + nombreCarpeta);
		}
	}

	private void escribirSuspenso() {
		String nombreAlumno = null;
		try (BufferedWriter filtroEscritura = new BufferedWriter(new FileWriter(nombreAlumno));){
			filtroEscritura.write(datos.toString());
			
			filtroEscritura.close();
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
}
