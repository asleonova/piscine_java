package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

@Component
public class Server {

    private final UsersService usersService;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void start(int port) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Hello from Server!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() throws IOException {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientServerInteraction() throws IOException {
        String login;
        String password;
        try {
            String msg;
            while (true) {
                msg = in.readLine();
                if (msg.equals("signUp")) {
                    while (true) {
                        out.println("Enter login: ");
                        login = in.readLine();
                        out.println("Enter password: ");
                        password = in.readLine();
                        usersService.signUp(login, password);
                        out.println("Successful!");
                        stop();
                    }
                } else {
                    out.println("enter 'signUp'");
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
