package commands.usercommands;

import commands.Command;
import core.MessageHandler;
import core.MySQL;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONObject;
import stuff.SECRETS;

import static core.JSONhandler.readJsonFromUrl;

public class CommandMiner implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (args.length==2) {
                if (args[0].equalsIgnoreCase("withdraw")) {
                    JSONObject json = readJsonFromUrl("https://api.coinhive.com/user/balance?name=" + event.getAuthor().getId() + "&secret=" + SECRETS.COINHIVESECRET);
                    Long mined = json.getLong("total");
                    String withdrawn = MySQL.get("user", "id", event.getAuthor().getId(), "withdrawnhashes");
                    String hashes = MySQL.get("user", "id", event.getAuthor().getId(), "hashes");
                    if (mined-Long.parseLong(withdrawn)>0) {
                        if (mined-Long.parseLong(withdrawn)-Long.parseLong(args[1])>=0) {
                            MySQL.update("user", "hashes`,`withdrawnhashes", Long.parseLong(hashes)+Long.parseLong(args[1])+"','"+Long.parseLong(withdrawn)+Long.parseLong(args[1]),"id", event.getAuthor().getId());
                            event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.sucess", "usercommands.miner.withdrawn", args[1],"sucess", event)).queue();
                        }
                    }
                }
            } else event.getTextChannel().sendMessage(MessageHandler.getEmbed("usercommands.miner.title", "usercommands.miner.description", "", "normal", event)).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}