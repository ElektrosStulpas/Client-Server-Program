package server;

import Pacman.Pacman;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ServerApp extends Application {

    static Pacman pacman = new Pacman();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Serveris");
        primaryStage.setScene(getLoginScene(primaryStage));
        pacman.setVisible(true);
        primaryStage.show();
    }

    private Scene getLoginScene(Stage primaryStage){

        GridPane gridPane = new GridPane();

        Label portoLabel = new Label("Porto numeris:");
        TextField portoField = new TextField();
        Button button = new Button("OK");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Server server = new Server(Integer.parseInt(portoField.getText()));
                Thread thread = new Thread(server);
                thread.setDaemon(true);
                thread.start();

                primaryStage.setScene(getChatScene(server));
            }

            private Scene getChatScene(Server server) {


                Label serverInfo = new Label("Komandos iš klientų:");
                ListView<String> listMessages = new ListView<>();
                ListView<String> listUsers = new ListView<>();

                ObservableList<String> listServerInfo = Server.server_info;
                listMessages.setItems(listServerInfo);

                ObservableList<String> listServerUsers = Server.client_names;
                listUsers.setItems(listServerUsers);

                GridPane gridPane = new GridPane();
                gridPane.add(serverInfo, 0, 0);
                gridPane.add(listMessages, 0, 1);
                gridPane.add(listUsers, 1, 1);

                return new Scene(gridPane);
            }
        });

        gridPane.add(portoLabel, 0, 0);
        gridPane.add(portoField, 1, 0);
        gridPane.add(button, 1, 1);

        return new Scene(gridPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}