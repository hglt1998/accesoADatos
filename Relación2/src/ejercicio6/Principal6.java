package ejercicio6;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Principal6 {

	private static final String DOCUMENTO_ORIGINAL = "universidad.xml";
	private static final String ETIQUETA_EMPLEADO = "empleado";
	private static final String ETIQUETA_NOMBRE = "nombre";
	private static final String SEPARADOR_ESPACIO = " ";
	private static final String ETIQUETA_APELLIDO = "apellido";
	
	public static void main(String[] args) {
		
		try {
			
			//Creación del árbol
			Document arbol = crearArbol();
			
			//Modificación del árbol
			modificarArbol(arbol);
			
			//Fichero de salida
			Source source = new DOMSource(arbol);
			Result result = new StreamResult("nuevoUniversidad.xml");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.transform(source, result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Document crearArbol() throws SAXException, IOException, ParserConfigurationException {
		Document arbol;
		
		arbol = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(DOCUMENTO_ORIGINAL);
		
		return arbol;
	}
	
	private static void modificarArbol(Document arbol) {
		Element elementoRaiz, empleadoActual, elementoNombre, elementoApellido;
		NodeList listaEmpleados;
		String nombreCompleto, nombre, apellido;
		
		elementoRaiz = (Element) arbol.getFirstChild();
		listaEmpleados = elementoRaiz.getElementsByTagName(ETIQUETA_EMPLEADO);
		
		for (int i = 0; i < listaEmpleados.getLength(); i++) {
			empleadoActual = (Element) listaEmpleados.item(i);
			
			nombreCompleto = empleadoActual.getElementsByTagName(ETIQUETA_NOMBRE).item(0).getTextContent();
			
			apellido = nombreCompleto.substring(nombreCompleto.lastIndexOf(SEPARADOR_ESPACIO) + 1, nombreCompleto.length());
			nombre = nombreCompleto.substring(0, nombreCompleto.lastIndexOf(SEPARADOR_ESPACIO));
			
			elementoNombre = (Element) empleadoActual.getElementsByTagName(ETIQUETA_NOMBRE).item(0);
			
			empleadoActual.removeChild(elementoNombre);
			
			elementoApellido = arbol.createElement(ETIQUETA_APELLIDO);
			elementoApellido.setTextContent(apellido);
			elementoNombre = arbol.createElement(ETIQUETA_NOMBRE);
			elementoNombre.setTextContent(nombre);
			
			empleadoActual.appendChild(elementoNombre);
			
			empleadoActual.appendChild(elementoApellido);
			
		}
		
	}

}






