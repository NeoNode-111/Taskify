package org.example.taskify.FrontEnd.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class PageLoader {

    private static final String path = "/fxmlStructure/";

    private static final HashMap<String, Parent>roots = new HashMap<>();

    public static Parent load(String fileName){
        if (roots.containsKey(fileName))
            return roots.get(fileName);
        var executor = Executors.newSingleThreadExecutor();
        var task = new Callable<FXMLLoader>() {
            @Override
            public FXMLLoader call() throws Exception {
                return new FXMLLoader(getClass().getResource(path + fileName));
            }
        };
        var future = executor.submit(task);
        try {
            var root = future.get().load();
            roots.put(fileName, (Parent) root);
            return (Parent) root;
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdownNow();
            executor.close();
        }
    }

}
