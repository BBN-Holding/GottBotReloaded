package GB.commands.usercommands;

import GB.Handler;
import GB.core.MessageHandler;
import GB.stuff.SECRETS;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONObject;

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
                    String withdrawn = new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "withdrawnhashes");
                    String hashes = new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "hashes");
                    if (mined-Long.parseLong(withdrawn)>0) {
                        if (mined-Long.parseLong(withdrawn)-Long.parseLong(args[1])>=0) {
                            new Handler().getMySQL().update("user", "hashes", String.valueOf(Long.parseLong(hashes)+Long.parseLong(args[1])), "id", event.getAuthor().getId());
                            new Handler().getMySQL().update("user", "withdrawnhashes", String.valueOf(Long.parseLong(withdrawn)+Long.parseLong(args[1])), "id", event.getAuthor().getId());
                            event.getTextChannel().sendMessage(MessageHandler.getEmbed("util.sucess", "usercommands.miner.withdrawn", args[1],"sucess", event)).queue();
                        }
                    }
                }
            } else event.getTextChannel().sendMessage(MessageHandler.getEmbed("usercommands.miner.title", "usercomamnds.miner.description", "", "normal", event)).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
