package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ClientTest extends Application {

    public void start(Stage primaryStage){

        try {
            Socket socket = new Socket("0.0.0.0", 2345);
            PrintWriter printer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


        primaryStage.setTitle("Valdymo pultas");

        GridPane gridPane = new GridPane();

        Label comLabel = new Label("Komanda:");
        TextField comField = new TextField();
        Button comButton = new Button("OK");

            comButton.setOnAction(event -> {
                printer.println(comField.getText());
                printer.flush();
                comField.clear();
            });



        gridPane.add(comLabel, 0, 0);
        gridPane.add(comField, 1, 0);
        gridPane.add(comButton, 1, 1);

        Scene scene = new Scene(gridPane);

        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(e -> {
            try {
                socket.close();
                printer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
