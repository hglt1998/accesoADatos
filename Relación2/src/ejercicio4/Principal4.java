package ejercicio4;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class Principal4 {

	private static final String NOMBRE_FICHERO = "alumnos.xml";
	
	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		
		leerFichero(NOMBRE_FICHERO);
		
		
	}

	private static void leerFichero(String nombreFichero) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		
		XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		
		reader.setContentHandler(new GestionContenido4());
		//Arranca el parser leyendo el fichero XML
		reader.parse(new InputSource(nombreFichero));
	}

}
