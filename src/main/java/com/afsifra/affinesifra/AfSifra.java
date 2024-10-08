package com.afsifra.affinesifra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AfSifra extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxml = new FXMLLoader(AfSifra.class.getResource("gui_f_konstiak.fxml"));
        Scene scene = new Scene(fxml.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Affini Sifra Konstiak");
        stage.centerOnScreen();
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

