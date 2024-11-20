package org.example.taskify.FrontEnd.controllers;

import javafx.fxml.Initializable;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController implements Initializable {

    public MainController() {
        this.fileName = "main.fxml";
    }

    @Override
    protected void stageConfig(){
        primaryStage.setTitle("Taskify");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setMaximized(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
