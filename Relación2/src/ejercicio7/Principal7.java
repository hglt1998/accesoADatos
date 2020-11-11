package ejercicio7;

import java.io.IOException;

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


public class Principal7 {
	
	private static final double PORCENTAJE_SUBIDA = 0.05;
	private static final String DOCUMENTO_ORIGINAL = "universidad.xml";
	private static final String ETIQUETA_EMPLEADO = "empleado";
	private static final String ETIQUETA_SUELDO = "sueldo";
	private static final String ETIQUETA_PUESTO = "puesto";
	private static final String ETIQUETA_SALARIO = "salario";
	private static final String ETIQUETA_DEPARTAMENTO = "departamento";

	public static void main(String[] args) {
		
		try {
			
			Document arbol = crearArbol();
			
			modificarArbol(arbol);
			
			Source source = new DOMSource(arbol);
			Result result = new StreamResult("universidadActualizado.xml");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.transform(source, result);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void modificarArbol(Document arbol) {
		
		Element elementoRaiz, empleadoActual, elementoSalario, elementoPuesto, departamentoActual;
		NodeList listaEmpleados, listaPuestos, listaDepartamentos;
		double salarioActual, nuevoSalario;
		String valorSalario;
		
		elementoRaiz = (Element) arbol.getFirstChild();
		listaEmpleados = elementoRaiz.getElementsByTagName(ETIQUETA_EMPLEADO);
		listaDepartamentos = elementoRaiz.getElementsByTagName(ETIQUETA_DEPARTAMENTO);
		
		for (int j = 0; j < listaDepartamentos.getLength(); j++) {
			
			departamentoActual = (Element) listaDepartamentos.item(j);
			
			for (int i = 0; i < listaEmpleados.getLength(); i++) {
				empleadoActual = (Element) listaEmpleados.item(i);
				listaPuestos = departamentoActual.getElementsByTagName(ETIQUETA_PUESTO);
				elementoPuesto = (Element) listaPuestos.item(0);
				
				if (elementoPuesto.getTextContent().equals("asociado")) {
					
					salarioActual = Double.parseDouble(empleadoActual.getAttribute(ETIQUETA_SALARIO));
					nuevoSalario = salarioActual + salarioActual * PORCENTAJE_SUBIDA;
					valorSalario = String.valueOf(nuevoSalario);
					
					elementoSalario = arbol.createElement(ETIQUETA_SUELDO);
					elementoSalario.setTextContent(valorSalario);
					empleadoActual.appendChild(elementoSalario);
					empleadoActual.removeAttribute(ETIQUETA_SALARIO);
					
				}
			}
		}		
	}

	private static Document crearArbol() throws SAXException, IOException, ParserConfigurationException{
		Document arbol = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(DOCUMENTO_ORIGINAL);
		
		return arbol;
	}
}






















