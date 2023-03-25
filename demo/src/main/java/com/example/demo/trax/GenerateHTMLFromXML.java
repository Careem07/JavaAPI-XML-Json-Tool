package com.example.demo.trax;

import com.example.demo.HelloApplication;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class GenerateHTMLFromXML {

    public void generatingHTMLFromXML() throws Exception {
        // Data
        File xmlFile = new File(HelloApplication.class.getResource("restaurants.xml").toURI());
        // Output
        File htmlFile = new File("restaurant.html");
        // Transformation Instructions
        File xsltFile = new File(HelloApplication.class.getResource("restaurants.xslt").toURI());

        generateHTMLFromXMLBasedOnXslt(htmlFile, xmlFile, xsltFile);
    }

    private void generateHTMLFromXMLBasedOnXslt(File htmlFile, File xmlFile, File xsltFile)
            throws IOException, SAXException, ParserConfigurationException, TransformerException {

        // Source of Data: XML File
        Document xmlDocument = createDocumentFromFile(xmlFile);
        DOMSource xmlDomSource = new DOMSource(xmlDocument);

        // Result of Transformation: HTML File
        StreamResult streamResult = new StreamResult(htmlFile);

        // Transformation Instructions defined in: XSLT File
        Document xsltDocument = createNSAwareDocumentFromFile(xsltFile);
        DOMSource xsltDomSource = new DOMSource(xsltDocument);

        // apply transformations
        transformation(xsltDomSource, xmlDomSource, streamResult);
    }

    private void transformation(DOMSource xsltDomSource, DOMSource xmlDomSource, StreamResult streamResult)
            throws TransformerException {
        // transformation
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        // load transformations instructions from the xslt file
        Transformer transformer = transformerFactory.newTransformer(xsltDomSource);
        // set transformer properties
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // perform transformation
        transformer.transform(xmlDomSource, streamResult);
    }

    private Document createDocumentFromFile(File xmlFile)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlFile);
    }

    private Document createNSAwareDocumentFromFile(File xmlFile)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlFile);
    }

}

/*
 *
 * Transformer
 *
 * XML-File HTML-File
 * <DOMSource> <StreamResult>
 *
 * Source =====> (Transformer) =====> Result
 * ^
 * |
 * Source
 *
 * <DOMSource>
 * XSLT-File
 *
 *
 *
 *
 * XSLT-File is in fact an XML File
 * XSLT-File uses Namespace: xsl
 *
 */
