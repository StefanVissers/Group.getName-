package com.example;

import Data.Queries;
import Data.builder.ChartData;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

/**
 * Created by floris-jan on 04-07-16.
 */
public class BarChart {

    static VBox sceneView;
    static javafx.scene.chart.BarChart barChart;
    static CategoryAxis XAxis;
    static NumberAxis YAxis;

    public static VBox getScene(String... args) {
        sceneView = new VBox();
        XAxis = new CategoryAxis();
        YAxis = new NumberAxis();
        sceneView.setPadding(new Insets(10, 10, 10, 10));
        sceneView.setPrefSize(Main.width, Main.height);
        barChart = new javafx.scene.chart.BarChart(XAxis, YAxis);

        ChartData cd;
        ChartData.ChartDataBuilder builder = null;
        for (String s : args) {
            if (s.startsWith("sql:")) {
                String[] sArray = s.split(": ");
                builder = new ChartData.ChartDataBuilder(sArray[1]);
            }
        }
        if (builder == null) {
            throw new NullPointerException("No Query found");
        }
        for (String s : args) {
            String[] sArray = s.split(": ");
            String type = sArray[0];
            String value = sArray[1];
            if (type.equalsIgnoreCase("title")) {
                builder.setNestedTitle(value);
            } else if (type.equalsIgnoreCase("desc")) {
                builder.setNestedDesc(value);
            } else if (type.equalsIgnoreCase("color")) {
                builder.setNestedColor(value);
            } else if (type.equalsIgnoreCase("filter")) {
                builder.setNestedFilter(value);
            } else if (type.equalsIgnoreCase("reltime")) {
                builder.setNestedRelativeTime(Queries.RelativeTime.valueOf(value));
            }
        }

        XAxis.setLabel("Wijk");
        YAxis.setLabel("Aantal fietstrommels");
        XYChart.Series series = new DatabaseReader().getBarChartData(builder.createChartData(), 1);
        barChart.getData().add(series);
        barChart.setTitle(builder.createChartData().getDesc());
        sceneView.getChildren().add(barChart);
        return sceneView;
    }
}
