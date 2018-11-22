package se.ifmo.ru;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class InterpolationApp extends Application {

    final private static int HEIGHT = 500;
    final private static int WIDTH = 1000;
    final private static NumberAxis xAxis = new NumberAxis();
    final private static NumberAxis yAxis = new NumberAxis();
    final private static LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
    private static ToggleGroup functionsGroup = new ToggleGroup();
    private static ToggleGroup testSetsGroup = new ToggleGroup();
    private static TextField textField = new TextField();
    private static Label xLabel = new Label();

    private static Double[] xArray;
    private static Double[] yArray;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setHgap(0);
        pane.setPrefSize(WIDTH, HEIGHT);

        GridPane gridPane1 = new GridPane();
        gridPane1.setHgap(0);
        gridPane1.setVgap(5);
        gridPane1.setAlignment(Pos.TOP_LEFT);
        gridPane1.setPadding(new Insets(10, 0, 10, 10));

        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);
        gridPane2.setAlignment(Pos.TOP_RIGHT);
        gridPane2.setPadding(new Insets(10, 10, 10, 0));

        lineChart.setLegendVisible(false);

        addFunctionsChoice(gridPane1);
        addTestSetChoice(gridPane1);

        addButtons(gridPane1, gridPane2);

        pane.add(gridPane1, 0, 1);
        pane.add(gridPane2, 1, 1);

        Scene scene = new Scene(pane);

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

    private static void addTestSetChoice(GridPane gridPane) {

        Label label = new Label("Choose a test set of points:");
        label.setFont(new Font(15));

        RadioButton testSet1 = new RadioButton("First set [0, 2pi] step = pi / 2");
        RadioButton testSet2 = new RadioButton("Second set [0, 2pi] step = pi / 4");
        RadioButton testSet3 = new RadioButton("Third set [0, 2pi] step = pi / 4, one Y is wrong");
        testSet1.setUserData(TestSet.FIRST);
        testSet2.setUserData(TestSet.SECOND);
        testSet3.setUserData(TestSet.THIRD);
        testSet1.setToggleGroup(testSetsGroup);
        testSet2.setToggleGroup(testSetsGroup);
        testSet3.setToggleGroup(testSetsGroup);

        gridPane.add(label, 0, 5);
        gridPane.add(testSet1, 0, 6);
        gridPane.add(testSet2, 0, 7);
        gridPane.add(testSet3, 0, 8);
    }

    private static void addXTextField(GridPane gridPane) {

        xLabel.setText("Enter X:");
        xLabel.setFont(new Font(15));

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        textField.setTextFormatter(formatter);

        gridPane.getChildren().remove(xLabel);
        gridPane.add(xLabel, 0, 10);

        gridPane.getChildren().remove(textField);
        gridPane.add(textField, 0, 11);

        Button checkButton = new Button("Find Y");
        checkButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x;
                x = Double.parseDouble(textField.textProperty().getValue());

                if (x > 10)
                    return;
                XYChart.Series series = new XYChart.Series();
                XYChart.Data data = new XYChart.Data(x, CubicSplineInterpolation.getInterpolatedFunctionY(x));
                series.getData().add(data);

                final Label label = new Label("Y = " + CubicSplineInterpolation.getInterpolatedFunctionY(x));
                label.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                label.setFont(new Font(15));
                gridPane.add(label, 0, 12);

                lineChart.getData().add(series);
            }
        });
        gridPane.add(checkButton, 1, 12);
    }

    private static void addModelChart(Function function) {

        XYChart.Series series = new XYChart.Series();
        series.setName("Model chart");

        for (double i = 0; i <= 10; i += 0.1) {
            XYChart.Data data = new XYChart.Data(i, function.calculateFunction(i));
            Rectangle rectangle = new Rectangle(0, 0);
            rectangle.setVisible(false);
            data.setNode(rectangle);
            series.getData().add(data);
        }

        lineChart.getData().add(series);
    }

    private static void addInterpolatedChart() {

        XYChart.Series series = new XYChart.Series();
        series.setName("Interpolated chart");

        for (double i = 0; i <= 10; i += 0.1) {
            double x = i;
            double y = CubicSplineInterpolation.getInterpolatedFunctionY(i);

            XYChart.Data data = new XYChart.Data(x, y);
            if (!isInArray(x, xArray)) {
                Rectangle rectangle = new Rectangle(0, 0);
                rectangle.setVisible(false);
                data.setNode(rectangle);
            }
            series.getData().add(data);
        }

        lineChart.getData().add(series);
    }

    private static void addButtons(GridPane gridPane, final GridPane functionsGridPane) {
        Button okButton = new Button("Ok");

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                TestSet testSet;
                Toggle toggle = testSetsGroup.getSelectedToggle();
                try {
                    testSet = (TestSet) toggle.getUserData();
                } catch (NullPointerException e) {
                    return;
                }

                Function function;
                Toggle toggle1 = functionsGroup.getSelectedToggle();
                try {
                    function = (Function) toggle1.getUserData();
                } catch (NullPointerException e) {
                    return;
                }

                testSet.setFunction(function);
                int n = testSet.setXTestSet().size();
                xArray = new Double[n];
                yArray = new Double[n];
                xArray = testSet.setXTestSet().toArray(xArray);
                yArray = testSet.setYTestSet().toArray(yArray);
                CubicSplineInterpolation.createSplines(xArray, yArray, xArray.length);

                if (functionsGridPane.getChildren().size() != 0) {
                    LineChart<Number, Number> lineChart = (LineChart<Number, Number>) functionsGridPane.getChildren().get(0);
                    while (lineChart.getData().size() > 0)
                        lineChart.getData().remove(0);
                } else
                    functionsGridPane.add(lineChart, 0, 0);
                addModelChart(function);
                addInterpolatedChart();
                addXTextField(gridPane);
            }
        });
        gridPane.add(okButton, 1, 9);
    }

    private static boolean isInArray(double x, Double[] xArray) {
        int i = 0;
        while (i < xArray.length) {
            if (Math.abs(xArray[i] - x) < 0.05)
                return true;
            i++;
        }
        return false;
    }

}
