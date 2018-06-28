package gb.listener;

/**
  @author Hax
  @time 20:18 26.06.2018
  @project GottBotRework
  @package gb.listener
  @class ReadyListener
 **/

import com.rethinkdb.RethinkDB;
import gb.GottBot;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        for (User user : event.getJDA().getUsers()) {
            // GottCoin
            if (GottBot.getDB().get("gottcoin", "userid", user.getId(), "userid") == null) {
                GottBot.getDB().insert("gottcoin", RethinkDB.r.hashMap("userid", user.getId())
                        .with("type", "user")
                        .with("miner", RethinkDB.r.array())
                        .with("gottcoinsmined", "0")
                        .with("gottcoins", "1000000"));
            }

        }
    }
}
