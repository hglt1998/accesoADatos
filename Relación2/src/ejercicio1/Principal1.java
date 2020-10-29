package ejercicio1;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Principal1
{

	private static final String NOMBRE_FICHERO = "personas.xml";

	
	public static void main(String[] args) 
	{
		
		try {
			leerXMLConSax(NOMBRE_FICHERO);
		
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private static void leerXMLConSax(String nombreFichero) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException
	{
		
		XMLReader reader= SAXParserFactory.newInstance().newSAXParser().getXMLReader();
		GestionContenido1 manejador=new GestionContenido1();
		
		reader.setContentHandler(manejador);
		
		//Arranca el parser leyendo el fichero xml
		reader.parse(new InputSource(nombreFichero));
		
		System.out.println("Persona de mayor edad " + manejador.getNombreDeEdadMayor() + 
				" con " + manejador.getEdadMayor() + " años");
		
		
	}



}