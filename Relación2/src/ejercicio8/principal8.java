package ejercicio8;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class principal8 {

	private static final String FICHERO_ORIGINAL = "zonas.xml";
	private static final String FICHERO_DESTINO = "zonasActualizado.xml";
	private static final String ETIQUETA_ZONA = "zona";
	private static final String ETIQUETA_CODIGO = "cod_zona";
	private static final String ETIQUETA_SUBDIRECTOR = "subdirector";
	private static final Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			
			//Creación del árbol
			Document arbol = crearArbol();
			
			//Modificación del árbol
			modificarArbol(arbol);
			
			//Crear archivo destino
			Source source = new DOMSource(arbol);
			Result result = new StreamResult(FICHERO_DESTINO);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
			System.out.println("Archivo actualizado correctamente");
			
		} catch (Exception e) {
			e.getMessage();
		}		
	}

	private static void modificarArbol(Document arbol) {
		
		Element elementoRaiz, zonaActual, elementoSubdirector;
		NodeList listaZonas;
		String nombreSubdirector;
		
		elementoRaiz = (Element) arbol.getFirstChild();
		listaZonas = elementoRaiz.getElementsByTagName(ETIQUETA_ZONA);
		
		for (int i = 0; i < listaZonas.getLength(); i++) {
			
			zonaActual = (Element) listaZonas.item(i);
			
			nombreSubdirector = solicitaString("Introduce el nombre del subdirector para la zona con código " + zonaActual.getAttribute(ETIQUETA_CODIGO) + ": ");               
			
			elementoSubdirector = arbol.createElement(ETIQUETA_SUBDIRECTOR);
			
			elementoSubdirector.setTextContent(nombreSubdirector);
			zonaActual.appendChild(elementoSubdirector);
			
			
		}
		
	}
	
	private static String solicitaString(String cadena) {
		String nombreSubdirector;
		System.out.print(cadena);
		nombreSubdirector = teclado.nextLine();
		
		return nombreSubdirector;
	}

	private static Document crearArbol() throws SAXException, IOException, ParserConfigurationException {
		Document arbol;
		
		arbol = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(FICHERO_ORIGINAL);
		
		return arbol;
	}

}
