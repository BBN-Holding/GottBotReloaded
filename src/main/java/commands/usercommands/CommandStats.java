package commands.usercommands;

import commands.Command;
import core.Main;
import core.MessageHandler;
import core.MySQL;
import core.commandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
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
        net.dv8tion.jda.core.entities.User user = event.getAuthor();
        Guild guild = event.getGuild();
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Stats")
                .addField(MessageHandler.get("usercommands.stats.stats1",user,guild), MySQL.get1("stats",  "1", "command"),true)
                .addField(MessageHandler.get("usercommands.stats.stats2",user,guild), MySQL.get1("stats", "1", "message"),true)
                .addField(MessageHandler.get("usercommands.stats.stats3",user,guild), String.valueOf(Main.shardManager.getGuilds().size()), true)
                .addField(MessageHandler.get("usercommands.stats.stats4",user,guild),String.valueOf(User), true)
                .addField(MessageHandler.get("usercommands.stats.stats5",user,guild), String.valueOf(event.getJDA().getRegisteredListeners().size()), true)
                .addField(MessageHandler.get("usercommands.stats.stats6",user,guild), String.valueOf(commandHandler.commands.size()), true)
                .addField(MessageHandler.get("usercommands.stats.stats7",user,guild), String.valueOf(MySQL.getallwithoutwhere("user", "id").size()), true)
                .addField(MessageHandler.get("usercommands.stats.stats8",user,guild), String.valueOf(MySQL.getallwithoutwhere("server", "id").size()), true)
                .setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl())
                .build()
        ).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
