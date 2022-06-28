package mostrar;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CreacionXML {
	public int escribirXml(ArrayList<String> datos) {
		int cogido = 0;
		try {
			 
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("Blogs");
            document.appendChild(root);
            
            crearElemento("Titulo", datos.get(0), root, document);
			crearElemento("Fecha", datos.get(1), root, document);
			crearElemento("nombre_archivo", datos.get(2), root, document);
			crearElemento("ImagenPrincipal", datos.get(3), root, document);
			crearElemento("Categoria", datos.get(4), root, document);
			crearElemento("Editor", datos.get(5), root, document);
			crearElemento("LineasCortas", datos.get(6), root, document);
			crearElemento("Resumen", datos.get(7), root, document);
			crearElemento("Texto", datos.get(8), root, document);
 
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(datos.get(2)));
            transformer.transform(domSource, streamResult);
            cogido = streamResult.hashCode();
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
		return cogido;
	}
	private static void crearElemento(String datoPeli, String valor, Element raiz, Document documento)
	{
		Element elem = documento.createElement(datoPeli);
		Text text = documento.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
}
