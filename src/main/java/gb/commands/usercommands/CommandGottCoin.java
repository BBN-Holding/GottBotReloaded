package gb.commands.usercommands;

import gb.Entitites.GottCoin.Miner;
import gb.Entitites.GottCoin.User;
import gb.GottBot;
import gb.Handler.CommandHandling.Command;
import com.rethinkdb.RethinkDB;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandGottCoin implements Command {


    @Override
    public String[] Aliases() {
        return new String[]{"gottcoin", "gc"};
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length < 1) {
            event.getTextChannel().sendMessage(GottBot.getMessage().getCommandTemplate(Aliases(), "gb.gc info|buyminer|payout(|pool|miner|wallet)", "GOTTCOIN WOUHHHH", Color.CYAN)).queue();
        } else {
            User user = new User(GottBot.getDB().getByWherewithoutField("gottcoin", "userid", event.getAuthor().getId()));
            switch (args[0].toLowerCase()) {
                case "info":
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.addField("User: "+event.getAuthor().getName(),
                            "Gottcoins: "+user.getGottcoins()+
                                    "\nMinedGottCoins: "+user.getGottcoinsmined()
                            , false);
                    String[] miners = user.getMiner();
                    for (int i =0;miners.length>i; i++) {
                        embedBuilder.addField("Miner "+String.valueOf(i+1),
                                "Pool: "+GottBot.getDB().getByID("gottcoin", miners[i], "pool")+
                                        "\nChance: "+GottBot.getDB().getByID("gottcoin", miners[i], "chance")+
                                        "\nGC Mined: "+GottBot.getDB().getByID("gottcoin", miners[i], "gottcoinsmined")+
                                        "\nGC: "+GottBot.getDB().getByID("gottcoin", miners[i], "gottcoins")
                                , true);
                    }
                    embedBuilder.setTitle("GottCoin - Info");
                    event.getTextChannel().sendMessage(embedBuilder.build()).queue();
                    break;

                case "miner":
                    if (args.length<2) {
                        StringBuilder minerstring=new StringBuilder();
                        for (String miner:user.getMiner()) {
                            minerstring.append(miner);
                        }
                        event.getTextChannel().sendMessage(minerstring.toString()).queue();
                    } else {
                        System.out.println(new Miner(args[1]));
                    }
                    break;

                case "buyminer":
                        if (Long.parseLong(user.getGottcoins())>=100) {
                            String[] soso = GottBot.getDB().insert("gottcoin", RethinkDB.r.hashMap("type", "miner")
                                    .with("author", event.getAuthor().getId())
                                    .with("chance", "10")
                                    .with("pool", "none")
                            .with("gottcoinsmined", "0")
                            .with("gottcoins", "0")).split(",");
                            String string = soso[4].split("=")[1].replace("[", "").replace("]", "");
                            String value = ", "+string;
                            StringBuilder miner=new StringBuilder();
                            for (String oneMiner:user.getMiner()) {
                                miner.append(oneMiner);
                            }
                            if (miner.toString().replace("]", "").equals("[")) value = string;
                            GottBot.getDB().update("gottcoin", "userid", event.getAuthor().getId(),
                                    RethinkDB.r.hashMap("miner",
                                            GottBot.getDB().get("gottcoin", "userid", event.getAuthor().getId(), "miner").replace("]", "")
                                                    +value+"]").with("gottcoins", String.valueOf(Long.parseLong(user.getGottcoins())-100)));
                            event.getTextChannel().sendMessage("successfully buyed miner").queue();
                        } else event.getTextChannel().sendMessage("Zu wenig geld hier so").queue();
                    break;
                case "payout":
                        String[] miners1=user.getMiner();
                        for (String miner:miners1) {
                            Long mined = Long.parseLong(GottBot.getDB().getByID("gottcoin", miner, "gottcoins"));
                            GottBot.getDB().update("gottcoin", "userid", event.getAuthor().getId(),
                                    RethinkDB.r.hashMap("gottcoins",String.valueOf(
                                            Long.parseLong(
                                                    user.getGottcoins()
                                            )+mined
                                                    ))
                                            .with("gottcoinsmined", Long.parseLong(user.getGottcoinsmined())+mined));
                        }
                        event.getTextChannel().sendMessage("successfully payed out mined GottCoins").queue();
                    break;
                case "pool":
                    if (args.length>=2) {
                        switch (args[1].toLowerCase()) {
                            case "create":

                                break;
                            case "join":

                                break;
                            case "list":

                                break;
                        }
                    }
                    break;
            }
        }
    }
}
