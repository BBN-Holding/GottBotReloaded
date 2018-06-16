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
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.gc register|info|buyminer|payout(|pools|miner|wallet)", "GOTTCOIN WOUHHHH", Color.CYAN)).queue();
        } else {
            switch (args[0].toLowerCase()) {
                case "info":
                    if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "userid") != null) {
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        String miner = GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "miner").replace("[", "").replace("]", "");
                        String[] miners;
                        miners = miner.split(", ");
                        if (miners.length==0) {
                            String string= miner.replace("[", "").replace("]", "");
                            miners[0] = string;
                        }
                        for (int i =0;miners.length>i; i++) {
                            embedBuilder.addField("Miner "+String.valueOf(i+1),
                                    "Pool: "+GottBot.getDB().getByID("gottcoin", miners[i], "pool")+
                                            "\nChance: "+GottBot.getDB().getByID("gottcoin", miners[i], "chance")+
                                            "\nGC Mined: "+GottBot.getDB().getByID("gottcoin", miners[i], "gottcoinsmined")+
                                            "\nGC: "+GottBot.getDB().getByID("gottcoin", miners[i], "gottcoins")
                                    , false);
                        }
                        embedBuilder.setTitle("GottCoin - Info");
                        event.getTextChannel().sendMessage(embedBuilder.build()).queue();
                    } else event.getTextChannel().sendMessage("NOT REGISTERED").queue();
                    break;

                case "register":
                    if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "userid") == null) {
                        GottBot.getDB().insert("gottcoin", RethinkDB.r.hashMap("userid", event.getAuthor().getId())
                                .with("type", "user")
                                .with("miner", RethinkDB.r.array()).with("gottcoinsmined", "0")
                                .with("gottcoins", "1000000"));
                        event.getTextChannel().sendMessage("now you can mine GottCoins. Wouh").queue();
                    } else event.getTextChannel().sendMessage("You're already registered").queue();
                    break;
                case "buyminer":
                    if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "userid") != null) {
                        if (Integer.parseInt(GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "gottcoins"))>=100) {
                            String[] soso = GottBot.getDB().insert("gottcoin", RethinkDB.r.hashMap("type", "miner")
                                    .with("author", event.getAuthor().getId())
                                    .with("chance", "10")
                                    .with("pool", "none")
                            .with("gottcoinsmined", "0")
                            .with("gottcoins", "0")).split(",");
                            String string = soso[4].split("=")[1].replace("[", "").replace("]", "");
                            String value = ", "+string;
                            if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "miner").replace("]", "").equals("[")) value = string;
                            GottBot.getDB().update("gottcoin", "userid", event.getAuthor().getId(),
                                    RethinkDB.r.hashMap("miner",
                                            GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "miner").replace("]", "")
                                                    +value+"]").with("gottcoins", String.valueOf(Integer.parseInt(GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "gottcoins"))-100)));
                            event.getTextChannel().sendMessage("successfully buyed miner").queue();
                        } else event.getTextChannel().sendMessage("Zu wenig geld hier so").queue();
                    } else event.getTextChannel().sendMessage("nicht registriert hier so").queue();
                    break;
                case "payout":
                    if (GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "userid") != null) {
                        String[] miners = GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "miner").replace("[", "").replace("]", "").split(", ");
                        for (String miner:miners) {
                            System.out.println(miners.length);
                            System.out.println(miner);
                            GottBot.getDB().update("gottcoin", "userid", event.getAuthor().getId(),
                                    RethinkDB.r.hashMap("gottcoins",String.valueOf(
                                            Integer.parseInt(
                                                    GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "gottcoins")
                                            )+
                                                    GottBot.getDB().getByID("gottcoin", miner, "gottcoins"))));
                        }
                        event.getTextChannel().sendMessage("successfully payed out mined GottCoins").queue();
                    } else event.getTextChannel().sendMessage("nicht registriert ey").queue();
                    break;
            }
        }
    }
}
