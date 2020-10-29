package ejercicio1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido1 extends DefaultHandler
{

	private String elementoActual;
	private String nombre;
	private int edad;
	
	private String nombreDeEdadMayor;
	private int edadMayor=0;
	
	
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String cadena=new String(ch,start,length);

		cadena = cadena.replaceAll("[\t\n]", "").trim(); // eliminamos los saltos de linea, blanco y tabuladores
		if (cadena.length() <= 0) { // solo la mostramos si tiene algo{
			return;
		}
		
		switch (elementoActual) {
		case "nombre":
			nombre=cadena;
			break;
		case "edad":
			edad=Integer.parseInt(cadena);
			break;
			
		}
		
	}

	@Override
	public void endDocument() throws SAXException {
		

	}

	public String getNombreDeEdadMayor() {
		return nombreDeEdadMayor;
	}

	

	public int getEdadMayor() {
		return edadMayor;
	}

	

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		elementoActual="";
		
		if (qName.equals("persona")) {
			if (edad >= edadMayor) {
				edadMayor=edad;
				nombreDeEdadMayor=nombre;
			}
		}
	}
		

	@Override
	public void startDocument() throws SAXException{
		
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		elementoActual=qName;
	}
	
	
}