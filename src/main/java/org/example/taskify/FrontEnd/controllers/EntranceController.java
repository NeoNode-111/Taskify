package org.example.taskify.FrontEnd.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class EntranceController extends BaseController implements Initializable {
    @FXML
    private Label loadingLbl;
    @FXML
    private ImageView backgroundImg;
    @FXML
    private ImageView backgroundImg2;

    public EntranceController() {
        this.fileName = "entrance.fxml";
    }

    @Override
    protected void stageConfig(){
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingLabelAnimation();
        backgroundFadeOut(backgroundImg);
        backgroundFadeOut(backgroundImg2);
    }

    private void loadingLabelAnimation(){
        var timeLine = new Timeline();
        var keyFrame1 = new KeyFrame(Duration.millis(200), e -> loadingLbl.setText("Loading..."));
        var keyFrame2 = new KeyFrame(Duration.millis(400), e -> loadingLbl.setText("Loading.."));
        var keyFrame3 = new KeyFrame(Duration.millis(600), e -> loadingLbl.setText("Loading."));
        timeLine.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.setAutoReverse(true);
        timeLine.play();
    }

    private void backgroundFadeOut(Node node){
        var fadeOut = new FadeTransition(Duration.seconds(0.5), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.9);
        fadeOut.setAutoReverse(true);
        fadeOut.setCycleCount(FadeTransition.INDEFINITE);
        fadeOut.play();
    }
}
