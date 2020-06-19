
/*
 * Name: Dhruv Dhananjaybhai Thakkar
 * Id: 1001815888
 *
 * */

package sample.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Map;

/*
    https://www.callicoder.com/javafx-registration-form-gui-tutorial/ get code for UI from this tutorial,
    Same tutorial has this GitHub repo: https://github.com/callicoder/javafx-examples/tree/master/javafx-registration-form-application
*/
public class UiForClientList extends Application {

    // root stage for show scene
    public static Stage rootStage;
    // get client message UI
    UiForClientMessage um = new UiForClientMessage();

    // get layout of this window
    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

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

        // return after update
        return gridPane;
    }

    // List for clients for oneToOneClientList
    public void oneToOneClientList() {
        // create new grid
        GridPane gridPane = createRegistrationFormPane();
        // create new list
        ListView<String> clientList = new ListView<>();
        // Iterate client data and add to list
        for (Map.Entry<String, Boolean> e :
                Main.selectClientForMessage.entrySet()) {
            if (!Main.thisUser.equals(e.getKey())) {
                // Add to list
                clientList.getItems().add(e.getKey());
            }
        }

        // Add whole list to layout and set styles
        gridPane.add(clientList, 0, 0, 2, 1);
        GridPane.setHalignment(clientList, HPos.RIGHT);
        GridPane.setMargin(clientList, new Insets(10, 0, 10, 20));

        // Add Submit Button
        Button submitButton = new Button("Next");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);

        // Add Button to layout and set styles
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.LEFT);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 150));

        // Get object of UiForClientList for show all list
        UiForClientList cl = new UiForClientList();

        // Event occur for Next button
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // handle event
            public void handle(ActionEvent event) {
                // get selected data
                String temp = clientList.getFocusModel().getFocusedItem();
                // put to map for reference
                Main.selectClientForMessage.put(temp, true);
                // Create scene
                Scene sceneOfClientMessage = new Scene(um.sendGridPane(), 800, 500);
                // set scene
                rootStage.setScene(sceneOfClientMessage);
                // SHow on window
                rootStage.show();
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
//        GridPane.setHalignment(closeButton, HPos.CENTER);
//        GridPane.setMargin(closeButton, new Insets(20, 0, 20, 0));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // handle event
            public void handle(ActionEvent event) {
                rootStage.close();
            }
        });

        // create and set scene for layout
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        rootStage.setScene(scene);
        // show the window
        rootStage.show();
    }

    // List for clients for oneToTwoClientList
    public void oneToManyClientList() {
        // create grid pane
        GridPane gridPane = createRegistrationFormPane();
        // get size of client from server
        int size = Main.selectClientForMessage.size();
        // create checkbox
        CheckBox[] clientsForMany = new CheckBox[size];
        int i = 0;
        ArrayList<CheckBox> ac = new ArrayList<>();
        // Iterate the loop and Add
        for (Map.Entry<String, Boolean> e :
                Main.selectClientForMessage.entrySet()) {
            if (!Main.thisUser.equals(e.getKey())) {
                clientsForMany[i] = new CheckBox(e.getKey());
                ac.add(clientsForMany[i]);
            }
            i++;
        }

        // Add to layout
        HBox clientList = new HBox();
        clientList.getChildren().addAll(ac);
        clientList.setSpacing(5);
        // Apply list
        gridPane.add(clientList, 0, 0, 2, 1);
        GridPane.setHalignment(clientList, HPos.RIGHT);
        GridPane.setMargin(clientList, new Insets(10, 0, 10, 20));

        // Add Submit Button
        Button submitButton = new Button("Next");
        // Set style and alignments to button
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        // Add button to layout
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.LEFT);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 150));
        // Create object of UiForClientList to start process over here
        UiForClientList cl = new UiForClientList();
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (CheckBox c :
                        ac) {
                    if (c.isSelected()) {
                        // Put client status if it selected
                        Main.selectClientForMessage.put(c.getText(), true);
                    }
                }
                // Create scene and show the window
                Scene sceneOfClientMessage = new Scene(um.sendGridPane(), 800, 500);
                rootStage.setScene(sceneOfClientMessage);
                rootStage.show();
            }
        });

        // Add close Button
        Button closeButton = new Button("Okay");
        // Edit style of button
        closeButton.setPrefHeight(40);
        closeButton.setDefaultButton(true);
        closeButton.setPrefWidth(100);
        // Add to layout and set style
        gridPane.add(closeButton, 0, 4, 2, 1);
//        GridPane.setHalignment(closeButton, HPos.CENTER);
//        GridPane.setMargin(closeButton, new Insets(20, 0, 20, 0));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // handle event
            public void handle(ActionEvent event) {
                rootStage.close();
            }
        });

        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        rootStage.setScene(scene);
        rootStage.show();
    }

    // List for clients for oneToAllClientList
    public void oneToAllClientList() {
        // Create gridPane layout
        GridPane gridPane = createRegistrationFormPane();
        // Get client size
        int size = Main.selectClientForMessage.size();
        CheckBox[] clientsForMany = new CheckBox[size];
        int i = 0;
        ArrayList<CheckBox> acx = new ArrayList<>();
        for (Map.Entry<String, Boolean> e :
                Main.selectClientForMessage.entrySet()) {
            // Check the users
            if (!Main.thisUser.equals(e.getKey())) {
                // If available add and put true (Selected)
                clientsForMany[i] = new CheckBox(e.getKey());
                clientsForMany[i].setSelected(true);
                // Add to list
                acx.add(clientsForMany[i]);
            }
            i++;
        }

        HBox clientList = new HBox();
        clientList.getChildren().addAll(acx);
        clientList.setSpacing(5);
        // Add to gridPane and set alignment
        gridPane.add(clientList, 0, 0, 2, 1);
        GridPane.setHalignment(clientList, HPos.RIGHT);
        GridPane.setMargin(clientList, new Insets(10, 0, 10, 20));

        // Add Submit Button
        Button submitButton = new Button("Next");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        // Add to gridPane and set alignment
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.LEFT);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 150));
        UiForClientList cl = new UiForClientList();

        // handle client action
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (CheckBox c :
                        acx) {
                    if (c.isSelected()) {
                        // Put active status to hash map
                        Main.selectClientForMessage.put(c.getText(), true);
                    }
                }
                // create scene and update
                Scene sceneOfClientMessage = new Scene(um.sendGridPane(), 800, 500);
                rootStage.setScene(sceneOfClientMessage);
                rootStage.show();
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
//        GridPane.setHalignment(closeButton, HPos.CENTER);
//        GridPane.setMargin(closeButton, new Insets(20, 0, 20, 0));

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            // handle event
            public void handle(ActionEvent event) {
                // close the window
                rootStage.close();
            }
        });
        // Create and sets the scene
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        rootStage.setScene(scene);
        rootStage.show();
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

    // select the type of communication
    @Override
    public void start(Stage primaryStage) throws Exception {
        rootStage = primaryStage;
        // If One to One communication call it's method and sets the layout
        if (rootStage.getTitle().equals("One to One communication")) {
            oneToOneClientList();
        }
        // If One to many communication call it's method and sets the layout
        if (rootStage.getTitle().equals("One to Many communication")) {
            oneToManyClientList();
        }
        // If Select all communication call it's method and sets the layout
        if (rootStage.getTitle().equals("Select all")) {
            oneToAllClientList();
        }
        // On the close request
        rootStage.setOnCloseRequest((WindowEvent e) -> {
            System.out.println("Primary stage is been closed!");
        });
    }
}