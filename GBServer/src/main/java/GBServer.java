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
    public static ArrayList<BufferedReader> inputs;
    public static ArrayList<PrintWriter> outputs;
    private static String password;
    public static ArrayList<String> Shardmanagerinforesult=new ArrayList<>();
    public GBServer() {
        new Thread(() -> {
            sockets = new ArrayList<>();
            inputs = new ArrayList<>();
            outputs = new ArrayList<>();
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
            GBServer.inputs.add(reader);
            PrintWriter print = new PrintWriter(clientSocket.getOutputStream());
            String line;
            boolean password = false;
            while (reader!=null&&(line = reader.readLine())!=null) {
                if (password) {
                    if (line.equalsIgnoreCase("Connected! I need a Shard!")) {
                        String getShard = new BotHandler().getStartShard();
                        String getMaxShard = new BotHandler().getMaxShards();
                        System.out.println(getShard+" is now Used. Max Shards "+getMaxShard);
                        print.println("Start with Shard " + getShard + " Max Shards: " + getMaxShard);
                        print.flush();
                    } else if (line.contains("Disconnected! Add Shard ")) {
                        String AddingShard = line.replace("Disconnected! Add Shard ", "");
                        System.out.println("New Availible Shard "+AddingShard);
                        clientSocket.close();
                        reader.close();
                        reader=null;
                        print.close();
                        print=null;
                        new BotHandler().addShard(AddingShard);
                    } else if (line.equals("ShardManager - Info")) {
                        PrintWriter finalPrint = print;
                        new Thread(() -> {
                            System.out.println("Shardmanager info");
                            if (!GBServer.Shardmanagerinforesult.isEmpty())
                                GBServer.Shardmanagerinforesult.clear();
                            new BotHandler().sendToAll("ShardManager - inforequest");
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String send = "";
                            System.out.println(GBServer.Shardmanagerinforesult.size());
                            for (String result : GBServer.Shardmanagerinforesult) {
                                System.out.println("jajajjaja");
                                send += result + ",";
                                System.out.println(result);
                            }
                            finalPrint.println("ShardManager - inforesult: " + send);
                            finalPrint.flush();
                        }).start();
                    } else if (line.startsWith("ShardManager - Inforesult")) {
                        String result = line.replace("ShardManager - Inforesult", "");
                        GBServer.Shardmanagerinforesult.add(result);
                        System.out.println("Receiving result from Shardmanager: "+result);
                    } else if (line.equals("Stop all!")) {
                        new BotHandler().sendToAll("Stop!");
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
    static ArrayList<String> shards = new ArrayList<>();
    static String Shards;
    public void main() {
        Shards="5";
        for (int i =0; (Integer.parseInt(Shards)-1)>i; i++) {
            shards.add(String.valueOf(i));
        }
        System.out.println("Starting with "+shards.size()+" Shards...");
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

    public void sendToAll(String message) {
        for (PrintWriter printWriter:GBServer.outputs) {
            printWriter.println(message);
            printWriter.flush();
        }
    }
}