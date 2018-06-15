package GB.commands.usercommands;

import GB.GottBot;
import GB.Handler.CommandHandling.Command;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.HashMap;

public class CommandGottCoin implements Command {


    @Override
    public String[] Aliases() {
        return new String[]{"gottcoin", "gc"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length < 1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.gc register|info(|pools|miner|wallet)", "GOTTCOIN WOUHHHH", Color.CYAN)).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "info":
                    if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "userid") != null) {
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        String miner = GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "miner");
                        String[] miners = miner.split(", ");
                        for (int i =0;miners.length-1>i; i++) {
                            String[] componets= GottBot.getDB().get("gottcoin", "serverid", miners[i], "components").split("::");
                            String resultcomponents="";
                            for (String component:componets) {
                                resultcomponents+=component;
                            }
                            embedBuilder.addField("Miner "+String.valueOf(i+1),
                                    "Pool: "+GottBot.getDB().get("gottcoin", "serverid", miners[i], "pool")+
                                            "Chance: "+GottBot.getDB().get("gottcoin", "serverid", miners[i], "chance")+
                                            "Components: "+resultcomponents+
                                            "Costed Money: "+GottBot.getDB().get("gottcoin", "serverid", miners[i], "costedmoney")+
                                            "GC Mined: "+GottBot.getDB().get("gottcoin", "serverid", miners[i], "gcmined")
                                    , false);
                        }

                        event.getTextChannel().sendMessage(embedBuilder.build()).queue();
                    }
                    break;

                case "register":
                    if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "userid") == null) {
                        GottBot.getDB().insert("gottcoin", RethinkDB.r.hashMap("userid", event.getAuthor().getId())
                                .with("miner", RethinkDB.r.array()).with("gottcoinsmined", "0").with("gottcoins", "0"));
                        event.getTextChannel().sendMessage("now you can mine GottCoins. Wouh").queue();
                    } else event.getTextChannel().sendMessage("You're already registered").queue();
                    break;
            }
        }
    }
}
