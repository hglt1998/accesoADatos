package ejercicio2;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class Principal2 {

	private static final String NOMBRE_FICHERO = "resultados.xml";
	
	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		
		leerFichero(NOMBRE_FICHERO);
		
	}

	private static void leerFichero(String nombreFichero) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		
		XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(new GestionContenido2());
		reader.parse(new InputSource(nombreFichero));
	}

}
