package ejercicio3;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

//import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class Principal3 extends DefaultHandler{

	private static final String NOMBRE_FICHERO = "resultados.xml";
	
	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		
		leerFichero(NOMBRE_FICHERO);
		
		
	}

	private static void leerFichero(String nombreFichero) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		
		XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		
//		reader.setContentHandler(new ContentHandler());
		//Arranca el parser leyendo el fichero XML
		reader.parse(new InputSource(nombreFichero));
	}

}
