package commands.usercommands;

import commands.Command;
import core.Main;
import core.MessageHandler;
import core.MySQL;
import core.commandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

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
        event.getTextChannel().sendMessage(new EmbedBuilder().setTitle("Stats")
                .addField(MessageHandler.get(event.getAuthor()).getString("stats1"), MySQL.get1("stats",  "1", "command"),true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats2"), MySQL.get1("stats", "1", "message"),true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats3"), String.valueOf(Main.shardManager.getGuilds().size()), true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats4"),String.valueOf(User), true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats5"), String.valueOf(event.getJDA().getRegisteredListeners().size()), true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats6"), String.valueOf(commandHandler.commands.size()), true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats7"), String.valueOf(MySQL.getallwithoutwhere("user", "id").size()), true)
                .addField(MessageHandler.get(event.getAuthor()).getString("stats8"), String.valueOf(MySQL.getallwithoutwhere("server", "id").size()), true)
                .setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl())
                .build()
        ).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
    }
}
