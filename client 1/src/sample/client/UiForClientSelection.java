
/*
 * Name: Dhruv Dhananjaybhai Thakkar
 * Id: 1001815888
 *
 * */

package sample.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/*
    https://www.callicoder.com/javafx-registration-form-gui-tutorial/ get code for UI from this tutorial,
    Same tutorial has this GitHub repo: https://github.com/callicoder/javafx-examples/tree/master/javafx-registration-form-application
*/
// Ui for selection of which type of communication client wants
public class UiForClientSelection {
    static String[] messages = null;
    public GridPane sendGridPane() {
        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
//        Scene scene = new Scene(gridPane, 800, 500);
        return gridPane;
    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    // Add component
    private void addUIControls(GridPane gridPane) {
        selectClientList(gridPane);
    }

    // Show alert
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public GridPane selectClientList(GridPane gridPane) {

        // Add Header
        Label headerLabel = new Label("Connected with server!");

        // set font and add to layout and set style format
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add checkMessage Button
        Button checkMessage = new Button("Check Message!");
        checkMessage.setPrefHeight(40);
        checkMessage.setDefaultButton(true);
        checkMessage.setPrefWidth(150);
        // Set styles and alignment for button
        gridPane.add(checkMessage, 0, 4, 2, 1);
        GridPane.setHalignment(checkMessage, HPos.CENTER);
        GridPane.setMargin(checkMessage, new Insets(20, 0, 20, 150));


        // Add Name Text Field
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setTooltip(new Tooltip("Select the language!"));
        // Add options
        choiceBox.getItems().add("One to One communication");
        choiceBox.getItems().add("One to Many communication");
        choiceBox.getItems().add("Select all");
        // Set styles and alignment
        gridPane.add(choiceBox, 1, 1);
        GridPane.setHalignment(choiceBox, HPos.LEFT);
        GridPane.setMargin(choiceBox, new Insets(10, 0, 10, 0));
        // Add Submit Button
        Button submitButton = new Button("Next");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        // Set styles and alignment
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.LEFT);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 150));
        UiForClientList cl = new UiForClientList();

        // When event occurs
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch (choiceBox.getValue()) {
                    case "One to One communication":
                        try {
                            // create stage
                            Stage s = new Stage();
                            // Set titles
                            s.setTitle("One to One communication");
                            // Run new UI window for this
                            cl.start(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "One to Many communication":
                        try {
                            // Create stage
                            Stage s = new Stage();
                            // Set Title
                            s.setTitle("One to Many communication");
                            // Run new UI window for this
                            cl.start(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Select all":
                        try {
                            // Create stage
                            Stage s = new Stage();
                            // Set Title
                            s.setTitle("Select all");
                            // Run new UI window for this
                            cl.start(s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Nothing!");
                        break;
                }
            }
        });
        // Create object for show messages
        UiForReadMessage ur = new UiForReadMessage();
        checkMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Request to server for read data
                ClientLogic.sendMessage(Main.thisUser + ":read");

                // String dataFrom = "Message from: " + ClientLogic.receiveMessage();
                String dataMessage = null;
                try {
                    // Read data
                    dataMessage = ClientLogic.receiveMessage();
                    // Reformat the message
                    messages = dataMessage.split("::done::");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Create UI window for read message
                Stage s = new Stage();
                GridPane gridPane = ur.createRegistrationFormPane();
                ur.addUIControls(gridPane, messages);
                Scene scene = new Scene(gridPane, 800, 500);
                // Set the scene in primary stage
                s.setScene(scene);
                try {
                    ur.start(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Add close Button
        Button closeButton = new Button("Close");
        // Edit style of button
        closeButton.setPrefHeight(40);
        closeButton.setDefaultButton(true);
        closeButton.setPrefWidth(100);

        // Add to layout and set style
        gridPane.add(closeButton, 0, 4, 2, 1);
        GridPane.setHalignment(closeButton, HPos.RIGHT);
//        GridPane.setMargin(closeButton, new Insets(20, 0, 20, 0));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // handle event
            public void handle(ActionEvent event) {
                // Close the window
                Main.rootStage.close();
                // Send server signal
                ClientLogic.sendCloseSignal();
            }
        });
        return gridPane;
    }
}