package ejercicio5;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Se dispone del fichero "resultados.xml" con los datos de los resultados del
 * partido de la ultima jornada de liga. Usando DOM y Transformer construir un
 * nuevo fichero xml llamado "resultadosNuevo.xml" donde se haga el siguiente
 * cambio sobre el fichero original:
 * 
 * - Crear un atributo en el elemento partido con el nombre resultadoQuiniela con
 * los valores "1", "X", "2"
 * 
 * @author Angel
 *
 */

public class Principal5Resuelto {

	private static final String FICHERO_RESULTADO = "resultadosNuevo.xml";
	private static final String EMPATE = "X";
	private static final String GANA_VISITANTE = "2";
	private static final String GANA_LOCAL = "1";
	private static final String ATRIBUTO_RESULTADO_QUINIELA = "resultadoQuiniela";
	private static final String FICHERO_ORIGINAL = "resultados.xml";
	private static final String ETIQUETA_GOLES = "goles";
	private static final String ETIQUETA_PARTIDO = "partido";

	public static void main(String[] args) {

	
		try {

			Document arbolDocumento = crearArbolDOM();

			crearAtributoResultado(arbolDocumento);
			
			transformarDOMEnXml(arbolDocumento);
			System.out.println("Documento " + FICHERO_RESULTADO+ " creado correctamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void crearAtributoResultado(Document arbolDocumento) {
		Element elementoRaiz,partidoActual,elementoGolesLocal,elementoGolesVisitante;
		Node raizDocumento;
		NodeList listaPartidos, listaGoles;
		String resultadoQuiniela;
		int numeroGolesLocal, numeroGolesVisitante;
		
		
		// Obtenemos todos los elementos partido del arbol.
		raizDocumento = arbolDocumento.getFirstChild();
		elementoRaiz = (Element) raizDocumento;
		listaPartidos = elementoRaiz.getElementsByTagName(ETIQUETA_PARTIDO);

		// Recorremos cada instancia de partido.
		for (int i = 0; i < listaPartidos.getLength(); i++) {
			// Realizo la conversión a Element
			partidoActual = (Element) listaPartidos.item(i);
			listaGoles = partidoActual.getElementsByTagName(ETIQUETA_GOLES);
			
			elementoGolesLocal = (Element) listaGoles.item(0);
			elementoGolesVisitante = (Element) listaGoles.item(1);
			
			// Primera forma (más corta)
			numeroGolesLocal = Integer.parseInt(elementoGolesLocal.getTextContent().trim());
			numeroGolesVisitante = Integer.parseInt(elementoGolesVisitante.getTextContent().trim());
			
			// Segunda forma
			//numeroGolesLocal = Integer.parseInt(elementoGolesLocal.getFirstChild().getNodeValue().trim());
			//numeroGolesVisitante = Integer.parseInt(elementoGolesVisitante.getFirstChild().getNodeValue().trim());
			
			
			if (numeroGolesLocal > numeroGolesVisitante) {
				resultadoQuiniela = GANA_LOCAL;
			} else {
				if (numeroGolesLocal < numeroGolesVisitante) {
					resultadoQuiniela = GANA_VISITANTE;
				} else {
					resultadoQuiniela = EMPATE;
				}
			}
			
			// Añadimos el atributo al partidoActual
			partidoActual.setAttribute(ATRIBUTO_RESULTADO_QUINIELA, resultadoQuiniela);
			
		}
	}

	private static void transformarDOMEnXml(Document arbolDocumento)
			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Source source = new DOMSource(arbolDocumento);
		Result result = new StreamResult(FICHERO_RESULTADO);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);
	}

	private static Document crearArbolDOM() throws ParserConfigurationException, SAXException, IOException {
		// Extraemos el arbol de nuestro documento XML.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document arbolDocumento = builder.parse(new File(FICHERO_ORIGINAL));
		return arbolDocumento;
	}

}