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

    public XYChart.Series getGroupedBarChartData(ChartData chartData, int column) {
        XYChart.Series series = new XYChart.Series();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(chartData.getSql_query());
            System.out.println("SQL Query: " + chartData.getSql_query());
            while (resultSet.next()) {
                if(resultSet.getString(2).length() == 1) {
                    series.getData().add(new XYChart.Data(resultSet.getString(1) + ", 0" + resultSet.getString(2), resultSet.getInt(column + 2)));
                } else {
                    series.getData().add(new XYChart.Data(resultSet.getString(1) + ", " + resultSet.getString(2), resultSet.getInt(column + 2)));
                }
                System.out.println("Data: " + chartData.getDesc() + " - Date " + resultSet.getString(1) + ", " + resultSet.getString(2) + " - Numbers: " + resultSet.getInt(column+2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
}