package ejercicio6;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
		Element elementoRaiz, empleadoActual, elementoNombre;
		NodeList listaEmpleados, nombreEmpleado;
		String nombreCompleto, nombre, apellido;
		String[] arrayNombreCompleto;
		
		elementoRaiz = (Element) arbol.getFirstChild();
		listaEmpleados = elementoRaiz.getElementsByTagName(ETIQUETA_EMPLEADO);
		
		for (int i = 0; i < listaEmpleados.getLength(); i++) {
			empleadoActual = (Element) listaEmpleados.item(i);
			
			nombreEmpleado = empleadoActual.getElementsByTagName(ETIQUETA_NOMBRE);
			nombreCompleto = nombreEmpleado.item(0).getNodeValue();
			
			arrayNombreCompleto = nombreCompleto.split(SEPARADOR_ESPACIO);
			apellido = arrayNombreCompleto[arrayNombreCompleto.length];
			nombre = nombreCompleto.substring(0, nombreCompleto.indexOf(apellido.charAt(0)));
			
			elementoNombre = (Element) empleadoActual.getElementsByTagName(ETIQUETA_NOMBRE).item(0);
			
			empleadoActual.removeChild(elementoNombre);
			
			empleadoActual.setAttribute(ETIQUETA_NOMBRE, nombre);
			empleadoActual.setAttribute(ETIQUETA_APELLIDO, apellido);
			
		}
		
	}

}






