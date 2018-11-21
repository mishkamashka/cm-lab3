package se.ifmo.ru;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class InterpolationApp extends Application {

    final private static int HEIGHT = 500;
    final private static int WIDTH = 800;
    final private static NumberAxis xAxis = new NumberAxis();
    final private static NumberAxis yAxis = new NumberAxis();
    final private static LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
    private static ToggleGroup functionsGroup = new ToggleGroup();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();

        GridPane gridPane1 = new GridPane();
        gridPane1.setMinSize(WIDTH / 2.0, HEIGHT);
        gridPane1.setHgap(5);
        gridPane1.setVgap(5);
        gridPane1.setAlignment(Pos.TOP_LEFT);
        gridPane1.setPadding(new Insets(10, 0, 10, 10));
//        gridPane.setBackground(new Background(new BackgroundFill(Color.web("#EAD3A8"), CornerRadii.EMPTY, Insets.EMPTY)));

        GridPane gridPane2 = new GridPane();
        gridPane2.setMinSize(WIDTH / 2.0, HEIGHT);
        gridPane2.setHgap(5);
        gridPane2.setVgap(5);
        gridPane2.setAlignment(Pos.TOP_LEFT);
        gridPane2.setPadding(new Insets(10, 10, 10, 0));

        addFunctionsChoice(gridPane1);
//        addModelChart(gridPane2);
        addButtons(gridPane1, gridPane2);

        borderPane.setLeft(gridPane1);
        borderPane.setRight(gridPane2);

        Scene scene = new Scene(borderPane);

        primaryStage.setTitle("Interpolation App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void addFunctionsChoice(GridPane gridPane) {

        Label label = new Label("Choose a function:");
        label.setFont(new Font(15));

        RadioButton function1 = new RadioButton(Function.FIRST.toString());
        RadioButton function2 = new RadioButton(Function.SECOND.toString());
        RadioButton function3 = new RadioButton(Function.THIRD.toString());
        function1.setUserData(Function.FIRST);
        function2.setUserData(Function.SECOND);
        function3.setUserData(Function.THIRD);
        function1.setToggleGroup(functionsGroup);
        function2.setToggleGroup(functionsGroup);
        function3.setToggleGroup(functionsGroup);

        gridPane.add(label, 0, 0);
        gridPane.add(function1, 0, 1);
        gridPane.add(function2, 0, 2);
        gridPane.add(function3, 0, 3);
    }

    private static void addModelChart() {

        XYChart.Series series = new XYChart.Series();
        series.setName("Model chart");

        Toggle toggle = functionsGroup.getSelectedToggle();
        Function function = (Function) toggle.getUserData();

        //TODO: выбранная функция применяется в цикле к i


        for (double i = -5; i <= 5; i += 0.1) {
            XYChart.Data data = new XYChart.Data(i, function.calculateFunction(i));
            Rectangle rectangle = new Rectangle(0, 0);
            rectangle.setVisible(false);
            data.setNode(rectangle);
            series.getData().add(data);
        }

        lineChart.getData().add(series);
    }

    private static void addButtons(GridPane gridPane, final GridPane functionsGridPane) {
        Button okButton = new Button("Ok");

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (functionsGridPane.getChildren().size() != 0) {
                    LineChart<Number, Number> lineChart = (LineChart<Number, Number>) functionsGridPane.getChildren().get(0);
                    if (lineChart.getData().size() > 1)
                        lineChart.getData().remove(1);
                    lineChart.getData().remove(0);
                }
                else
                    functionsGridPane.add(lineChart, 0, 0);
                addModelChart();
            }
        });
        gridPane.add(okButton, 1, 4);
    }

}
