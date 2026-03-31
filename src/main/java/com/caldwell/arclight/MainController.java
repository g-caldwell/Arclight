package com.caldwell.arclight;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: MainController.java                                                          //
// Description: Controller for main.fxml                                              //
// ********************************************************************************** //

public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text journalText;
    @FXML
    private Text managerText;
    @FXML
    private Button managerButton;
    @FXML
    private Button journalButton;
    @FXML
    private AnchorPane mainMenuAnchorPane;
    @FXML
    private ImageView mainMenuImage;

    // initializer for positions and music
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initializing menu...");
        // manager text animation
        FadeTransition managerFade = new FadeTransition();
        managerFade.setNode(managerText);
        managerFade.setFromValue(0);
        managerFade.setToValue(1);
        managerFade.setDuration(Duration.millis(4000));
        managerFade.play();
        TranslateTransition managerTranslate = new TranslateTransition();
        managerTranslate.setNode(managerText);
        managerTranslate.setByY(3);
        managerTranslate.setAutoReverse(true);
        managerTranslate.setDuration(Duration.millis(1000));
        managerTranslate.setCycleCount(Animation.INDEFINITE);
        managerTranslate.play();
        RotateTransition managerRotate = new RotateTransition();
        managerRotate.setNode(managerText);
        managerRotate.setByAngle(1);
        managerRotate.setAutoReverse(true);
        managerRotate.setInterpolator(Interpolator.LINEAR);
        managerRotate.setDuration(Duration.millis(1000));
        managerRotate.setCycleCount(Animation.INDEFINITE);
        managerRotate.play();

        // journal text animation
        FadeTransition journalFade = new FadeTransition();
        journalFade.setNode(journalText);
        journalFade.setFromValue(0);
        journalFade.setToValue(1);
        journalFade.setDuration(Duration.millis(4000));
        journalFade.play();
        TranslateTransition journalTranslate = new TranslateTransition();
        journalTranslate.setNode(journalText);
        journalTranslate.setByY(-3);
        journalTranslate.setAutoReverse(true);
        journalTranslate.setDuration(Duration.millis(1000));
        journalTranslate.setCycleCount(Animation.INDEFINITE);
        journalTranslate.play();
        RotateTransition journalRotate = new RotateTransition();
        journalRotate.setNode(journalText);
        journalRotate.setByAngle(-1);
        journalRotate.setAutoReverse(true);
        journalRotate.setInterpolator(Interpolator.LINEAR);
        journalRotate.setCycleCount(Animation.INDEFINITE);
        journalRotate.setDuration(Duration.millis(1000));
        journalRotate.play();
    }

    // switch to manager view
    @FXML
    public void onManagerButton(ActionEvent event) throws IOException {
        System.out.println("switching to manager mode...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1125, 632);
        stage.setScene(scene);
        stage.show();
    }

    // switch to journal view
    @FXML
    public void onJournalButton(ActionEvent event) throws IOException {
        System.out.println("switching to journal mode");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("journal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1125, 632);
        stage.setScene(scene);
        stage.show();
    }
}