//package ejercicio3	;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
//
//public class GestionContenido3 extends DefaultHandler {
//	
//	private String etiquetaActual, equipoLocal, equipoVisitante;
//	private int golesLocal, golesVisitante;
//	private boolean esLocal = true;
//
//	@Override
//	public void characters(char[] ch, int start, int lenght) throws SAXException {
//		
//		String contenido = new String(ch, start, lenght);
//		
//		switch (etiquetaActual) {
//			case "equipo":
//				System.out.println(contenido + " ");
//				
//				break;
//			case "goles":
//				golesLocal = Integer.parseInt(contenido);
//				esLocal = true;
//				
//	
//			default:
//				break;
//			}
//		
//	}
//
//	@Override
//	public void endDocument() throws SAXException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void endElement(String uri, String localName, String qName) throws SAXException {
//		this.etiquetaActual = "";
//		if (qName.equals("partido")) {
//			
//			if (golesLocal == golesVisitante) {
//				
//			}
//			
//			System.out.println();
//			
//		}
//		
//	}
//
//	@Override
//	public void startDocument() throws SAXException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void startElement(String uri, String localName, String qName, Attributes arg3) throws SAXException {
//		this.etiquetaActual = qName;
//		
//	}
//	
//}
