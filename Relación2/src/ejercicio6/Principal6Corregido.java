package ejercicio6;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class Principal6Corregido {

	public static void main(String[] args) {
		
	
	
		try{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();  // crea un objeto builder que permite crear el arbol DOM
			Document arbol=builder.parse(new File("universidad.xml")); // crea el arbol DOM a partir del fichero
			
			Document arbolReducido = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("universidad.xml"));
			System.out.println(arbolReducido);
			
			separarApellidosYNombre(arbol);
			

			Source source = new DOMSource(arbol);
			Result result = new StreamResult("universidad.xml");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			
			transformer.transform(source, result);
			System.out.println("Proceso terminado correctamente");
			
		}catch(Exception e){
			System.out.println("Error " + e.getMessage());
		}

	}

	private static void separarApellidosYNombre(Document arbol) throws IOException {
		Element elementoEmpleado, elementoNombre, elementoApellido;
		String nombreCompleto, soloNombre, soloApellido;
		
		
		Element raiz=(Element) arbol.getFirstChild(); // obtiene el primer nodo, raiz del arbol
		
		NodeList listaNodos=raiz.getElementsByTagName("empleado"); // obtiene una lista de nodos hijo del raiz
		
		
		for(int i=0; i<listaNodos.getLength(); i++){ // recorremos la lista de nodos
			
			elementoEmpleado=(Element) listaNodos.item(i); // obtiene el nodo i-esimo de la lista
		
			elementoNombre=(Element) elementoEmpleado.getElementsByTagName("nombre").item(0);
			
			nombreCompleto=elementoNombre.getTextContent();
			
			soloNombre=obtenerNombre(nombreCompleto);
			soloApellido=obtenerApellido(nombreCompleto);
			
			
			elementoNombre.setTextContent(soloNombre);
			elementoApellido=arbol.createElement("apellido");
			elementoApellido.setTextContent(soloApellido);
			
			elementoEmpleado.appendChild(elementoApellido);

			
		}
	}

	private static String obtenerNombre(String nombreCompleto) {
		
		return nombreCompleto.substring(0, nombreCompleto.lastIndexOf(' '));
	}

	private static String obtenerApellido(String nombreCompleto) {
	
		return nombreCompleto.substring(nombreCompleto.lastIndexOf(' ')+ 1);
	}

	

}
