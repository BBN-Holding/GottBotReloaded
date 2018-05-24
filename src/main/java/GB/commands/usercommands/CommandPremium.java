package GB.commands.usercommands;

import GB.Handler;
import GB.core.Main;
import GB.commands.Command;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import GB.stuff.DATA;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommandPremium implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length!=1) {
            String status;
            if (new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "premium").equals("none")) status= new Handler().getMessageHandler().get("util.none",event.getAuthor(), event.getGuild());
            else {
                Date date = new Date();
                date.setTime(Long.parseLong(new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "premium")));
                status= "until "+date.toGMTString();
            }
            event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("usercommands.premium.status.title", "usercomamnds.premium.status.text", status,"normal", event)).queue();
        } else if (args[0].equalsIgnoreCase("buy")) {
            Guild bbn = Main.shardManager.getGuildById(DATA.BBNS);
            if (event.getGuild().getId().equals(bbn.getId())) {
                if (new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "premium").equalsIgnoreCase("none")) {
                    long Date = TimeUnit.MILLISECONDS.toDays(new Date().getTime());
                    Date = Date + 31;
                    Date = TimeUnit.DAYS.toMillis(Date);
                    if (Long.parseLong(new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "hashes")) >= 750000) {
                        new Handler().getMySQL().update("user", "premium", String.valueOf(Date), "id", event.getAuthor().getId());
                        new Handler().getMySQL().update("user", "hashes", String.valueOf(Long.parseLong(new Handler().getMySQL().get("user", "id", event.getAuthor().getId(), "hashes")) - 750000), "id", event.getAuthor().getId());
                        bbn.getController().addSingleRoleToMember(bbn.getMember(event.getAuthor()), bbn.getRoleById(408660274103451649L)).queue();
                        event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.sucess", "usercomamnds.premium.buyed", "", "sucess", event)).queue();
                    } else
                        event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.mine", "https://miner.bigbotnetwork.de/", "error", event)).queue();
                } else
                    event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "usercommands.premium.alreadyhas", "", "error", event)).queue();
            } else event.getTextChannel().sendMessage(new Handler().getMessageHandler().getEmbed("util.error", "util.bbnguild", "https://disco.gg/bbn", "error", event)).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
