package com.caldwell.arclight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: Main.java                                                                    //
// Description: Class containing main method for running the program                  //
// ********************************************************************************** //

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // initialize main menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1125, 632);

        // icons and images
        Image icon = new Image("arclightIcon.png");

        // main menu setup
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.setTitle("Project Arclight");
        stage.setX(397.5);
        stage.setY(224);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}