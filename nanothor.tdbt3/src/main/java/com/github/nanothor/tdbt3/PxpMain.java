package com.github.nanothor.tdbt3;

import java.io.File;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PxpMain {
	public static void main(String[] args) throws Exception {
		JFileChooser fc = new JFileChooser(".");
		fc.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
		int res = fc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			Document document = loadXml(file);
			validateDtd(document);
			validateXsd(document);

			System.out.println(file);
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
