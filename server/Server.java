package server;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private static ServerSocket serverSocket;
    static ObservableList<String> client_names;
    static ObservableList<String> server_info;

    Server(int port_number) {
        client_names = FXCollections.observableArrayList();
        server_info = FXCollections.observableArrayList();
        try {
            serverSocket = new ServerSocket(port_number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ClientThread currentClientThread;
        while(true){
            try {
                Socket currentClientSocket = serverSocket.accept();

                if (serverSocket.isClosed()){
                    break;
                }

                Platform.runLater(() -> client_names.add("Klientas " + currentClientSocket.getRemoteSocketAddress()));

                currentClientThread = new ClientThread(currentClientSocket);
                Thread clientThread = new Thread(currentClientThread);
                clientThread.setDaemon(true);
                clientThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
