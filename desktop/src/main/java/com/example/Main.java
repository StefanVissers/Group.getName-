package com.example;

/**
 * Created by floris-jan on 30-06-16.
 */

import Data.Queries;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.lang.reflect.Field;
import java.net.*;
import java.util.HashMap;

public class Main extends Application {

    public static int width;
    public static int height;
    public static Scene scene;
    public static boolean staticMap;
    public static boolean openInNewWindow;
    public static BorderPane borderPane;

    public static Stage pieChart1Stage;
    public static Scene pieChart1Scene;

    public static Stage lineChart1Stage;
    public static Scene lineChart1Scene;

    public static Stage pieChart2Stage;
    public static Scene pieChart2Scene;

    public static Stage barChart1Stage;
    public static Scene barChart1Scene;

    public static Stage stackBarChart1Stage;
    public static Scene stackBarChart1Scene;

    // Setting if db name gets changed
    public static String DatabaseName = "Database";

    /**
     *
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String args[]) throws MalformedURLException {
        //Default preferences
        staticMap = false;
        openInNewWindow = false;
        General.backgroundImageFileString = "Background 1.png";

        try {
            Class<?> macFontFinderClass = Class.forName("com.sun.t2k.MacFontFinder");
            Field psNameToPathMap = macFontFinderClass.getDeclaredField("psNameToPathMap");
            psNameToPathMap.setAccessible(true);
            psNameToPathMap.set(null, new HashMap<String, String>());
        } catch (Exception e) {
            // ignore
        }

        launch();
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    //Creates, initiates and draws the window and all of its elements
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Statistics");
        width = 1000;
        height = 800;
        borderPane = new BorderPane();
        final DatabaseReader databaseReader = new DatabaseReader();

        //Creates HBox-menubar layout and buttons
        MenuBar menuBar = new MenuBar();
        Menu general = new Menu("General");
        Menu statistics = new Menu("Statistics");

        MenuItem help = new MenuItem("Help");
        MenuItem preferences = new MenuItem("Preferences");
        MenuItem about = new MenuItem("About");
        MenuItem quit = new MenuItem("Quit");

        MenuItem start = new MenuItem("Start");
        MenuItem stat1 = new MenuItem("5 meest gestolen fietsenkleuren");
        MenuItem stat2 = new MenuItem("5 meest gestolen fietsenmerken");
        MenuItem stat3 = new MenuItem("Gestolen fietsen per maand");
        MenuItem stat4 = new MenuItem("Question 3: ?");
        MenuItem stat5 = new MenuItem("Question 4: ?");
        MenuItem stat6 = new MenuItem("Wijken met de meeste trommels");
        MenuItem stat7 = new MenuItem("Question 6: ?");

        general.getItems().addAll(help, preferences, about, quit);
        statistics.getItems().addAll(start, stat1,stat2,stat3,stat4,stat5,stat6,stat7);
        menuBar.getMenus().addAll(general, statistics);
        borderPane.setTop(menuBar);
        borderPane.setCenter(General.getStartScene());

        //Sets listeners for the General menubar-buttons
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(General.getHelpScene());
            }
        });

        preferences.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(General.getPreferencesScene());
            }
        });

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(General.getAboutScene());
            }
        });

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(General.getStartScene());
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        stat1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox = new PieChart().getScene(Queries.getPieStat1());
                if(openInNewWindow) {
                    pieChart1Stage = new Stage();
                    pieChart1Stage.setTitle("Pie Chart");
                    pieChart1Scene = new Scene(vBox, width, height);
                    pieChart1Scene.setRoot(vBox);
                    pieChart1Stage.setScene(pieChart1Scene);
                    pieChart1Stage.show();
                }
                else {
                    borderPane.setCenter(vBox);
                }
            }
        });

        stat2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox = new PieChart().getScene(Queries.getPieStat2());
                if(openInNewWindow) {
                    pieChart2Stage = new Stage();
                    pieChart2Stage.setTitle("Pie Chart");
                    pieChart2Scene = new Scene(vBox, width, height);
                    pieChart2Scene.setRoot(vBox);
                    pieChart2Stage.setScene(pieChart2Scene);
                    pieChart2Stage.show();
                }
                else {
                    borderPane.setCenter(vBox);
                }
            }
        });

        stat3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox = LineChart.getScene(Queries.getLineStat1());
                if(openInNewWindow) {
                    lineChart1Stage = new Stage();
                    lineChart1Stage.setTitle("Line Chart");
                    lineChart1Scene = new Scene(vBox, width, height);
                    lineChart1Scene.setRoot(vBox);
                    lineChart1Stage.setScene(lineChart1Scene);
                    lineChart1Stage.show();
                }
                else {
                    borderPane.setCenter(vBox);
                }
            }
        });

        stat4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vbox = new VBox();
                Text text = new Text();
                text.setText("Compare the piecharts here.");
                text.setFont(Font.font("null", FontWeight.MEDIUM, 40));
                text.setWrappingWidth(Main.scene.getWidth());
//                vbox.getChildren().addAll(text, PieChartX.getScene(chartInfo, true), PieChartX.getScene(chartInfo, true));
                if(openInNewWindow) {
                    pieChart2Stage = new Stage();
                    pieChart2Stage.setTitle("Area Chart 2");
                    pieChart2Scene = new Scene(vbox, width, height);
//                    pieChart2Scene.setRoot(PieChartX.getScene(chartInfo, true));
                    pieChart2Stage.setScene(pieChart2Scene);
                    pieChart2Stage.show();
                }
                else {
                    borderPane.setCenter(vbox);
                }
            }
        });

        stat5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(openInNewWindow) {
                    stackBarChart1Stage = new Stage();
                    stackBarChart1Stage.setTitle("Stacked Bar Chart");
//                    stackBarChartScene = new Scene(StackBarChart1.getScene("buurtprobleem fietsendiefstal", false), width, height);
//                    stackBarChartScene.setRoot(StackBarChart1.getScene("buurtprobleem fietsendiefstal", false));
                    stackBarChart1Stage.setScene(stackBarChart1Scene);
                    stackBarChart1Stage.show();
                }
                else {
//                    borderPane.setCenter(StackBarChart1.getScene("buurtprobleem fietsendiefstal", false));
                }
            }
        });

        stat6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox = BarChart.getScene(Queries.getBarStat1());
                if(openInNewWindow) {
                    barChart1Stage = new Stage();
                    barChart1Stage.setTitle("BarChart");
                    barChart1Scene = new Scene(vBox);
                    barChart1Scene.setRoot(vBox);
                    barChart1Stage.setScene(barChart1Scene);
                    barChart1Stage.show();
                }
                else {
                    borderPane.setCenter(BarChart.getScene(Queries.getBarStat1()));
                }
            }
        });

        //Creates and instantiates the scene
        scene = new Scene(borderPane, width, height);
        scene.setRoot(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
