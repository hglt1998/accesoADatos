package ejercicio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Principal1Bis {

	private static final String NOMBRE_FICHERO = "personas.xml";
	
	public static void main(String[] args) {
		try {
		
			leerConSax(NOMBRE_FICHERO);
		
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void leerConSax(String nombreFichero) throws FileNotFoundException, SAXException, ParserConfigurationException, IOException {

		XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		GestionContenido1Bis manejador = new GestionContenido1Bis();
		
		reader.setContentHandler(manejador);
		
		reader.parse(new InputSource(nombreFichero));
		
		
	}	

}
