package com.desktop;

import Data.builder.ChartData;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 * Created by floris-jan on 04-07-16.
 */
public class LineChart {

    static VBox sceneView;
    static javafx.scene.chart.LineChart lineChart;
    static CategoryAxis XAxis;
    static NumberAxis YAxis;

    public static VBox getScene(String... args) {
        sceneView = new VBox();
        XAxis = new CategoryAxis();
        YAxis = new NumberAxis();
        sceneView.setPadding(new Insets(10, 10, 10, 10));
        sceneView.setPrefSize(Main.width, Main.height);
        lineChart = new javafx.scene.chart.LineChart(XAxis, YAxis);

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
            }
        }

        XAxis.setLabel("Jaar");
        YAxis.setLabel("Gestolen fietsen");
        XYChart.Series series = new DatabaseReader().getBarChartData(builder.createChartData(), 2);
        series.setName(builder.createChartData().getTitle());
        lineChart.getData().add(series);
        lineChart.setTitle(builder.createChartData().getDesc());
        sceneView.getChildren().add(lineChart);
        return sceneView;
    }
}
