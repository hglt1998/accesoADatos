package ejercicio5;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

public class Principal5 {

	private static final String NOMBRE_DOCUMENTO = "resultados.xml";
	private static final String ETIQUETA_PARTIDO = "partido";
	private static final String ETIQUETA_GOLES = "goles";
	private static final String RESULTADO_EMPATE = "X";
	private static final String RESULTADO_LOCAL = "1";
	private static final String RESULTADO_VISITANTE = "2";
	private static final String TEXTO_ATRIBUTO = "ResultadoQuiniela";
	
	public static void main(String[] args) {
		
		try {
			
			//Creación de árbol DOM
			Document arbol = crearArbol();
			
			
			//Manipular el árbol
			crearAtributoQuiniela(arbol);
			
			
			//Convertir árbol a XML
			convertirArbolAXml(arbol);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static Document crearArbol() throws SAXException, IOException, ParserConfigurationException {
		Document arbol = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(NOMBRE_DOCUMENTO));
		return arbol;
	}

	private static void crearAtributoQuiniela(Document arbolDocumento) {
		Element elementoRaiz, partidoActual, elementoGolesLocal, elementosGolesVisitante;
		Node raizDocumento;
		NodeList listaPartidos, listaGoles;
		String resultadoQuiniela;
		int numeroGolesLocal, numeroGolesVisitante;
		
		raizDocumento = arbolDocumento.getFirstChild();
		elementoRaiz = (Element) raizDocumento;
		listaPartidos = elementoRaiz.getElementsByTagName(ETIQUETA_PARTIDO);
		
		for (int i = 0; i < listaPartidos.getLength(); i++) {
			
			partidoActual = (Element) listaPartidos.item(i);
			listaGoles = partidoActual.getElementsByTagName(ETIQUETA_GOLES);
			
			elementoGolesLocal = (Element) listaGoles.item(0);
			elementosGolesVisitante = (Element) listaGoles.item(1);
			
			numeroGolesLocal = Integer.parseInt(elementoGolesLocal.getTextContent().trim());
			numeroGolesVisitante = Integer.parseInt(elementosGolesVisitante.getTextContent().trim());
			
			if (numeroGolesLocal > numeroGolesVisitante) {
				resultadoQuiniela = RESULTADO_LOCAL;
			} else {
				if (numeroGolesLocal < numeroGolesVisitante) {
					resultadoQuiniela = RESULTADO_VISITANTE;
				} else {
					resultadoQuiniela = RESULTADO_EMPATE;
				}

			}
			
			partidoActual.setAttribute(TEXTO_ATRIBUTO, resultadoQuiniela);
			
		}
	}
	
	private static void convertirArbolAXml(Document arbol) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		TransformerFactory.newInstance().newTransformer().transform(new DOMSource(arbol), new StreamResult("resultadosNuevo.xml"));
	}
}