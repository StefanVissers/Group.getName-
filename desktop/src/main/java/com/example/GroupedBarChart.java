package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Data.Queries;
import Data.builder.ChartData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
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

        final ComboBox<String> pickYearComboBox = new ComboBox<>();
        pickYearComboBox.setMinWidth(200);
        pickYearComboBox.setPromptText("Select a year");
        pickYearComboBox.setEditable(false);

        final ComboBox<String> pickArea1ComboBox = new DatabaseReader().getPickAreaComboBox(Queries.getFietsdiefstalAreas());
        pickArea1ComboBox.setMinWidth(200);
        pickArea1ComboBox.setPromptText("Select an area");
        pickArea1ComboBox.setEditable(false);

        final ComboBox<String> pickArea2ComboBox = new DatabaseReader().getPickAreaComboBox(Queries.getFietstrommelsAreas());
        pickArea2ComboBox.setMinWidth(200);
        pickArea2ComboBox.setPromptText("Select an area");
        pickArea2ComboBox.setEditable(false);

        pickYearComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Selected year: " + pickYearComboBox.getSelectionModel().getSelectedItem().toString());
            }
        });

        pickArea1ComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Selected area: " + pickArea1ComboBox.getSelectionModel().getSelectedItem().toString());
            }
        });

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
        sceneView.getChildren().addAll(pickArea1ComboBox, pickArea2ComboBox, pickYearComboBox, barChart);
        return sceneView;
    }
}
