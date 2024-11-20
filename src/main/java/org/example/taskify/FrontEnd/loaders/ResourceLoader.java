package org.example.taskify.FrontEnd.loaders;

import javafx.scene.image.Image;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ResourceLoader {

    public static Image imageLoader(String path) {
        var executor = Executors.newSingleThreadExecutor();
        var task = new Callable<>() {
            @Override
            public Object call() throws Exception {
                return new Image(path);
            }
        };
        try {
            return (Image) executor.submit(task);
        }finally {
            executor.shutdown();
            executor.close();
        }
    }
}
