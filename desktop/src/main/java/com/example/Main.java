package com.example;

/**
 * Created by floris-jan on 30-06-16.
 */

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

    public static Stage mapStage;
    public static Scene mapScene;

    public static Stage pieChartStage;
    public static Scene pieChartScene;

    public static Stage areaChart1Stage;
    public static Scene areaChart1Scene;

    public static Stage areaChart2Stage;
    public static Scene areaChart2Scene;

    public static Stage pieChart2Stage;
    public static Scene pieChart2Scene;

    public static Stage stackBarChartStage;
    public static Scene stackBarChartScene;

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

        //Creates HBox-menubar layout and buttons
        MenuBar menuBar = new MenuBar();
        Menu general = new Menu("General");
        Menu statistics = new Menu("Statistics");

        MenuItem help = new MenuItem("Help");
        MenuItem preferences = new MenuItem("Preferences");
        MenuItem about = new MenuItem("About");
        MenuItem quit = new MenuItem("Quit");

        MenuItem start = new MenuItem("Start");
        MenuItem stat1 = new MenuItem("Question 1: ?");
        MenuItem stat2 = new MenuItem("Question 2: ?");
        MenuItem stat3 = new MenuItem("Question 2: ?");
        MenuItem stat4 = new MenuItem("Question 3: ?");
        MenuItem stat5 = new MenuItem("Question 4: ?");
        MenuItem stat6 = new MenuItem("Question 5: ?");
        MenuItem stat7 = new MenuItem("Question 5: ?");

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

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        stat1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(openInNewWindow) {
                    pieChartStage = new Stage();
                    pieChartStage.setTitle("Pie Chart");
//                pieChartScene = new Scene(PieChartX.getScene(chartInfo, false), width, height);
//                pieChartScene.setRoot(PieChartX.getScene(chartInfo, false));
                    pieChartStage.setScene(pieChartScene);
                    pieChartStage.show();
                }
                else {
//                borderPane.setCenter(PieChartX.getScene(chartInfo, false));
                }
            }
        });

        stat2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(openInNewWindow) {
                    areaChart1Stage = new Stage();
                    areaChart1Stage.setTitle("Area Chart 1");
//                areaChart1Scene = new Scene(AreaChartX.getScene(chartInfo), width, height);
//                areaChart1Scene.setRoot(AreaChartX.getScene(chartInfo));
                    areaChart1Stage.setScene(areaChart1Scene);
                    areaChart1Stage.show();
                }
                else {
//                    borderPane.setCenter(AreaChartX.getScene(chartInfo));
                }
            }
        });

        stat3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(openInNewWindow) {
                    areaChart2Stage = new Stage();
                    areaChart2Stage.setTitle("Area Chart 2");
//                    areaChart2Scene = new Scene(AreaChartX.getScene(chartInfo), width, height);
//                    areaChart2Scene.setRoot(AreaChartX.getScene(chartInfo));
                    areaChart2Stage.setScene(areaChart2Scene);
                    areaChart2Stage.show();
                }
                else {
//                    borderPane.setCenter(AreaChartX.getScene(chartInfo));
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
                    stackBarChartStage = new Stage();
                    stackBarChartStage.setTitle("Stacked Bar Chart");
//                    stackBarChartScene = new Scene(StackBarChart1.getScene("buurtprobleem fietsendiefstal", false), width, height);
//                    stackBarChartScene.setRoot(StackBarChart1.getScene("buurtprobleem fietsendiefstal", false));
                    stackBarChartStage.setScene(stackBarChartScene);
                    stackBarChartStage.show();
                }
                else {
//                    borderPane.setCenter(StackBarChart1.getScene("buurtprobleem fietsendiefstal", false));
                }
            }
        });

        stat6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(openInNewWindow) {
                    mapStage = new Stage();
                    mapStage.setTitle("Map");
//                    mapScene = new Scene(MapChart1.getScene(), width, height);
//                    mapScene.setRoot(MapChart1.getScene());
                    mapStage.setScene(mapScene);
                    mapStage.show();
                }
                else {
//                    borderPane.setCenter(MapChart1.getScene());
                }
            }
        });

        stat7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String year = 2006 + "";
                if(openInNewWindow) {
                    mapStage = new Stage();
                    mapStage.setTitle("Map");
//                    mapScene = new Scene(MapChart2.getScene(year), width, height);
//                    mapScene.setRoot(MapChart2.getScene(year));
                    mapStage.setScene(mapScene);
                    mapStage.show();
                }
                else {
//                    borderPane.setCenter(MapChart2.getScene(year));
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
