package com.example.demo.trax;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.example.demo.Restaurant;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class SaveDOMWithTrax {

        public void savingDomWithTrax(List<Restaurant> restaurants, List<String> columns) throws Exception {
            Document document = createEmptyDocument();
            populateDocumentWithData(document, restaurants, columns);
            saveDomAsXmlFile(document);
        }

        private Document createEmptyDocument() throws ParserConfigurationException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.newDocument();
        }

        private void populateDocumentWithData(Document document, List<Restaurant> restaurants, List<String> columns) {

            Element messages = document.createElement("restaurants");

            restaurants.forEach(restaurant -> {
                Element message1 = createRestaurent(document, columns, restaurant.getName(), restaurant.getLocation(), restaurant.getEmail(),
                        restaurant.getPhone(),
                        restaurant.getRate());
                messages.appendChild(message1);
            });

            document.appendChild(messages);
        }

        private Element createRestaurent(Document document, List<String> columns, String name, String location, String email,
                                         String phone, String rate) {

            Element messageElement = document.createElement("restaurants");

            if (columns.contains("name")) {
                Element typeElement = document.createElement("name");
                typeElement.setTextContent(name);
                messageElement.appendChild(typeElement);
            }

            if (columns.contains("location")) {
                Element colorElement = document.createElement("location");
                colorElement.setTextContent(location);
                messageElement.appendChild(colorElement);
            }

            if (columns.contains("email")) {
                Element modelElement = document.createElement("email");
                modelElement.setTextContent(email);
                messageElement.appendChild(modelElement);
            }

            if (columns.contains("phone")) {
                Element priceElement = document.createElement("phone");
                priceElement.setTextContent(phone);
                messageElement.appendChild(priceElement);
            }

            if (columns.contains("rate")) {
                Element bodyElement = document.createElement("rate");
                bodyElement.setTextContent(rate);
                messageElement.appendChild(bodyElement);
            }

            return messageElement;

            // Alternative to: setting attribute

            // Attr dateAttr = document.createAttribute("date");
            // dateAttr.setValue(LocalDate.now().toString());
            // messageElement.setAttributeNode(dateAttr);

        }

        private void saveDomAsXmlFile(Document document) throws TransformerException {
            // Source
            DOMSource domSource = new DOMSource(document);
            // Result
            File outputXmlFile = new File("restaurants.xml");
            StreamResult streamResult = new StreamResult(outputXmlFile);
            // Simple pass through transformer (i.e without transformation instructions)

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);
        }

    }


