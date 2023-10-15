package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.Map;

public class DiskAnalyzerApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Disk Analyzer");
        StackPane layout = new StackPane();
        Scene one = new Scene(layout, 300, 300);
        stage.setScene(one);
        Button button1 = new Button("Open Directory");
        layout.getChildren().add(button1);

        button1.setOnMouseClicked(mouseEvent -> {
            var chooser = new DirectoryChooser();
            File selectedDirectory = chooser.showDialog(stage);
            var scanner = new DiskScanner();
            Map<String, Long> scanned = scanner.scan(selectedDirectory);
            BorderPane border = new BorderPane();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (Map.Entry<String, Long> entry: scanned.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

            border.setCenter(new PieChart(pieChartData));
            Scene two = new Scene(border, 700, 700);
            stage.setScene(two);
        });
        System.out.println("An improvment");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
