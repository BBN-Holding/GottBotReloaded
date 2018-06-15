package GB.listener;

import GB.GottBot;
import GB.commands.usercommands.GottCoin;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.*;

public class Shardlistener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("[Start] Starting GottCoin Thread");
        // TODO: Fertig machen
        new Thread(() -> {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Random random = new Random();
                    HashMap<String, String> miner = GottBot.getDB().getMap("gottcoin", "type", "miner", "minerid", "chance");
                        int totalSum = miner.size();
                        int count = random.nextInt(totalSum) + 1;
                        String currentObject;
                        while (count > 0) {
                            for (Map.Entry<String, String> entry : miner.entrySet()) {
                                currentObject = entry.getKey();
                                count -= Integer.parseInt(entry.getValue());
                                if (count <= 0) GottBot.getDB()
                                        .update("gottcoin", "minerid", currentObject, RethinkDB.r.hashMap("gottcoins",
                                                String.valueOf(Integer.parseInt(GottBot.getDB().get("gottcoin", "minerid", currentObject, "gottcoins"))+1))
                                                .with("minedgottcoins",
                                                        String.valueOf(Integer.parseInt(GottBot.getDB().get("gottcoin", "minerid", currentObject, "minedgottcoins"))+1)));
                            }
                        }
                }
            }, 1000, 1000);
        }).start();
    }
}
