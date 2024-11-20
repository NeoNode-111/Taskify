package org.example.taskify.FrontEnd.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.taskify.FrontEnd.loaders.PageLoader;

public class BaseController {

    protected final Stage primaryStage = new Stage();

    protected String fileName;

    public BaseController() {
        this.fileName = "";
    }

    public void runPage(Stage stage){
        if (stage.isShowing())
            stage.close();
        if (stage.getScene() == null)
            stage.setScene(new Scene(PageLoader.load(fileName)));
        stage.getScene().setRoot(PageLoader.load(fileName));
        primaryStage.setScene(stage.getScene());
        stageConfig();
        primaryStage.show();
    }

    protected void stageConfig(){
    }

    public void closePage(){
        primaryStage.close();
    }

}
