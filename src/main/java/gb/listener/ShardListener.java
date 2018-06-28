package gb.listener;

import gb.GottBot;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.*;

public class ShardListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("[Start] Starting GottCoin Thread");
        new Thread(() -> {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Random random = new Random();
                        ArrayList<String> string1 = GottBot.getDB().getAllWhere("gottcoin", "type", "miner", "id");
                        ArrayList<String> officialminer = new ArrayList<>();
                        for (String id : string1) {
                            String chance = GottBot.getDB().getByID("gottcoin", id, "chance");
                            for (int i = 0; Integer.parseInt(chance) > i; i++) {
                                officialminer.add(id);
                            }
                        }
                        if (officialminer.size()!=0) {
                            int randomnumber = random.nextInt(officialminer.size());
                            String minerid = officialminer.get(randomnumber);
                            String minedgottcoins = GottBot.getDB().getByID("gottcoin", minerid, "gottcoinsmined");
                            GottBot.getDB()
                                    .update("gottcoin", "id", minerid, RethinkDB.r.hashMap("gottcoinsmined",
                                            String.valueOf(
                                                    Integer.parseInt(minedgottcoins) + 1))
                                    .with("gottcoins",
                                            String.valueOf(
                                                    Integer.parseInt(GottBot.getDB().get("gottcoin", "id", minerid, "gottcoins")) + 1)));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1000, 1000);
        }).start();
    }
}
