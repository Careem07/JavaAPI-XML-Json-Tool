package com.example.demo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class RestaurantHandler extends DefaultHandler {

    private ArrayList<Restaurant> restaurants;
    private Restaurant restaurant;
    private String currentElement = "";
    private StringBuilder currentText;

    boolean nameCheck;
    boolean locationCheck;
    boolean phoneCheck;
    boolean emailCheck;
    boolean rateCheck;


    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }


    

    public RestaurantHandler(boolean nameCheck, boolean locationCheck, boolean phoneCheck, boolean emailCheck,
            boolean rateCheck) {
        this.nameCheck = nameCheck;
        this.locationCheck = locationCheck;
        this.phoneCheck = phoneCheck;
        this.emailCheck = emailCheck;
        this.rateCheck = rateCheck;
    }




    @Override
    public void startDocument() throws SAXException {
        restaurants = new ArrayList<Restaurant>();
//        restaurant = new Restaurant();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("Start Element: " + qName);
        currentElement = qName;
        switch (currentElement){
            case "restaurants" :
                break;
            case  "restaurant" :
                restaurant = new Restaurant();
                break;
            default :  currentText = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("restaurants")) {
            return;
        }

        if (qName.equals("restaurant")) {
            restaurants.add(restaurant);
            return;
        }

        String content = currentText.toString();

        switch (currentElement){
            case "name" : 
                if(nameCheck)
                    restaurant.setName(content);
            case "location" :
                if(locationCheck)
                    restaurant.setLocation(content);
            case "phone" : 
                if(phoneCheck)
                    restaurant.setPhone(content);
            case "email" : 
                if(emailCheck)
                    restaurant.setEmail(content);
            case "rate" : 
                if(rateCheck)
                    restaurant.setRate(content);
        }

        currentElement ="";

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentText != null) {
            currentText.append(ch, start, length);
        }
    }


//    Error Handling

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Oooops! some Warning");
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("didn't see that one coming! some Error");
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("OMG! some Fatal Error");
        super.fatalError(e);
    }

}
