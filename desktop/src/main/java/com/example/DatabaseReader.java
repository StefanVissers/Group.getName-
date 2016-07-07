package com.example;

/**
 * Created by floris-jan on 23-06-16.
 */
import java.sql.*;

import Data.Queries;
import Data.builder.ChartData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DatabaseReader {
    static final String DB_URL = "jdbc:sqlite:shared/libs/dataSource.db";
    Connection connection;
    Statement statement;

    public DatabaseReader() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<javafx.scene.chart.PieChart.Data> getPieChartData(ChartData chartData, int column) {
        ResultSet resultSet;
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(chartData.getSql_query());
            while (resultSet.next()) {
                list.add(new PieChart.Data(resultSet.getString(1), resultSet.getInt(column+1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public XYChart.Series getBarChartData(ChartData chartData, int column) {
        XYChart.Series series = new XYChart.Series();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(chartData.getSql_query());
            while (resultSet.next()) {
                series.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(column+1)));
                System.out.println("Data: " + chartData.getDesc() + " - " + resultSet.getString(1) + resultSet.getInt(column+1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    public XYChart.Series getGroupedBarChartData(ChartData chartData, int column,String wijk1, String wijk2, String jaar) {
        XYChart.Series series = new XYChart.Series();
        ResultSet resultSet;
        try {
            if(chartData.isFiltered()) {
                resultSet = statement.executeQuery(chartData.getFiltered_query());
            } else {
                resultSet = statement.executeQuery(chartData.getSql_query());
            }
            System.out.println("SQL Query: " + chartData.getSql_query());
            int i = 0;
            while (resultSet.next()) {
                System.out.println("String 4: " + resultSet.getString(4) + " wijk1: " + GroupedBarChart.wijk1 + " wijk2: " + GroupedBarChart.wijk2);
                String wijk = resultSet.getString(4);
                if(GroupedBarChart.wijk1 != null || GroupedBarChart.wijk2 != null) {
                    System.out.println("!Null " + wijk + " - " + GroupedBarChart.wijk1 + " or " + GroupedBarChart.wijk2);
                    if(GroupedBarChart.wijk1 == null) {
                        GroupedBarChart.wijk1 = "!";
                    } else if(GroupedBarChart.wijk2 == null) {
                        GroupedBarChart.wijk2 = "!";
                    }
                    if(resultSet.getString(4) != null) {
                        if (resultSet.getString(4).contains(GroupedBarChart.wijk1) || resultSet.getString(4).contains(GroupedBarChart.wijk2)) {
                            System.out.println(wijk + " - " + GroupedBarChart.wijk1 + " or " + GroupedBarChart.wijk2);
                            if (resultSet.getString(2).length() == 1) {
                                series.getData().add(new XYChart.Data(resultSet.getString(1) + ", 0" + resultSet.getString(2), resultSet.getInt(column + 2)));
                            } else {
                                series.getData().add(new XYChart.Data(resultSet.getString(1) + ", " + resultSet.getString(2), resultSet.getInt(column + 2)));
                            }
                        }
                    }
                } else {
                    System.out.println("Not " + wijk + " - " + GroupedBarChart.wijk1 + " or " + GroupedBarChart.wijk2);
                    if (resultSet.getString(2).length() == 1) {
                        series.getData().add(new XYChart.Data(resultSet.getString(1) + ", 0" + resultSet.getString(2), resultSet.getInt(column + 2)));
                    } else {
                        series.getData().add(new XYChart.Data(resultSet.getString(1) + ", " + resultSet.getString(2), resultSet.getInt(column + 2)));
                    }
                }

//                String rString = resultSet.getString(1);
//                if(GroupedBarChart.jaar == null) {
//                    GroupedBarChart.jaar = "2012";
//                }
//
//                if (rString.contains(GroupedBarChart.jaar)) {
//                    System.out.println(GroupedBarChart.jaar + " - " + rString);
//                    if (resultSet.getString(2).length() == 1) {
//                        series.getData().add(new XYChart.Data(resultSet.getString(1) + ", 0" + resultSet.getString(2), resultSet.getInt(column + 2)));
//                    } else {
//                        series.getData().add(new XYChart.Data(resultSet.getString(1) + ", " + resultSet.getString(2), resultSet.getInt(column + 2)));
//                    }
//                } else {
//                    System.out.println("not " + GroupedBarChart.jaar + " - " + rString);
//                }
//                series.getData().add(new XYChart.Data(resultSet.getString(1) + ", 0" + resultSet.getString(2), resultSet.getInt(column + 2)));
//                i++;
//                series.getData().add(new XYChart.Data("Hello", i));
//                series.getData().add(new XYChart.Data("Goodbye", resultSet.getInt(column+2)));
                System.out.println("Data: " + chartData.getDesc() + " - Date " + resultSet.getString(1) + ", " + resultSet.getString(2) + " - Numbers: " + resultSet.getInt(column+2) + " - Area: " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(series.getData().isEmpty()) {
            System.out.println("Series is empty.");
            series.getData().add(new XYChart.Data(" ", 0));
        }
        return series;
    }

    public XYChart.Series getLineChartData(ChartData chartData, int column) {
        XYChart.Series series = new XYChart.Series();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(chartData.getSql_query());
            while (resultSet.next()) {
                series.getData().add(new XYChart.Data(resultSet.getInt(1), resultSet.getInt(column+1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    public ComboBox<String> getPickComboBox(String query) {
        final ComboBox<String> pickComboBox = new ComboBox<>();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Result: " + resultSet.getString(1));
                if(resultSet.getString(1) != "") {
                    pickComboBox.getItems().add(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pickComboBox;
    }
}