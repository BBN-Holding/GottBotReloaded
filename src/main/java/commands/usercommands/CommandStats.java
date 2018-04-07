package commands.usercommands;

import commands.Command;
import core.Main;
import core.MessageHandler;
import core.MySQL;
import core.commandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ResourceBundle;

public class CommandStats implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        int i=0;
        long User=0;
        while (event.getJDA().getGuilds().size()>i) {
            User=User+event.getJDA().getGuilds().get(i).getMembers().size();
            i++;
        }
        ResourceBundle msg = MessageHandler.get(event.getAuthor());
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Stats")
                .addField(msg.getString("usercomamnds.stats.stats1"), MySQL.get1("stats",  "1", "command"),true)
                .addField(msg.getString("usercomamnds.stats.stats2"), MySQL.get1("stats", "1", "message"),true)
                .addField(msg.getString("usercomamnds.stats.stats3"), String.valueOf(Main.shardManager.getGuilds().size()), true)
                .addField(msg.getString("usercomamnds.stats.stats4"),String.valueOf(User), true)
                .addField(msg.getString("usercomamnds.stats.stats5"), String.valueOf(event.getJDA().getRegisteredListeners().size()), true)
                .addField(msg.getString("usercomamnds.stats.stats6"), String.valueOf(commandHandler.commands.size()), true)
                .addField(msg.getString("usercomamnds.stats.stats7"), String.valueOf(MySQL.getallwithoutwhere("user", "id").size()), true)
                .addField(msg.getString("usercomamnds.stats.stats8"), String.valueOf(MySQL.getallwithoutwhere("server", "id").size()), true)
                .setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl())
                .build()
        ).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
