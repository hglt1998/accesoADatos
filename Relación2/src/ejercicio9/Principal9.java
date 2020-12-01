package ejercicio9;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Principal9 {

	private static final String FICHERO_ORIGEN = "universidadEj9.xml";
//	private static final String FICHERO_DESTINO = "universidad.txt";
	
	public static void main(String[] args) {
		
		try {
			
			leerConSax(FICHERO_ORIGEN);
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Error " + e.getMessage());
		}
		
		
	}

	private static void leerConSax(String ficheroOrigen) throws SAXException, ParserConfigurationException, IOException {
		
		XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		GestionDeContenido9 manejador = new GestionDeContenido9();
		
		reader.setContentHandler(manejador);
		reader.parse(new InputSource(ficheroOrigen));
		
		
	}

}
