package com.desktop;

/**
 * Created by floris-jan on 30-06-16.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class General {
    public static String backgroundImageFileString;
    //Returns the Welcome-scene

    /**
     *
     * @return
     */
    public static VBox getStartScene() {
        VBox startView = new VBox();
        startView.setPrefSize(Main.width, Main.height);
        startView.setAlignment(Pos.CENTER);

        Text startText = new Text();
        startText.setText("Welcome!");
        startText.setFont(Font.font("null", FontWeight.BOLD, 120));

        Text startTextSub = new Text();
        startTextSub.setText("Start using this application by selecting something from the menu.");
        startTextSub.setFont(Font.font("null", FontWeight.LIGHT, 30));
        if(Main.scene != null) { startTextSub.setWrappingWidth(startText.getWrappingWidth()); }
        else { startTextSub.setWrappingWidth(Main.width); }
        startTextSub.setTextAlignment(TextAlignment.CENTER);
        startView.getChildren().addAll(startText, startTextSub);
        return startView;
    }

    //Returns the Help-scene
    public static VBox getHelpScene() {
        VBox sceneView = new VBox();
        sceneView.setAlignment(Pos.CENTER);

        Text sceneText = new Text();
        sceneText.setText("Help");
        sceneText.setFont(Font.font("null", FontWeight.LIGHT, 40));

        Text sceneTextSub = new Text();
        sceneTextSub.setText("You can view the different graphical representations of our data using the Statistics submenu in the menubar.");
        sceneTextSub.setFont(Font.font("null", FontWeight.LIGHT, 12));
        if(Main.scene != null) { sceneTextSub.setWrappingWidth(sceneText.getWrappingWidth()); }
        else { sceneTextSub.setWrappingWidth(Main.width); }
        sceneTextSub.setTextAlignment(TextAlignment.CENTER);
        sceneView.getChildren().addAll(sceneText, sceneTextSub);
        return sceneView;
    }

    //Returns the Preferences-scene
    public static VBox getPreferencesScene() {
        VBox sceneView = new VBox();
        sceneView.setPrefSize(Main.width, Main.height);
        sceneView.setSpacing(10);
        sceneView.setAlignment(Pos.CENTER);

        Text sceneText = new Text();
        sceneText.setText("Preferences");
        sceneText.setFont(Font.font("null", FontWeight.LIGHT, 40));

        CheckBox staticMapBox = new CheckBox();
        staticMapBox.setText("Enable Static Maps");
        if(Main.staticMap) { staticMapBox.setSelected(true); }
        staticMapBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.staticMap = !Main.staticMap;
            }
        });

        CheckBox openInNewWindowBox = new CheckBox();
        openInNewWindowBox.setText("Open every chart in a seperate window");
        if(Main.openInNewWindow) { openInNewWindowBox.setSelected(true); }
        openInNewWindowBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.openInNewWindow = !Main.openInNewWindow;
            }
        });

        final ComboBox backgroundImageComboBox = new ComboBox();
        backgroundImageComboBox.setEditable(false);
        backgroundImageComboBox.getItems().addAll(
                "None",
                "Background 1.png",
                "Background 2.png",
                "Background 3.png",
                "Background 4.png"
        );
        backgroundImageComboBox.setPromptText("Select a background");
        backgroundImageComboBox.setEditable(true);
        backgroundImageComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                backgroundImageFileString = backgroundImageComboBox.getSelectionModel().getSelectedItem().toString();
                Main.borderPane.setCenter(getPreferencesScene());
            }
        });
        backgroundImageComboBox.setValue(backgroundImageFileString);

        Text ChangeDataBaseHeader = new Text("Change database root name:");
        final TextField ChangeDatabaseField = new TextField(Main.DatabaseName);
        ChangeDatabaseField.setMaxWidth(100);
        ChangeDatabaseField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ChangeDatabaseField.selectAll();
            }
        });

        Button ConfirmDBChange = new Button("Confirm Change");
        ConfirmDBChange.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.DatabaseName = ChangeDatabaseField.getCharacters().toString();
                ChangeDatabaseField.setText(Main.DatabaseName);
            }
        });

        sceneView.getChildren().addAll(sceneText, staticMapBox, openInNewWindowBox, backgroundImageComboBox, ChangeDataBaseHeader, ChangeDatabaseField, ConfirmDBChange);
        return sceneView;
    }

    //Returns the About-scene
    public static VBox getAboutScene() {
        VBox sceneView = new VBox();
        sceneView.setAlignment(Pos.CENTER);

        Text sceneText = new Text();
        sceneText.setText("Copyright© Group.getName() 2016");
        sceneText.setFont(Font.font("null", FontWeight.LIGHT, 40));

        Text sceneTextSub = new Text();
        sceneTextSub.setText("Thomas de Graaf, Stefan Visser, Jacob Vroegop, L. Pekelharing, F.J. Willemsen");
        sceneTextSub.setFont(Font.font("null", FontWeight.LIGHT, 20));
        if(Main.scene != null) { sceneTextSub.setWrappingWidth(sceneText.getWrappingWidth()); }
        else { sceneTextSub.setWrappingWidth(Main.width); }
        sceneTextSub.setTextAlignment(TextAlignment.CENTER);

        sceneView.getChildren().addAll(sceneText, sceneTextSub);
        return sceneView;
    }
}
