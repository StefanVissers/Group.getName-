package com.desktop;

import Data.builder.ChartData;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * Created by floris-jan on 04-07-16.
 */
public class PieChart {

    VBox sceneView;
    javafx.scene.chart.PieChart pieChart;

    public PieChart() {
        sceneView = new VBox();
        pieChart = new javafx.scene.chart.PieChart();
    }

    public VBox getScene(String... args) {
        sceneView.setPadding(new Insets(10, 10, 10, 10));
        sceneView.setPrefSize(Main.width, Main.height);

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
            }
        }

        ObservableList<javafx.scene.chart.PieChart.Data> list = new DatabaseReader().getPieChartData(builder.createChartData(), 1);

        pieChart.setData(list);
        pieChart.setTitle(builder.createChartData().getDesc());
        sceneView.getChildren().add(pieChart);
        return sceneView;
    }
}
