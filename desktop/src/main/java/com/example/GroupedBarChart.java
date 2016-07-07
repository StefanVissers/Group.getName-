package com.example;

import java.util.ArrayList;
import java.util.List;

import Data.Queries;
import Data.builder.ChartData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by floris-jan on 04-07-16.
 */
public class GroupedBarChart {

    static VBox sceneView;
    static javafx.scene.chart.BarChart barChart;
    static CategoryAxis XAxis;
    static NumberAxis YAxis;
    static String wijk1;
    static String wijk2;
    static String jaar;

    public static VBox getScene(String... args) {
        sceneView = new VBox();
        XAxis = new CategoryAxis();
        YAxis = new NumberAxis();
        sceneView.setPadding(new Insets(10, 10, 10, 10));
        sceneView.setPrefSize(Main.width, Main.height);
        barChart = new javafx.scene.chart.BarChart(XAxis, YAxis);
        List<ChartData> chartDatas = new ArrayList<>();
        ChartData.ChartDataBuilder builder = null;

        Text text1 = new Text("Wijk voor fietstrommels:");

        final ComboBox<String> pickArea1ComboBox = new DatabaseReader().getPickComboBox(Queries.getFietsdiefstalAreas());
        if(wijk1 != null) {
            pickArea1ComboBox.getSelectionModel().select(wijk1);
        }
        pickArea1ComboBox.setMinWidth(300);
        pickArea1ComboBox.setPromptText("Select an area");
        pickArea1ComboBox.setEditable(false);

        Text text2 = new Text("Wijk voor fietsdiefstal:");
        final ComboBox<String> pickArea2ComboBox = new DatabaseReader().getPickComboBox(Queries.getFietstrommelsAreas());
        if(wijk2 != null) {
            pickArea2ComboBox.getSelectionModel().select(wijk2);
        }
        pickArea2ComboBox.setMinWidth(300);
        pickArea2ComboBox.setPromptText("Select an area");
        pickArea2ComboBox.setEditable(false);

        pickArea1ComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Selected area: " + pickArea1ComboBox.getSelectionModel().getSelectedItem().toString());
                wijk1 = pickArea1ComboBox.getSelectionModel().getSelectedItem().toString();
                Main.borderPane.setCenter(getScene(Queries.getGroupedBarStat1()));
            }
        });

        pickArea2ComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Selected area: " + pickArea2ComboBox.getSelectionModel().getSelectedItem().toString());
                wijk2 = pickArea2ComboBox.getSelectionModel().getSelectedItem().toString();
                Main.borderPane.setCenter(getScene(Queries.getGroupedBarStat1()));
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
        barChart.getData().add(series1);
        barChart.getData().add(series2);
        barChart.setTitle(chartDatas.get(0).getDesc());
        sceneView.getChildren().addAll(text1, pickArea1ComboBox, text2, pickArea2ComboBox, barChart);
        return sceneView;
    }
}
