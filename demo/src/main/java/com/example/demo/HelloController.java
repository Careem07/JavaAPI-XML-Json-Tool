package com.example.demo;

import com.example.demo.toJSON.CreateJson;
import com.example.demo.trax.GenerateHTMLFromXML;
import com.example.demo.trax.SaveDOMWithTrax;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class HelloController implements Initializable {

    @FXML
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private CheckBox checkBox4;

    @FXML
    private CheckBox checkBox5;

    @FXML
    private TableColumn<Restaurant, String> col1;

    @FXML
    private TableColumn<Restaurant, String> col2;

    @FXML
    private TableColumn<Restaurant, String> col3;

    @FXML
    private TableColumn<Restaurant, String> col4;

    @FXML
    private TableColumn<Restaurant, String> col5;

    @FXML
    private Button filterBtn;

    @FXML
    private Button htmlBtn;

    @FXML
    private TableView<Restaurant> table;

    @FXML
    private Button xmlBtn;

    ObservableList<Restaurant> restaurantList = FXCollections.observableArrayList();
    ObservableList<Restaurant> filterRestuarents = FXCollections.observableArrayList();
    RetrieveRestaurants retrieveRestaurants = new RetrieveRestaurants();
    List<Restaurant> Allrestuarents = new ArrayList<>();
    List<Restaurant> filteredRest = new ArrayList<>();

    RestaurantHandler hander ;

    private List<String> columns = new ArrayList<>();

    @FXML
    void onFllter(ActionEvent event) throws Exception{

         hander = new RestaurantHandler(checkBox1.isSelected(), checkBox2.isSelected(),
         checkBox3.isSelected(), checkBox4.isSelected(), checkBox5.isSelected());
         Allrestuarents =  retrieveRestaurants.readingWithSax(hander);
         filterRestuarents.setAll(Allrestuarents);
        table.getColumns().clear();
        if (checkBox1.isSelected()) {

            col1 = new TableColumn<>("Name");
            col1.setCellValueFactory(new PropertyValueFactory<>("name"));
            table.getColumns().add(col1);
            // TablePosition<Restaurant,String> pos =  table.getSelectionModel().getSelectedCells();
            // int row = pos.getRow();
            // Restaurant restaurant = table.getItems().get(1);
            // String restName = col1.getCellData(restaurant);
            // System.out.println("value " + restName);
            columns.add("name");
            
        }
        if (checkBox2.isSelected()){
            col2 = new TableColumn<>("Location");
            col2.setCellValueFactory(new PropertyValueFactory<>("location"));
            table.getColumns().add(col2);
            columns.add("location");
            

        }
        if (checkBox3.isSelected()){
            col3 = new TableColumn<>("Phone");
            col3.setCellValueFactory(new PropertyValueFactory<>("phone"));
            table.getColumns().add(col3);
            
            columns.add("phone");
        }
        if (checkBox4.isSelected()){
            col4 = new TableColumn<>("Email");
            col4.setCellValueFactory(new PropertyValueFactory<>("email"));
            table.getColumns().add(col4);
            columns.add("email");
        }
        if (checkBox5.isSelected()){
            col5 = new TableColumn<>("Rate");
            col5.setCellValueFactory(new PropertyValueFactory<>("rate"));
            table.getColumns().add(col5);
            columns.add("rate");
        }


        table.setItems(filterRestuarents);
    }

    @FXML
    void onHTML(ActionEvent event) {
        GenerateHTMLFromXML fromXML = new GenerateHTMLFromXML();
        try {
            fromXML.generatingHTMLFromXML();
            System.out.println("HTML created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onXML(ActionEvent event) {
        SaveDOMWithTrax domWithTrAX = new SaveDOMWithTrax();
        System.out.println("here");
        try {
            domWithTrAX.savingDomWithTrax(filterRestuarents,columns);
            System.out.println("XML created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void toJson(ActionEvent event) throws IOException {
        CreateJson js = new CreateJson();
        js.createJsonFromTable(filterRestuarents.stream().toList());
    }

    @FXML
    void onUpload(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
             hander = new RestaurantHandler(checkBox1.isSelected(), checkBox2.isSelected(),
            checkBox3.isSelected(), checkBox4.isSelected(), checkBox5.isSelected());
            Allrestuarents =  retrieveRestaurants.readingWithSax(hander);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
