package Publisher;

import Main.Getlistofsecondaryitems;
import Createaccount.InsertCustomerdata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static Publisher.Publisher.publisherkey;

public class Publisheredit {
    @FXML
    ComboBox Location=new ComboBox();

    @FXML
    PasswordField Password=new PasswordField();
    @FXML
    Button backbutton=new Button();
    @FXML
    Button submit=new Button();
    @FXML
    Button reset=new Button();
    @FXML
    void Reset(ActionEvent event) {
        Password.clear();

    }
    @FXML
    void Submit(ActionEvent event) {

        String userpass=Password.getText();
        String locationid=null;

        try{
            locationid= Location.getValue().toString();
            locationid= InsertCustomerdata.getLocation(locationid);
            // System.out.println(locationid);
            publisherutil.setName(locationid,publisherkey,"Location_id");

        }catch (Exception e){


        }

        if(userpass.length()!=0){
            publisherutil.setName(userpass,publisherkey,"Password");
        }



    }
    @FXML
    void Backbutton(ActionEvent actionEvent){
        Stage stage;
        Parent root;
        stage = (Stage) backbutton.getScene().getWindow();
        //load up OTHER FXML document
        try {
            root = FXMLLoader.load(getClass().getResource("publisher.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Publisher Account");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        ObservableList<String> locationlist =
                FXCollections.observableArrayList();
        List<List<String>> locations= Getlistofsecondaryitems.getAllLocation();


        for(List<String> s: locations){

            for(String x:s){
                //  System.out.println(x);
                locationlist.add(x);
            }
        }
        Location.setItems(locationlist);



    }


}
