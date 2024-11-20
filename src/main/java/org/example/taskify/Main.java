package org.example.taskify;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.taskify.FrontEnd.controllers.EntranceController;
import org.example.taskify.FrontEnd.controllers.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class Main extends Application {

    private static volatile ConfigurableApplicationContext appContext;

    public static void main(String[] args) {
        new Thread(() -> Application.launch(Main.class, args)).start();
        appContext = SpringApplication.run(Main.class, args);
    }

    @Override
    public void start(Stage stage) {
        var entrance = new EntranceController();
        entrance.runPage(stage);
        var timer = new PauseTransition();
        timer.setDuration(Duration.seconds(5));
        timer.setOnFinished(e -> {
            entrance.closePage();
            new MainController().runPage(stage);
        });
        timer.play();
    }

    @Override
    public void stop() {
        Platform.exit();
        while (appContext == null)
            Thread.onSpinWait();
        SpringApplication.exit(appContext, () -> 1);
    }
}
