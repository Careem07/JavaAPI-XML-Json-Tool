package com.example.demo;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class RetrieveRestaurants {

    public List<Restaurant> readingWithSax(RestaurantHandler handler) throws Exception {
        RestaurantHandler studentsHandler = handler;
        File xmlFile = new File(HelloApplication.class.getResource("data.xml").toURI());

        System.out.println("hi");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(xmlFile, studentsHandler);

        List<Restaurant> list = studentsHandler.getRestaurants();
        System.out.println("Number of Students: " + list.size());
        for (Restaurant student : list) {
            System.out.println(student);

        }
        return list;
    }
}
