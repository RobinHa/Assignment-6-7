package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Functionality functionality = new Functionality();
        ViewImplementer viewImplementer = new ViewImplementer(functionality);

        ViewDrawer drawer = new ViewDrawer(functionality, viewImplementer);

        Scene scene = new Scene(viewImplementer, 700, 500);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("theme.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("RNA 2D Viewer");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
