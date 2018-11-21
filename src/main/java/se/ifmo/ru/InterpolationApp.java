package se.ifmo.ru;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Hello world!
 *
 */
public class InterpolationApp extends Application {

    final private static int HEIGHT = 500;
    final private static int WIDTH = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();


        Label label = new Label("Hi! Live in this, man...");
        label.setFont(new Font(35));


        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setFill(Color.AQUAMARINE);

        primaryStage.setTitle("Interpolation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
