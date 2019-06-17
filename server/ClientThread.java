package server;

import javafx.application.Platform;
import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket client_socket;
    private BufferedReader input_buffered_reader;

    ClientThread(Socket client_socket) {
        this.client_socket = client_socket;

        try {
            input_buffered_reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        String info_to_server;

        while(true){
            try {
                info_to_server = input_buffered_reader.readLine();

                if (info_to_server == null){
                    break;
                }

                ServerApp.pacman.setVisible(true);

                if (info_to_server.equals("start")){
                    ServerApp.pacman.startGame();
                }

                if (info_to_server.equals("moveUp")){
                    ServerApp.pacman.moveUp();
                }

                if (info_to_server.equals("moveDown")){
                    ServerApp.pacman.moveDown();
                }

                if (info_to_server.equals("moveRight")){
                    ServerApp.pacman.moveRight();
                }

                if (info_to_server.equals("moveLeft")){
                    ServerApp.pacman.moveLeft();
                }

                if (info_to_server.equals("circleUp")){
                    ServerApp.pacman.circleStructure(0x26, 0x27, 0x28, 0x25);
                }

                if (info_to_server.equals("circleDown")){
                    ServerApp.pacman.circleStructure(0x28, 0x25, 0x26, 0x27);
                }

                if (info_to_server.equals("circleRight")){
                    ServerApp.pacman.circleStructure(0x27, 0x28, 0x25, 0x26);
                }

                if (info_to_server.equals("circleLeft")){
                    ServerApp.pacman.circleStructure(0x25, 0x26, 0x27, 0x28);
                }

                if (info_to_server.equals("pause")){
                    ServerApp.pacman.pause();
                }

                if (info_to_server.equals("exit")){
                    ServerApp.pacman.escape();
                }


                String finalInfo_to_server = info_to_server;
                Platform.runLater(() -> Server.server_info.add(finalInfo_to_server));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closingThread();
    }

    private void closingThread(){
        try {
            Platform.runLater(() -> Server.client_names.remove("Klientas " + client_socket.getRemoteSocketAddress()));
            input_buffered_reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
