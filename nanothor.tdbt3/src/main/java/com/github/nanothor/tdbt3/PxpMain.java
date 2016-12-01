package com.github.nanothor.tdbt3;

import java.io.File;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class PxpMain {
	public static void main(String[] args) throws Exception {
		JFileChooser fc = new JFileChooser(".");
		fc.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
		int res = fc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			Document document = loadXml(file);
			// validateDtd(document);
			validateXsd(document);

			System.out.println(file);

			// // Use a Transformer for output
			// TransformerFactory tFactory = TransformerFactory.newInstance();
			// Transformer transformer = tFactory.newTransformer();
			//
			// DOMSource source = new DOMSource(document);
			// StreamResult result = new StreamResult(System.out);
			// transformer.transform(source, result);
		}
	}

	private static void validateDtd(Document document) {

	}

	private static void validateXsd(Document document) throws Exception {
		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		URL su = PxpMain.class.getResource("/xml/db.xsd");
		assert su != null;
		Schema schema = factory.newSchema(su);
		Validator validator = schema.newValidator();
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("tbd:alunos/tbd:aluno");
		NodeList nl = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nl.getLength(); i++) {
			System.out.println(nl.item(i));
		}
		
		System.out.println(nl.getLength());
		
		validator.validate(new DOMSource(document));
	}

	private static Document loadXml(File file) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(file);

		return doc;
	}
}
