package com.example;

import java.util.ArrayList;
import java.util.List;

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
public class GroupedBarChart {

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
        List<ChartData> chartDatas = new ArrayList<>();

        ChartData.ChartDataBuilder builder = null;

        int cdCount = 1;
        boolean inObject = false, ranOnce = false, parsing = false;

        for (String s : args) {
            if (s.equals("^START^" + cdCount) && !inObject) {
                inObject = true;
            } else if (inObject) {
                if ( !(s.equals("^END^" + cdCount)) ) {
                    String[] splitted = s.split(": ");
                    if (s.startsWith("sql:")) {
                        builder = new ChartData.ChartDataBuilder(splitted[1]);
                    } else {
                        if (builder == null) {
                            throw new NullPointerException("No Query found");
                        } else {
                            builder.tryParse(splitted[0], splitted[1]);
                        }
                    }
                } else if (s.equals("^END^" + cdCount)){
                    if (builder == null) {
                        throw new NullPointerException("No Query found");
                    } else {
                        inObject = false; cdCount++; ranOnce = true;
                        chartDatas.add(builder.createChartData());
                        builder = null;
                    }
                }
            } else {
                if (!ranOnce) {
                    System.out.println("GBC_FACTORY malformed input, every object for chartdata must start with ^START^<NUMBER> and end with ^END^<SAMENUMBER>");
                }
                else {
                    break;
                }
            }
        }

        XAxis.setLabel("Maand");
        YAxis.setLabel("Aantal fietstrommels/fietsdiefstallen");
        XYChart.Series series1 = new DatabaseReader().getGroupedBarChartData(chartDatas.get(0), 1);
        XYChart.Series series2 = new DatabaseReader().getGroupedBarChartData(chartDatas.get(1), 1);
        series1.setName(chartDatas.get(0).getTitle());
        series2.setName(chartDatas.get(1).getTitle());
        barChart.getData().addAll(series1, series2);
        barChart.setTitle(chartDatas.get(0).getDesc());
        sceneView.getChildren().add(barChart);
        return sceneView;
    }
}
