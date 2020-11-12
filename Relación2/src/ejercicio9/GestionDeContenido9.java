package ejercicio9;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionDeContenido9 extends DefaultHandler{

	String elementoActual;
	String nombreDepartamento;
	String nombreEmpleado;
	int sueldo;
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);
		
		contenido = contenido.replaceAll("[\t\n", "").trim();
		
		switch (elementoActual) {
		case "departamento":
			nombreDepartamento = contenido;
			break;
		case "nombre":
			nombreEmpleado = contenido;
			break;
		case "empleado":
//			sueldo = elementoActual.
		default:
			break;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
	}

	@Override
	public void startDocument() throws SAXException {
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elementoActual = qName;
	}

	
	
}
