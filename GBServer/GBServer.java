package GBServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GBServer {
    private static ServerSocket serverSocket;
    private static ArrayList<Socket> sockets;
    private static ArrayList<BufferedReader> inputs;
    private static ArrayList<PrintWriter> outputs;
    private static String password;
    public GBServer() {
        new Thread(() -> {
            sockets = new ArrayList<Socket>();
            inputs = new ArrayList<BufferedReader>();
            outputs = new ArrayList<PrintWriter>();
            try {
                serverSocket = new ServerSocket(6775);

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Bot connected");
                    outputs.add(new PrintWriter(socket.getOutputStream()));
                    new Thread(new ClientHandler(socket, password)).start();
                    sockets.add(socket);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            new BotHandler().main();
            new GBServer();
            System.out.println("Reading Password");
            FileReader fr = new FileReader("password.txt");
            BufferedReader br = new BufferedReader(fr);

            password = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private String passwordd;

    ClientHandler(Socket socket, String Password) {
        clientSocket = socket;
        passwordd=Password;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter print = new PrintWriter(clientSocket.getOutputStream());
            String line;
            boolean password = false;
            while ((line = reader.readLine())!=null) {
                if (password) {
                    if (line.equalsIgnoreCase("Connected! I need a Shard!")) {
                        print.println("Start with Shard " + new BotHandler().getStartShard() + " Max Shards: " + new BotHandler().getMaxShards());
                        print.flush();
                    } else if (line.contains("Disconnected! Add Shard ")) {
                        String AddingShard = line.replace("Disconnected! Add Shard ", "");
                        new BotHandler().addShard(AddingShard);
                    }
                } else {
                    if (line.equals("Password: "+passwordd)) password = true;
                    else {print.println("Password false!");print.flush();}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class BotHandler {
    static ArrayList<String> shards = new ArrayList<String>();
    static String Shards;
    public void main() {
        Shards="5";
        for (int i =0; (Integer.parseInt(Shards)-1)>i; i++) {
            shards.add(String.valueOf(i));
        }
        System.out.println("Starting with "+Shards+" Shards...");
    }

    public String getStartShard() {
        String result = shards.get(0);
        shards.remove(0);
        return result;
    }

    public void addShard(String Shard) {
        shards.add(Shard);
    }

    public String getMaxShards() {

        return String.valueOf(Integer.parseInt(Shards)-1);
    }

}